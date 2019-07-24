/*class that will show the home page of the system
 * this is a custom jpanel class 
 * this custom class will be placed on card in main window
 * need custom panel class object of  "CineplexName" 
 * need custome panel class object of "ButtonPanel"
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel
{
       //member variable list
	
		private JButton btnBuy;               		//button to display on window for buying ticket
		
		private CineplexName cName;          		//to show cineplex name on top of the page
		
		private NextArrival pNext;       			//to show next arrival movie list in grid view
		
		@SuppressWarnings("unused")
		private JPanel pSlideShow;           		//to display now showing movie list in a slide show style
		
		private ButtonPanel pButton;         		//home,schedule,movie list,login button panel
		
		private BufferedImage img;
		private SlideShow slide;            		//to show now playing movies in a slide 
		
		
		//constructor 
		Home()
		{
			
			setSize(1366,768);
			setLayout(null);
			//setIcon(new ImageIcon("G:/OneDrive/Java Project/Eclipse Workspace/java project test/src/images/batman-v-superman-dawn-of-just.jpg")); 
			
			
			
			
			initialization();
			addComponentToHomePanel();
			addActionListenToButton();
			
			
			
			              
		}

		//method to create concrete of of member variable/component
		
		private void initialization() 
		{
			
			cName = new CineplexName();
			
			//next arrival
			
			pNext = new NextArrival();
			
			
			//button
			
			btnBuy= new JButton();
			btnBuy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/rsz_1capture1.jpg")));
			//buttonpanel
			pButton= new ButtonPanel();
			
			
			//slide
			
			slide =new SlideShow();
			
			
		}


		private void addComponentToHomePanel() 
		{
			//cName.setBounds(100,5,1166,120);
			
			pButton.setBounds(100,130,1166,50);
			
			
			btnBuy.setBounds(900, 570,366,130);
			
			//pNext.setBounds(100,550,600,170);
			//lblNextArrival.setBackground(Color.CYAN);
			
			slide.setBounds(100,190,1166,350);
			
	
			//this.path = "/images/batman-v-superman-dawn-of-just.jpg";
			
			
			try {
				img = ImageIO.read(getClass().getResource("/images/B an B.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			add(cName);
			add(btnBuy);
			add(pButton);
			add(pNext);
			add(slide);
			
			
			
		}


		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
		
		public ButtonPanel getBtnPanel()
		{
			return pButton;
		}
		
		public JButton getBtnBuy()
		{
			return btnBuy;
		}
		
		private void addActionListenToButton() {
			// TODO Auto-generated method stub
			
			getBtnBuy().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MainFrame.getInstance().changeToSchedule();
					
				
				}
			});
			
		}
		
	
	

		
		
		
		
		
		
		
		
		
		
		
	
}
