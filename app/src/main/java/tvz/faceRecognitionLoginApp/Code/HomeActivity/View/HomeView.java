package tvz.faceRecognitionLoginApp.Code.HomeActivity.View;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View.CreateEditDialog;

public interface HomeView {

        //create and edit user
        void createNewUser(View v);
        void editUser(View v);

        void showToast(Toast t);

        //menu handlers
        void aboutActivity(String txt, Class c);
        void connectToGoogle(Intent i);
}
