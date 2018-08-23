package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Presenter.CreateEditPresenter;
import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Presenter.CreateEditPresenterImpl;
import tvz.faceRecognitionLoginApp.R;


public class CreateEditActivity extends Activity implements CreateEditView {

    private String determinator;
    private CreateEditPresenter presenter;

    public static final Integer CAMERA_RETURN_CODE = 1;


    public String path = Environment.getExternalStorageDirectory() + "/" + "MyFirstApp/";

    @BindView(R.id.c_e_usernameET) EditText inputUsername;
    @BindView(R.id.c_e_passwdET) EditText inputPasswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit);

        ButterKnife.bind(this);

        determinator = getIntent().getDataString();

        Log.i("CREATE/EDIT", "determinator = " + determinator);

        presenter = new CreateEditPresenterImpl(this);

        declareCreateOrEdit();
    }

    /**
     * Call presenter with determinator to find out what should be done next
     * determinator is String which describes request - create or edit
     */
    public void declareCreateOrEdit() {
        presenter.declareCreateOrEditPresenter(this, determinator);
    }


    /**
     * Inflate the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
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
            case R.id.back:
                presenter.backPresenter(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called from presenter to set already existing informations to be visible
     * to the user
     * This method is called only in case when user tries to edit something
     * @param username
     * @param passwd
     */
    @Override
    public void setInfoBeforeEdit(String username, String passwd) {
        inputUsername.setText(username);
        inputPasswd.setText(passwd);
    }

    /**
     * Called from the Presenter when it decides to close the current activity
     * Recieves Toast and shows it, after it finish the current activity
     * @param t
     */
    @Override
    public void showToast(Toast t) {
        t.show();
        finish();
    }

    /**
     * When user clicks on save button, this method will be caled
     * Calls presenter to decide what should be done next
     * @param v
     */
    @Override
    public void saveChanges(View v) throws IOException {
        presenter.savePresenter(this, inputUsername.getText().toString(),
               inputPasswd.getText().toString(), determinator);
    }



    /**
     * When user clicks on quit button, this method will be caled
     * Calls presenter to decide what should be done next
     * @param v
     */
    @Override
    public void quitChanges(View v) {
        presenter.quitPresenter(this, inputUsername.getText().toString(),
                inputPasswd.getText().toString());
    }

    /**
     * Set error message to the user
     * Called from the presenter when it decides that error message should be shown
     * @param message
     * @param identifier
     */
    @Override
    public void setTxtError(String message, String identifier) {
        switch(identifier) {
            case "username":
                inputUsername.setError(message);
                break;
            case "password":
                inputPasswd.setError(message);
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Create Intent and start Camera Activity for result
     * @param v
     */
    public void startCamera(View v) {

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
            presenter.detectFace(this, cameraBmp);

        } else {
        Log.i("Camera return code", "Camera didn't return expected code");
        }
    }
}
