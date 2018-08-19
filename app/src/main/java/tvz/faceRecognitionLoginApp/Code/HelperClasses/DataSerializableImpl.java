package tvz.faceRecognitionLoginApp.Code.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSerializableImpl implements DataSerializable, Serializable{

    private static final String directory = "LoginApp";
    private static final String userDataFile = "user_data.dat";

    /**
     * Serialisation method
     * Serialise object(created, edited) to data;
     * @param userHelper
     * @param a
     */
    @Override
    public void writeObjectToFile(final UserInformationHelper userHelper, final Activity a) {

        //File fileX = new File(a.getFilesDir(), directory);
        //String filename = userData;
        FileOutputStream outputStream;
        String stringForSend;

        if(userHelper == null) {
            Log.i("Serializable", "NULL objekt");
        } else {
            Log.i("Serializable", "password = "+ userHelper.getPassword());
        }

        stringForSend = userHelper.getUsername() + "-.-" + userHelper.getPassword();

        try {
            Log.i("ON CREATE - CE", "in try");
            outputStream = a.openFileOutput(userDataFile, Context.MODE_PRIVATE);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(stringForSend);
            outputStreamWriter.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File f = a.getFileStreamPath(userDataFile);
        Log.i("WRITEtoFILE", "velicina dat = " + f.length());
    }

    /**
     * Deserialisation method
     * DeSerialise data from file to object
     * @param a
     * @return new UserInformationHelper object
     * @throws IOException
     */
    @Override
    public UserInformationHelper readDataFromFile(Activity a) throws IOException {

        String username = "";
        String password = "";
        int stringPartNumber = 2;
        String line = "";
        String bilder = "";
        UserInformationHelper userHelper = UserInformationHelper.getSuperHelper();
        UserInformationHelper newUserHelper = null;

        try {
            File file = new File(a.getFilesDir(), userDataFile);
            Log.i("READfromFILE", "velicina dat F-getdir = " + file.length());

            FileInputStream inputStream = a.openFileInput(userDataFile);

            InputStreamReader inputReader = new InputStreamReader(inputStream);

            BufferedReader reader = new BufferedReader(inputReader);

            StringBuffer buffer = new StringBuffer();

            //while we do not pass empty string
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            inputReader.close();
            bilder = buffer.toString();

            String[] userPass = bilder.split("-.-");
            username = userPass[0];
            password = userPass[1];

            Log.i("Size of bilder", "length = "+ bilder.length());
            Log.i("Size of bilder", "bilder = "+ bilder);
            Log.i("results", "username = "+ username);
            Log.i("results", "password = "+ password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(userHelper == null) {
            if(!username.isEmpty() && !password.isEmpty()) {
                Log.i("Serializable", "not empty, new object created");
                newUserHelper =  new UserInformationHelper(username, password, true);
            } else {
                newUserHelper = userHelper;
            }
        }

        return newUserHelper;
    }

}
