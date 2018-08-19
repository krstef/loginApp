package tvz.faceRecognitionLoginApp.Code.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface DataSerializable {

    void writeObjectToFile(UserInformationHelper userHelper, Activity a);

    UserInformationHelper readDataFromFile(Activity a) throws IOException;

}
