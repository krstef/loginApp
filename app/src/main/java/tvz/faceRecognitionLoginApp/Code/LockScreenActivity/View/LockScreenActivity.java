package tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Presenter.LockScreenPresenter;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.Presenter.LockScreenPresenterImpl;
import tvz.faceRecognitionLoginApp.R;

//TODO poslozi cijeli dir po MVP arhitekturi
public class LockScreenActivity extends Activity implements LockScreenView {

    private String flag;
    private LockScreenPresenter lockScreenPresenter;
    public static final Integer CAMERA_RETURN_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set up our Lockscreen
            makeFullScreen();
            //startService(new Intent(this,LockScreenService.class));

            setContentView(R.layout.activity_lock_screen);

            lockScreenPresenter = new LockScreenPresenterImpl(this);

        try {
            lockScreenPresenter.initDataPresenter(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A simple method that sets the screen to fullscreen.  It removes the Notifications bar,
     *   the Actionbar and the virtual keys (if they are on the phone)
     */
    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

    @Override
    public void unlockScreen(View view) {
        //Instead of using finish(), this totally destroys the process
        //android.os.Process.killProcess(android.os.Process.myPid());
        //finish
        LockScreenDialog dialog = new LockScreenDialog();
        dialog.show(getFragmentManager(), "Login");
    }

    public void unlockWithFace(View v) {
        Intent cameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_RETURN_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_RETURN_CODE && resultCode == RESULT_OK) {
            Log.i("Camera return code", "OK");
            /*
            Load image data from intent and save it to Bitmap object
             */
            Bitmap cameraBmp = (Bitmap) data.getExtras().get("data");
            lockScreenPresenter.loginWithFace(this, cameraBmp);
        } else {
            Log.i("Camera return code", "Camera didn't return expected code");
            //finish();
        }
    }

    @Override
    protected void onDestroy() {
        lockScreenPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void callCreateEditUser(Class c, String determinator) {
        Intent i = new Intent (this, c);
        i.setData(Uri.parse(determinator));
        startActivity(i);
    }
}
