package tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor;

public interface HomeMenuModel {

    public interface MenuItemHandler {
        void callInfo(String txt);
        void callWeb(String url);
    }

    void menuItemClicked(String title, MenuItemHandler handler);
}
