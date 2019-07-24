/*form where user will register themselves as an user in the system 
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class NewUserForm extends JPanel
{
	private JLabel lblUserId,lblPassWord,lblMobile,lblEmail,lblTitle,lblUserName;
	private JTextField txtUserName,txtPassWord,txtMobile,txtEmail,txtUserId;
	
	//JLabel Main;
	private ConnectionClass db ;						// to connect database
	private String Query;
	private ResultSet Rs;
	private Statement St,St2;
	private JButton btnConfirm;
	
	
	String mobileNum;
	
	
	NewUserForm()
	{
			
		setLayout(null);
		initialization();
		addComponentToForm();
		addActionToButton();
		setBackground(Color.cyan);
	}


	


	private void initialization() 
	{
		lblTitle = new JLabel("NEW USER FORM");
		
		
		lblUserName=new JLabel("USER NAME");
		lblUserId = new JLabel("USER ID");
		lblPassWord=new JLabel("PASSWORD");
		lblMobile=new JLabel("MOBILE NO");
		lblEmail=new JLabel("EMAIL");
		
		
		
		txtUserName=new JTextField(15);
		txtUserId = new JTextField(15);
		txtPassWord=new JTextField(15);
		txtMobile=new JTextField(15);
		txtEmail=new JTextField(15);
		
		
		
		btnConfirm = new JButton("Confirm");
		
		db = new ConnectionClass();
		St= db.getSt();
		St2=db.getSt2();
		
		Rs=db.getRs();
		
		
	}


	private void addComponentToForm() 
	{
		lblTitle.setBounds(380, 5,400,40);
		lblTitle.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
		
		lblUserName.setBounds(200, 60, 100, 40);
		lblUserName.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtUserName.setBounds(600, 60, 200, 40);
		
		lblUserId.setBounds(200,140,200,40);
		lblUserId.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtUserId.setBounds(600,140,200,40);
		
		
		lblPassWord.setBounds(200, 220, 200, 40);
		lblPassWord.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtPassWord.setBounds(600, 220, 200, 40);
		
		
		
		lblMobile.setBounds(200, 300, 200, 40);
		lblMobile.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtMobile.setBounds(600, 300, 200, 40);
		
		
		
		lblEmail.setBounds(200, 380, 200, 40);
		lblEmail.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtEmail.setBounds(600, 380, 200, 40);
		
		
		btnConfirm.setBounds(350, 435, 200, 60);
		btnConfirm.setBackground(new Color(250,128,114));
		btnConfirm.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		add(lblTitle);
		
		add(lblUserName);
		add(txtUserName);
		
		
		add(lblUserId);
		add(txtUserId);
		
		add(lblPassWord);
		add(txtPassWord);
		
		add(lblMobile);
		add(txtMobile);
		
		
		
		add(lblEmail);
		add(txtEmail);
		
		add(btnConfirm);
		
		
		
	}
	
	
	public boolean phoneValid()
	{
		char[] chars = mobileNum.toCharArray();
		for (int i =0; i < chars.length; i++)
		{
		    if(chars[i]!='0' && chars[i]!='1' && chars[i]!='2' && chars[i]!='3' &&chars[i]!='4' &&chars[i]!='5' &&chars[i]!='6' &&chars[i]!='7' &&chars[i]!='8' &&chars[i]!='9' )
		    {
		    	return true;
		    }
		    
		}
		return false;
	}
	
	
	
	private void addActionToButton() {
		
		
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userID  = txtUserId.getText();
				String userName  = txtUserName.getText();
				String passWord  = txtPassWord.getText();
				 mobileNum = txtMobile.getText();
				String email 	 = txtEmail.getText();
				//System.out.println("sql for:"+userName+" "+passWord+" "+mobileNum+" "+email);
				
				
				
				
				
				
				
				try
				{
					int userCount=0;
				
					Query="SELECT COUNT(*) from customer WHERE `customer`.`username`='"+userID+"' or `customer`.`Mail`='"+email+"'";
				
					Rs=St.executeQuery(Query);
					while(Rs.next())
						{
							userCount=Rs.getInt("COUNT(*)");
							if(userCount!=0)
							{
								JOptionPane.showMessageDialog(null, "use unique UserName/mail");
								
							}
							
							else if(userID.equals("")|| userName.equals("") || passWord.equals("") ||mobileNum.equals("") ||email.equals("") )
							{
								JOptionPane.showMessageDialog(null, "Please No Null Filelds!!!");
								userID ="";
								userName="";
								email="";
								mobileNum="";
								passWord = "";
								//resetTextField();
							}
							
							else
							{
								if(!( (mobileNum.startsWith("017")||mobileNum.startsWith("016")||mobileNum.startsWith("015")||mobileNum.startsWith("018")||mobileNum.startsWith("019") )   &&  mobileNum.length()==11))
								{
									JOptionPane.showMessageDialog(null, "invalid phone number");
									userID ="";
									userName="";
									email="";
									mobileNum="";
									passWord = "";
									//resetTextField();
									
									
								}
								
								else if(phoneValid())
								{
									JOptionPane.showMessageDialog(null, "invalid phone number");
									userID ="";
									userName="";
									email="";
									mobileNum="";
									passWord = "";
								}
								else if( !((email.endsWith("@gmail.com") || email.endsWith("@yahoo.com") || email.endsWith("@live.com") || email.endsWith("@outlook.com")|| email.endsWith("@aiub.edu")||email.endsWith("@hotmail.com")) &&  !(email.startsWith("@"))) )
								{
									JOptionPane.showMessageDialog(null, "not a valid email");
									userID ="";
									userName="";
									email="";
									mobileNum="";
									passWord = "";
									//resetTextField();
									
								}
								
								else  {
									try 
									{

										Query = "INSERT INTO `customer` (`username`, `Password`, `Name`, `Mobile Number`,`Mail`) VALUES ('"
												+ userID + "', '" + passWord + "', '" + userName + "', '" + mobileNum
												+ "','" + email + "')";
										St2.executeUpdate(Query);
										JOptionPane.showMessageDialog(null, "new User registered");
										resetTextField();
										MainFrame.getInstance().changeToLogin();

									
									} catch (SQLException ee1) {

										System.err.println("Got an exception!");
										System.err.println(ee1.getMessage());

									} 
								}
							
							
							
							}
						}
				
				}
				catch (SQLException ee) 
				{
					
					System.err.println("Got an exception!");
				    System.err.println(ee.getMessage());
				      
				}
				
				
				
				
			}
		});
		
		
	}
	
	public void resetTextField() 
	{
		
		txtEmail.setText("");
		txtMobile.setText("");
		txtPassWord.setText("");
		txtUserId.setText("");
		txtUserName.setText("");
		
	}
	
	public JButton getBtnConfirm()
	{
		return btnConfirm;
	}
	

	
}
