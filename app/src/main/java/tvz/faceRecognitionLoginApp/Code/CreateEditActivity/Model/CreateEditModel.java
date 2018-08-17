package tvz.faceRecognitionLoginApp.Code.CreateEditActivity.Model;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.UserInformationHelper;

public interface CreateEditModel {

    interface CreateEditModelChecker {
        void emptyValue(String message, String identifier);
    }

    boolean saveUser (String username, String passwd, String determinator,
                                    CreateEditModelChecker cEMChecker);

}
