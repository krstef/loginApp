package tvz.faceRecognitionLoginApp.Code.HomeActivity.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tvz.faceRecognitionLoginApp.Code.AboutActivity.AboutActivity;
import tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View.CreateEditActivity;
import tvz.faceRecognitionLoginApp.Code.HelperClasses.CreateEditHelper;
import tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor.HomeMenuModel;
import tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor.HomeMenuModelImpl;
import tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor.HomeModel;
import tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor.HomeModelImpl;
import tvz.faceRecognitionLoginApp.Code.HomeActivity.View.HomeView;
import tvz.faceRecognitionLoginApp.Code.LockScreenActivity.View.LockScreenActivity;

public class HomePresenterImpl implements HomePresenter, HomeMenuModel.MenuItemHandler {

    HomeView homeView;
    HomeModel homeModel;
    HomeMenuModel homeMenuModel;
    public static final String create = "Create";
    public static final String edit = "Edit";

    public HomePresenterImpl (HomeView hView) {
        this.homeView = hView;
        this.homeModel = new HomeModelImpl();
        this.homeMenuModel = new HomeMenuModelImpl();
    }

    @Override
    public void onDestroy() {
        homeView = null;
    }

    @Override
    public void checkAppInitialStart(Activity a) throws IOException {
        try {
            homeModel.checkInitialStartModel(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * View calls this method when user clicks on "Info" item from menu
     * Method calls Model where the resposne for View will be build
     * @param title
     */
    @Override
    public void homeMenuInfo(String title) {
        homeMenuModel.menuItemClicked(title, this);
    }

    /**
     * Model calls this method with String message as response on homeMenuInfo() method
     * Method sends resonse message and appropriate class to View
     * @param txt
     */
    @Override
    public void callInfo(String txt) {
        homeView.aboutActivity(txt, AboutActivity.class);
    }

    /**
     * View calls this method when user clicks on "Google" item from menu
     * Method calls Model where the resposne for View will be build
     * @param title
     */
    @Override
    public void homeMenuWeb(String title) {
        homeMenuModel.menuItemClicked(title, this);
    }

    /**
     * Model calls this method with appropriate URL as response on homeMenuWeb() method
     * Method creates intent and calls the view with it
     * @param url
     */
    @Override
    public void callWeb(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        homeView.connectToGoogle(i);
    }

    /**
     * This method handels click event on "Create new user" button
     * @param a
     */
    @Override
    public void createNewUserCheck(Activity a) {
        CreateEditHelper helper = homeModel.checkCreateOrEdit(create);
        if (helper.getFlag()) {
            Toast t = Toast.makeText(a, helper.getMessage().toString(), Toast.LENGTH_LONG);
            homeView.showToast(t);

        } else {
           Intent i = new Intent(a.getApplicationContext(), CreateEditActivity.class);
            i.setData(Uri.parse(create));
            Log.i("PRESENTER", "\n\n\n intent kreiran");
            /*
            Za parcalable objekt:
            a.startActivityForResult(i, 1);
             */
            a.startActivity(i);
        }
    }

    /**
     * This method handels click event on "Edit user" button
     * @param a
     */
    @Override
    public void editUserCheck(Activity a) {
        CreateEditHelper helper = homeModel.checkCreateOrEdit(edit);
            if(helper.getFlag()) {
                Toast t = Toast.makeText(a, helper.getMessage().toString(), Toast.LENGTH_LONG);
                homeView.showToast(t);
            } else {
               Intent i = new Intent(a.getApplicationContext(), CreateEditActivity.class);
                i.setData(Uri.parse(edit));
                a.startActivity(i);
            }
        }

    }
