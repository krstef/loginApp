package tvz.faceRecognitionLoginApp.Code.HelperClasses;


import android.graphics.Bitmap;

/**
 * This class is used in HomeActivity classes (presenter and modelImpl) as helper
 * Object of this class contains String message and success flag
 */
public class CreateEditHelper {

    private String message;
    private boolean flag;
    private Bitmap image;

    public CreateEditHelper(boolean flag, String message, Bitmap image) {
        super();
        this.flag = flag;
        this.message = message;
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

     public boolean getFlag() {
        return flag;
     }

     public Bitmap getImage() {
        return image;
     }

}
