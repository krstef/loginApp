package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;

public class CreateEditModelImpl extends UserInformationHelper implements CreateEditModel {

    private static final String userNameIsEmpty = "Username cannot be empty";
    private static final String userNameIsEmptyID = "username";
    private static final String passwdIsEmpty = "Password cannot be empty";
    private static final String passwdIsEmptyID = "password";

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
     * @param username
     * @param passwd
     * @param determinator
     * @param cEMChecker
     * @return
     */
    @Override
    public boolean saveUser (final String username, final String passwd, final String determinator,
                             final CreateEditModelChecker cEMChecker) {
        boolean flag = true;

        if(username.isEmpty()) {
            cEMChecker.emptyValue(userNameIsEmpty, userNameIsEmptyID);
            flag = false;
        } else if (passwd.isEmpty()) {
            cEMChecker.emptyValue(passwdIsEmpty, passwdIsEmptyID);
            flag = false;
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
        return flag;
    }

}
