package tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Model;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import java.io.IOException;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.FaceDetectionHelper;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;

public class LockScreenModelImpl extends UserInformationHelper implements LockScreenModel {

    public static final String create = "Create";
    private static final String loginFace = "login";

    /**
     * Just to update LockScreen service when main activity is not running
     * @param a
     * @throws IOException
     */
    @Override
    public void initDataModel (Activity a, final LockScreenModelCheck check) throws IOException {

        UserInformationHelper userHelper = UserInformationHelper.getSuperHelper();

        boolean flag = false;

        if(userHelper == null) {
            userHelper = readDataFromFile(a);
            if(userHelper == null) {
                flag = false;
            } else {
                flag = true;
            }
        }

        check.userCreated(flag, create);
        saveObject(userHelper);
    }

    @Override
    public boolean loginWithFaceModel(Activity a, Bitmap image) {
        boolean flag = false;
        FaceDetectionHelper faceDetection = new FaceDetectionHelper();

        if (faceDetection.faceDetection(a, image, loginFace)) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}
