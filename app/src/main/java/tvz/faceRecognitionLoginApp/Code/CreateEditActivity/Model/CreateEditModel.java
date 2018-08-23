package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model;

import android.app.Activity;
import android.graphics.Bitmap;

import java.io.IOException;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.CreateEditHelper;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;

public interface CreateEditModel {

    interface CreateEditModelChecker {
        void emptyValue(String message, String identifier);
        void userEmpty(String message, Activity a);
    }

    boolean saveUser (Activity a, String username, String passwd, String determinator,
                      CreateEditModelChecker cEMChecker) throws IOException;

    String detectFace (Activity a, Bitmap image);

}
