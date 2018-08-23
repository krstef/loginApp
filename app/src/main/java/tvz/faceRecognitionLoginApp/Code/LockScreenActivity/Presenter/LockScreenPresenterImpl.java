package tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.IOException;

import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View.CreateEditActivity;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Model.LockScreenModel;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Model.LockScreenModelImpl;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View.LockScreenView;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View.RecognitionSucceedDialog;


public class LockScreenPresenterImpl implements LockScreenPresenter, LockScreenModel.LockScreenModelCheck {

    LockScreenView lockscreenView;
    LockScreenModel lockScreenModel;

    public LockScreenPresenterImpl(LockScreenView view) {
        this.lockscreenView = view;
        this.lockScreenModel = new LockScreenModelImpl();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void initDataPresenter(Activity a) throws IOException {

        try {
            lockScreenModel.initDataModel(a, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userCreated(boolean userCreated, String determinator) {
        if(!userCreated && !determinator.isEmpty()) {
            lockscreenView.callCreateEditUser(CreateEditActivity.class, determinator);
        }
    }

    @Override
    public void loginWithFace(final Activity a, Bitmap image) {
        UserInformationHelper userHelper = UserInformationHelper.getSuperHelper();
        new Thread(new Runnable() {
            public void run() {
                // a potentially  time consuming task
                try {
                    Thread.sleep(2100); //LENGTH_SHORT = 2s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }).start();

        if(lockScreenModel.loginWithFaceModel(a, image)) {
            Toast.makeText(a.getApplicationContext(), "Welcome " + userHelper.getUsername(),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(a.getApplicationContext(), "Face not recognized",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
