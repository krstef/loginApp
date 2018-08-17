package tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor;

import android.util.Log;

import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model.CreateEditModelImpl;
import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View.CreateEditActivity;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.CreateEditHelper;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;

public class HomeModelImpl implements HomeModel {

    private static final String userCreatedWarning = "User already created\n" +
            "You cannot access this functionality\n" +
            "Instead, use 'Edit user'";

    private static final String userNotCreatedWarning = "User is not created\n" +
            "You cannot access this functionality\n" +
            "Instead, use 'Create new user'";

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

        return new CreateEditHelper(created, msg);
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
