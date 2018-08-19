package tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Presenter;

import android.app.Activity;

import java.io.IOException;

import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View.CreateEditActivity;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Model.LockScreenModel;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Model.LockScreenModelImpl;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View.LockScreenView;


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
}
