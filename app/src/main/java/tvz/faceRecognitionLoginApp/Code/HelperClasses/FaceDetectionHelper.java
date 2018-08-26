package tvz.faceRecognitionLoginApp.Code.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.FaceDetector;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;

import tvz.faceRecognitionLoginApp.R;

/**
 * This is helper class for face detection
 */
public class FaceDetectionHelper  {

    DataSerializable dataSerializable;

    private boolean imageSaved = false;

    public FaceDetectionHelper() {
        this.dataSerializable = new DataSerializableImpl();
    }

    public boolean faceDetection(Activity a, Bitmap cameraBmp, String determinator) {
        ImageView imgView = (ImageView) a.findViewById(R.id.imageView1);

        /*
        Determine action - save or login
        if save, then call serializable to save the bitmap to file
        if login, then call serializable to load bitmap from file, start comparison and
        return the result
         */
        Bitmap temporary = null;
        switch (determinator) {
            case "save":
                temporary = faceDetector(cameraBmp, a);
                if(temporary != null) {

                if(dataSerializable.writeBitmapToFile(temporary, a)) {
                 imageSaved = true;
                    imgView.setImageDrawable(new BitmapDrawable(a.getResources(), temporary));
                } else {
                    return false;
                }
                }
                break;
            case "login":
                //load image from file - start deserialization
                Bitmap loadedImage = dataSerializable.readBitmapFromFile(a);
                //convert loaded image
                Bitmap savedImage = faceDetector(loadedImage, a);
                //convert input image
                Bitmap loginImageForComparison = faceDetector(cameraBmp, a);

                if(loadedImage == null) {
                    Log.i("Face Detection", "loaded img is null, deserialization problem");
                    return false;
                } else if(loginImageForComparison == null) {
                    Log.i("Face Detection", "input image not converted successfully");
                    return false;
                } else if(savedImage == null) {
                    Log.i("Face Detection", "saved image not converted successfully");
                    return false;
                }
                else {
                    imageSaved = true;
                    //call logic for comparison
                    Log.i("Face Detection", "Success, face detected");
                }
            default:
                break;
        }
        return imageSaved;
    }

    /**
     * Detects face on the given image and returns bitmap ready for face recognition
     * Draw red rectangle on the image if it will be helpful - commented option
     * @param cameraBmp
     * @param a
     * @return
     */
    private Bitmap faceDetector(Bitmap cameraBmp, Activity a) {
        /*
        Convert to RGB_565 format and make it mutable (we're going to write on the image so
        we need to make sure that the bitmap is mutable)
        */
        Bitmap finalImg = cameraBmp.copy(Bitmap.Config.RGB_565, true);

        /*
        Setup Canvas object
        */
        Canvas tempCanvas = new Canvas(finalImg);
        tempCanvas.drawBitmap(finalImg, 0, 0, null);

        /*
        Create FaceDetector object using its builder.
        But It's possible that, the first time our face detector runs, Google Play Services
        won't be ready to process faces yet. So we need to check if our detector is
        operational before we use it.
        */
        com.google.android.gms.vision.face.FaceDetector faceDetector = new
                com.google.android.gms.vision.face.FaceDetector.Builder(a.getApplicationContext()).setTrackingEnabled(false)
                .build();
        if (!faceDetector.isOperational()) {
            //new AlertDialog.Builder(v.getContext()).setMessage("Could not set up the face detector!").show();
        }
            /*
            Now we're ready to detect faces. This is really straightforward -- Create a frame
            using the bitmap,then call the detect method on the FaceDetector, using this frame,
            to get back a SparseArray of Face objects.
             */
        Frame frame = new Frame.Builder().setBitmap(finalImg).build();
        SparseArray<Face> faces = faceDetector.detect(frame);
        Log.i("FaceDetection", "Number of faces = " + String.valueOf(faces.size()));

        //frame.

        if(faces.size() > 0) {
            return finalImg;
        } else {
            return null;
        }

              /*
        Simple Paint object which will be used for drawing on the image
        */
       /* Paint myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(5);
        myRectPaint.setColor(Color.RED);
        myRectPaint.setStyle(Paint.Style.STROKE); */

            /*
            Draw Rectangles on the Faces

            At this point you'll have a SparseArray of Faces. You can iterate through this array to
            get the coordinates of the bounding rectangle for the face. The API returns the x,y
            coordinates of the top left corner, as well as the width and height. Rectangles require
            x,y of the top left and bottom right corners, so you have to calculate the bottom right
            using the top left, width and height. Here's the code.
             */
      /*  for (int i = 0; i < faces.size(); i++) {
            Face thisFace = faces.valueAt(i);
            float x1 = thisFace.getPosition().x;
            float y1 = thisFace.getPosition().y;
            float x2 = x1 + thisFace.getWidth();
            float y2 = y1 + thisFace.getHeight();
            tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, myRectPaint);
        }
        ImageView imgView = (ImageView) a.findViewById(R.id.imageView1);
        imgView.setImageDrawable(new BitmapDrawable(a.getResources(), finalImg));*/
    }

}
