package tvz.faceRecognitionLoginApp.Code.HelperClasses;

import android.util.Log;


/**
 * This class is used in whole app as helper
 *
 * Object of this class contains all relevant informations about the app user
 *
 * Use getSuperHelper() function to access UserInformationHelper object and its properties
 * from anywhere in the app
 *
 * UserInformationHelper object properties could be changed only in CreateEditModelImpl!!!
 */

/*
TODO napravi serijalizaciju i deserijalizaciju u txt file pa pomoću toga provjeravaj usera
To će omogućiti da na lock screenu pozovem potpuni destroy app čim se uspješno ulogiram
 */

public class UserInformationHelper {

    private String username;
    private String password;
    private boolean userCreated;

    public UserInformationHelper (String username, String password, boolean userCreated) {
        super();
        this.username = username;
        this.password = password;
        this.userCreated = userCreated;
    }

    public UserInformationHelper(){
    }

    private static UserInformationHelper superHelper = null;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean getUserCreated() {
        return userCreated;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }

    public void saveObject(UserInformationHelper helper) {

        superHelper = helper;

        Log.i("HELPER", superHelper.getUsername());
    }

    public static UserInformationHelper getSuperHelper() {
        return superHelper;
    }
}
