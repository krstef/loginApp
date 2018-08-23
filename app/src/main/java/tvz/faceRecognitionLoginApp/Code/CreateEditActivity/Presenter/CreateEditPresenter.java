package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import java.io.IOException;

public interface CreateEditPresenter {

    void onDestroy();

    void savePresenter(Activity a, String username, String password,String determinator) throws IOException;

    void quitPresenter(Activity a, String username, String password);

    void backPresenter(Activity a);

    void declareCreateOrEditPresenter(Activity a, String determinator);

    void detectFace(Activity a, Bitmap image);

}
