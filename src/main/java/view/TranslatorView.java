package view;

import java.util.Scanner;

// ユーザとのやり取りを管理するクラス
public class TranslatorView {
    // 入力用スキャナーの準備
    private Scanner scanner = new Scanner(System.in);

    // ユーザからの入力を取得するメソッド
    public String getInput() {
        System.out.println("翻訳したいテキスト(英語または日本語)を入力してください");
        return scanner.nextLine();
    }

    // 翻訳結果を表示するメソッド
    public void displayTranslation(String translatedText) {
        System.out.println("翻訳結果:　" + translatedText);
    }

    // エラーメッセージを表示
    public void displayError(String errorMessage) {
        System.out.println("エラーメッセージ: " + errorMessage);
    }

}
