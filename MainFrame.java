import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.*;



public class MainFrame {
	
	
	private static MainFrame uniqueInstance;
	
	private JFrame frame = new JFrame("Naptune");
	
	private CardLayout cl  = new CardLayout();
	private JPanel mainPanel = new JPanel();	
	
	private Home home;
	private LogInPage lPage;
	private UserLogIn ulogIn;
	private AdminLogIn adLogIn;
	private Schedule schedule;
	private Schedule2Page schedule2;
	private NewUserPage lUserForm;
	private EmployeeLogIn empLogIn;
	private EmployeePage empPage;
	private AddEmployeePageAdmin adminAddEmpPage;
	private RemoveEmployeePage adminRemoveEmpPage;
	private AddShowPageAdmin adminAddShowPage;
	private RemoveShowPageAdmin adminRemoveShowPage;
	private AdminPage adminPage;
	private MovieListPage movieList;
	private ConfirmPage confirmSelectedMovie;
	private AddNewMoviePage addNewMovie;
	private NextArrivalSetPage addNextMovie;
	private MovieDetails movieDetails;
	private ChangeUserPasswordPage changeUserPass;
	
	
	public JMenuBar bar;
	private JMenu file;
	private JMenuItem LogOut,changePass,user;
	
	Boolean userLoginStatus = false;
	Boolean userLoginFromLoginPage = false;
	private String userName,empID,adminID,userID;
	
	private ResultSet Rs;
	private  Statement St;
	private ConnectionClass db;
	
	
	
	private MainFrame() {
		
		frame.setSize(1366,768);
		mainPanel.setLayout(cl);
		frame.setResizable(false);
		
		init();
		
		
		makeCard();
		
		addActionToButton();
		
		addMenuToFrame();
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	private void init(){
		
		home = new Home();
		lPage = new LogInPage();
		ulogIn = new UserLogIn();
		adLogIn = new AdminLogIn();
		schedule = new Schedule();
		schedule2 = new Schedule2Page();
		lUserForm = new NewUserPage();
		empLogIn = new EmployeeLogIn();
		empPage = new EmployeePage();
		adminAddEmpPage = new AddEmployeePageAdmin();
		adminRemoveEmpPage = new RemoveEmployeePage();
		adminAddShowPage = new AddShowPageAdmin();
		adminRemoveShowPage = new RemoveShowPageAdmin();
		adminPage = new AdminPage();
		movieList = new MovieListPage();
		confirmSelectedMovie = new ConfirmPage();
		addNewMovie = new AddNewMoviePage();
		addNextMovie = new NextArrivalSetPage();
		movieDetails = new MovieDetails();
		changeUserPass = new ChangeUserPasswordPage();
		
		db = new ConnectionClass();
		Rs = db.getRs();
		St = db.getSt();
			
	}
	
	private void addMenuToFrame() {
		this.bar = new JMenuBar(); 
		
		this.file = new JMenu("User");
		
		this.user = new JMenuItem(userID);
		this.changePass = new JMenuItem("Change Password");
		this.LogOut = new JMenuItem("LogOut");
		frame.setJMenuBar(this.bar);
		
		this.bar.add(Box.createHorizontalGlue());
		this.bar.add(this.file);
		this.file.add(this.user);
		this.file.add(this.changePass);
		this.file.add(this.LogOut);
		bar.setVisible(false);
		
		this.changePass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(mainPanel, "Change User Password");
			}
		});
		
		this.LogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				userLoginStatus = false;
				bar.setVisible(false);
				cl.show(mainPanel, "Home");
			}
		});
		
	}
	
	private void makeCard(){
		
		mainPanel.add(home, "Home");
		mainPanel.add(schedule, "Schedule");
		mainPanel.add(schedule2, "Schedule 2 Page");
		mainPanel.add(lPage, "Login Page");
		mainPanel.add(adLogIn, "Admin Login");
		mainPanel.add(ulogIn, "User Login");
		mainPanel.add(lUserForm, "User Form");
		mainPanel.add(empLogIn, "Employee LogIn");
		mainPanel.add(empPage, "Employee Page");
		mainPanel.add(adminAddEmpPage, "Add Employee Page Admin");
		mainPanel.add(adminRemoveEmpPage, "Remove Employee Page");
		mainPanel.add(adminAddShowPage, "Add Show Page Admin");
		mainPanel.add(adminRemoveShowPage, "Remove Show Page");
		mainPanel.add(adminPage, "Admin Page");
		mainPanel.add(movieList, "Movie List Page");
		mainPanel.add(confirmSelectedMovie, "Confirm Page");
		mainPanel.add(addNewMovie, "Add Movie Page");
		mainPanel.add(addNextMovie, "Add Next Movie Page");
		mainPanel.add(movieDetails, "Movie Details");
		mainPanel.add(changeUserPass, "Change User Password");
		
		
		cl.show(mainPanel, "Home");
	}
	
	public void changeToUserPass()
	{
		cl.show(mainPanel, "Change User Password");
	}
	public void changeToMovieDetails()
	{
		cl.show(mainPanel, "Movie Details");
	}
	public void changeToAddNextMoviePage()
	{
		cl.show(mainPanel, "Add Next Movie Page");
	}
	public void changeToAddMoviePage()
	{
		cl.show(mainPanel, "Add Movie Page");
	}
	public void changeToConfirmSelectedPage()
	{
		cl.show(mainPanel, "Confirm Page");
	}
	public void changeToMovieListPage()
	{
		cl.show(mainPanel, "Movie List Page");
	}
	public void changeToAdminPage()
	{
		cl.show(mainPanel, "Admin Page");
	}
	public void changeToRemoveShowPage()
	{
		cl.show(mainPanel, "Remove Show Page");
	}
	public void changeToAddShowPage()
	{
		cl.show(mainPanel, "Add Show Page Admin");
	}
	public void changeToRemoveEmployeePage()
	{
		cl.show(mainPanel, "Remove Employee Page");
	}
	public void changeToHome()
	{
		cl.show(mainPanel, "Home");
	}
	public void changeToSchedule()
	{
		cl.show(mainPanel, "Schedule");
	}
	public void changeToSchedule2Page()
	{
		cl.show(mainPanel, "Schedule 2 Page");
	}
	public void changeToLogin()
	{
		cl.show(mainPanel, "Login Page");
	}
	public void changeToAdminLogin()
	{
		cl.show(mainPanel, "Admin Login");
	}
	public void changeToUserLogin()
	{
		cl.show(mainPanel, "User Login");
	}
	public void changeToUserForm()
	{
		MainFrame.getInstance().lUserForm.getFormPanel().resetTextField();
		cl.show(mainPanel, "User Form");
	}
	public void changeToEmployeeLogin()
	{
		cl.show(mainPanel, "Employee LogIn");
	}
	public void changeToEmployeePage()
	{
		cl.show(mainPanel, "Employee Page");
	}
	public void changeToAddEmployeePage()
	{
		cl.show(mainPanel, "Add Employee Page Admin");
	}
	
	
	@SuppressWarnings("unused")
	private void addActionToButton(){
			
	UserLogin:
			
			ulogIn.getBtnLogin().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					try{
						userID=ulogIn.getLoginPanel().getTxtId().getText();
						String inputPassword=new String(ulogIn.getLoginPanel().getPass().getPassword());
						String password = null;
						
						
						String query ="select * from `customer` where `username`='"+userID+"'";
						Rs = St.executeQuery(query);
						while(Rs.next())
						{
							password = Rs.getString("Password");
							
							
						}
						
						if(inputPassword.equals(password))
						{
							ulogIn.getLoginPanel().getTxtId().setText("");
							ulogIn.getLoginPanel().getPass().setText("");
							userLoginStatus = true;
							bar.setVisible(true);
							userName = userID;
							if(userLoginFromLoginPage==true){
								cl.show(mainPanel, "Confirm Page");
								userLoginFromLoginPage = false;
							}
							else{
									//System.out.println(userID);
									cl.show(mainPanel, "Home");
								}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"U r not an user,try again");
							ulogIn.getLoginPanel().getTxtId().setText("");
							ulogIn.getLoginPanel().getPass().setText("");
							userLoginStatus = false;
						}
						
						}
						catch(Exception ex)
						{
							System.out.println("erro"+ex);
						}
					
				
				}
			});
			
			
			
		EmployeeLogin:
			
			empLogIn.getBtnLogin().addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try{
						empID=empLogIn.getLoginPanel().getTxtId().getText();
						String inputPassword=new String(empLogIn.getLoginPanel().getPass().getPassword());
						String password = null; 
						
						
						String query ="select * from `emp` where `ID`='"+empID+"'";
						Rs = St.executeQuery(query);
						while(Rs.next())
						{
							password = Rs.getString("Password");
							
							
						}
						
						
						if(inputPassword.equals(password))
						{
							empLogIn.getLoginPanel().getTxtId().setText("");
							empLogIn.getLoginPanel().getPass().setText("");
							cl.show(mainPanel, "Employee Page");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"U r not an Employee,try again");
							empLogIn.getLoginPanel().getTxtId().setText("");
							empLogIn.getLoginPanel().getPass().setText("");
						}
						
						}
						catch(Exception ex)
						{
							System.out.println("erro"+ex);
						}
				
				}
			});
			
			
		AdminLogin:
			
			
				
			adLogIn.getBtnLogInAdmin().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					try{
						adminID=adLogIn.getLoginPanel().getTxtId().getText();
						String inputPassword=new String(adLogIn.getLoginPanel().getPass().getPassword());
						String password = null;  
						
						
						String query ="select * from `admin` where `ID`='"+adminID+"'";
						Rs = St.executeQuery(query);
						while(Rs.next())
						{
							password = Rs.getString("Password");
							
							
						}
						
						
						if(inputPassword.equals(password))
						{
							adLogIn.getLoginPanel().getTxtId().setText("");
							adLogIn.getLoginPanel().getPass().setText("");
							cl.show(mainPanel, "Admin Page");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"U r not an admin,try again");
							adLogIn.getLoginPanel().getTxtId().setText("");
							adLogIn.getLoginPanel().getPass().setText("");
						}
						
						}
						catch(Exception ex)
						{
							System.out.println("erro"+ex);
						}
					
				}
			});	
	}

	
	
	public static MainFrame getInstance()
	{
		if(uniqueInstance==null)
		{
			uniqueInstance = new MainFrame();
		}
		return uniqueInstance;
			
	}
	
	public Schedule2Page getSchedule2Page()
	{
		return schedule2;
	}
	
	public RemoveShowPageAdmin getRemoveShowPage()
	{
		return adminRemoveShowPage;
	}
	public AddShowPageAdmin getAddShowPage()
	{
		return adminAddShowPage;
	}
	
	public EmployeePage getEmployeePage()
	{
		return empPage;
	}
	
	public MovieDetails getMovieDetails()
	{
		return movieDetails;
	}
	public MovieListPage getMovieListPage()
	{
		return movieList;
	}
	
	public String getUserName()
	{
		return userID;
	}
	
	public String getEmpID()
	{
		return empID;
	}
	public String getAdminID()
	{
		return adminID;
	}
	
	

}
