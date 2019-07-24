
/*this class shows the schedule page of the program
 * by clicking "schedule" button 
 * on this page customers/viewrs will select date,movie name, then it will show schedule for that movie 
 */


import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Schedule  extends JPanel 

{
	private BufferedImage img;
	private CineplexName cName;                          //theatername
	private ButtonPanel pButton; 					     //button panel
	private DayMovieSelectionPanel pSelect;				//day & movie sele
	
	Schedule()
	{
		setSize(1366,768);
		setLayout(null);
		
		
		
		initialization();
		addComponentToSchedule_01();
		addActionListenToButton();
	}


	private void initialization()
	{
		cName = new CineplexName();
		pButton = new ButtonPanel();
		pSelect= DayMovieSelectionPanel.getInstance();
		
		
		
		
		
	}

	private void addComponentToSchedule_01() 
	{
		//cName.setBounds(100,5,1166,120);
		pButton.setBounds(100,130,1166,50);
		//pSelect.setBounds(400,300,500,300);
		
		
		
		try {
			img = ImageIO.read(getClass().getResource("/images/schedule.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(cName);
		add(pButton);
		add(pSelect);
		
		
		
		
		
		
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
	
	
	private void addActionListenToButton() {
		
		pSelect.getBtnShowSchedule().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
//					Schedule2Panel pSchedule = Schedule2Panel.getInstance();
//					pSchedule.setBounds(233,180,900,450);
//					pSchedule.setValues();
//					pSchedule.addJCBTheater();
//					pSchedule.addItemListnerToJCB();
//					MainFrame.getInstance().getSchedule2Page().add(pSchedule);
			}
		});
		
	}
	
	
	
}

