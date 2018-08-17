package tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor;

public class HomeMenuModelImpl implements HomeMenuModel {

private static final String url = "https://www.google.com";

    @Override
    public void menuItemClicked(final String title, final MenuItemHandler handler) {

        switch(title) {
            case "Info":
                handler.callInfo("");
                break;
            case "Google":
                handler.callWeb(url);
            default:
                break;
        }
    }
}
