package tvz.faceRecognitionLoginApp.Code.HelperClasses;


/**
 * This class is used in HomeActivity classes (presenter and modelImpl) as helper
 * Object of this class contains String message and success flag
 */
public class CreateEditHelper {

    private String message;
    private boolean flag;

    public CreateEditHelper(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

     public boolean getFlag() {
        return flag;
     }

}
