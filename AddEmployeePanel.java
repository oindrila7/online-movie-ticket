
/*form where user will register themselves as an user in the system 
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
import javax.swing.plaf.ColorUIResource;
//import javax.swing.*;
public class AddEmployeePanel extends JPanel
{
	
	private JLabel lblTitle;
	private JLabel lblEmployeeName,lblEmployeeId,lblEmployeePassWord,lblEmployeeMobile;
	private JTextField txtEmployeeName,txtEmployeeId,txtEmployeePassWord,txtEmployeeMobile;
	
	
	private JButton btnAddEmployee,btnBack;
	
	
	private ConnectionClass  db;
	private Statement St,St2;
	private ResultSet Rs;
	private String Query;
	
	private String userName,userId,passWord,mobileNum,email;
	
	
	AddEmployeePanel()
	{
			
		setLayout(null);
		setSize(900,550);
		setBackground(new Color(175,238,238));
		initialization();
		addComponentToForm();
		addActionToButton();
		//setBackground(Color.LIGHT_GRAY);
		
	}


	


	private void initialization() 
	{
		lblTitle = new JLabel("ADD NEW EMPLOYEE");
		lblTitle.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
		
		
		lblEmployeeName=new JLabel("EMPLOYEE NAME");
		lblEmployeeId=new JLabel("EMPLOYEE ID");
		lblEmployeePassWord=new JLabel("PASSWORD");
		lblEmployeeMobile=new JLabel("MOBILE NO");
		
		
		
		
		txtEmployeeName=new JTextField(15);
		txtEmployeeId=new JTextField(15);
		txtEmployeePassWord=new JTextField(15);
		txtEmployeeMobile=new JTextField(15);
		
		
		btnBack= new JButton("BACK");
		btnAddEmployee = new JButton("ADD EMPLOYEE");
		
		
		
		
		userName="";
		userId="";
		passWord="";
		mobileNum="";
		email="";
		
		
		db = new ConnectionClass();
		St= db.getSt();
		St2=db.getSt2();
		
		Rs=db.getRs();
		
		
	}


	private void addComponentToForm() 
	{
		lblTitle.setBounds(380,50,300,50);
		
		lblEmployeeName.setBounds(200, 150, 150, 40);
		lblEmployeeName.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtEmployeeName.setBounds(600, 150, 200, 40);
		
		
		lblEmployeeId.setBounds(200, 240, 200, 40);
		lblEmployeeId.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtEmployeeId.setBounds(600, 240, 200, 40);
		
		
		
		lblEmployeePassWord.setBounds(200, 330, 200, 40);
		lblEmployeePassWord.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtEmployeePassWord.setBounds(600, 330, 200, 40);
		
		
		
		lblEmployeeMobile.setBounds(200, 420, 200, 40);
		lblEmployeeMobile.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		txtEmployeeMobile.setBounds(600, 420, 200, 40);
		
		btnBack.setBounds(750, 5, 150, 50);
		btnBack.setBackground(Color.RED);
		btnBack.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		btnAddEmployee.setBounds(350,490, 200, 50);
		btnAddEmployee.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnAddEmployee.setBackground(new Color(205,92,100));
		
		add(lblTitle);
		
		add(lblEmployeeName);
		add(txtEmployeeName);
		
		add(lblEmployeeId);
		add(txtEmployeeId);
		
		add(lblEmployeePassWord);
		add(txtEmployeePassWord);
		
		
		
		add(lblEmployeeMobile);
		add(txtEmployeeMobile);
		
		add(btnBack);
		add(btnAddEmployee);
		
		
		
	}
	
	public JButton getBtnAddEmployee()
	{
		return btnAddEmployee;
	}
	
	public JButton getBtnBack()
	{
		return btnBack;
	}
	
	private void addActionToButton() {
		
		btnAddEmployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				userName  = txtEmployeeName.getText();
				userId    = txtEmployeeId.getText();
				passWord  = txtEmployeePassWord.getText();
				mobileNum = txtEmployeeMobile.getText();
				email 	 = txtEmployeeMobile.getText();
				
				if(userName.equals("")||userId.equals("")||passWord.equals("")||mobileNum.equals("")||email.equals(""))
				{
					JOptionPane.showMessageDialog(null, "you must provide all the info");
				}
				else 
				{
					try
					{
						int Ecount=0;
						Query="SELECT COUNT(*) from emp WHERE `emp`.`ID`='"+userId+"'";
						Rs=St.executeQuery(Query);
						while(Rs.next())
						{
							Ecount=Rs.getInt("COUNT(*)");
							if(Ecount!=0)
							{
								JOptionPane.showMessageDialog(null, "use unique Emp ID");
								
							}
							else
							{
							
							try 
							{	
								int dialogButton = 0;
								dialogButton = JOptionPane.showConfirmDialog (null, "adding this employee\n"+userName+"\n"+userId+"\n"+mobileNum+"\n"+email+"\n"
										,"Warning",dialogButton);
								

				                if(dialogButton == JOptionPane.YES_OPTION)
				                {
				                	Query ="INSERT INTO `emp` (`ID`, `Password`, `Name`, `Mobile`) VALUES ('"+userId+"', '"+passWord+"', '"+userName+"', '"+mobileNum+"')";
				                	St2.executeUpdate(Query);								
				                	JOptionPane.showMessageDialog(null, "new employee registered");
				                	ResetTextField();
				                }
							}catch (SQLException ee1) 
							{
									
									System.err.println("Got an exception!");
							        System.err.println(ee1.getMessage());			     
							      
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
				
				MainFrame.getInstance().changeToAddEmployeePage();
				
			}
				
		});
		
		getBtnBack().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int dialogButton = 0;
				dialogButton = JOptionPane.showConfirmDialog (null, "all your unsaved info will be lost","Warning",dialogButton);
				

                if(dialogButton == JOptionPane.YES_OPTION)
                {
                	MainFrame.getInstance().changeToAdminPage();
                	ResetTextField();
                }
			}
		});
		


	
	}
	
	public void ResetTextField()
	{
		
		txtEmployeeName.setText("");
		txtEmployeeId.setText("");
		txtEmployeePassWord.setText("");
		txtEmployeeMobile.setText("");
		
		
		userName="";
		userId="";
		passWord="";
		mobileNum="";
		email="";
		
	}
}

