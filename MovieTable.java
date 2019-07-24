import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MovieTable extends JPanel {
	
	private static MovieTable uniqueInstance;
	private JTable table;
	
	private String [] columnNames = {"Serial", "Movie Name", "Status"};
	

	private Object [][] data = new Object[20][3];
	
	@SuppressWarnings("unused")
	private ResultSet Rs,Rs2,Rs3,Rs4,Rs5;
	@SuppressWarnings("unused")
	private Statement St,St2,St3,St4,St5;
	private ConnectionClass db;
	private String Query,Query1;
	private MovieTable() 
	{
		setSize(500,150);
		setLayout(new FlowLayout());
		setBackground(new Color(80,100,90));
		
		
		
		//setValues();
		
		//initialization();
		//addComponent();
		

	}
	
	public void initialization()
	{
		
		
		table = new JTable(data,columnNames);
		
		
		
	}
	
	public void  addComponent()
	{
		table.setPreferredScrollableViewportSize(new Dimension(450, 140));
		table.setFillsViewportHeight(true);
		

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		repaint();
		
		table.setModel(tableModel);
		JScrollPane scrollpane = new JScrollPane(table);
		
		add(scrollpane);
		
		
	}
	
	public void setValues()
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
		
		
		Query = "SELECT DISTINCT `movie`.`M_ID`,`movie`.`Movie Name`,`movie`.`Status` from movie";
		Query1 = "SELECT DISTINCT `next movie`.`Movie Name` from `next movie`";
		try 
		{
			
			Rs=St.executeQuery(Query);
			Rs2=St2.executeQuery(Query1);
			
			int i = 0;
			while(Rs.next())
			{
				data[i][0]=i+1;
				data[i][1]=Rs.getString("Movie Name");
				data[i][2]=Rs.getString("Status");
				i++;
				
			}
			while(Rs2.next())
			{
				data[i][0]=i+1;
				data[i][1]=Rs2.getString("Movie Name");
				data[i][2]="Upcoming";
				i++;
			}
		}catch (SQLException e12) {
				
			//	e12.printStackTrace();
				System.err.println("Got an exception!");
			    System.err.println(e12.getMessage());
			}
	
	}
	
	public static MovieTable getInstance()
	{
		if(uniqueInstance==null)
		{
			uniqueInstance = new MovieTable();
		}
		return uniqueInstance;
			
	}
	
	

}
