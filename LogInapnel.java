import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInapnel extends JPanel{
	
	private JLabel lblId,lblPass;
	private  JTextField txtId;
	private JPasswordField pPass;
	
	
	LogInapnel()
	
	{
		setLayout(null);
		setSize(400,220);
		setBackground(new Color(135,204,250));
		initialization();
		addComponentTo();
		
	}


	private void initialization() {
		 lblId =new JLabel("use ID");
		 lblPass= new JLabel("password");
		
		 
		 
		 txtId =new JTextField(15);
		 pPass = new JPasswordField(15);
		
	}


	private void addComponentTo()
	{
		this.lblId.setBounds(30, 30, 150, 30);
		this.txtId.setBounds(180,30,150,30);
		
		
		
		this.lblPass.setBounds(30,120,150,30);
		this.pPass.setBounds(180,120,150,30);
		
		
		
	

		
		add(lblId);
		add(lblPass);
		add(txtId);
		add(pPass);
		
	}
	
	public JTextField getTxtId()
	{
		return txtId;
	}
	public JPasswordField getPass()
	{
		return pPass;
	}

}
