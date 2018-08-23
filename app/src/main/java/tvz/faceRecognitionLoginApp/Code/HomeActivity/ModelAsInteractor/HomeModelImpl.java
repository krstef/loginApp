package tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor;

import android.app.Activity;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model.CreateEditModelImpl;
import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View.CreateEditActivity;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.CreateEditHelper;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;

public class HomeModelImpl extends UserInformationHelper implements HomeModel {

    private static final String userCreatedWarning = "User already created\n" +
            "You cannot access this functionality\n" +
            "Instead, use 'Edit user'";

    private static final String userNotCreatedWarning = "User is not created\n" +
            "You cannot access this functionality\n" +
            "Instead, use 'Create new user'";
    private static final String userDataFile = "user_data.dat";


    /**
     * Called from onCreate() to check if we have data already created
     *
     * If we catch exception, it means that we have initial start of the app and we should not
     * make any actions
     * If we do not catch exception, we should check if UserInformationHelper is filled out
     * with appropriate data
     *
     * If object is null, then call DataSerializable interface to fill the object
     *
     * Ensures that we always have updated object
     * @param a
     */
    @Override
    public void checkInitialStartModel(Activity a) throws IOException {
        UserInformationHelper helper = UserInformationHelper.getSuperHelper();
        FileInputStream in = null;

        try {
            in = a.openFileInput(userDataFile);
        } catch (FileNotFoundException e) {
            Log.d("INITIAL", "Initial check, exception thrown");
        }

        if(in == null) {
            Log.i("null", "jesam li ušao u null");
            helper = null;
        } else {
            if (helper == null) {
                helper = readDataFromFile(a);
            } else {
                helper = null;
            }
        }

        saveObject(helper);
    }

    /**
     * Called from presenter with flag which indicates user action (create or edit)
     * Check if user has been created and refuse or accept action with appropriate message
     * @param flag
     * @return
     */
    @Override
    public CreateEditHelper checkCreateOrEdit (String flag) {

        UserInformationHelper userHelper = UserInformationHelper.getSuperHelper();

        if(userHelper == null) {
            Log.i("MODEL - home", "Ne postoji objekt");
        } else {
            Log.i("MODEL - home", userHelper.getUsername());
        }

        String msg;
        boolean created = false;

        switch (flag) {
            case "Create":
                //if(userHelper.getUserCreated()) {
                if(userHelper != null) {
                    created = true;
                    msg = userCreatedWarning;
                } else {
                    created = false;
                    msg = "";
                }
                break;
            case "Edit":
                //if(userHelper.getUserCreated()) {
                if(userHelper == null) {
                    created = true;
                    msg = userNotCreatedWarning;
                } else {
                    created = false;
                    msg = "";
                }
                break;
            default:
                created = false;
                msg = "App has some error, sorry";
                break;
        }

        return new CreateEditHelper(created, msg, null);
    }

    public boolean checkIfUserCreated() {
        UserInformationHelper userHelper = UserInformationHelper.getSuperHelper();

        if (userHelper == null) {
            return false;
        } else {
            return true;
        }
    }
}
