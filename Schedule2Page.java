import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Schedule2Page extends JPanel
{
	
	//Schedule2Panel pSchedule;
	private CineplexName pName;
	private ButtonPanel pButton;
	
	private JButton btnBuy;
	
	private BufferedImage img;
	
	
	Schedule2Page() 
	{
		setSize(1366,768);
		setLayout(null);
		
		
		initialization();
		addComponent();
		
		addActionToButton();

	}


	private void addActionToButton() {
		// TODO Auto-generated method stub
		
//		btnBuy.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				confirmPanel.getInstance().receiveValue();
//				confirmPanel.getInstance().setValues();
//				
//				if(MainFrame.getInstance().userLoginStatus==false){
//					MainFrame.getInstance().userLoginFromLoginPage = true;
//					MainFrame.getInstance().changeToUserLogin();
//				}
//				else
//				{
//					MainFrame.getInstance().changeToConfirmSelectedPage();
//				}
//			}
//		});
		
	}


	private void initialization()
	{
		pName= new CineplexName();
		pButton= new ButtonPanel();
		//pSchedule= new Schedule2Panel();
		btnBuy= new JButton();
		
	}

	private void addComponent() {
		pName.setBounds(100,5,1166,120);
		pButton.setBounds(100,125,1166,50);
		btnBuy.setBounds(590,640,230,87);
		btnBuy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/buy.png")));
		//pSchedule.setBounds(233,180,900,546);
		//btnBuy.setBackground(new Color(230,230,250));
		try {
			img = ImageIO.read(getClass().getResource("/images/removeEmp.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(pName);
		add(pButton);
		//add(pSchedule);
		add(btnBuy);
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
}
