package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.View;

import android.view.View;
import android.widget.Toast;

public interface CreateEditView {

    void showToast(Toast t);
    void saveChanges(View v);
    void quitChanges(View v);
    void setTxtError(String message, String identifier);
    void setInfoBeforeEdit(String username, String passwd);
}
