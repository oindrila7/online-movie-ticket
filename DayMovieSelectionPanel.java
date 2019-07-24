

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.org.apache.xerces.internal.dom.PSVIElementNSImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DayMovieSelectionPanel extends JLabel{
	
	private static DayMovieSelectionPanel uniqueInstance;
	
	private JComboBox<String> jcbDay;
	private JComboBox<String> jcbMovie;
	private JButton btnShowSchedule;
	
	private JLabel lblDate,lblMovieName;
	
	
	
	
	
	private ConnectionClass db ;						// to connect database
	
	
	private String Query;
	private String sDay,sMovie;   							//sql holder
	
	
	private ResultSet Rs,Rs2,Rs3;
	private Statement St,St2,St3;
	
	private String[] day= new String[100];
	private String[] movie=new String[100] ;
	
	DayMovieSelectionPanel()
	{
		//setSize(500,300);
		setBounds(200,190,966,400);
		setBackground(Color.cyan);
		setLayout(null);
		setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/batman-v-superman-dawn-of-just (Custom).jpg"))); 

		
        //connection build
		
		db = new ConnectionClass();
		St= db.getSt();
		St2=db.getSt2();
		St3=db.getSt3();
		
		
		Rs= db.getRs();
		Rs2=db.getRs2();
		Rs3=db.getRs3();
		//methods
		
		initialization();
		addComponent();
		addItemListnerToJCB();
		addActionListerToButton();
		
		
	}

	
	private void initialization() 
	{
		day[0]="not selected";
		movie[0]="not selected";
		
		jcbDay= new JComboBox<String>(day);
		jcbMovie= new JComboBox<String>();
		
		btnShowSchedule = new JButton("Show Schedule"); 
		
		lblDate= new JLabel("SELECT A DAY");
		lblMovieName= new JLabel("SELECT MOVIE");
		
		
		sDay=day[0];
		sMovie=movie[0];
		

		
	}

	private void addComponent() 
	{
		
		lblDate.setBounds(250,120,240,40);
		jcbDay.setBounds(600,120,100,40);
		jcbDay.setBackground(new Color(255,228,181));
		
		lblMovieName.setBounds(250,220,240,40);
		jcbMovie.setBounds(600,220,100,40);
		jcbMovie.setBackground(new Color(255,228,181));
		
		lblDate.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 30));
		lblDate.setForeground(new Color(65,105,225));
		
		
		lblMovieName.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 30));
		lblMovieName.setForeground(new Color(65,105,225));
		
		btnShowSchedule.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 18));
		btnShowSchedule.setBounds(400, 300, 170, 50);
		btnShowSchedule.setBackground(new Color(0,191,255));
		
		
		add(lblDate);
		add(jcbDay);
		
		add(lblMovieName);
		add(jcbMovie);
		
		add(btnShowSchedule);
		
		
		addDayToJCBDay();
		
	}
	
	public void addDayToJCBDay() 
	{
		Query="SELECT DISTINCT Day from schedule";
		
		try {
			Rs=St.executeQuery(Query);
			
			jcbDay.removeAllItems();
			jcbDay.addItem("not selected");
			int i=1;
			while(Rs.next())
			{
				jcbDay.addItem(Rs.getString("Day"));
				day[i]=Rs.getString("Day");
				i++;
			}
			
			
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		
		
		
	}


	public JButton getBtnShowSchedule()
	{
		return btnShowSchedule;
	}
	
	
	public void addActionListerToButton() {
		
		
		btnShowSchedule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(sDay.equals("not selected")||sMovie.equals("not selected"))
				{
					JOptionPane.showMessageDialog(null, "please select all the option");
				}
				else
				{
					Schedule2Panel pSchedule = Schedule2Panel.getInstance();
					pSchedule.setBounds(233,180,900,450);
					pSchedule.setValues();
					pSchedule.addJCBTheater();
					pSchedule.addItemListnerToJCB();
					MainFrame.getInstance().getSchedule2Page().add(pSchedule);
					MainFrame.getInstance().changeToSchedule2Page();
					ResetJCB();
				}
			}
		});
		
		
	}
	
	public void addItemListnerToJCB()
	{
		//item listner to day slection box
		
		jcbDay.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) 
			{
				if(event.getStateChange()==ItemEvent.SELECTED)
				{
					sDay=day[jcbDay.getSelectedIndex()];
				//	System.out.println(sDay);
					
					
					//sqp for movie option selection minimize
					
					
					Query="SELECT DISTINCT `movie`.`Movie Name` from `movie`,`schedule` WHERE `movie`.`M_ID`=`schedule`.`M_ID` and `schedule`.`Day`='" +sDay+"'";
				
					try {
						Rs2=St2.executeQuery(Query);
						jcbMovie.removeAllItems();
						jcbMovie.addItem("not Selected");
						int i =1;
						while(Rs2.next())
						{
							jcbMovie.addItem(Rs2.getString("Movie Name"));
							movie[i]=Rs2.getString("Movie Name");
							i++;
						}
						
						
						
					} catch (SQLException e) {
						
						//e.printStackTrace();
						System.err.println(e.getMessage());
					}
					
				
				
				
				
				
				}
				
				
			}
		});
		
		
		//itemlistner to movie selection box
		
		jcbMovie.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange()== ItemEvent.SELECTED)
				{
					sMovie=movie[jcbMovie.getSelectedIndex()];
					//System.out.println(sMovie);
				}
				
			}
		});
		
		
		
		
		
		
		
	}
	
	
	
	
	public String getDay()
	{
		return sDay;
	}
	
	public String getMovie()
	{
		return sMovie;
	}
	
	public void ResetJCB()
	{
		jcbMovie.setSelectedIndex(0);
		jcbDay.setSelectedIndex(0);
		sDay=day[0];
		sMovie=day[0];
		
	}
	
	
	public static DayMovieSelectionPanel getInstance()
	{
		if(uniqueInstance==null)
		{
			uniqueInstance = new DayMovieSelectionPanel();
		}
		return uniqueInstance;
			
	}
	

}

