import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminPage extends JPanel
{
	
	private CineplexName cName;
	private JButton btnAddEmployee,btnRemoveEmployee,btnAddShow,btnRemoveShow,btnAddMovie,btnAddNextMovie,btnLogOut;
	private BufferedImage img;
	
	
	AdminPage()
	{
		setSize(1366,768);
		setLayout(null);
		
		
		initialization();
		addComponent();
		addActionToButton();
		
		
	}

	private void addActionToButton() {
		
		btnAddEmployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeToAddEmployeePage();
			
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeToHome();
			
			}
		});
		
		btnAddShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().getAddShowPage().getAddShowPanel().initializeMovieName();
				MainFrame.getInstance().changeToAddShowPage();
			
			}
		});
		
		btnRemoveEmployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeToRemoveEmployeePage();
			
			}
		});
		
		btnAddMovie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeToAddMoviePage();
			
			}
		});
		
		btnAddNextMovie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeToAddNextMoviePage();
			
			}
		});
		
		btnRemoveShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().getRemoveShowPage().getRemoveShowPanel().initializeMovieName();
				MainFrame.getInstance().changeToRemoveShowPage();
			
			}
		});
		
	}

	private void initialization() {
		
		cName             = new CineplexName();
		btnAddEmployee    = new JButton("ADD NEW EMPLOYEE");
		btnAddMovie    	  = new JButton("ADD NEW MOVIE");
		btnAddNextMovie    	  = new JButton("ADD NEXT ARRIVAL MOVIE");
		btnRemoveEmployee = new JButton("REMOVE EMPLOYEE");
		btnRemoveShow	  = new JButton("REMOVE/RESET SHOW");
		btnAddShow        = new JButton("ADD NEW SHOW");
		btnLogOut		  = new JButton("LOG OUT");;
		
	}

	private void addComponent() 
	{
		
		cName.setBounds(100,5,1166,120);
		
		btnAddShow.setBounds(383,250,250,50);
		btnAddShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnAddShow.setBackground(new Color(135,206,255));
		
		btnAddMovie.setBounds(383, 450, 250, 50);
		btnAddMovie.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnAddMovie.setBackground(new Color(135,206,255));
		
		btnLogOut.setBounds(583, 550, 200, 50);
		btnLogOut.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnLogOut.setBackground(new Color(135,206,255));
		
		btnRemoveShow.setBounds(733,250,250,50);
		btnRemoveShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		btnRemoveShow.setBackground(new Color(135,206,255));
		
		btnAddEmployee.setBounds(383,350,250,50);
		btnAddEmployee.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnAddEmployee.setBackground(new Color(135,206,255));
		
		btnRemoveEmployee.setBounds(733,350,250,50);
		btnRemoveEmployee.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnRemoveEmployee.setBackground(new Color(135,206,255));
		
		btnAddNextMovie.setBounds(733,450,250,50);
		btnAddNextMovie.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 15));
		btnAddNextMovie.setBackground(new Color(135,206,255));
		
		try {
			img = ImageIO.read(getClass().getResource("/images/adminPage.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(cName);
		add(btnAddShow);
		add(btnAddMovie);
		add(btnRemoveShow);
		add(btnAddEmployee);
		add(btnRemoveEmployee);
		add(btnLogOut);
		add(btnAddNextMovie);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public JButton getBtnAddshow()
	{
		return btnAddShow;
	}
	
	public JButton getBtnAddMovie()
	{
		return btnAddMovie;
	}
	public JButton getBtnAddNextMovie()
	{
		return btnAddNextMovie;
	}
	public JButton getBtnRemoveshow()
	{
		return btnRemoveShow;
	}
	
	public JButton getBtnAddEmployee()
	{
		return btnAddEmployee;
	}
	
	public JButton getBtnRemoveEmployee()
	{
		return btnRemoveEmployee;
	}
	
	public JButton getBtnLogOut()
	{
		return btnLogOut;
	}

}
