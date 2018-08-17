package tvz.faceRecognitionLoginApp.Code.HomeActivity.ModelAsInteractor;

import tvz.faceRecognitionLoginApp.Code.HelperClasses.CreateEditHelper;

public interface HomeModel {

    CreateEditHelper checkCreateOrEdit (String flag);

    boolean checkIfUserCreated();

}
