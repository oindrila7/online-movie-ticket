/*
 * when customer want to buy ticket S/he has to log in 
 * admin has to log in to change anything on the system
 * employee has to log in to sale tickets
 * in all of the above cases this page will generate and give login option 
 * which type of user are they
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LogInPage extends JPanel {

			
			
			private CineplexName cName;          				//to show cineplex name on top of the page
			
			private BufferedImage img;   	
			
			private ButtonPanel pButton;         				//home,schedule,movie list,login button panel
			
			
			private JButton btnAdmin;						//to go to admin log in page 
			private JButton btnUser;						//to go to user log in page
			private JButton btnEmployee;					//to go to employee login page
			private JButton  btnNew;        				//to go to new user form page
			
			
			
			
			
			
			
			
			//constructor 
			LogInPage()
			{
				
				setSize(1366,786);
				setLayout(null);
				
				
				initialization();
				addComponentToHomePanel();
				addActionListenToButton();
				
				
				
				              
			}

			//method to create concrete of of member variable/component
			
			private void initialization() 
			{
				
				cName = new CineplexName();
				
				//buttonpanel
				pButton= new ButtonPanel();
				
				
				//button
				
				btnAdmin= new JButton("AS ADMIN");
				btnUser=new JButton("SIGN IN");
				btnEmployee=new JButton("AS EMPLOYEE");
				btnNew =new JButton("SIGN UP");
				
				
			}


			private void addComponentToHomePanel() 
			{
				cName.setBounds(100,5,1166,120);
				
				pButton.setBounds(100,130,1166,50);
				
				
				
				btnUser.setBounds(500, 243, 400, 50);
				btnUser.setBackground(new Color(100,159,237));
				btnUser.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
				
				btnNew.setBounds(500, 343, 400, 50);
				btnNew.setBackground(new Color(72,209,204));
				btnNew.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
				
				
				btnAdmin.setBounds(500, 443, 400, 50);
				btnAdmin.setBackground(new Color(65,105,225));
				btnAdmin.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
				
				
				btnEmployee.setBounds(500, 543, 400, 50);
				btnEmployee.setBackground(new Color(123,104,238));
				btnEmployee.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
				
				try {
					img = ImageIO.read(getClass().getResource("/images/logIn.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				add(cName);
				add(pButton);
				
				
				add(btnUser);
				add(btnNew);
				
				
				
				add(btnAdmin);
				add(btnEmployee);
			}
			
			public JButton getUser()
			{
				return btnUser;
			}
			
			public JButton getEmp()
			{
				return btnEmployee;
			}
			
			public JButton getAdmin()
			{
				return btnAdmin;
			}
			
			public JButton getUserForm()
			{
				return btnNew;
			}

			
			public ButtonPanel getBtnPanel()
			{
				return pButton;
			}
			
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}

			private void addActionListenToButton() {
				
				
				btnUser.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(MainFrame.getInstance().userLoginStatus==false){
							MainFrame.getInstance().userLoginFromLoginPage = false; //8:29
							MainFrame.getInstance().changeToUserLogin();
						}
						else{
							JOptionPane.showMessageDialog(null,"you already logged into the system ");
							MainFrame.getInstance().changeToLogin();//844
						}
					}
				});
				
				btnNew.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(MainFrame.getInstance().userLoginStatus==false)
							MainFrame.getInstance().changeToUserForm();
						else{
							JOptionPane.showMessageDialog(null,"you already logged into the system ");
							MainFrame.getInstance().changeToLogin(); //844
						}
						
					
					}
				});
				
				btnEmployee.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(MainFrame.getInstance().userLoginStatus==false){
							
							MainFrame.getInstance().getEmployeePage().getTicketPanel().initializeMovieName();
							MainFrame.getInstance().changeToEmployeeLogin();
						}
						else{
							JOptionPane.showMessageDialog(null,"you already logged into the system as user. Please logout first");
							MainFrame.getInstance().changeToLogin(); //844
						}
					
					}
				});
				
				btnAdmin.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(MainFrame.getInstance().userLoginStatus==false)
							MainFrame.getInstance().changeToAdminLogin();
						else{
							JOptionPane.showMessageDialog(null,"you already logged into the system as user. plz log out first!! ");
							MainFrame.getInstance().changeToLogin(); //844
						}
						
					
					}
				});
				
			}
			
		
			
			
			
			
			
			
			
			
			
			
			
		
}


