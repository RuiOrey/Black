package main.java;
import java.awt.Color;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.*;
import org.opencv.core.*;





//
//Detects faces in an image, draws boxes around them, and writes the results
//to "faceDetection.png".
//
class DetectFaceDemo {
public void run() {
 System.out.println("\nRunning DetectFaceDemo");

 // Create a face detector from the cascade file in the resources
 // directory.
// String facefilterpath = getClass().getResource("../resources/haarcascade_mcs_eyepair_big.xml").getPath();
 String facefilterpath = getClass().getResource("../resources/haarcascade_eye.xml").getPath();
 facefilterpath =  facefilterpath.substring(1,facefilterpath.length());
 CascadeClassifier faceDetector = new CascadeClassifier(facefilterpath);
 String pngpath = getClass().getResource("../resources/brown_eyes.jpg").getPath();
 pngpath =  pngpath.substring(1,pngpath.length());
 Mat image = Highgui.imread(pngpath);

 // Detect faces in the ismage.
 // MatOfRect is a special container class for Rect.
 MatOfRect faceDetections = new MatOfRect();
 faceDetector.detectMultiScale(image, faceDetections);

 
 Mat image2=image;
 
 
 
 Imgproc.cvtColor(image2, image, 6); // 6 = CV_BGR2GRAY not working
 Imgproc.GaussianBlur(image, image, new Size(7,7), 4, 4);
// Imgproc.medianBlur(image,image, 2);
 MatOfPoint3f circles = new MatOfPoint3f();
 MatOfPoint3f circles2 = new MatOfPoint3f();

Imgproc.HoughCircles(image, circles, Imgproc.CV_HOUGH_GRADIENT,5,image.rows()/5,100,100,10,50);

Imgproc.HoughCircles(image, circles2, Imgproc.CV_HOUGH_GRADIENT,5,image.rows()/5,100,100,50,400);

 Imgproc.cvtColor(image, image, 8); // 6 = CV_BGR2GRAY not working
 
 System.out.println(String.format("Detected %s faces", faceDetections));
 // Draw a bounding box around each face.
 for (Rect rect : faceDetections.toArray()) {
   // Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0),100);
 }

 System.out.println(String.format("Detected %s circles", circles.total()));
 
 
for(Point3 circle : circles.toArray()){
 	 Point center = new Point(circle.x, circle.y);
     int radius = (int) Math.round(circle.z);      
     Core.circle(image, center, 3,new Scalar(0,255,0), -1, 8, 0);
     Core.circle(image, center, radius, new Scalar(0,0,255), 3, 8, 0);
    // Core.circle(image, center, radius, new Scalar(0,255,0), 10,8, 0);    
    }
for(Point3 circle : circles2.toArray()){
 	 Point center = new Point(circle.x, circle.y);
     int radius = (int) Math.round(circle.z);      
     Core.circle(image, center, 3,new Scalar(0,255,0), -1, 8, 0);
     Core.circle(image, center, radius, new Scalar(0,0,255), 3, 8, 0);
    // Core.circle(image, center, radius, new Scalar(0,255,0), 10,8, 0);    
    }

 //Core.circle(image, new Point(100,100), 10, new Scalar(0,255,0), 10, 8, 0);  
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