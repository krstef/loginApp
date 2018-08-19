package tvz.faceRecognitionLoginApp.Code.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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


public class UserInformationHelper extends Activity {

    private String username;
    private String password;
    private boolean userCreated;

    DataSerializable dataSerializable;

    public UserInformationHelper() {
        this.dataSerializable = new DataSerializableImpl();
    }


    public UserInformationHelper (String username, String password, boolean userCreated) {
        super();
        this.username = username;
        this.password = password;
        this.userCreated = userCreated;
    }

    private static UserInformationHelper superHelper = null;

    //private static UserInformationHelper readHelper = null;

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

    public static UserInformationHelper getSuperHelper() {
        return superHelper;
    }

    public void saveObject(UserInformationHelper helper) throws IOException {
        superHelper = helper;
    }

    public void writeObjectToFile(UserInformationHelper helper, Activity a) {
        dataSerializable.writeObjectToFile(helper, a);
    }

    public UserInformationHelper readDataFromFile(Activity a) throws IOException {
        return dataSerializable.readDataFromFile(a);
    }


}
