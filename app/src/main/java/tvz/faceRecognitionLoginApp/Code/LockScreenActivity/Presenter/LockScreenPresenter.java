package tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Presenter;

import android.app.Activity;

import java.io.IOException;

public interface LockScreenPresenter {

    void onDestroy();

    void initDataPresenter(Activity a) throws IOException;
}
