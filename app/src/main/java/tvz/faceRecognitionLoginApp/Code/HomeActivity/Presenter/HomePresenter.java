package tvz.faceRecognitionLoginApp.Code.HomeActivity.Presenter;

import android.app.Activity;

public interface HomePresenter {

    void onDestroy();
    void startLockScreen(Activity a);

    void createNewUserCheck(Activity a);
    void editUserCheck(Activity a);

    void homeMenuInfo(String title);
    void homeMenuWeb(String title);

}
