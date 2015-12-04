import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AddSeasonView extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel seasonPanel = new JPanel();

	JButton okButton = new JButton("Add");
	JButton jbAddSeasonCancelButton = new JButton("Cancel");

	JLabel YearSelection = new JLabel("Select Season Year: ");
	JButton YearSelectionButton = new JButton("Year Selection View");
	JComboBox<City> cityComboBox;

	JTextField nameField = new JTextField(20);

	JList<Person> personList;
	JScrollPane personScrollBar;

	City citySelected;
	List<Person> peopleSelected = new ArrayList<Person>();
	Map<String, Person> personMap = new LinkedHashMap<String, Person>();
	Integer yearSelected;

	public AddSeasonView(DataBaseModel model) {
		setTitle("Add Season");
		seasonPanel.setLayout(new BoxLayout(seasonPanel, BoxLayout.Y_AXIS));
		JPanel nameSelection = new JPanel(new GridLayout(0, 2, 0, 0));
		JLabel nameLabel = new JLabel("Enter a name");
		nameSelection.add(nameLabel);
		nameSelection.add(nameField);
		seasonPanel.add(nameSelection);
		JPanel yearSelection = new JPanel(new GridLayout(0, 2, 0, 0));
		yearSelection.add(YearSelection);
		yearSelection.add(YearSelectionButton);
		seasonPanel.add(yearSelection);
		
		JPanel citySelection = new JPanel(new GridLayout(0, 2, 0, 0));
		JLabel cityLabel = new JLabel("Select City");
		City c[] = DataBaseModel.getJListOfCities().values().toArray(new City[0]);
		Arrays.sort(c, new CityComparator());
		cityComboBox = new JComboBox<City>(c);
		citySelection.add(cityLabel);
		citySelection.add(cityComboBox);
		cityComboBox.setSelectedIndex(-1);

		JPanel personSelection = new JPanel(new GridLayout(0, 2, 0, 0));
		JLabel personLabel = new JLabel("Select people");
		personSelection.add(personLabel);

		personList = new JList<Person>(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));
		personScrollBar = new JScrollPane(personList);
		personScrollBar.setBounds(50, 50, 50, 100);
		personSelection.add(personScrollBar);
		
		seasonPanel.add(citySelection);
		seasonPanel.add(personSelection);

		JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 0, 0));
		buttonPanel.add(okButton).setEnabled(false);
		buttonPanel.add(jbAddSeasonCancelButton);
		seasonPanel.add(buttonPanel);

		add(seasonPanel);
		setLocation(400, 200);

		pack();
		setVisible(true);
		
	}

	public JButton getAddSeasonOKButton() {
		return okButton;
	}

	public JButton getAddSeasonCancelButton() {
		return jbAddSeasonCancelButton;
	}

	public JButton getYearSelectionButton() {
		return YearSelectionButton;
	}
}
