
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

public class AddNewMoviePage extends JPanel
{
	private JLabel lblMovieName,lblStatus,lblPosterLink,lblTrailerLink,lblDetails;
	private JLabel lblPageHeader;
	private JPanel addMoviePanel;
	private JTextField txtMovieName,txtStatus,txtPosterLink,txtTrailerLink,txtDetails;
	private BufferedImage img;
	private String movieName,posterLink,trailerLink,details,status;
	
	private JButton btnBack,btnAddShow;
	
	private CineplexName cName;
	
	private ConnectionClass  db;
	private Statement St,St2;
	private ResultSet Rs,Rs2;
	private String Query;
	
	
	public AddNewMoviePage() 
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
		
		
		
		lblPageHeader= new JLabel("ADD MOVIE PAGE");
		
		lblMovieName= new JLabel("ENTER MOVIE NAME");
		lblStatus= new JLabel("ENTER Status");
		lblPosterLink= new JLabel("ENTER POSTER LINK");
		lblTrailerLink= new JLabel("ENTER TRAILER LINK");
		lblDetails= new JLabel("PROVIDE DETAILS ");
		
		txtMovieName = new JTextField(); 
		txtStatus = new JTextField();
		txtPosterLink= new JTextField();
		txtTrailerLink= new JTextField();
		txtDetails= new JTextField();;
		
		
		btnBack = new JButton("BACK");
		btnAddShow = new JButton("ADD SHOW");
		
		db = new ConnectionClass();
		St= db.getSt();
		St2= db.getSt2();
		Rs=db.getRs();
		Rs2=db.getRs2();
		
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
		
		lblMovieName.setBounds(250,90,150,50);
		txtMovieName.setBounds(500,100,250,30);
		
		
		lblDetails.setBounds(250,160,150,50);
		txtDetails.setBounds(500,170,250,30);
		
		
		lblPosterLink.setBounds(250,230,150,50);
		txtPosterLink.setBounds(500,240,250,30);
		
		
		lblTrailerLink.setBounds(250,300,150,50);
		txtTrailerLink.setBounds(500,310,250,30);
		
		lblStatus.setBounds(250,370,150,50);
		txtStatus.setBounds(500,380,250,30);
		
		
		try {
			img = ImageIO.read(getClass().getResource("/images/addNewMovie.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnBack.setBounds(730, 5, 150, 50);
		btnBack.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		btnAddShow.setBounds(375,470,150,50);
		btnAddShow.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
		
		
		
		
		
		
		addMoviePanel.add(lblPageHeader);
		
		addMoviePanel.add(lblMovieName);
		addMoviePanel.add(txtMovieName);
		
		/*addMoviePanel.add(lblStatus);
		addMoviePanel.add(txtStatus);*/
		
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
	
	
	public void getInfo()
	{
		movieName= txtMovieName.getText();
		posterLink= txtPosterLink.getText();
		trailerLink= txtTrailerLink.getText();
		details= txtDetails.getText();
		//status = txtStatus.getText();
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
					dialogButton = JOptionPane.showConfirmDialog(null, "you sure you want to porceed?\n"+"add this"+movieName+"to system");
				

					if(dialogButton == JOptionPane.YES_OPTION)
	                {

					
						try
						{
							int movieCount=0;
					
							Query="SELECT COUNT(*) from `movie` WHERE `movie`.`Movie Name`='"+movieName+"'";
					
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
									
										
										Query ="INSERT INTO `movie` (`Movie Name`, `Description`, `Status`, `Image Link`,`Trailer Link`) VALUES ('"+movieName+"', '"+details+"', 'Running', '"+posterLink+"','"+trailerLink+"')";
										St2.executeUpdate(Query);	
										JOptionPane.showMessageDialog(null, "movie added to the system");
										resetTextField();
									
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
	                }
				}
				
			}

			
		});
			
		btnBack.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				resetTextField();
				MainFrame.getInstance().changeToAdminPage();
				
			}
		});
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	private void resetTextField() 
	{
		
		txtMovieName.setText("");
		txtPosterLink.setText("");
		txtTrailerLink.setText("");
		txtDetails.setText("");
		txtStatus.setText("");
		
		movieName="";
		posterLink="";
		trailerLink="";
		details="";
		status = "";
		
	
		
	}
	
	
	
	
	

}



