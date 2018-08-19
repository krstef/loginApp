package tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor;

import android.app.Activity;

import java.io.IOException;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.CreateEditHelper;

public interface HomeModel {

    CreateEditHelper checkCreateOrEdit (String flag);

    void checkInitialStartModel(Activity a) throws IOException;

}
