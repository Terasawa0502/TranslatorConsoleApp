import controller.TranslatorController;
import service.TranslationService;
import view.TranslatorView;

// アプリケーションのエントリーポイントとなるMainクラス
public class Main {
    public static void main(String[] args) {
        // 各クラスのインスタンスを生成
        TranslationService translationService = new TranslationService();
        TranslatorView translatorView = new TranslatorView();
        TranslatorController translatorController = new TranslatorController(translationService, translatorView);

        // アプリケーションを実行
        translatorController.run();

    }
}