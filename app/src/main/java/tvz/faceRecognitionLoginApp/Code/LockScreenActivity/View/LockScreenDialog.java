package tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;
import tvz.faceRecognitionLoginApp.R;

public class LockScreenDialog extends DialogFragment {

    UserInformationHelper userHelper = UserInformationHelper.getSuperHelper();

    @Override
    public Dialog onCreateDialog(android.os.Bundle savedInstanceState) {
        final View view =
                getActivity().getLayoutInflater()
                        .inflate(R.layout.pass_login_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //treba dodati logiku koja ce spremiti unesene vrijednosti negdje

        builder.setView(view)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText passwordEditText = (EditText) view.findViewById(R.id.lockLoginPass);
                        if(userHelper == null) {
                            //dodati logiku koja ce hendlati slucaj kada user nije iskreirao account
                            getActivity().finish();
                        }
                        if(!passwordEditText.getText().toString().equals(userHelper.getPassword().toString())) {

                            passwordEditText.setError("Wrong password! Please try again");
                            Toast.makeText(getActivity(), "Wrong password\n" + "Please try again", Toast.LENGTH_LONG).show();
                        } else {
                            //android.os.Process.killProcess(android.os.Process.myPid());
                            getActivity().finish();
                        }
                    }
                });
        return builder.create();
    }
}
