package tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import tvz.faceRecognitionLoginApp.R;


public class LockScreenActivity extends Activity implements LockScreenView {

    private String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set up our Lockscreen

            makeFullScreen();
            //startService(new Intent(this,LockScreenService.class));

            setContentView(R.layout.activity_lock_screen);
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

    /*
    TODO inicijaliziraj deserializable userInfo objekt i napravi s njime provjeru
    Ako ga imaš, ok, super, može login..
    Ako ga nemaš, jebiga amigo, redirectaj nas na kreiranje novog
     */
    public void unlockScreen(View view) {
        //Instead of using finish(), this totally destroys the process
        //android.os.Process.killProcess(android.os.Process.myPid());
        //finish
        LockScreenDialog dialog = new LockScreenDialog();
        dialog.show(getFragmentManager(), "Login");
    }
}
