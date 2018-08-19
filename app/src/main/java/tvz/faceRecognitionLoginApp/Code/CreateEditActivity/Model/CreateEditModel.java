package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model;

import android.app.Activity;

import java.io.IOException;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;

public interface CreateEditModel {

    interface CreateEditModelChecker {
        void emptyValue(String message, String identifier);
    }

    boolean saveUser (Activity a, String username, String passwd, String determinator,
                      CreateEditModelChecker cEMChecker) throws IOException;

}
