import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovieListPage extends JPanel
{
	
	private CineplexName cName;
	private ButtonPanel pButton;
	private MovieListPanel pMovieList;
	
	private BufferedImage img;
	
	
	public MovieListPage() 
	{
		setSize(1366,786);
		setLayout(null);
		
		initialization();
		addComponent();
		
		
	}



	private void initialization() {
		
		cName= new CineplexName();
		pButton= new ButtonPanel();
		pMovieList= MovieListPanel.getInstance();
		
		
	}

	private void addComponent() {
		cName.setBounds(100,5,1166,120);
		pMovieList.setBounds(383,190,600,500);
		
		try {
			img = ImageIO.read(getClass().getResource("/images/movieList.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		add(cName);
		add(pButton);
		add(pMovieList);
		}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public ButtonPanel getBtnPanel()
	{
		return pButton;
	}
	
	public MovieListPanel getMovieListPanel()
	{
		return pMovieList;
	}

}
