package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.CreateEditHelper;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.DataSerializable;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.DataSerializableImpl;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.FaceDetectionHelper;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;

public class CreateEditModelImpl extends UserInformationHelper implements CreateEditModel {

    private static final String userNameIsEmpty = "Username cannot be empty";
    private static final String userNameIsEmptyID = "username";
    private static final String passwdIsEmpty = "Password cannot be empty";
    private static final String passwdIsEmptyID = "password";
    private static final String imgNotSaved = "Image is not saved successfully\n" +
            "Please try again";
    private static final String imgSaved = "Image saved successfully";
    private static final String saveImage = "save";
    private static final String userImageIsNotSaved = "You should also take a\n" +
            "photo for face recognition";

    boolean isImageAvailable = false;

    UserInformationHelper userHelper = UserInformationHelper.getSuperHelper();

    /**
     * Called from presenter to save user (called with username, pass, subinterface and
     * determinator which provides information which action user wants to do - create or edit
     *
     * for edit action check if User Info object exits and set new values
     * for create action also check if user info object exits and if not, create new object
     * with received params
     *
     * saveObject() saves edited or newly created object to User Info
     *
     * writeObjectToFile() saves newly created object to file so the resources will be always
     * available - needed for service(when app is not running, lock screen service will pull
     * data from resource)
     * @param username
     * @param passwd
     * @param determinator
     * @param cEMChecker
     * @return
     */
    @Override
    public boolean saveUser (Activity a, final String username, final String passwd, final String determinator,
                             final CreateEditModelChecker cEMChecker) throws IOException {
        boolean flag = true;
        if(username.isEmpty()) {
            cEMChecker.emptyValue(userNameIsEmpty, userNameIsEmptyID);
            return false;
        } else if (passwd.isEmpty()) {
            cEMChecker.emptyValue(passwdIsEmpty, passwdIsEmptyID);
            return false;
        } else if (!isImageAvailable) {
            Log.i("jel usao!", String.valueOf(isImageAvailable));
            cEMChecker.userEmpty(userImageIsNotSaved, a);
            return false;
        } else {
            flag = true;
        }

        /*
        If we have edit action, we have to be sure that we have object created
        (first check has been made in HomePresenterImpl - edit or create will be called
        only when user clicks on them)
        Here we have some another checks
         */
        switch (determinator) {
            case "Edit":
                if(userHelper != null) {
                    userHelper.setUsername(username);
                    userHelper.setPassword(passwd);
                    userHelper.setUserCreated(true);
                    flag = true;
                    break;
                } else {
                    Log.i("CreateEditModel", "UserInfo object exits - Edit OK");
                    flag = false;
                    break;
                }
            case "Create":
                if(userHelper != null) {
                    Log.i("CreateEditModel", "UserInfo object exits - Create NOT OK");
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            default:
                userHelper = new UserInformationHelper(username, passwd, flag);
                break;
        }

        saveObject(userHelper);

        writeObjectToFile(userHelper, a);

        return flag;
    }

    public String detectFace (Activity a, final Bitmap image) {
        //TODO updateaj userinfo objekt o tome da je slika spremljena
        String message = "";
        boolean flag = false;

        FaceDetectionHelper faceDetection = new FaceDetectionHelper();

        if (faceDetection.faceDetection(a, image, saveImage)) {
            message = imgSaved;
            flag = true;
        } else {
            message = imgNotSaved;
            flag = false;
        }

        isImageAvailable = flag;
        return message;
    }
}
