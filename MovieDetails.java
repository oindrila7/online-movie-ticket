import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;



public class MovieDetails extends JPanel
{
	private CineplexName cName;
	private ButtonPanel pButton;
	private JLabel lblPoster,lblDetails;
	private JButton btnTrailer,btnBuyTicket;
	@SuppressWarnings("unused")
	private String posterLink,details,movieName,trailerLink;
	
	private ResultSet Rs;
	private  Statement St;
	private ConnectionClass db;
	@SuppressWarnings("unused")
	private BufferedImage img;
	private String Query;
	
	MovieDetails()
	{
		setSize(1366,768);
		setLayout(null);
		
		initialization();
		addComponent();
		//receiveValue();
		//setValue();
		addActionListnerToButton();
		
		
		
	}
	
	
	
	
	private void initialization() {
		cName= new CineplexName();
		pButton = new ButtonPanel();
		
		lblPoster= new JLabel();
		lblDetails = new JLabel();
		
		btnTrailer= new JButton();
		btnBuyTicket= new JButton("BUY TICKET");
		
		db = new ConnectionClass();
		Rs = db.getRs();
		St = db.getSt();
		
		
	}




	private void addComponent() {
		// TODO Auto-generated method stub
		
		cName.setBounds(100,5,1166,120);
		pButton.setBounds(100,125,1166,50);
		
		lblPoster.setBounds(150,210,350,400);
		lblPoster.setBackground(Color.red);
		
		
		lblDetails.setBounds(150,630,350,100);
		lblDetails.setBackground(Color.blue);
		
		btnTrailer.setBounds(550,210,500,450);
		btnTrailer.setBackground(Color.black);
		
		btnBuyTicket.setBounds(716,500,150,50);
		btnBuyTicket.setBackground(Color.RED);
		
		
		
		add(cName);
		add(pButton);
		add(lblPoster);
		add(lblDetails);
		add(btnTrailer);
		add(btnBuyTicket);
		
	}




	@SuppressWarnings("unused")
	public void setValue() {
		// TODO Auto-generated method stub
		
		Query = "SELECT * from movie where `Movie Name` = '"+movieName+"'";
		try 
		{
			
			Rs=St.executeQuery(Query);
			int i =0;
			
			while(Rs.next())
			{
				posterLink = Rs.getString("Image Link");
				trailerLink = Rs.getString("Trailer Link");
				details = Rs.getString("Description");
				i++;
				
			}
		}catch (SQLException e12) {
				
			//	e12.printStackTrace();
				System.err.println("Got an exception!");
			    System.err.println(e12.getMessage());
			}
		
		lblDetails.setText(details);
		lblPoster.setIcon(new ImageIcon(getClass().getClassLoader().getResource(posterLink)));
		btnTrailer.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/play.jpg")));
	}




	public void receiveValue() {
		// TODO Auto-generated method stub
		
		movieName = MainFrame.getInstance().getMovieListPage().getMovieListPanel().getMovie();
		
	}

	public JPanel getBrowserPanel() {
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		JWebBrowser webBrowser = new JWebBrowser();
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		webBrowser.setBarsVisible(false);
		webBrowser.navigate("https://www.youtube.com/v/b-Cr0EWwaTk?fs=1");
		return webBrowserPanel;
	}

	public void addActionListnerToButton()
	{
		/*btnTrailer.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//YouTubeViewer frame = new YouTubeViewer(trailerLink);
				//frame.setVisible(true);
				//new YouTubeViewer();
				NativeInterface.open();
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						JFrame frame = new JFrame("YouTube Viewer");
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
						frame.setSize(800, 600);
						frame.setLocationByPlatform(true);
						frame.setVisible(true);
					}
				});
				NativeInterface.runEventPump();
		    
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
					@Override
					public void run() {
						NativeInterface.close();
					}
				}));
				
			}
		});*/
	}

}
