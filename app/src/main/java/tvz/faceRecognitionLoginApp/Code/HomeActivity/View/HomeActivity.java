package tvz.faceRecognitionLoginApp.Code.HomeActivity.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View.CreateEditActivity;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.KompleksniObject;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;
import tvz.faceRecognitionLoginApp.Code.HomeActivity.Presenter.HomePresenter;
import tvz.faceRecognitionLoginApp.Code.HomeActivity.Presenter.HomePresenterImpl;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View.LockScreenActivity;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View.LockScreenService;
import tvz.faceRecognitionLoginApp.R;

public class HomeActivity extends Activity implements HomeView {

    private HomePresenter presenter;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        presenter = new HomePresenterImpl(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, LockScreenService.class);
        startService(intent);
    }

    /*
    Used for getting data from activity on result

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Home", "usao sam u onActivityResult");
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {

        KompleksniObject obj = data.getExtras().getParcelable("myObject");
        Log.i( "Ime: ", obj.getIme().toString());
            }
        }
    } */

    /**
     * Inflate the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    /**
     * Check which item from menu was selected and than call presenter to decide
     * what should be done next
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutHome:
                presenter.homeMenuInfo(item.getTitle().toString());
                return true;
            case R.id.web:
                presenter.homeMenuWeb(item.getTitle().toString());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Called from presenter
     * Method creates new Intent with informations received from presenter and
     * starts new activity
     * @param txt
     * @param c
     */
    @Override
    public void aboutActivity(String txt, Class c) {
       Intent i = new Intent(getApplicationContext(), c);
       i.setData(Uri.parse(txt));
       startActivity(i);
    }

    /**
     * Called from presenter
     * Method starts new activity with intent received from presenter
     * @param i
     */
    @Override
    public void connectToGoogle(Intent i) {
        startActivity(i);
    }

    /**
     * Triggered by click event on "Create New User" button
     * Calls presenter do decide what should be done next
     * @param v
     */
    @Override
    public void createNewUser(View v) {

        presenter.createNewUserCheck(this);
    }

    /**
     * Triggered by click event on "Edit user" button
     * Calls presenter do decide what should be done next
     * @param v
     */
    @Override
    public void editUser(View v) {
        presenter.editUserCheck(this);
    }

    @Override
    public void showToast(Toast t) {
        t.show();
    }

}
