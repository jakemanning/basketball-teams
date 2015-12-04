import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddPlaceCityView extends JFrame implements Serializable

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel filePanel = new JPanel(new GridLayout(7,0,0,0));
	JPanel buttonPanel = new JPanel(new GridLayout(0,2,0,0));
	
	JLabel jlCityName = new JLabel("City Name");
	JLabel jlLatitude = new JLabel("Latitude");
	JLabel jlLongitude = new JLabel("Longitude");
	
	JButton jbCityAddButton = new JButton("Add");
	JButton cancelButton = new JButton("Cancel");
	
	JTextField jfName = new JTextField(20);
	JTextField jfLatitude = new JTextField(20);
	JTextField jfLongitude = new JTextField(20);
	

	
	AddPlaceCityView()
	{
		setTitle("City Selection");
		
		filePanel.add(jlCityName);
		filePanel.add(jfName);
		filePanel.add(jlLatitude);
		filePanel.add(jfLatitude);
		filePanel.add(jlLongitude);
		filePanel.add(jfLongitude);
		
		buttonPanel.add(jbCityAddButton);
		buttonPanel.add(cancelButton);
		filePanel.add(buttonPanel);
		
		add(filePanel);
		setLocation(400,200);
		
		pack();
		setVisible(true);
	}
	
	public String getCityName()
	{
		return jfName.getText();
	}
	
	public String getLatitude()
	{
		return jfLatitude.getText();
	}
	
	public String getLongitude()
	{
		return jfLongitude.getText();
	}
	
	public JButton getAddButton()
	{
		return jbCityAddButton;
	}
	
	public JButton getCancelButton()
	{
		return cancelButton;
	}
}
