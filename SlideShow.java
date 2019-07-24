/*class that will display the slide show in home page
 * each now showing movie is a diff Jpanel
 * Jpanel will hold movie pic
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class SlideShow extends JLabel {
	
	private JPanel pNowShowing1;
	private JPanel pNowShowing2;
	private JPanel pNowShowing3;                 //variabel to hold on playting movie pic
	private JPanel pNowShowing4;
	
	private BufferedImage img;
	private int sx1=0, sy1=0, sx2=366, sy2;
	
	SlideShow()
	{
		setLayout(null);
		setBounds(1, 0, 1166, 400);
		
		initialization();
		addComponentToSlide();
		thread_run();
		
	}


	private void initialization() {
		
		pNowShowing1=new JPanel();
		pNowShowing2=new JPanel();
		pNowShowing3=new JPanel();
		pNowShowing4=new JPanel();	
		
		
		
		
	}


	private void addComponentToSlide() {

		pNowShowing1.setBackground(Color.RED);
		pNowShowing2.setBackground(Color.BLACK);
		pNowShowing3.setBackground(Color.BLUE);
		pNowShowing4.setBackground(Color.yellow);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/kong.jpg"));
			sy2= img.getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pNowShowing1.setBounds(1, 0, 1166, 400);
		pNowShowing2.setBounds(292, 0, 291, 400);
		pNowShowing3.setBounds(583, 0, 292, 400);
		pNowShowing4.setBounds(875, 0, 291, 400);
		
		//add(pNowShowing1);
		//add(pNowShowing2);
		//add(pNowShowing3);
		//add(pNowShowing4);
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), sx1, sy1, sx2, sy2, null);
	}


	private void thread_run() {
		// TODO Auto-generated method stub
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					sx1 +=1;
					sx2 +=1;
					repaint();
					try {
						Thread.sleep(15);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(sx1 >= 366 && sx2 >= 366)
					{
						sx1 =0;
						sx2 =366;
					}
				}
			}
		});
		
		t.start();
		
		
		
		
		
	}
	
	
	
	
	

}
