import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPersonView extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	JPanel personView = new JPanel(new GridLayout(10, 0, 0, 0));

	JLabel personNameLabel = new JLabel();
	JTextField personNameText = new JTextField(20);
	JLabel personDateOfBirthLabel = new JLabel();
	JTextField personBirthDateText = new JTextField(20);
	JLabel personBirthPlaceLabel = new JLabel("Birth Place");

	JLabel personSelectStateLabel = new JLabel("Select State:");
	JButton personStateButton = new JButton("State Selection View");
	JLabel personSelectCityLabel = new JLabel("Select City:");
	JButton personCityButton = new JButton("City Selection View");
	JButton personOKButton = new JButton("OK");

	JLabel personDateOfDeathLabel = new JLabel("Death Date: (dd/MM/yyyy)");
	JTextField personDeathText = new JTextField(20);

	JButton personCancelButton = new JButton("Cancel");

	State stateSelected;
	City citySelected;

	public AddPersonView() {
		setTitle("Add Person");

		personView.add(personNameLabel);
		personNameLabel.setText("<HTML><U>Name</U></HTML>");
		personView.add(personNameText);
		personView.add(personDateOfBirthLabel);
		personDateOfBirthLabel.setText("<HTML><U>Birth Date: (dd/MM/yyyy)</U></HTML>");
		personView.add(personBirthDateText);
		personView.add(personBirthPlaceLabel);
		personBirthPlaceLabel.setText("<HTML><U>Birth Place</U></HTML>");

		JPanel stateSelectionGrid = new JPanel(new GridLayout(0, 2, 0, 0));
		stateSelectionGrid.add(personSelectStateLabel);
		stateSelectionGrid.add(personStateButton);
		personView.add(stateSelectionGrid);

		JPanel citySelectionGrid = new JPanel(new GridLayout(0, 2, 0, 0));
		citySelectionGrid.add(personSelectCityLabel);
		citySelectionGrid.add(personCityButton);
		personCityButton.setEnabled(false);
		personView.add(citySelectionGrid);

		personView.add(personDateOfDeathLabel);
		personDateOfDeathLabel.setText("<HTML><U>Death Date(Optional): (dd/MM/yyyy)</U></HTML>");
		personView.add(personDeathText);
		JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 0, 0));

		personOKButton.setEnabled(false);

		buttonPanel.add(personOKButton);
		buttonPanel.add(personCancelButton);
		personView.add(buttonPanel);

		add(personView);
		setLocation(400, 200);

		pack();
		setVisible(true);

	}

	public JButton getStateButton() {
		return personStateButton;
	}

	public JButton getCityButton() {
		return personCityButton;
	}

	public JButton getOKButton() {
		return personOKButton;
	}

	public JButton getCancelButton() {
		return personCancelButton;
	}

	public String getBirthDateText() {
		return personBirthDateText.getText();
	}

	public String getPersonDeathText() {
		return personDeathText.getText();
	}

	public String getPersonNameText() {
		return personNameText.getText();
	}

	public State getStateSelected() {
		return stateSelected;
	}
}
