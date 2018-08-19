package tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Model;

import android.app.Activity;

import java.io.IOException;

public interface LockScreenModel {

    interface LockScreenModelCheck {
        void userCreated(boolean flag, String string);
    }
    void initDataModel (Activity a, LockScreenModelCheck check) throws IOException;
}
