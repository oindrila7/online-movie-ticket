//this is a panel where user will change pass word
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.eclipse.swt.internal.win32.MINMAXINFO;

public class ChangeUserPasswordPanel extends JPanel {
	
	
	private JLabel lblPageHeader,lblPreviousPassword,lblNewPassword;
	private JPasswordField ptxtPreviousPassword;
	private JTextField txtNewPassword;
	private JButton btnBack,btnChangePassword;
	private String userName ; //get this from main frame which user is logged in
	
	String previousPassword,newPassword;
	
	
	private ResultSet Rs,Rs2,Rs3,Rs4,Rs5;
	private Statement St,St2,St3,St4,St5;
	private ConnectionClass db;
	private String Query;
	
	public ChangeUserPasswordPanel() 
	{
		setSize(566,400);
		setLayout(null);
		initialization();
		addComponent();
		addActionListnerToButton();
		setBackground(Color.CYAN);
		
		//System.out.println(userName);
		
		
	}
	
	
	
	private void initialization() 
	{
		
		lblPageHeader= new JLabel("USER PASSWORD CHANGE PAGE");
		
		
		lblPreviousPassword= new JLabel("enter previous password");
		lblNewPassword =new JLabel("enter new password");
		
		
		ptxtPreviousPassword = new JPasswordField(30);
		txtNewPassword = new JTextField(30);
		
		
		btnBack = new JButton("BACK");
		btnChangePassword = new JButton("change password");
		
		
		previousPassword="";
		newPassword="";
		
		db = new ConnectionClass();
		
		Rs=db.getRs();
		Rs2=db.getRs2();
		Rs3=db.getRs3();
		Rs4=db.getRs4();
		Rs5=db.getRs5();
		
		St=db.getSt();
		St2=db.getSt2();
		St3=db.getSt3();
		St4=db.getSt4();
		St5=db.getSt5();
		
		
		
	}

	private void addComponent() 
	{
		lblPageHeader.setBounds(175,5,150,40);
		
		lblPreviousPassword.setBounds(83,100,150,50);
		ptxtPreviousPassword.setBounds(283,110,150,30);
		
		
		lblNewPassword.setBounds(83,200,150,50);
		txtNewPassword.setBounds(283,210,150,30);
		
		
		btnBack.setBounds(476,5,80,40);
		
		
		btnChangePassword.setBounds(208,300,150,50);
		
		add(lblPageHeader);
		
		add(lblPreviousPassword);
		add(ptxtPreviousPassword);
		
		add(lblNewPassword);
		add(txtNewPassword);
		
		
		add(btnBack);
		add(btnChangePassword);
		
		
	}

	private void addActionListnerToButton() 
	{
		btnChangePassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				String inputPassword = new String(ptxtPreviousPassword.getText());
				
				userName=MainFrame.getInstance().getUserName();
				
				newPassword= txtNewPassword.getText();
				
				//System.out.println(previousPassword);
				//System.out.println(userName);
				//System.out.println(newPassword);
				//System.out.println(inputPassword);
				
				
				if(inputPassword.equals("") || newPassword.equals(""))
				{
					JOptionPane.showMessageDialog(null, "you must fill up all the fields");
				}
				else 
				{
					
					
					String query ="select * from `customer` where `username`='"+userName+"'";
					try 
					{
						Rs = St.executeQuery(query);
						while(Rs.next())
						{
							previousPassword = Rs.getString("Password");
							//System.out.println(previousPassword);
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					if(inputPassword.equals(previousPassword))
						{
							int dialogButton = 0;
						
							dialogButton = JOptionPane.showConfirmDialog (null, "you password will be changed,continue?","Warning",dialogButton);
					
							if(dialogButton==JOptionPane.YES_OPTION)
							{	
								//update password using user name 
								
			                	
			                	try {
								
			                		
									
									String Query1="UPDATE `customer` set `Password` = '"+newPassword+"' where `username`='"+userName+"'";
									
									
									St2.executeUpdate(Query1);
									
									
									JOptionPane.showMessageDialog(null, "your password has been changed");
									MainFrame.getInstance().userLoginStatus = false;
									MainFrame.getInstance().bar.setVisible(false);
									MainFrame.getInstance().changeToLogin();
									reset();
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}	
						
								//run sql for update in date base
								
								//change page to user login login page
								
								
						
							}
						}
							
							
							else 
							{
								JOptionPane.showMessageDialog(null, "please provide the correct current password");
					
							}	
					
				}
			}
		});
		
		
		
		
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int dialogButton = 0;
				
				dialogButton = JOptionPane.showConfirmDialog (null, "all your unsaved data will be losy,continue?","Warning",dialogButton);
		
				if(dialogButton==JOptionPane.YES_OPTION)
				{	
					MainFrame.getInstance().changeToHome();
				}
				
			}
		});
		


		
	}

	public void reset()
	{
		ptxtPreviousPassword.setText("");
		txtNewPassword.setText("");
		previousPassword="";
		newPassword="";
	}

}
