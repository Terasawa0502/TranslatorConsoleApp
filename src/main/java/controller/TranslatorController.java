package controller;

import model.Translation;
import service.TranslationService;
import view.TranslatorView;

// アプリケーション全体の制御を行うクラス
public class TranslatorController {

    // 翻訳サービスのインスタンス
    private TranslationService translationService;
    // 翻訳ビューのインスタンス
    private TranslatorView translatorView;

    // コンストラクタ
    public TranslatorController(TranslationService translationService, TranslatorView translatorView) {
        this.translationService = translationService;
        this.translatorView = translatorView;
    }

    // アプリケーションの実行メソッド
    public void run() {
        try {
            // ユーザからの入力を取得
            String text = translatorView.getInput();
            // 翻訳処理を実行
            Translation translation = translationService.translate(text);
            // 翻訳結果を表示
            translatorView.displayTranslation(translation.getTranslatedText());

        } catch (Exception e) {
            // エラー発生時のメッセージを表示
            translatorView.displayError("翻訳に失敗しました " + e.getMessage());
        }
    }
}
