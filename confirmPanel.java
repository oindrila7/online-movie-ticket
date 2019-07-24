import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class confirmPanel extends JPanel
{	
	private static confirmPanel uniqueInstance;
	
	private JLabel lblMovieName,lblDay,lblTheater,lblShowTime,lblTicketAmount,lblCost,lblPageHeader;
	private JLabel lblSelectedMovieName,lblSelectedDay,lblSelectedTheater,lblSelectedShowTime,lblSelectedTicketAmount,lblSelectedCost;		JLabel lblMoviePoster;
	private JButton  btnConfirmPurchase, btnBack;	     //variable		
			
	private String movieName,day,theater,showTime,ticketAmount,cost,movieId,showTimeId,posterLink;
	private int ticketAvailable;
	
	private ResultSet Rs,Rs2,Rs3,Rs4,Rs5;
	private Statement St,St2,St3,St4,St5;
	private ConnectionClass db;
	private String Query;		
			
	private confirmPanel() 
	{
		setSize(1166,500);
		setLayout(null);
		setBackground(new Color(230,90,100));
		//receiveValue();		
				
		initialization();
		addComponent();
		//receiveValue();
		//setValues();
		addActionListnerToButton();     //to constructor
		
				
	}



	public void setValues() {
		
		lblSelectedMovieName.setText(movieName);
		lblSelectedDay.setText(day);
		lblSelectedTheater.setText(theater);
		lblSelectedShowTime.setText(showTime);
		lblSelectedTicketAmount.setText(ticketAmount);
		lblSelectedCost.setText(cost);
		super.repaint();
		
		
		Query = "SELECT * from movie where `Movie Name` = '"+movieName+"'";
		try 
		{
			
			Rs=St.executeQuery(Query);
			int i =0;
			
			while(Rs.next())
			{
				posterLink = Rs.getString("Image Link");
				i++;
			}
		}catch (SQLException e12) {
				
			//	e12.printStackTrace();
				System.err.println("Got an exception!");
			    System.err.println(e12.getMessage());
			}
		
		lblMoviePoster.setIcon(new ImageIcon(getClass().getClassLoader().getResource(posterLink)));
		
		
		
	}



	public void receiveValue() {
		// TODO Auto-generated method stub
		Schedule2Panel schedule2Panel = Schedule2Panel.getInstance();
		ticketAvailable = schedule2Panel.getTicketAvailable();
		movieName=schedule2Panel.getMovie();
		day=schedule2Panel.getDay();
		theater=schedule2Panel.getTheater();
		showTime=schedule2Panel.getShowTime();						//fetching value form schedule page
		ticketAmount=String.valueOf(schedule2Panel.getTicket());
		cost=String.valueOf(schedule2Panel.getPrice());
		
		
		
	}



	public void initialization() 
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
				
		lblPageHeader =new JLabel("CONFIRM PURCHASE PAGE");
		lblMoviePoster=new JLabel();
				
				
				
		lblMovieName= new JLabel("MOVIE NAME");
		lblDay = new JLabel("DAY");
		lblTheater= new JLabel("THEATER");
		lblShowTime= new JLabel("SHOWTIME");
		lblTicketAmount= new JLabel("TICKET AMOUNT");
		lblCost= new JLabel("TOTAL COST");
				
				
				
		btnConfirmPurchase = new JButton("CONFIRM BUY");
		btnBack = new JButton("BACK");     //init method		
				
				
		lblSelectedMovieName = new JLabel();
		lblSelectedDay = new JLabel();
		lblSelectedTheater= new JLabel();
		lblSelectedShowTime= new JLabel();
		lblSelectedTicketAmount= new JLabel();
		lblSelectedCost= new JLabel();
				
				
				
	}



	public void addComponent() 
	{
		lblPageHeader.setBounds(600,15,350,60);
		lblPageHeader.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		lblMoviePoster.setBounds(50,45 ,300, 350);
				
		lblMovieName.setBounds(550,100,100,30);
		lblMovieName.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		lblSelectedMovieName.setBounds(750,100,270,30);
				
				
				
		lblDay.setBounds(550,150,100,30);
		lblDay.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		lblSelectedDay.setBounds(750,150,100,30);
				
				
		lblTheater.setBounds(550,200,100,30);
		
		lblTheater.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		lblSelectedTheater.setBounds(750,200,100,30);
				
				
		lblShowTime.setBounds(550,250,100,30);
		lblShowTime.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		lblSelectedShowTime.setBounds(750,250,100,30);
				
				
		lblTicketAmount.setBounds(550,300,100,30);
		lblTicketAmount.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		lblSelectedTicketAmount.setBounds(750,300,100,30);
				
		lblCost.setBounds(550,350,100,30);
		lblCost.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		lblSelectedCost.setBounds(750,350,100,30);
		
		btnBack.setBounds(1000, 20, 150, 50);
		btnBack.setBackground(new Color(230,230,250));
		btnBack.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		
		btnConfirmPurchase.setBounds(650,420,150,50);
		btnConfirmPurchase.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		btnConfirmPurchase.setBackground(new Color(230,230,250));//addcomponent

		add(btnConfirmPurchase); 							//addcomponent
		add(btnBack);
				
				
		add(lblPageHeader);
		add(lblMoviePoster);
				
		add(lblMovieName);
		add(lblSelectedMovieName);
		
		add(lblDay);
		add(lblSelectedDay);
				
				
		add(lblTheater);
		add(lblSelectedTheater);
				
		add(lblShowTime);
		add(lblSelectedShowTime);
				
		add(lblTicketAmount);
		add(lblSelectedTicketAmount);
				
				
		add(lblCost);
		add(lblSelectedCost);

				
	}
	
	private void addActionListnerToButton() 
	{
		btnConfirmPurchase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int dialogButton = 0;
				dialogButton = JOptionPane.showConfirmDialog (null, "Confirm your purchase?","Warning",dialogButton);
				String mail="";

                if(dialogButton == JOptionPane.YES_OPTION)
                {
                	//run sql
                	String userName = MainFrame.getInstance().getUserName();
                	//System.out.println(userName);
                	Query="SELECT `M_ID` from movie where `Movie Name` = '"+movieName+"'";
                	String Query1="SELECT `St_ID` from showtime where `Start Time` = '"+showTime+"'";
                	String Query2 = "Select `Mail` from `customer` where username = '"+userName+"'";
                	
                	try {
						Rs3=St3.executeQuery(Query);
						Rs2=St2.executeQuery(Query1);
						Rs4=St4.executeQuery(Query2);
						while(Rs3.next())
						{
							movieId=Rs3.getString("M_ID");
						}
						
						while(Rs2.next())
						{
							showTimeId=Rs2.getString("St_ID");
						}
						
						while(Rs4.next())
						{
							mail =Rs4.getString("Mail");
						}
						
						
						int updateTicket = ticketAvailable -Integer.valueOf(ticketAmount) ;
						
						Query = "UPDATE `schedule` set `Ticket Count` = '"+updateTicket+"' where `Day`='"+day+"'and `M_ID`='"+movieId+"' and `Theater`= '"+theater+"' and `St_ID`= '"+showTimeId+"'";
						
						
						St.executeUpdate(Query);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                	Random rand = new Random();
                	int reference = 1000+rand.nextInt(9999);
                	
                	JOptionPane.showMessageDialog(null, "Ticket Booked, Your Reference No "+reference+" and Counter no : '2' \nMail with bKash Number is sent to "+mail+"\nPlease pay before 24 hrs to confirm purchase.");
                	MainFrame.getInstance().changeToHome();
                }
                else if(dialogButton== JOptionPane.NO_OPTION)
                {
                	//stay on page
                }
               
				
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				MainFrame.getInstance().changeToSchedule2Page();
			}
		});
		
	}
	
	
	
	public static confirmPanel getInstance()
	{
		if(uniqueInstance==null)
		{
			uniqueInstance = new confirmPanel();
		}
		return uniqueInstance;
			
	}
		
}


