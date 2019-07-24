

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NextArrivalSetPage extends JPanel
{
	private JLabel lblMovieName,lblPosterLink,lblTrailerLink,lblDetails;
	private JLabel lblPageHeader;
	private JPanel addMoviePanel;
	private JTextField txtMovieName,txtPosterLink,txtTrailerLink,txtDetails;
	
	private String movieName,posterLink,trailerLink,details;
	
	private JButton btnBack,btnAddShow;
	private BufferedImage img;
	private CineplexName cName;
	
	private ConnectionClass  db;
	private Statement St,St2;
	@SuppressWarnings("unused")
	private ResultSet Rs,Rs2;
	private String Query;
	
	
	public NextArrivalSetPage() 
	{
		setSize(1366,768);
		setLayout(null);
		setBackground(Color.BLUE);
		
		initialization();
		addComponent();
		addActionListnerToButton();
		
	}


	


	private void initialization() 
	{
		
		cName = new CineplexName();
		
		addMoviePanel = new JPanel();
		
		
		
		lblPageHeader= new JLabel("Next Arrival PAGE");
		
		lblMovieName= new JLabel("ENTER MOVIE NAME");
		lblPosterLink= new JLabel("ENTER POSTER LINK");
		lblTrailerLink= new JLabel("ENTER TRAILER LINK");
		lblDetails= new JLabel("PROVIDE DETAILS ");
		
		txtMovieName = new JTextField(); 
		txtPosterLink= new JTextField();
		txtTrailerLink= new JTextField();
		txtDetails= new JTextField();;
		
		
		btnBack = new JButton("BACK");
		btnAddShow = new JButton("ADD SHOW");
		
		db = new ConnectionClass();
		St= db.getSt();
		St2= db.getSt2();
		Rs=db.getRs();
		
	}


	private void addComponent() 
	{
		addMoviePanel.setLayout(null);
		addMoviePanel.setSize(900,550);
		addMoviePanel.setBounds(233, 130, 900, 550);
		addMoviePanel.setBackground(new Color(175,238,238));
		
		cName.setBounds(100,5,1166,120);
		
		lblPageHeader.setBounds(400,5,250,50);
		lblPageHeader.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 25));
		
		lblMovieName.setBounds(250,110,150,50);
		txtMovieName.setBounds(500,120,250,30);
		
		
		lblDetails.setBounds(250,200,150,50);
		txtDetails.setBounds(500,210,250,30);
		
		
		lblPosterLink.setBounds(250,290,150,50);
		txtPosterLink.setBounds(500,300,250,30);
		
		
		lblTrailerLink.setBounds(250,380,150,50);
		txtTrailerLink.setBounds(500,390,250,30);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/nextArrival.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		btnBack.setBounds(730, 5, 150, 50);
		btnBack.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnAddShow.setBounds(375,470,150,50);
		btnAddShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		
		
		
		
		
		addMoviePanel.add(lblPageHeader);
		
		addMoviePanel.add(lblMovieName);
		addMoviePanel.add(txtMovieName);
		
		addMoviePanel.add(lblDetails);
		addMoviePanel.add(txtDetails);
		
		addMoviePanel.add(lblPosterLink);
		addMoviePanel.add(txtPosterLink);
		
		addMoviePanel.add(lblTrailerLink);
		addMoviePanel.add(txtTrailerLink);
		
		addMoviePanel.add(btnBack);
		addMoviePanel.add(btnAddShow);
		
		
		add(cName);
		add(addMoviePanel);
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void getInfo()
	{
		movieName= txtMovieName.getText();
		posterLink= txtPosterLink.getText();
		trailerLink= txtTrailerLink.getText();
		details= txtDetails.getText();
	}
	
	private void addActionListnerToButton() 
	{
		btnAddShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				
				getInfo();
				if(movieName.equals("")||posterLink.equals("")|| trailerLink.equals("")||details.equals(""))
				{
					JOptionPane.showMessageDialog(null, "you must provide every info ");
					
				}
				else
				{
					int dialogButton = 0;
					dialogButton = JOptionPane.showConfirmDialog (null, "You sure you want to porceed?\n"+"add this"+movieName+"to system","Warning",dialogButton);
					//System.out.println(movieName+" "+posterLink+" "+trailerLink+" "+details+" "+trailerLink);
					//input sql here
					if(dialogButton == JOptionPane.YES_OPTION){
					try
					{
						int movieCount=0;
					
						Query="SELECT COUNT(*) from `next movie` WHERE `next movie`.`Movie Name`='"+movieName+"'";
					
						Rs=St.executeQuery(Query);
						while(Rs.next())
							{
								movieCount=Rs.getInt("COUNT(*)");
								if(movieCount!=0)
								{
									JOptionPane.showMessageDialog(null, "use unique MovieName");
									
								}
								
								else
								{
								
									try 
									{
									
										
										Query ="INSERT INTO `next movie` (`Movie Name`,`Details`,`Poster Link`,`Trailer Link`) VALUES ('"+movieName+"','"+details+"','"+posterLink+"','"+trailerLink+"')";
										St2.executeUpdate(Query);								
									
									}catch (SQLException ee1) 
									{
										
										System.err.println("Got an exception!");
								        System.err.println(ee1.getMessage());			     
								      
									}
								
								
								
								}
							}
					
					}
					catch (SQLException ee) 
					{
						
						System.err.println("Got an exception!");
					    System.err.println(ee.getMessage());
					      
					}
					
					//resetTextField();
					}
				}
				
			}

			
		});
		
		
		
		
		
		
		
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int dialogueButton = 0;
				dialogueButton = JOptionPane.showConfirmDialog (null, "Sure you want to go bakc?\nall the change you made will be lost","Warning",dialogueButton);
				
				if(dialogueButton==JOptionPane.YES_OPTION)
				{
					//go to admina main page
					MainFrame.getInstance().changeToAdminPage();
					
					resetTextField();
					
				}
				else
				{
					//stay on the page
				}
			}
		});
		
		
		
		
		
		
		
	}
	
	
	private void resetTextField() 
	{
		
		txtMovieName.setText("");
		txtPosterLink.setText("");
		txtTrailerLink.setText("");
		txtDetails.setText("");
		
		movieName="";
		posterLink="";
		trailerLink="";
		details="";
		
	
		
	}
	
	
	
	
	

}




