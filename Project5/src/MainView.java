import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Button to click to add new city to the city list based on filled text
	 * fields.
	 */
	JButton addPlace = new JButton("Add");
	/** Button to clear text fields of city info. */
	JButton editPlace = new JButton("Edit");
	/** Button to clear city list. */
	JButton deletePlace = new JButton("Delete");

	/**
	 * Button to click to add new city to the city list based on filled text
	 * fields.
	 */
	JButton addPeople = new JButton("Add");
	/** Button to clear text fields of city info. */
	JButton editPeople = new JButton("Edit");
	/** Button to clear city list. */
	JButton deletePeople = new JButton("Delete");
	/** Button for degree separation */
	JButton degreeOfSeparation = new JButton("Separation");

	/**
	 * Button to click to add new city to the city list based on filled text
	 * fields.
	 */
	JButton addTeam = new JButton("Add");
	/** Button to clear text fields of city info. */
	JButton editTeam = new JButton("Edit");
	/** Button to clear city list. */
	JButton deleteTeam = new JButton("Delete");

	/** Label for the list of People */
	JLabel peopleLabel = new JLabel("Players");
	/** Label for the list of Places */
	JLabel placeLabel = new JLabel("Places");
	/** Label for the list of Teams */
	JLabel teamLabel = new JLabel("Teams");

	/** Creation of the Menu Bar of the Main Control */
	JMenuBar menuBar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu graph = new JMenu("Graph");

	/**
	 * Menu options if the User wishes to load, save, import, or export data.
	 */
	JMenuItem jmiLoad = new JMenuItem("Load");
	JMenuItem jmiSave = new JMenuItem("Save");
	JMenuItem jmiImport = new JMenuItem("Import");
	JMenuItem jmiExport = new JMenuItem("Export");
	JMenuItem jmiClear = new JMenuItem("Clear All");

	/** Menu options if the User wishes to graph selected data. */
	JMenuItem jmiMap = new JMenuItem("Map");
	JMenuItem jmiPieChart = new JMenuItem("Pie Chart");

	/** Testing to see data in the Places Column. */
	DefaultListModel<City> placeListModel = new DefaultListModel<City>();
	JList<City> placeList = new JList<City>(placeListModel);
	JScrollPane placeScrollBar = new JScrollPane(placeList);

	/** Testing to see data in the Peoples Column. */
	DefaultListModel<Person> peopleListModel = new DefaultListModel<Person>();
	JList<Person> peopleList = new JList<Person>(peopleListModel);
	JScrollPane peopleScrollBar = new JScrollPane(peopleList);

	/** Testing to see data in the Teams Column. */
	DefaultListModel<Season> teamListModel = new DefaultListModel<Season>();
	JList<Season> seasonList = new JList<Season>(teamListModel);
	JScrollPane seasonScrollBar = new JScrollPane(seasonList);

	City cityToEdit;
	State stateToEdit;

	List<Person> peopleSelected = new ArrayList<Person>();
	List<City> placesSelected = new ArrayList<City>();
	Season teamSelected;

	boolean placesSaved = true;
	boolean peopleSaved = true;
	boolean teamsSaved = true;

	MainView() {
		setTitle("TeamMate");

		peopleScrollBar.setPreferredSize(new Dimension(270, 400));
		placeScrollBar.setPreferredSize(new Dimension(270, 400));
		seasonScrollBar.setPreferredSize(new Dimension(270, 400));

		menuBar.add(file);
		menuBar.add(graph);

		jmiMap.setEnabled(false);
		jmiPieChart.setEnabled(false);
		jmiPieChart.setToolTipText("There must be a person to graph");
		jmiMap.setToolTipText("There must be a person to map");

		graph.add(jmiPieChart);
		graph.add(jmiMap);// .setEnabled(false);

		file.add(jmiLoad);
		jmiSave.setToolTipText("There must be data to save");
		file.add(jmiSave).setEnabled(false);// .setEnabled(false);
		file.add(jmiImport);
		file.add(jmiExport).setEnabled(false);
		file.add(jmiClear).setEnabled(false);
		jmiExport.setToolTipText("There must be data to export");

		// Creates a panel for the List of Places.
		JPanel placePanel = new JPanel();
		placePanel.setLayout(new BoxLayout(placePanel, BoxLayout.Y_AXIS));
		placePanel.add(placeLabel);
		placePanel.add(placeScrollBar);
		JPanel placeButtonPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		placeButtonPanel.add(addPlace);
		placeButtonPanel.add(editPlace).setEnabled(false);
		editPlace.setToolTipText("There must be a place to edit");
		placeButtonPanel.add(deletePlace).setEnabled(false);
		deletePlace.setToolTipText("There must be a place to delete");
		placePanel.add(placeButtonPanel);

		// Creates a panel for the List of Persons.
		JPanel peoplePanel = new JPanel();
		peoplePanel.setLayout(new BoxLayout(peoplePanel, BoxLayout.Y_AXIS));
		peoplePanel.add(peopleLabel);
		peoplePanel.add(peopleScrollBar);
		JPanel peopleButtonPanel = new JPanel(new GridLayout(0, 4, 0, 0));
		peopleButtonPanel.add(addPeople).setEnabled(false);
		addPeople.setToolTipText("There must be a place to add a person");
		peopleButtonPanel.add(editPeople).setEnabled(false);
		editPeople.setToolTipText("There must be a person to edit");
		peopleButtonPanel.add(deletePeople).setEnabled(false);
		deletePeople.setToolTipText("There must be a person to delete");
		peopleButtonPanel.add(degreeOfSeparation).setEnabled(false);
		degreeOfSeparation.setToolTipText("There must a roster to compare");
		peoplePanel.add(peopleButtonPanel, BorderLayout.SOUTH);

		// Creates a panel for the List of Teams.
		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
		teamPanel.add(teamLabel);
		teamPanel.add(seasonScrollBar);
		JPanel teamButtonPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		teamButtonPanel.add(addTeam).setEnabled(false);
		addTeam.setToolTipText("There must be a person and place to add a team");
		teamButtonPanel.add(editTeam).setEnabled(false);
		editTeam.setToolTipText("There must be a team to edit");
		teamButtonPanel.add(deleteTeam).setEnabled(false);
		deleteTeam.setToolTipText("There must be a team to delete");
		teamPanel.add(teamButtonPanel);
		seasonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Places panels in the Container.
		JPanel bodyPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		bodyPanel.add(placePanel);
		bodyPanel.add(peoplePanel);
		bodyPanel.add(teamPanel);

		// Set up the content pane and add all the panels to it.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(menuBar, BorderLayout.NORTH);
		add(bodyPanel, BorderLayout.CENTER);
		setLocation(400, 200);

		pack();
		setVisible(true);
	}

	/**
	 * Gets the Add Button for the Place Column for the Action Listener.
	 * 
	 * @return addPlace The button action for adding places.
	 */
	public JButton getAddPlace() {
		return addPlace;
	}

	/**
	 * Gets the Edit Button for the Place Column for the Action Listener.
	 * 
	 * @return editPlace The button action for editing places.
	 */
	public JButton getEditPlace() {
		return editPlace;
	}

	/**
	 * Gets the Delete Button for the Place Column for the Action Listener.
	 * 
	 * @return deletePlace The button action for deleting places.
	 */
	public JButton getDeletePlace() {
		return deletePlace;
	}

	/**
	 * Gets the Add Button for the People Column for the Action Listener.
	 * 
	 * @return addPeople The button action of adding people.
	 */
	public JButton getAddPeople() {
		return addPeople;
	}

	/**
	 * Gets the Edit Button for the People Column for the Action Listener.
	 * 
	 * @return editPeople The button action of editing people.
	 */
	public JButton getEditPeople() {
		return editPeople;
	}

	/**
	 * Gets the Delete Button for the People Column for the Action Listener.
	 * 
	 * @return deletePeople The button action for deleting people.
	 */
	public JButton getDeletePeople() {
		return deletePeople;
	}

	/**
	 * Gets the Add Button for the Team Column for the Action Listener.
	 * 
	 * @return addTeam The button action for adding teams.
	 */
	public JButton getAddTeam() {
		return addTeam;
	}

	/**
	 * Gets the Edit Button for the Team Column for the Action Listener.
	 * 
	 * @return editTeam The button action for editing teams.
	 */
	public JButton getEditTeam() {
		return editTeam;
	}

	/**
	 * Gets the Delete Button for the Team Column for the Action Listener.
	 * 
	 * @return deleteTeam The button action for deleting teams.
	 */
	public JButton getDeleteTeam() {
		return deleteTeam;
	}

	/**
	 * Gets the JMenuItem for the Age Selection of the Graph Menu for the Action
	 * Listener.
	 * 
	 * @return jmiAge The menu item action for graphing ages.
	 */
	public JMenuItem getPieChart() {
		return jmiPieChart;
	}

	/**
	 * Gets the JMenuItem for the Location Selection of the Graph Menu for the
	 * Action Listener.
	 * 
	 * @return jmiLocation The menu item action for mapping locations.
	 */
	public JMenuItem getMap() {
		return jmiMap;
	}

	public JList<Person> getPeopleList() {
		return peopleList;
	}

	public JList<City> getPlaceList() {
		return placeList;
	}

	public JList<Season> getSeasonList() {
		return seasonList;
	}

	public DefaultListModel<Person> getPeopleListModel() {
		return peopleListModel;
	}

	public DefaultListModel<City> getPlaceListModel() {
		return placeListModel;
	}

	public DefaultListModel<Season> getSeasonListModel() {
		return teamListModel;
	}

	public JMenuItem getLoad() {
		return jmiLoad;
	}

	public JMenuItem getSave() {
		return jmiSave;
	}

	public JMenuItem getImport() {
		return jmiImport;
	}

	public JMenuItem getExport() {
		return jmiExport;
	}

	public JButton getDegreeOfSeparation() {
		return degreeOfSeparation;
	}

	public JMenuItem getClear() {
		return jmiClear;
	}

}
