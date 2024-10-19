package service;

import model.Translation;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// 翻訳サービスを実装するクラス　外部APIを利用してテキストを翻訳する
public class TranslationService {

    // LibreTranslateサービスのエンドポイント(ローカルホスト)
    private static String ENDPOINT = "http://localhost:5001/translate";

    // 翻訳を実施するメソッド
    public Translation translate (String text) throws Exception {
        // テキストに日本語が含まれているかを判定(ひらがな、カタカナ、漢字)
        boolean containsJapanese = text.matches(".*[\\p{IsHan}\\p{IsHiragana}\\p{IsKatakana}].*");
        // 翻訳後の言語を設定
        String targetLang = containsJapanese ? "en" : "ja";
        // 翻訳前の言語を設定
        String sourceLang = targetLang.equals("ja") ? "en" : "ja";

        // リクエストボディをJSON形式で作成
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("q", text); // 翻訳するテキスト
        jsonObject.put("source", sourceLang); // 翻訳前の言語
        jsonObject.put("target", targetLang); // 翻訳後の言語
        jsonObject.put("format", "text"); // テキスト形式
        jsonObject.put("alternatives", 3); // 代替翻訳の数
        jsonObject.put("api_key", ""); // APIキー（必要に応じて）

        // Httpクライアントを作成し、リクエストを送信
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT)) // APIエンドポイント
                .header("Content-Type", "application/json") // リクエストヘッダーにContent-typeを設定
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString())) // POSTメソッドでJSONボディを送信
                .build();

        // レスポンスを受け取り
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // ステータスコードが200なら成功
        if (httpResponse.statusCode() == 200) {
            JSONObject responseBody = new JSONObject(httpResponse.body());
            String translatedText = responseBody.getString("translatedText");
            // 翻訳結果をTranslationオブジェクトとして返す
            return new Translation(text, translatedText);
        } else {
            throw new RuntimeException("Translation failed with status code: " + httpResponse.statusCode());
        }
    }
}
