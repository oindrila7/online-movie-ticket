
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jdk.nashorn.internal.scripts.JO;

public class AddShowPanel extends JPanel
{
	private JLabel lblAddShow;
	private JLabel lblMovie,lblDate,lblTheater,lblShowTime;
	private JComboBox<String> jcbMovie,jcbDay,jcbTheater,jcbShowTime;
	
	
	private JButton btnAddShow,btnBack;
	
	//database variables
	
	@SuppressWarnings("unused")
	private ResultSet Rs,Rs2,Rs3,Rs4,Rs5;
	@SuppressWarnings("unused")
	private Statement St,St2,St3,St4,St5;
	private ConnectionClass db;
	private String Query;
	
	
	//ARRAY
	
	private String [] day= new String[100] ;
	private String [] movie= new String [100];
	private String [] theater=new String[100];
	private String [] showtime = new String[100];
	
	private String sMovie,sDay,sTheater,sShowTime;
	private String movieId,showTimeId;
	
	AddShowPanel()
	{
		setSize(900,550);
		setLayout(null);
		setBackground(new Color(175,238,238));
		initialization();
		addComponent();
		
		addActionListen();
		addItemListnerToJCB();
		
		initializeMovieName();
		
	}

	

	private void initialization() 
	{
		
		//database and sql variables initialization
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
		
		
		
		
		
		lblAddShow = new JLabel("ADD SHOW PAGE");
		
		
		lblMovie= new JLabel("SELECT MOVIE");
		lblDate = new JLabel("SELECT DATE");
		lblTheater= new JLabel("SELECT THEATER");
		lblShowTime= new JLabel("SELECT SHOWTIME");
		
		
		jcbMovie = new JComboBox<String>();
		jcbDay =new JComboBox<String>();
		jcbTheater=new JComboBox<String>();
		jcbShowTime=new JComboBox<String>();
		
		
		
		
		
		btnAddShow = new JButton("ADD SHOW");
		btnBack= new JButton("BACK");
		
		
		day[0]="NOT SELECTED";
		movie[0]="NOT SELECTED";
		showtime[0]="NOT SELECTED";
		theater[0]="NOT SELECTED";
		
			
		
	}
	
	
	private void addComponent() 
	{
		lblAddShow.setBounds(375,70,280,50);
		lblAddShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
		
		
		lblMovie.setBounds(200,150,150,30);
		lblMovie.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbMovie.setBounds(550,150,150,30);
		
		
		
		lblDate.setBounds(200,220,150,30);
		lblDate.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbDay.setBounds(550,220,150,30);
		
		
		lblTheater.setBounds(200,290,150,30);
		lblTheater.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbTheater.setBounds(550,290,150,30);
		
		
		lblShowTime.setBounds(200,360,150,30);
		lblShowTime.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbShowTime.setBounds(550,360,150,30);
		
		
		
		
		
		btnBack.setBounds(700, 20, 150, 50);
		btnBack.setBackground(Color.RED);
		btnBack.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		
		btnAddShow.setBounds(375, 480, 150, 45);
		btnAddShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnAddShow.setBackground(new Color(205,92,100));
		
		
		
		add(lblAddShow);
		
		add(lblMovie);
		add(jcbMovie);
		
		add(lblDate);
		add(jcbDay);
		
		add(lblTheater);
		add(jcbTheater);
		
		add(lblShowTime);
		add(jcbShowTime);
		

		
		add(btnBack);
		add(btnAddShow);
		
		
	}
	
	
	public JButton getBtnAddShow()
	{
		return btnAddShow;
	}
	
	public JButton getBtnBack()
	{
		return btnBack;
	}
	
	public void ResetJCB()
	{
		jcbMovie.setSelectedIndex(0);
		jcbDay.setSelectedIndex(0);
		jcbTheater.setSelectedIndex(0);
		jcbShowTime.setSelectedItem(0);
		
	}
	
	public void initializeMovieName() 
	{
		
		Query = "SELECT DISTINCT `movie`.`Movie Name` from movie";
		try 
		{
			
			Rs=St.executeQuery(Query);
			jcbMovie.removeAllItems();
			 
			jcbMovie.addItem("not selected");
			
			int i =1;
			
			
			while(Rs.next())
			{
				jcbMovie.addItem(Rs.getString("Movie Name"));
				movie[i]=Rs.getString("Movie Name");
				i++;
				
			}
		}catch (SQLException e12) {
				
			//	e12.printStackTrace();
				System.err.println("Got an exception!");
			    System.err.println(e12.getMessage());
			}
	}
	
	
	
	
	public void addItemListnerToJCB()
	{
		
		jcbMovie.addItemListener(new  ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e1) {
				// TODO Auto-generated method stub
				
				if(e1.getStateChange()==ItemEvent.SELECTED)
				{
					sMovie=movie[jcbMovie.getSelectedIndex()];
					//System.out.println(sMovie);
						
					
					
					//code from minimize day option
					
					Query="SELECT DISTINCT `schedule`.`Day` FROM `schedule`,`movie` WHERE `movie`.`Movie Name`='"+sMovie+"'";                     
					
					
					try
					{
						Rs2=St2.executeQuery(Query);
						jcbDay.removeAllItems();
						 
						jcbDay.addItem("not selected");
						
						int i =1;
						
						
						while(Rs2.next())
						{
							jcbDay.addItem(Rs2.getString("Day"));
							day[i]=Rs2.getString("Day");
							i++;
							
							
						}
					
					} 
					
					
					
					catch (SQLException e11) {
						// TODO Auto-generated catch block
						//e11.printStackTrace();
						 System.err.println(e11.getMessage());
					}
					
					
					
					
					
					
				}
				
			}
		});
		
		
		
		
		jcbDay.addItemListener(new ItemListener() 
		{
			
			@Override
			public void itemStateChanged(ItemEvent e2) 
			{
				
				if(e2.getStateChange()==ItemEvent.SELECTED)
				{
					sDay=day[jcbDay.getSelectedIndex()];
					//System.out.println(sDay);
				
				//select DISTINCT `schedule`.`Theater` FROM `schedule`,`movie` where `movie`.`M_ID` = `schedule`.`M_ID` and `movie`.`Movie Name`='Aynabaji'
				
					//query to minimize theater result sets
					
					Query="select DISTINCT `schedule`.`Theater` FROM `schedule`,`movie` where `movie`.`Movie Name`='" + sMovie + "' and `schedule`.`Day`='" + sDay + "'";         
				
				
					try {
						Rs3=St3.executeQuery(Query);
						jcbTheater.removeAllItems();
						 
						jcbTheater.addItem("not selected");
						
						int i =1;
						
						
						while(Rs3.next())
						{
							jcbTheater.addItem(Rs3.getString("Theater"));
							theater[i]=Rs3.getString("Theater");
							i++;
							
							
						}
						
						
						
						
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.err.println(e.getMessage());
					}
				}
				
			}
			
			
			
			
		});
		
		
		jcbTheater.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					sTheater=theater[jcbTheater.getSelectedIndex()];
					//System.out.println(sTheater);
					
					
					//code to minimize show time
					
					
					Query="SELECT DISTINCT `showtime`.`Start Time` from `showtime` where `showtime`.`St_ID`not in(SELECT DISTINCT `St_ID` from `schedule` WHERE `schedule`.`Day`='"+sDay+"' and `schedule`.`Theater`='"+sTheater+"')";                   
					
					
					try {
						Rs4=St4.executeQuery(Query);
						jcbShowTime.removeAllItems();
						 
						jcbShowTime.addItem("not selected");
						
						int i =1;
						
						
						while(Rs4.next())
						{
							jcbShowTime.addItem(Rs4.getString("Start Time"));
							showtime[i]=Rs4.getString("Start Time");
							i++;
							
							
						
						}
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						System.err.println(e1.getMessage());
						//System.out.println("rs4 error");
					}
				}
				
			}
		});
		
		
		
		jcbShowTime.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					sShowTime=showtime[jcbShowTime.getSelectedIndex()];
					//System.out.println(sShowTime);
				}
				
			}
		});
		
	
	
	}
	
	
	
	
	
	
	
	private void addActionListen() 
	{
		//btn log in in admin addshow page
		
		btnAddShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String status = null;
				
				if(sMovie.equals("NOT SELECTED")||sDay.equals("NOT SELECTED")||sTheater.equals("NOT SELECTED")||sShowTime.equals("NOT SELECTED"))
				{
					
	                JOptionPane.showMessageDialog(null,"please provide all the info");
				}
				else
				{
					int dialogButton = 0;
					dialogButton = JOptionPane.showConfirmDialog (null, "adding this show\n"+sMovie+"\n"+sDay+"\n"+sTheater+"\n"+sShowTime+"\n"
							,"Warning",dialogButton);
					

	                if(dialogButton == JOptionPane.YES_OPTION)
	                {
	                	Query="SELECT `M_ID`,`Status` from movie where `Movie Name` = '"+sMovie+"'";
	                	String Query1="SELECT `St_ID` from showtime where `Start Time` = '"+sShowTime+"'";
	                	
	                	
	                	try {
							Rs3=St3.executeQuery(Query);
							Rs2=St2.executeQuery(Query1);
							while(Rs3.next())
							{
								movieId=Rs3.getString("M_ID");
								status = Rs3.getString("Status");
								//System.out.println(status);
							}
							
							while(Rs2.next())
							{
								showTimeId=Rs2.getString("St_ID");
							}
							
							Query = "INSERT INTO `schedule` (`Day`, `M_ID`, `Theater`, `St_ID`,`Ticket Count`) VALUES ('"+sDay+"', '"+movieId+"', '"+sTheater+"', '"+showTimeId+"','200')";
							St.executeUpdate(Query);
							
							Query1="UPDATE `movie` set `Status` = 'Running' where `M_ID`='"+movieId+"'";
							St4.executeUpdate(Query1);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
	                	
	                	JOptionPane.showMessageDialog(null,"SHOW ADDED");
	                	ResetJCB();
	                	MainFrame.getInstance().changeToAddShowPage();
	                }
				}
			}
		});
			
		btnBack.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int dialogButton = 0;
				dialogButton = JOptionPane.showConfirmDialog (null, "all your data on this page will be lost\nsure you want to continue?","Warning",dialogButton);
				
				if(dialogButton==JOptionPane.YES_OPTION)
				{	
					ResetJCB();
					MainFrame.getInstance().changeToAdminPage();
				}
			}
		});
		
		
	}
	

}

