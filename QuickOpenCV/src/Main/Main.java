package ballcollector;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class Main {
	static Mat image;
	static Mat dest;
	
	static {System.loadLibrary( Core.NATIVE_LIBRARY_NAME );}
	
	/*
	 * You should use this function to do whatever with 
	 * the image you're getting from your camera.
	 * There are 2 
	 */
	public static void whatToDoFunc(){
		// Write your functions here
		
		
		//End of whatToDoFunc
	}
	
	public static void main(String[] args) {
		VideoCapture cap = new VideoCapture();
		cap.open(0);
		
		if(cap.isOpened()){
			System.out.println("Connected to cam");
		}else{
			System.out.println("Could not connect");
			System.exit(0);
		}
		
		CamShower shower = new CamShower();
		image = new Mat();
		dest = new Mat();

		while(true){
			cap.read(image);
			whatToDoFunc();
			BufferedImage img = toBufferedImage(dest);
			shower.render(img);
			shower.frame.setSize(img.getWidth(), img.getHeight());
		}
	}
	
	public static BufferedImage toBufferedImage(Mat m){
        // Code from http://stackoverflow.com/questions/15670933/opencv-java-load-image-to-gui
        // Check if image is grayscale or color
	    int type = BufferedImage.TYPE_BYTE_GRAY;
	    if ( m.channels() > 1 ) {
	        type = BufferedImage.TYPE_3BYTE_BGR;
	    }
	    // Transfer bytes from Mat to BufferedImage
	    int bufferSize = m.channels()*m.cols()*m.rows();
	    byte [] b = new byte[bufferSize];
	    m.get(0,0,b); // get all the pixels
	    BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
	    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	    System.arraycopy(b, 0, targetPixels, 0, b.length);
	    return image;
	}
}
