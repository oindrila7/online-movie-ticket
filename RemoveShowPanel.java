
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

public class RemoveShowPanel extends JPanel
{
	
	//panel element variable
	
	private JLabel lblAddShow;
	private JLabel lblMovie,lblDay,lblTheater,lblShowTime;
	private JComboBox<String> jcbMovie,jcbDay,jcbTheater,jcbShowTime;
	
	
	private JButton btnRemoveShow,btnBack,btnRemoveAllShow,btnResetShow;
	
	//database variables
	
	private ResultSet Rs,Rs2,Rs3,Rs4,Rs5;
	private Statement St,St2,St3,St4,St5;
	private ConnectionClass db;
	private String Query;
	
	
	//ARRAY
	
	private String [] day= new String[100] ;
	private String [] movie= new String [100];
	private String [] theater=new String[100];
	private String [] showtime = new String[100];
	private String sMovie,sDay,sTheater,sShowTime,movieId,showTimeId;
	
	
	RemoveShowPanel()
	{
		setSize(900,550);
		setLayout(null);
		setBackground(new Color(175,238,238));
		initialization();
		addComponent();
		addActionListner();
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
		
		
		
		
		//panel element
		lblAddShow = new JLabel("Remove/Reset Show Page");
		
		
		lblMovie= new JLabel("SELECT MOVIE");
		lblDay = new JLabel("SELECT DATE");
		lblTheater= new JLabel("SELECT THEATER");
		lblShowTime= new JLabel("SELECT SHOWTIME");
		
		
		jcbMovie = new JComboBox<String>();
		jcbDay =new JComboBox<String>();
		jcbTheater=new JComboBox<String>();
		jcbShowTime=new JComboBox<String>();
		
		
		
		
		
		btnRemoveShow = new JButton("REMOVE");
		btnRemoveAllShow = new JButton("REMOVE ALL SHOW");
		btnResetShow = new JButton("RESET");
		btnBack= new JButton("BACK");
		
		
		day[0]="NOT SELECTED";
		movie[0]="NOT SELECTED";
		showtime[0]="NOT SELECTED";
		theater[0]="NOT SELECTED";
		
	     
		
			
		
	}
	
	
	
	private void addComponent() 
	{
		lblAddShow.setBounds(200,50,400,40);
		lblAddShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
		
		
		lblMovie.setBounds(200,100,150,30);
		lblMovie.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbMovie.setBounds(550,100,150,30);
		
		
		
		lblDay.setBounds(200,240,150,30);
		lblDay.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbDay.setBounds(550,240,150,30);
		
		
		lblTheater.setBounds(200,310,150,30);
		lblTheater.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbTheater.setBounds(550,310,150,30);
		
		
		lblShowTime.setBounds(200,380,150,30);
		lblShowTime.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbShowTime.setBounds(550,380,150,30);
		
		
		
		
		
		btnBack.setBounds(700, 20, 150, 50);
		btnBack.setBackground(Color.RED);
		btnBack.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		btnRemoveAllShow.setBounds(275, 170, 250, 40);
		btnRemoveAllShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		btnRemoveShow.setBounds(275, 480, 150, 40);
		btnRemoveShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnRemoveShow.setBackground(new Color(205,92,100));
		
		btnResetShow.setBounds(440, 480, 150, 40);
		btnResetShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnResetShow.setBackground(new Color(205,92,100));
		
		
		
		add(lblAddShow);
		
		add(lblMovie);
		add(jcbMovie);
		
		add(lblDay);
		add(jcbDay);
		
		add(lblTheater);
		add(jcbTheater);
		
		add(lblShowTime);
		add(jcbShowTime);
		
        add(btnRemoveAllShow);
		add(btnResetShow);
		add(btnBack);
		add(btnRemoveShow);
		
		
	}
	
	public JButton getBtnRemoveShow()
	{
		return btnRemoveShow;
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
		jcbShowTime.setSelectedIndex(0);
	}
	
	public void initializeMovieName() 
	{
		
		Query = "SELECT DISTINCT `movie`.`Movie Name` from schedule,movie where schedule.M_ID=movie.M_ID and movie.Status = 'Running'";
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
					
					Query="SELECT DISTINCT `schedule`.`Day` FROM `schedule`,`movie` WHERE `schedule`.`M_ID`=`movie`.`M_ID` and `movie`.`Movie Name`='"+sMovie+"'";                     
					
					
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
					
					Query="select DISTINCT `schedule`.`Theater` FROM `schedule`,`movie` where `movie`.`M_ID` = `schedule`.`M_ID` and `movie`.`Movie Name`='" + sMovie + "' and `schedule`.`Day`='" + sDay + "'";         
				
				
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
		
		
		jcbTheater.addItemListener(new ItemListener() 
		{
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					sTheater=theater[jcbTheater.getSelectedIndex()];
					//System.out.println(sTheater);
					
					
					//code to minimize show time
					
					
					//select `showtime`.`Start Time` FROM `schedule`,`showtime` ,`movie`
					//where `showtime`.`St_ID` = `schedule`.`St_ID`  and `schedule`.`M_ID`=movie.M_ID
					//and `movie`.`Movie Name`='Aynabaji' and `schedule`.`Day`='Monday' and schedule.Theater='Ecstasy'
					Query="select `showtime`.`Start Time` FROM `schedule`,`showtime` ,`movie` where `showtime`.`St_ID` = `schedule`.`St_ID`  and `schedule`.`M_ID`=movie.M_ID and `movie`.`Movie Name`='"+sMovie +"' and `schedule`.`Day`='"+sDay +"' and schedule.Theater='"+sTheater+"'";                   
					
					
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
						System.out.println("rs4 error");
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
	
	
	
	
	public void addActionListner()
	{
		
		//code for back to main admin page
		btnRemoveShow.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{

				if(sMovie.equals("NOT SELECTED")||sDay.equals("NOT SELECTED")||sTheater.equals("NOT SELECTED")||sShowTime.equals("NOT SELECTED"))
				{
					
	                JOptionPane.showMessageDialog(null,"please provide all the info");
				}
				else
				{
					int dialogButton = 0;
					dialogButton = JOptionPane.showConfirmDialog (null, "Removing this show\n"+sMovie+"\n"+sDay+"\n"+sTheater+"\n"+sShowTime+"\n"
							,"Warning",dialogButton);
					
					if(dialogButton==JOptionPane.YES_OPTION)
					{
						
						Query="SELECT `M_ID` from movie where `Movie Name` = '"+sMovie+"'";
	                	String Query1="SELECT `St_ID` from showtime where `Start Time` = '"+sShowTime+"'";
	                	
	                	
	                	try {
							Rs3=St3.executeQuery(Query);
							Rs2=St2.executeQuery(Query1);
							while(Rs3.next())
							{
								movieId=Rs3.getString("M_ID");
							}
							
							while(Rs2.next())
							{
								showTimeId=Rs2.getString("St_ID");
							}
							
							Query = "DELETE from `schedule` where `Day`='"+sDay+"'and `M_ID`='"+movieId+"' and `Theater`= '"+sTheater+"' and `St_ID`= '"+showTimeId+"'";
							
							
							St.executeUpdate(Query);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						
						
						JOptionPane.showMessageDialog(null, "SHOW HAS BEEN REMOVED");
						initializeMovieName();
						ResetJCB();
						MainFrame.getInstance().changeToRemoveShowPage();
					}
				}
			}
		});
			
		btnRemoveAllShow.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{

				if(sMovie.equals("NOT SELECTED"))
				{
					
	                JOptionPane.showMessageDialog(null,"please provide movie name");
				}
				else
				{
					int dialogButton = 0;
					dialogButton = JOptionPane.showConfirmDialog (null, "Removing All show for  "+sMovie+"\t"
							,"Warning",dialogButton);
					
					if(dialogButton==JOptionPane.YES_OPTION)
					{
						
						Query="SELECT `M_ID` from movie where `Movie Name` = '"+sMovie+"'";
	                	
	                	try {
							Rs3=St3.executeQuery(Query);
							while(Rs3.next())
							{
								movieId=Rs3.getString("M_ID");
							}
							
							Query = "DELETE from `schedule` where `M_ID`='"+movieId+"'";
							String Query1="UPDATE `movie` set `Status` = 'Archived' where `M_ID`='"+movieId+"'";
							
							St.executeUpdate(Query);
							St2.executeUpdate(Query1);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						
						
						JOptionPane.showMessageDialog(null, "ALL SHOW HAS BEEN REMOVED");
						initializeMovieName();
						ResetJCB();
					}
				}
			}
		});
		
		
		btnResetShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(sMovie.equals("NOT SELECTED")||sDay.equals("NOT SELECTED")||sTheater.equals("NOT SELECTED")||sShowTime.equals("NOT SELECTED"))
				{
					
	                JOptionPane.showMessageDialog(null,"please provide all the info");
				}
				
				else{
				
				int dialogButton = 0;
				dialogButton = JOptionPane.showConfirmDialog (null, "Are you sure to Reset this Show?","Warning",dialogButton);
				if(dialogButton==JOptionPane.YES_OPTION)
				{	
					
					String userName = MainFrame.getInstance().getAdminID();
					
					Query="SELECT `M_ID` from movie where `Movie Name` = '"+sMovie+"'";
                	String Query1="SELECT `St_ID` from showtime where `Start Time` = '"+sShowTime+"'";
                	
                	try {
						Rs3=St3.executeQuery(Query);
						Rs2=St2.executeQuery(Query1);
						while(Rs3.next())
						{
							movieId=Rs3.getString("M_ID");
						}
						
						while(Rs2.next())
						{
							showTimeId=Rs2.getString("St_ID");
						}
						
						Query = "UPDATE `schedule` set `Ticket Count` = '200' where `Day`='"+sDay+"'and `M_ID`='"+movieId+"' and `Theater`= '"+sTheater+"' and `St_ID`= '"+showTimeId+"'";
						
						
						St.executeUpdate(Query);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ResetJCB();
				}
			}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int dialogButton = 0;
				dialogButton = JOptionPane.showConfirmDialog (null, "All your unsaved data will be lost. Procced?","Warning",dialogButton);
				if(dialogButton==JOptionPane.YES_OPTION)
				{	
					MainFrame.getInstance().changeToAdminPage();
					ResetJCB();
				}
			}
		});
		
		
	}
	
	
	
	
	
	

}


