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
import javax.swing.JTextField;

import jdk.nashorn.internal.scripts.JO;

public class TicketSellngPanel extends JPanel
{
	private JLabel lblEmployeePage;
	private JLabel lblMovie,lblDay,lblTheater,lblShowTime,lblQuantity,lblPrice;
	private JTextField txtPrice;
	private JComboBox<String> jcbMovie,jcbDay,jcbTheater,jcbShowTime;
	private JComboBox<Integer> jcbTicketAmount;
	private JComboBox<Integer> jcbQuantity;
	
	private JButton btnConfirm,btnLogOut;
	
	//database variables
	
	private ResultSet Rs,Rs2,Rs3,Rs4,Rs5;
	private Statement St,St2,St3,St4,St5;
	private ConnectionClass db;
	private String Query;
	private int  price;
	
	
	//ARRAY
	
	private String [] day= new String[100] ;
	private String [] movie= new String [100];
	private String [] theater=new String[100];
	private String [] showtime = new String[100];
	private int    [] Ticket ={0,1,2,3,4,5,6,7,8,9,10,111,121,333,444,444,555,66888,00,88,76};
	
	private String sMovie,sDay,sTheater,sShowTime,movieId,showTimeId;
	private int sTicket;
	private int TicketCount=0 ;
	
	
	TicketSellngPanel()
	{
		setSize(900,550);
		setLayout(null);
		setBackground(new Color(255,99,71));
		initialization();
		addComponent();
		
		addAcitonListner();
		
		addItemListnerToJCB();
		
	}

	

	private void initialization() 
	{
		
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
		
		day[0]="not selected";
		movie[0]="not selected";
		showtime[0]="not selected";
		theater[0]="not selected";
		Ticket[0]=0;
		
		lblEmployeePage = new JLabel("TICKET SELLING PAGE");
		
		
		lblMovie= new JLabel("SELECT MOVIE");
		lblDay = new JLabel("SELECT DAY");
		lblTheater= new JLabel("SELECT THEATER");
		lblShowTime= new JLabel("SELECT SHOWTIME");
		lblQuantity= new JLabel("SELECT TICKET");
		lblPrice = new JLabel("TOTAL COST");
		txtPrice = new JTextField(20);
		
		jcbMovie = new JComboBox<String>(movie);
		jcbDay =new JComboBox<String>(day);
		jcbTheater=new JComboBox<String>(theater);
		jcbShowTime=new JComboBox<String>(showtime);
		
		
		jcbTicketAmount=new JComboBox<Integer>();
		
		
		btnConfirm = new JButton("CONFIRN");
		btnLogOut= new JButton("LOG OUT");
		
		
		day[0]="not selected";
		movie[0]="not selected";
		showtime[0]="not selected";
		theater[0]="not selected";
		Ticket[0]=0;
		
		sDay=day[0];
		sMovie=movie[0];
		sTheater=theater[0];
		sShowTime=showtime[0];
		
		
		
		
		//initalization o Ticket option as its totally fixed and max is 10 
		
		jcbTicketAmount.removeAllItems();
		Ticket[0]=0;
		for(int i=1;i<11;i++)
		{
			jcbTicketAmount.addItem(i);
			Ticket[i]=i;
		}
			
		
	}
	
	
	private void addComponent() 
	{
		lblEmployeePage.setBounds(375,40,250,40);
		lblEmployeePage.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
		
		
		lblMovie.setBounds(200,90,150,30);
		jcbMovie.setBounds(550,90,150,30);
		
		
		
		lblDay.setBounds(200,160,150,30);
		jcbDay.setBounds(550,160,150,30);
		
		
		lblTheater.setBounds(200,230,150,30);
		jcbTheater.setBounds(550,230,150,30);
		
		
		lblShowTime.setBounds(200,300,150,30);
		jcbShowTime.setBounds(550,300,150,30);
		
		
		lblQuantity.setBounds(200,370,150,30);
		jcbTicketAmount.setBounds(550,370,150,30);
		
		lblPrice.setBounds(200,440,150,30);
		txtPrice.setBounds(550,440,150,30);
		txtPrice.setEditable(false);
		
		btnLogOut.setBounds(700, 20, 150, 50);
		btnLogOut.setBackground(new Color(175,238,238));
		btnLogOut.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		
		btnConfirm.setBounds(375, 480, 150, 40);
		btnConfirm.setBackground(new Color(175,238,238));
		btnConfirm.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		
		
		
		add(lblEmployeePage);
		
		add(lblMovie);
		add(jcbMovie);
		
		add(lblDay);
		add(jcbDay);
		
		add(lblTheater);
		add(jcbTheater);
		
		add(lblShowTime);
		add(jcbShowTime);
		
		add(lblQuantity);
		add(jcbTicketAmount);
		
		add(lblPrice);
		add(txtPrice);
		
		add(btnLogOut);
		add(btnConfirm);
		
		initializeMovieName();
		
		
		jcbTicketAmount.removeAllItems();
		for(int i=0;i<11;i++)
		{
			jcbTicketAmount.addItem(i);
			Ticket[i]=i;
		}
		
	}
	
	
	public JButton getBtnLogOut()
	{
		return btnLogOut;
	}
	
	public JButton getBtnConfirm()
	{
		return btnConfirm;
	}
	
	
	public void ResetJCB()
	{
		try {
			jcbMovie.setSelectedIndex(0);
			jcbDay.setSelectedIndex(0);
			jcbTheater.setSelectedIndex(0);
			jcbShowTime.setSelectedIndex(0);
			jcbTicketAmount.setSelectedIndex(0);
			
			sDay=day[0];
			sMovie=movie[0];
			sTheater=theater[0];
			sShowTime=showtime[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			 System.err.println(e.getMessage());
		}
		
	}
	
	
	
	
	
	public void initializeMovieName() 
	{
		
		Query = "SELECT DISTINCT `movie`.`Movie Name` from schedule,movie where schedule.M_ID=movie.M_ID";
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
		
		
		jcbTheater.addItemListener(new ItemListener() {
			
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
					
					//sql for tikket
					
					Query="SELECT DISTINCT `schedule`.`Ticket Count`  from schedule,movie,showtime where `schedule`.`M_ID`=`movie`.`M_ID` and `showtime`.`St_ID`=`schedule`.`St_ID` and schedule.Day ='" +sDay+ "' and `movie`.`Movie Name`='" + sMovie + "'AND `schedule`.`Theater`='" +sTheater+ "' and `showtime`.`Start Time`='" +sShowTime+ "'";                                           
					
					try {
						Rs5=St5.executeQuery(Query);
						
						
						while(Rs5.next())
						{
							TicketCount= Rs5.getInt("Ticket Count");	
						}
						
						
				      }	
					catch (SQLException e1) 
					{
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						System.err.println(e1.getMessage());
					}
					
					
					
				}
				
			}
		});
		
		
		jcbTicketAmount.addItemListener(new ItemListener() 
		{
			
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					if(TicketCount==0)
					{
						JOptionPane.showMessageDialog(null, "show house full");
					}
					else 
					{
						sTicket=Ticket[jcbTicketAmount.getSelectedIndex()];
					
						if(TicketCount<sTicket)
						{
							JOptionPane.showMessageDialog(null, "not enough ticket");
							jcbTicketAmount.setSelectedIndex(0);
						}
						else
						{	
													
							price= 300* sTicket;
							txtPrice.setText(String.valueOf(price));
							
						}
						
					}
				
				}
			}
		});
		
	
	
	}
	
	
	
	
	
	
	
	
	
	
	public void addAcitonListner()
	{
		//log out
		btnLogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainFrame.getInstance().changeToHome();
				ResetJCB();
			}
		});
		
		
		
		//selling ticket code
		
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(sMovie.equals("not selected")||sDay.equals("not selected")||sTheater.equals("not selected")||sShowTime.equals("not selected")||sTicket==0)
				{
					JOptionPane.showMessageDialog(null, "please porvide all the info");
					
				}
				else
				{
					int dialogButton = 0;
					dialogButton = JOptionPane.showConfirmDialog (null, "selling ticket for\nmovie name: "+sMovie+"\nshow time: "+sShowTime+"\nticket :"+sTicket+"\ncost :"+price,"Warning",dialogButton);
					

	                if(dialogButton == JOptionPane.YES_OPTION)
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
							
							
							int updateTicket = TicketCount-sTicket;
							
							Query = "UPDATE `schedule` set `Ticket Count` = '"+updateTicket+"' where `Day`='"+sDay+"'and `M_ID`='"+movieId+"' and `Theater`= '"+sTheater+"' and `St_ID`= '"+showTimeId+"'";
							
							
							St.executeUpdate(Query);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                	
	                	JOptionPane.showMessageDialog(null, "ticket sold");
	                	ResetJCB();
	                }
				}
				
				
			}
		});
		
	}
	
	
	
	

}

