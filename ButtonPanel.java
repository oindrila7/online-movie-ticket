/*
 * this class will add home,schedule , movie list,login button to window in all the page
 *
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel
{
	
	private JButton btnHome; 				//to go to home 
	private JButton btnSchedule;       		//to go to schedule
	private JButton btnMovieList;			//to go to movie list
	private JButton btnLogIn; 				//to go to log in page

	
	ButtonPanel()
	{
		setSize(1166,50);
		setLayout(null);
		setBounds(100,130,1166,50);
		initialization();
		addComponentToButtonPanel();
		addActionToButton();
	}


	private void addActionToButton() {
		
		btnSchedule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeToSchedule();
			
			}
		});
		
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeToHome();
			
			}
		});
		
		
		btnLogIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeToLogin();
			
			}
		});
		
		btnMovieList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				MovieTable.getInstance().setValues();
				MovieTable.getInstance().initialization();
				MovieTable.getInstance().addComponent();
				MovieListPanel.getInstance().initializeMovieName();
				
				MainFrame.getInstance().changeToMovieListPage();
			
			}
		});
		
	}


	private void initialization() 
	{
		//
		
		btnHome= new JButton("Home"); 			
		btnSchedule=new JButton("Schedule");       		
		btnMovieList=new JButton("Movie List");			
		btnLogIn=new JButton("Log In"); 
		
	}


	private void addComponentToButtonPanel() 
	{
			
//		btnHome.setBackground(new Color(135,206,250));
//		btnSchedule.setBackground(new Color(135,206,250));
//		btnMovieList.setBackground(new Color(135,206,250));
//		btnLogIn.setBackground(new Color(135,206,250));
		
		btnHome.setBackground(new Color(220,20,60));
		btnSchedule.setBackground(new Color(220,20,60));
		btnMovieList.setBackground(new Color(220,20,60));
		btnLogIn.setBackground(new Color(220,20,60));
			
		btnHome.setBounds(1, 0, 292, 50);
			
		btnSchedule.setBounds(292, 0, 291, 50);
			
		btnMovieList.setBounds(583, 0, 292, 50);
			
		btnLogIn.setBounds(875, 0, 291, 50);
			
		btnHome.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		//btnHome.setForeground(new Color(135,206,250));	
		btnSchedule.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));	
		btnMovieList.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));	
		btnLogIn.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		add(btnHome);
		add(btnSchedule);
		add(btnMovieList);
		add(btnLogIn);
		
	}	

}
