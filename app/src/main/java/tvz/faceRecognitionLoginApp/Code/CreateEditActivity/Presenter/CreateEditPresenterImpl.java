package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model.CreateEditModel;
import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model.CreateEditModelImpl;
import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View.CreateEditView;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.KompleksniObject;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;
import tvz.faceRecognitionLoginApp.Code.HomeActivity.View.HomeActivity;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View.LockScreenActivity;

import static android.os.SystemClock.sleep;

public class CreateEditPresenterImpl implements CreateEditPresenter, CreateEditModel.CreateEditModelChecker {

    CreateEditView createEditView;
    CreateEditModel createEditModel;

    /**
     * Constructor
     * @param cEView
     */
    public CreateEditPresenterImpl (CreateEditView cEView) {
        this.createEditView = cEView;
        this.createEditModel = new CreateEditModelImpl();
    }

    /**
     * Declare is activity called for edit or for create
     * Set existing informations to user view if we have edit action requested
     * @param a
     * @param determinator
     */
    @Override
    public void declareCreateOrEditPresenter(Activity a, String determinator) {
        if(determinator.isEmpty()) {
            Toast t = Toast.makeText(a, "Error", Toast.LENGTH_SHORT);
            createEditView.showToast(t);
        } else {
            switch (determinator) {
                case "Edit":
                    UserInformationHelper helper = UserInformationHelper.getSuperHelper();
                    createEditView.setInfoBeforeEdit(helper.getUsername(), helper.getPassword());
                case "Create":
                default:
                    break;
            }
        }
    }

    @Override
    public void onDestroy () { createEditView = null; }

    /**
     * Called from view when save button is pressed
     * Calls model to save the informations into object and informs
     * user that action was successfully done
     * @param a
     * @param username
     * @param passwd
     */
    @Override
    public void savePresenter(Activity a, String username, String passwd, String determinator) throws IOException{
        Log.i("CREATE/EDIT Presenter", "uletio sam u savePresenter metodu");

        if(createEditModel.saveUser(a, username, passwd, determinator, this)) {
            Toast t = Toast.makeText(a, "Saved successfully", Toast.LENGTH_SHORT);
            createEditView.showToast(t);
        }

          //odgovor na activity for result primjer
          /*
        KompleksniObject kompleksniObject = new KompleksniObject
                ("Pero", "Zdero", true);
          Intent i = new Intent();
          i.putExtra("myObject", kompleksniObject);
          a.setResult(Activity.RESULT_OK, i);
          a.finish();
        } */
    }

    /**
     * Called from view when quit button is pressed
     * If username or pass are empty, shows error
     * Else show alert and ask user if it sure about their decision
     * @param a
     * @param username
     * @param passwd
     */
    @Override
    public void quitPresenter(Activity a, String username, String passwd) {
        //ako su oba prazna, nemas kaj spremit pa odi vanka
        if(username.isEmpty() && passwd.isEmpty()) {
            a.finish();
        } else {
            //TODO pozovi alert
            Toast t = Toast.makeText(a, "quit", Toast.LENGTH_SHORT);
            createEditView.showToast(t);
        }
    }

    /**
     * Called from onOptionsItemSelcted from the View
     * Creates the toast for the View
     * @param a
     */
    @Override
    public void backPresenter(Activity a) {
        Toast t = Toast.makeText(a, "Return", Toast.LENGTH_SHORT);
        createEditView.showToast(t);
    }

    /**
     * Called from the model
     * Forwards error message and its identifier to the View where error
     * message will be setted to the user
     * @param message
     * @param identifier
     */
    @Override
    public void emptyValue(String message, String identifier) {
        createEditView.setTxtError(message, identifier);
    }
}
