package tvz.faceRecognitionLoginApp.Code.HomeActivity.Presenter;

import android.app.Activity;

import java.io.IOException;

public interface HomePresenter {

    void onDestroy();
    void checkAppInitialStart(Activity a) throws IOException;

    void createNewUserCheck(Activity a);
    void editUserCheck(Activity a);

    void homeMenuInfo(String title);
    void homeMenuWeb(String title);

}
