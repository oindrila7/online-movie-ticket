import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CineplexName extends JLabel{
	
	
	public CineplexName() {
		
		this.setIcon(new ImageIcon(getClass().getResource("/images/rsz_capture.jpg")));
		
		setBounds(100,5,1166,120);
	}
}
