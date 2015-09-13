package ballcollector;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

public class CamShower extends JPanel{
	JFrame frame;
	Image image;
	int WIDTH = 410;
	int HEIGHT = 280;
	Mat circles = null;
	
	public CamShower(){
		frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, null);
		/*For showing the lines that are the balls axis
		if(image != null){
			g2d.drawLine(image.getWidth(null)/2-30, 0, image.getWidth(null)/2-30, image.getHeight(null));
			g2d.drawLine(image.getWidth(null)/2+30, 0, image.getWidth(null)/2+30, image.getHeight(null));
		}*/
		if(circles != null){
			for(int i = 0; i < circles.cols(); i++) {
				double[] circle = circles.get(0, i);
				g2d.drawOval((int)circle[0] - (int)circle[2],(int)circle[1] - (int)circle[2], (int)circle[2] * 2, (int)circle[2] * 2);
			}
		}
		
	}
	
	public void render(Image img){
		image = img;
		repaint();
	}
}
