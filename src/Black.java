import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Black
{
   public static void main( String[] args )
   {
      System.loadLibrary("VERSION");
      Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
      System.out.println( "mat = " + mat.dump() );
   }
}