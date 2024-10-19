package model;

// 翻訳データを表現するクラス
public class Translation {
    // 原文
    private String originalText;
    // 翻訳結果
    private String translatedText;

    // コンストラクタ
    public Translation(String originalText, String translatedText) {
        this.originalText = originalText;
        this.translatedText = translatedText;
    }

    // 原文を取得するメソッド
    public String getOriginalText() {
        return originalText;
    }

    // 翻訳結果を取得するメソッド
    public String getTranslatedText() {
        return translatedText;
    }
}
