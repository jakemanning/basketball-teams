import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AddPersonCityView extends JFrame implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel filePanel = new JPanel(new GridLayout(2,0,0,0));
	JPanel buttonPanel = new JPanel(new GridLayout(0,2,0,0));
	
	JComboBox<City> cityListComboBox = new JComboBox<City>();
	
	JButton cityOKButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	
	State stateSelected;


	AddPersonCityView()
	{
		setTitle("City Selection");

		filePanel.add(cityListComboBox);
		
		buttonPanel.add(cityOKButton);
		buttonPanel.add(cancelButton);
		filePanel.add(buttonPanel);
		
		add(filePanel);
		setLocation(400,200);
		
		pack();
		setVisible(true);
	}
	
	public JButton getOKButton()
	{
		return cityOKButton;
	}
	
	public JButton getCancelButton()
	{
		return cancelButton;
	}
	
	public JComboBox<City> getCityListComboBox()
	{
		return cityListComboBox;
	}

	public void setCityList(City[] cityList)
	{
		for(City city:cityList)
		{
			cityListComboBox.addItem(city);
		}

		cityListComboBox.setSelectedIndex(0);
	}
}
