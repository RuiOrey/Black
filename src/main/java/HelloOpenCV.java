package main.java;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;


//
//Detects faces in an image, draws boxes around them, and writes the results
//to "faceDetection.png".
//
class DetectFaceDemo {
public void run() {
 System.out.println("\nRunning DetectFaceDemo");

 // Create a face detector from the cascade file in the resources
 // directory.
 String facefilterpath = getClass().getResource("../resources/lbpcascade_frontalface.xml").getPath();
 facefilterpath =  facefilterpath.substring(1,facefilterpath.length());
 CascadeClassifier faceDetector = new CascadeClassifier(facefilterpath);
 String pngpath = getClass().getResource("../resources/lena.png").getPath();
 pngpath =  pngpath.substring(1,pngpath.length());
 Mat image = Highgui.imread(pngpath);

 // Detect faces in the ismage.
 // MatOfRect is a special container class for Rect.
 MatOfRect faceDetections = new MatOfRect();
 faceDetector.detectMultiScale(image, faceDetections);

 System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

 // Draw a bounding box around each face.
 for (Rect rect : faceDetections.toArray()) {
     Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
 }

 // Save the visualized detection.
 String filename = "faceDetection.png";
 System.out.println(String.format("Writing %s", filename));
 Highgui.imwrite(filename, image);
}
}

public class HelloOpenCV {
public static void main(String[] args) {
 System.out.println("Hello, OpenCV");

 // Load the native library.
 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
 new DetectFaceDemo().run();
}
}