package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import tvz.faceRecognitionLoginApp.R;

//CreateEditDialog dialog = new CreateEditDialog();
//            homeView.showCreateDialog(dialog);

public class CreateEditDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(android.os.Bundle savedInstanceState) {
        final View view =
                getActivity().getLayoutInflater()
                        .inflate(R.layout.create_edit_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //treba dodati logiku koja ce spremiti unesene vrijednosti negdje

        builder.setView(view)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        EditText userNameEditText = (EditText) view.findViewById(R.id.dialog_username);
                        EditText passwordEditText = (EditText) view.findViewById(R.id.dialog_password);

                        if ( userNameEditText.getText().length() == 0 || passwordEditText.getText().length() == 0 ) {
                            Toast.makeText(getActivity(), "Korisničko ime i/ili lozinka nisu uneseni.", Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                Toast.makeText(getActivity(), "Zasto odustajes", Toast.LENGTH_LONG).show();
            }
        });
        return builder.create();
    }
}
