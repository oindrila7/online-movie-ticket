import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RemoveEmployeePanel extends JPanel
{
	
	private JTextField txtSearchBox;     //enter the employee name to search 
	private JButton btnSearch,btnRemove,btnBack;          //press so see searchbox
	private JComboBox<String> jcbName;
	private JComboBox<String> jcbId;
	
	private JLabel lblName,lblId,lblTitle;
	
	
	private ConnectionClass db;
	
	private Statement St,St2;
	private ResultSet Rs,Rs2;
	
	private String Query,sName,sId;
	
	
	
	private String [] Name= new String[100];
	private String [] Id=new String [100];
	
	
	
	RemoveEmployeePanel()
	{
		setSize(800,500);
		setLayout(null);
		setBackground(Color.CYAN);
		initialization();
		addComponent();
		
		addActionListner();
		addItemListnerToJCB();
	}

	private void initialization() 
	{
		
		db= new ConnectionClass();
		Rs= db.getRs();
		Rs2= db.getRs2();
		St=db.getSt();
		St2=db.getSt2();
		
		lblTitle = new JLabel("REMOVE EMPLOYEE PAGE");
	
		txtSearchBox = new JTextField(15);
		btnSearch = new JButton("SEARCH");
		
		Name[0]="not selected";
		Id[0]="not selected";
		
		sName=Name[0];
		sId=Id[0];
		
		lblName  = new JLabel("SELECT EMPLOYEE NAME");
		jcbName= new JComboBox<String>(Name);
		
		
		lblId    = new JLabel("SELECT EMPLOYEE ID");
		jcbId= new JComboBox<String>(Id);
		
		btnBack= new JButton("BACK");
		
		btnRemove = new JButton("REMOVE EMPLOYEE");
		
		
		
		
		
	}

	private void addComponent() 
	{
		btnBack.setBounds(650,2,100,30);
		lblTitle.setBounds(325,10,200,40);
		
		txtSearchBox.setBounds(290,100,120,30);
		btnSearch.setBounds(415, 100, 100, 30);
		
		
		lblName.setBounds(275,150,200,30);
		jcbName.setBounds(475,150,100,30);
		
		
		lblId.setBounds(275,250,200,30);
		jcbId.setBounds(475,250,100,30);
		
		btnRemove.setBounds(325, 340, 200, 50);
		
		
		add(lblTitle);
		add(btnBack);
		
		add(txtSearchBox);
		add(btnSearch);
		
		add(lblName);
		add(jcbName);
		
		add(lblId);
		add(jcbId);
		
		add(btnRemove);	
		
	}
	
	public void ResetTextFieldandJCB()
	{
		txtSearchBox.setText("");
		Name[0]="not selected";
		Id[0]="not selected";
		
		jcbName.setSelectedIndex(0);
		jcbId.setSelectedIndex(0);
		sName=Name[0];
		sId=Id[0];
	}

	private void addItemListnerToJCB() 
	{
		
		
		jcbName.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent e1) 
			{	
				if(e1.getStateChange() == ItemEvent.SELECTED)
				{
					
					sName=Name[jcbName.getSelectedIndex()];
					
					
					Query="SELECT * FROM emp WHERE Name='"+sName+"'";
					
					//System.out.println(sName);
				try {
						
						Rs2=St2.executeQuery(Query);
						jcbId.removeAllItems();
						jcbId.addItem("not selected");
						int i =1;
						
						
						while(Rs2.next())
						{
							jcbId.addItem(Rs2.getString("ID"));
							Id[i]=Rs2.getString("ID");
							i++;
							
						}
					} catch (SQLException e12) {
						
					//e12.printStackTrace();
					System.err.println("Got an exception!");
				    System.err.println(e12.getMessage());
				}
				}
		}
		});
		
		
		jcbId.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e2) {
				if(e2.getStateChange()==ItemEvent.SELECTED)
				{
					
					
					sId=Id[jcbId.getSelectedIndex()];
					//System.out.println(sName);
					//System.out.println(sId);
				}
				
			}
		});
		
		
	}

	
	
	
	
	private void addActionListner() 
	{
		
		
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String Search = txtSearchBox.getText();
				Query=" SELECT * FROM emp WHERE `emp`.`Name` LIKE '%"+Search+"%'";
				
				try {
					
					Rs= St.executeQuery(Query);
					jcbName.removeAllItems();
					jcbName.addItem("not selected");
					int i=1;
					while(Rs.next())
					{
						jcbName.addItem(Rs.getString("Name"));					
						Name[i]=Rs.getString("Name");		
						i++;

					}
					
					
				} catch (SQLException e13) {
					
					//e13.printStackTrace();
					System.err.println("Got an exception!");
				    System.err.println(e13.getMessage());
				}
				

					
				
			}
		});
		
		
		
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(sId.equals("not selected"))
				{
					JOptionPane.showMessageDialog(null, "you must select a ID to remove that employee");
				}
				else
				{
					int dialogButton = 0;
					dialogButton = JOptionPane.showConfirmDialog (null, "removing this emplyee\n"+sName+"\n"+sId,"Warning",dialogButton);
					

	                if(dialogButton == JOptionPane.YES_OPTION)
	                {
	                	Query ="DELETE FROM `emp` WHERE `emp`.`ID` = '"+sId+"'";
	                
	                	try 
	                	{
	                		St2.executeUpdate(Query);
	                		JOptionPane.showMessageDialog(null, "Confirm Delete");
	                		MainFrame.getInstance().changeToRemoveEmployeePage();
	                		ResetTextFieldandJCB();
	                		MainFrame.getInstance().changeToRemoveEmployeePage();
	                	}
	                	catch (SQLException e1) 
	                	{
					
	                		//e1.printStackTrace();
	                		System.err.println("Got an exception!");
	                		System.err.println(e1.getMessage());
	                	}
	                	
	                }
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int dialogButton = 0;
				dialogButton = JOptionPane.showConfirmDialog (null, "all your unsaved info will be lost","Warning",dialogButton);
				

                if(dialogButton == JOptionPane.YES_OPTION)
                {
                	
                	MainFrame.getInstance().changeToAdminPage();
                	ResetTextFieldandJCB();
                }
			}
		});

		
	}
	
	public JButton getBtnRemoveEmployee()
	{
		return btnRemove;
	}
	public JButton getBtnBack()
	{
		return btnBack;
	}

}
