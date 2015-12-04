import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPlaceCityView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel filePanel = new JPanel(new GridLayout(7, 0, 0, 0));
	JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 0, 0));

	JLabel jlCityName = new JLabel("City Name");
	JLabel jlLatitude = new JLabel("Latitude");
	JLabel jlLongitude = new JLabel("Longitude");

	JButton jbCityEditButton = new JButton("Edit");
	JButton cancelButton = new JButton("Finish");

	JTextField jfName = new JTextField(20);
	JTextField jfLatitude = new JTextField(20);
	JTextField jfLongitude = new JTextField(20);

	EditPlaceCityView() {
		setTitle("City Selection");

		filePanel.add(jlCityName);
		filePanel.add(jfName);
		filePanel.add(jlLatitude);
		filePanel.add(jfLatitude);
		filePanel.add(jlLongitude);
		filePanel.add(jfLongitude);

		buttonPanel.add(jbCityEditButton);
		buttonPanel.add(cancelButton);
		filePanel.add(buttonPanel);

		add(filePanel);
		setLocation(400, 200);

		pack();
		setVisible(true);
	}

	public String getCityName() {
		return jfName.getText();
	}

	public JTextField getCityNameField() {
		return jfName;
	}

	public String getLatitude() {
		return jfLatitude.getText();
	}

	public JTextField getCityLatitudeField() {
		return jfLatitude;
	}

	public String getLongitude() {
		return jfLongitude.getText();
	}

	public JTextField getCityLongitudeField() {
		return jfLongitude;
	}

	public JButton getAddButton() {
		return jbCityEditButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

}
