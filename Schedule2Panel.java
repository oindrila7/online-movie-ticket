import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Schedule2Panel extends JPanel {
	
	private static Schedule2Panel uniqueInstance;
	
	private JLabel lblShowTime,lblTheater,lblTicketAmount,lblMovieDetails,lblDate,pMoviePoster,lblPrice;
	private JTextField txtPrice;
	
	private JComboBox<String > jcbTheater,jcbShowTime;
	private JComboBox<Integer> jcbTicketAmount;
	
	
	
	private String [] Theater = new String[100];
	private String [] ShowTime= new String[100];
	private int    [] Ticket= {0,1,2,3,4,5,6,7,8,9,10,111,121,333,444,444,555,66888,00,88,76};
	
	//database variable
	
	private String day,movie,details,posterLink;                               //to hold day and movie from previous page
	private JButton btnBack;
	
	private ConnectionClass db;
	private Statement St,St2,St3,St4,St5;
	private ResultSet Rs,Rs2,Rs3,Rs4,Rs5;
	private String Query;
	private String sTheater,sShowtime;
	private int sTicket;
	private int price;
	private int TicketCount=0 ;
	
	
	
	private Schedule2Panel() 
	{
		setSize(900,450);
		setBackground(new Color(102,205,170));
		setLayout(null);
		
		
		initialization();
		addComponent();
		addACtionListnerTo();
		//addItemListnerToJCB();
		
		
		
	}

	
	private void initialization()
	{
		
		
		
		//data base variable initialization
		db = new ConnectionClass();
		Rs=db.getRs();
		Rs2=db.getRs2();
		Rs3=db.getRs3();
		Rs4=db.getRs4();
		Rs5=db.getRs5();
		
		
		St= db.getSt();
		St2= db.getSt2();
		St3= db.getSt3();
		St4= db.getSt4();
		St5= db.getSt5();;
		
		
		btnBack = new JButton("BACK");
		
		lblDate = new JLabel(day);
		lblTheater=new JLabel("SELECT THEATER");
		lblShowTime=new JLabel("SELECT SHOWTIME");
		lblTicketAmount=new JLabel("SELECT AMOUNT");
		lblMovieDetails =new JLabel("thereeasdasdbsbasdoiasbdasugdysagdasijdasiudbsaydgasiudgvsadvsaiduvdusaidohfsuhiahubsuhhubahhuhabsahuhdvshaudaudshuahwdsa");
		lblPrice = new JLabel("TOTAL COST");
		txtPrice = new JTextField(20);
		
		
		jcbTheater = new JComboBox<String>(Theater);
		jcbShowTime= new JComboBox<String>(ShowTime);
		jcbTicketAmount= new JComboBox<Integer>();
		
		
		Theater[0]="not selected";
		ShowTime[0]="not selcted";
		Ticket[0]=0;
		
		sTheater="not selected";
		sShowtime="not selected";
		sTicket=0;
		
		
		
		//initalization o Ticket option as its totally fixed and max is 10 
		
		jcbTicketAmount.removeAllItems();
		for(int i=0;i<11;i++)
		{
			jcbTicketAmount.addItem(i);
			Ticket[i]=i;
		}
		
		
		pMoviePoster = new JLabel(new ImageIcon("G:/javacode/workspace/MovieTheaterManagementSystem/src/images/HomeBack.jpg"));
		
		
		
		
		
	}

	private void addComponent() 
	{
		lblDate.setBounds(500,20,100,50);
		
		pMoviePoster.setBounds(50,30,300,400);
		pMoviePoster.setBackground(Color.BLACK);
		
		
		
		lblTheater.setBounds(500,90,150,50);
		jcbTheater.setBounds(650,100,100,30);
		
		
		lblShowTime.setBounds(500,190,150,50);
		jcbShowTime.setBounds(650,200,100,30);
		
		
		lblTicketAmount.setBounds(500,290,150,50);
		jcbTicketAmount.setBounds(650,300,100,30);
		
		
		lblPrice.setBounds(500,390,150,50);
		txtPrice.setBounds(650,400,100,30);
		txtPrice.setEditable(false);
		
		lblMovieDetails.setBounds(100,500,300,50);
		
		btnBack.setBounds(700, 20, 150, 50);
		btnBack.setBackground(Color.RED);
		btnBack.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		add(btnBack);
		
		add(pMoviePoster);
		add(lblMovieDetails);
		
		add(lblDate);
		add(lblTheater);
		add(jcbTheater);
		
		add(lblShowTime);
		add(jcbShowTime);
		
		add(lblTicketAmount);
		add(jcbTicketAmount);
		
		add(lblPrice);
		add(txtPrice);
		
		
	//	addJCBTheater();
		
		jcbTicketAmount.removeAllItems();
		for(int i=0;i<11;i++)
		{
			jcbTicketAmount.addItem(i);
			Ticket[i]=i;
		}
		
		
		
		
	}
	
	
	public void ResetJCB()
	{
		Theater[0]="not selected";
		ShowTime[0]="not selected";
		Ticket[0]=0;
		
		jcbTheater.setSelectedIndex(0);
		jcbShowTime.setSelectedIndex(0);
		jcbTicketAmount.setSelectedIndex(0);
		
		
		
		sTheater=Theater[0];
		sShowtime=ShowTime[0];
		sTicket=0;
		
	}
	
	
	
	public void addJCBTheater()
	{
		//to add theater option
		
		Query="SELECT DISTINCT `schedule`.`Theater` from schedule,movie where `schedule`.`M_ID`=`movie`.`M_ID` and schedule.Day ='"+ day +"' and `movie`.`Movie Name`='"+ movie +"'";                   
	
		try {
			Rs=St.executeQuery(Query);
			jcbTheater.removeAllItems();
			jcbTheater.addItem("not selected");
			int i=1;
			
			while(Rs.next())
			{
				jcbTheater.addItem(Rs.getString("Theater"));
				Theater[i]= Rs.getString("Theater");
				i++;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
	
	}
	
	

	
	
	public void addItemListnerToJCB(){
		
		
		jcbTheater.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					sTheater=Theater[jcbTheater.getSelectedIndex()];
					//System.out.println(sTheater);
					
					jcbTicketAmount.setSelectedIndex(0);  //10:04
					
					//for show my 
					
					Query="SELECT DISTINCT `showtime`.`Start Time` from schedule,movie,showtime where `schedule`.`M_ID`=`movie`.`M_ID` and `showtime`.`St_ID`=`schedule`.`St_ID` and schedule.Day ='" + day + "' and `movie`.`Movie Name`='" +movie+ "'AND `schedule`.`Theater`='" +sTheater+ "'"   ;                             
				
					try {
						Rs2=St2.executeQuery(Query);
						jcbShowTime.removeAllItems();
						jcbShowTime.addItem("not Selected");
						int i=1;
						
						while(Rs2.next())
						{
							jcbShowTime.addItem(Rs2.getString("Start Time"));
							ShowTime[i]=Rs2.getString("Start Time");
							i++;
						}
						
						
						
						
					} catch (SQLException e1) {
						
						//e1.printStackTrace();
						System.err.println(e1.getMessage());
					}
					
				
				}
				
				
				
				
			}
		});
		
		
		//
		jcbShowTime.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				
				
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					sShowtime=ShowTime[jcbShowTime.getSelectedIndex()];
					//System.out.println(sShowtime);
					jcbTicketAmount.setSelectedIndex(0);     //10:04
					
					//sql for tikket
					
					Query="SELECT DISTINCT `schedule`.`Ticket Count`  from schedule,movie,showtime where `schedule`.`M_ID`=`movie`.`M_ID` and `showtime`.`St_ID`=`schedule`.`St_ID` and schedule.Day ='" +day+ "' and `movie`.`Movie Name`='" + movie + "'AND `schedule`.`Theater`='" +sTheater+ "' and `showtime`.`Start Time`='" +sShowtime+ "'";                                           
					
					try {
						Rs3=St3.executeQuery(Query);
						
						
						while(Rs3.next())
						{
							TicketCount= Rs3.getInt("Ticket Count");	
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
					if(TicketCount<11)
					{
						JOptionPane.showMessageDialog(null, "not enough ticket");
					}
					else 
					{
						sTicket=Ticket[jcbTicketAmount.getSelectedIndex()];
					
					
						//System.out.println(sTicket);
						price = 300* sTicket;
						txtPrice.setText(String.valueOf(price));
						//System.out.println(price);
					}
				
				}
			}
		});
		
		
		
		
	}
	
	
	
		private void addACtionListnerTo() {
		
			MainFrame.getInstance().getSchedule2Page().getBtnBuy().addActionListener(new ActionListener() 
			{
			
				@Override
				public void actionPerformed(ActionEvent e) 
				{
				
					confirmPanel.getInstance().receiveValue();
					confirmPanel.getInstance().setValues();
				
					if(MainFrame.getInstance().userLoginStatus==false)
					{
						if(sTheater.equals("not selected")||sShowtime.equals("not selected")||sTicket==0) 
						{
							JOptionPane.showMessageDialog(null, "please select all the option");
						}
						else
						{
							MainFrame.getInstance().userLoginFromLoginPage = true; //8:24
							MainFrame.getInstance().changeToUserLogin();
							ResetJCB();
						}
						
					}
					else
					{
						if(sTheater.equals("not selected")||sShowtime.equals("not selected")||sTicket==0) 
						{
							JOptionPane.showMessageDialog(null, "please select all the option");
						}
						else
						{
							MainFrame.getInstance().changeToConfirmSelectedPage();
							ResetJCB();
						}
					}
				}
		   });
		
		
		 btnBack.addActionListener(new ActionListener() 
		 {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int dialogButton = 0;
				dialogButton = JOptionPane.showConfirmDialog (null, "you unsaved info will be lost,continue ","Warning",dialogButton);
				

                if(dialogButton == JOptionPane.YES_OPTION)
                {
                	MainFrame.getInstance().changeToSchedule();
                	ResetJCB();
                }
			}
		});
		
	}

	
	public void setValues(){
		
		DayMovieSelectionPanel d = DayMovieSelectionPanel.getInstance();
		
		//caatching form previous page
		day = d.getDay();
		movie=d.getMovie();
		
		Query = "SELECT * from movie where `Movie Name` = '"+movie+"'";
		try 
		{
			
			Rs=St.executeQuery(Query);
			int i =0;
			
			while(Rs.next())
			{
				posterLink = Rs.getString("Image Link");
				details = Rs.getString("Description");
				i++;
				
			}
		}catch (SQLException e12) {
				
			//	e12.printStackTrace();
				System.err.println("Got an exception!");
			    System.err.println(e12.getMessage());
			}
		
		lblMovieDetails.setText(details);
		pMoviePoster.setIcon(new ImageIcon(getClass().getClassLoader().getResource(posterLink)));
		
		
		
		
	}
	
	public String getDay() 
	{
		return day;	
	}
	public String getMovie() 
	{
		return movie;	
	}
	public String getTheater() 
	{
		return sTheater;	
	}
	public String getShowTime() 
	{
		return sShowtime;	
	}
	public int getTicket() 
	{
		return sTicket;	
	}
	public int getPrice() 
	{
		return price;	
	}
	public int getTicketAvailable() 
	{
		return TicketCount;	
	}
	
	
	public static Schedule2Panel getInstance()
	{
		if(uniqueInstance==null)
		{
			uniqueInstance = new Schedule2Panel();
		}
		return uniqueInstance;
			
	}
	
	

}

