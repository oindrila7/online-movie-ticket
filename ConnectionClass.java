
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionClass {
	
	
	private Connection con;
	private Statement st,st2,st3,st4,st5;
	private ResultSet rs,rs2,rs3,rs4,rs5;
	
	
	
	
	ConnectionClass()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/multiplex","root","");
			st = con.createStatement();
			st2=con.createStatement();
			st3=con.createStatement();
			st4=con.createStatement();
			st5=con.createStatement();
			
			//System.out.println("yes");
			
		}catch(Exception ex)
		{
			System.out.println("no");
			System.out.println("data base connection error"+ex);
			
		}
	}
	
	public Connection getCon()
	{
		return con;
	}
	
	public Statement getSt()
	{
		return st;
	}
	public Statement getSt2()
	{
		return st2;
	}
	public Statement getSt3()
	{
		return st3;
	}
	public Statement getSt4()
	{
		return st4;
	}
	
	public Statement getSt5()
	{
		return st5;
	}
	
	
	public ResultSet getRs()
	{
		return rs;
	}
	
	public ResultSet getRs2()
	{
		return rs2;
	}
	public ResultSet getRs3()
	{
		return rs3;
	}
	public ResultSet getRs4()
	{
		return rs4;
	}
	public ResultSet getRs5()
	{
		return rs5;
	}

}