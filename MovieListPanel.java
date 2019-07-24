

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieListPanel extends JPanel
{
	private static MovieListPanel uniqueInstance;
	private MovieTable table;
	private JComboBox<String> jcbMovie;
	private JButton btnShowMOvieDetails; 
	private JLabel lblMovie;
	
	
	@SuppressWarnings("unused")
	private ResultSet Rs,Rs2,Rs3,Rs4,Rs5;
	@SuppressWarnings("unused")
	private Statement St,St2,St3,St4,St5;
	private ConnectionClass db;
	private String Query;
	
	private String [] movie= new String [100];
	
	private String sMovie;
	
	private MovieListPanel() 
	{
		setSize(600,500);
		setLayout(null);
		setBackground(new Color(80,100,90));
		
		initialization();
		addComponent();
		
		addItemListnerToJCB();
		addActionToButton();
		initializeMovieName();
		
		

	}
	
	private void addActionToButton() {
		
		btnShowMOvieDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(sMovie.equals("not selected"))
				{
					JOptionPane.showMessageDialog(null, "You must select a movie");
				}
				else
				{
					MainFrame.getInstance().getMovieDetails().receiveValue();
					MainFrame.getInstance().getMovieDetails().setValue();
					jcbMovie.setSelectedIndex(0);
					MainFrame.getInstance().changeToMovieDetails();
				}
			}
		});
		
	}

	public void initialization()
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
		
		
		movie[0] = "not selected";
		
		table = MovieTable.getInstance();
		
		lblMovie= new JLabel("SELECT MOVIE",JLabel.CENTER);
        lblMovie.setForeground(Color.white);
		
		jcbMovie = new JComboBox<String>();
		
	    btnShowMOvieDetails = new JButton("Show Movie Details");
	/*	btnMovieName2= new JButton("MOVIE2");
		btnMovieName3= new JButton("MOVIE3");
		btnMovieName4= new JButton("MOVIE4");
		btnMovieName5= new JButton("MOVIE5");; */
	}
	
	public void  addComponent()
	{
		 
		//table.setBounds(50, 50, 500, 150);
		
		lblMovie.setBounds(100,137,150,50);
		lblMovie.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		jcbMovie.setBounds(300,150,150,30);
		 
		 
		 
		btnShowMOvieDetails.setBounds(200,350,200,50);
		btnShowMOvieDetails.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		 
		 
		add(btnShowMOvieDetails);

		//add(table);
		add(lblMovie);
		add(jcbMovie);
		
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
						
				}
			}
		});
	}
	
	public String getMovie()
	{
		return sMovie;
	}
	public MovieTable getMovieTable()
	{
		return table;
	}
	
	public static MovieListPanel getInstance()
	{
		if(uniqueInstance==null)
		{
			uniqueInstance = new MovieListPanel();
		}
		return uniqueInstance;
			
	}
	
	
}

