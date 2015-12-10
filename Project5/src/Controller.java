import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Lab #5 CS 2334, Section 11 March 12, 2015
 * <p>
 * The MVC controller for the program.
 * </p>
 * 
 * @version 1.0
 * 
 */
public class Controller {
	/** Model affiliated with the Controller. */
	private DataBaseModel model;

	private AddPersonView addPersonView;

	private AddPersonCityView addPersonCityView;

	private AddPersonStateView addPersonStateView;

	private AddPlaceStateView addPlaceStateView;

	private AddPlaceCityView addPlaceCityView;

	private AddSeasonView addSeasonView;

	private DegreeSelectionView degreeSelectionView;

	private TeamIDView teamIdentificationView;

	private EditPlaceStateView editPlaceStateView;

	private EditPlaceCityView editPlaceCityView;

	private EditPersonView editPersonView;

	private EditPersonStateView editPersonStateView;

	private DegreeSeparationView degreeSeparationView;
	private EditPersonCityView editPersonCityView;

	private MainView mainView;

	private SorryView sorryView;

	private WriteAndReadFile write;

	private YearSelectionView yearSelectionView;

	/**
	 * Creates new StateController
	 */
	public Controller() {
		// intentionally empty

	}

	/**
	 * When the User adds a person, he or she will then be added to the list of
	 * Persons.
	 */
	private class AddPeopleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addPersonView = new AddPersonView();
			setAddPersonView(addPersonView);

		}
	}

	/**
	 * When the User edits a person, he or she will then be changed and re-add
	 * in the list of Persons.
	 */
	private class EditPeopleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (Person p : mainView.peopleSelected) {
				if (p.getShouldEdit() == true) {
					editPersonView = new EditPersonView(p.getTextName());
					editPersonView.person = p;
					setEditPersonView(editPersonView);
					editPersonView.personNameText.setText(p.getTextName());
					editPersonView.personBirthDateText.setText(p.getDateOfBirth());
					editPersonView.personStateButton.setText(p.getStateOfBirth().toString());
					editPersonView.personCityButton.setText(p.getCityOfBirth().getCityName().toString());
					editPersonView.stateSelected = p.getCityOfBirth().getState();
					editPersonView.citySelected = p.getCityOfBirth();
					if (p.getDateOfDeath() != null) {
						editPersonView.personDeathText.setText(p.getDateOfDeath());
					}
				} else {
					System.out.println(p.getCommaName() + " is a constant");
				}
			}
		}
	}

	/**
	 * When the User deleted a person, he or she will then be deleted from the
	 * list of Persons.
	 */
	private class DeletePeopleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (Person p : mainView.peopleSelected) {
				for (Integer year : DataBaseModel.getJListOfPersons().get(p.getTextName().toUpperCase()).getSeasonList()
						.keySet()) {
					DataBaseModel.getJListOfPersons().get(p.getTextName().toUpperCase()).getSeasonList().get(year)
							.getRosterMap().remove(p.getTextName().toUpperCase());
				}
				DataBaseModel.getJListOfPersons().remove(p.getTextName().toUpperCase());
			}

			mainView.getPlaceList().setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));
			mainView.getPeopleList().setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));

			mainView.getSeasonListModel().clear();
			for (String id : DataBaseModel.getJListOfIdentifications().keySet()) {
				for (Integer year : DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().keySet()) {
					mainView.getSeasonListModel()
							.addElement(DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().get(year));
				}
			}
		}
	}

	/**
	 * When the User adds a place, that place will then be added to the list of
	 * Places.
	 */
	private class AddPlaceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addPlaceStateView = new AddPlaceStateView();
			setAddPlaceStateView(addPlaceStateView);
		}
	}

	/**
	 * When the User edits a place, that place will then be changed and re-added
	 * to the list of Places.
	 */
	private class EditPlaceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (City c : mainView.placesSelected) {
				if (c.getShouldEdit()) {
					editPlaceStateView = new EditPlaceStateView(c.getStateName() + "," + c.getCityName());
					setEditPlaceStateView(editPlaceStateView);
					editPlaceStateView.setEditPlaceComboBoxIndex(c.getState());
					editPlaceStateView.stateToEdit = c.getStateName();
					editPlaceStateView.cityToEdit = c.getCityName();
				} else {
					System.out.println(c.getCityName() + " is constant");
				}
			}

		}
	}

	/**
	 * When the User deletes a place, that place will then be removed from the
	 * list of Places.
	 */
	private class DeletePlaceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Person> mapList = new ArrayList<Person>(DataBaseModel.getJListOfPersons().values());
			for (City c : mainView.placesSelected) {
				for (Person person : mapList) {
					if (person.getCityOfBirth().getCityName().equalsIgnoreCase(c.getCityName())) {
						ArrayList<Season> seasons = new ArrayList<Season>(person.getSeasonList().values());
						for (Season season : seasons) {
							season.getRosterMap().remove(person.getTextName().toUpperCase());
							if (season.getTeamCity().getCityName().equalsIgnoreCase(c.getCityName())) {
								person.getSeasonList().remove(season.getTeamName().toUpperCase());
							}
						}
						DataBaseModel.getJListOfPersons().remove(person.getTextName().toUpperCase());
					}
				}
				ArrayList<Identification> identificationList = new ArrayList<Identification>(
						DataBaseModel.getJListOfIdentifications().values());
				for (Identification id : identificationList) {
					for (Season season : id.getSeasonList().values()) {
						if (season.getTeamCity().getCityName().equalsIgnoreCase(c.getCityName())) {
							DataBaseModel.getJListOfIdentifications().remove(id.getIdentification());
						}
					}
				}

				DataBaseModel.getJListOfCities().remove(c.getCityName().toUpperCase() + c.getStateName().toUpperCase());
				c.getState().getListOfCities().remove(c.getCityName().toUpperCase() + c.getStateName().toUpperCase());
			}

			mainView.getPlaceList().setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));
			mainView.getPeopleList().setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));

			mainView.getSeasonListModel().clear();
			for (String id : DataBaseModel.getJListOfIdentifications().keySet()) {
				for (Integer year : DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().keySet()) {
					mainView.getSeasonListModel()
							.addElement(DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().get(year));
				}
			}
		}
	}

	/**
	 * When the User adds a team, that team will then be added to the list of
	 * Teams under a specific season.
	 */
	private class AddTeamListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			teamIdentificationView = new TeamIDView();
			setTeamIDView(teamIdentificationView);
		}
	}

	/**
	 * When the User edits a team, that team will then be edited and re-add to
	 * the list of Teams under a specific season.
	 */
	private class EditTeamListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (mainView.teamSelected != null && !mainView.teamSelected.getShouldEdit()) {
				System.out.println("Editing: " + mainView.teamSelected);

			}
		}
	}

	/**
	 * When the User deletes a team, that team will then be removed from the
	 * list of Teams under a specific season.
	 */
	private class DeleteTeamListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Season seasonSelected = mainView.teamSelected;

			for (Identification id : DataBaseModel.getJListOfIdentifications().values()) {
				id.getSeasonList().remove(seasonSelected.getYear());
			}

			mainView.getSeasonListModel().clear();
			for (String id : DataBaseModel.getJListOfIdentifications().keySet()) {
				for (Integer year : DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().keySet()) {
					mainView.getSeasonListModel()
							.addElement(DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().get(year));
				}
			}
		}
	}

	/**
	 * When the User selects the Save option in the File menu, the current
	 * status of the lists is written to an external text file.
	 */
	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] possibleValues = { "Teams", "Players", "Places" };

			int selectedValue = JOptionPane.showOptionDialog(null, "Choose one", "Input",
					JOptionPane.INFORMATION_MESSAGE, 0, null, possibleValues, possibleValues[2]);
			if (selectedValue >= 0) {
				JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
				FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter("Comma Separated Values",
						"csv");
				chooser.addChoosableFileFilter(commaSeparatedFilter);
				chooser.setFileFilter(commaSeparatedFilter);
				int returnValue = chooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();

					try {
						System.out.println("Saving.....");
						if (possibleValues[selectedValue].equals("Places")) {

							FileWriter outfile = new FileWriter(selectedFile);
							BufferedWriter bw = new BufferedWriter(outfile);
							for (City c : DataBaseModel.getJListOfCities().values()) {
								bw.write(c.getCityName() + "; " + c.getStateName() + "; " + c.getLatitude() + "; "
										+ c.getLongitude());
								bw.newLine();
							}
							mainView.placesSaved = true;
							bw.close();

						}

						else if (possibleValues[selectedValue].equals("People")) {
							FileWriter outfile = new FileWriter(selectedFile);
							BufferedWriter bw = new BufferedWriter(outfile);
							for (Person p : DataBaseModel.getJListOfPersons().values()) {
								if (p.getDateOfDeath() == null) {
									bw.write(p.getTextName() + "," + p.getDateOfBirth() + ","
											+ p.getCityOfBirth().getCityName() + "," + p.getStateOfBirth());

								} else {

									bw.write(p.getTextName() + "," + p.getDateOfBirth() + ","
											+ p.getCityOfBirth().getCityName() + "," + p.getStateOfBirth() + ","
											+ p.getDateOfDeath());
								}
								bw.newLine();

							}
							mainView.peopleSaved = true;
							bw.close();
						} else {
							FileWriter outfile = new FileWriter(selectedFile);
							BufferedWriter bw = new BufferedWriter(outfile);

							for (Identification id : DataBaseModel.getJListOfIdentifications().values()) {
								bw.write(id.getIdentification() + ":");
								bw.newLine();
								for (Integer year : id.getSeasonList().keySet()) {
									Season season = id.getSeasonList().get(year);
									bw.write(season.getYear() + "; " + season.getTeamName() + "; "
											+ season.getTeamCity().getCityName() + "; "
											+ season.getTeamCity().getStateName() + "; "
											+ season.getMembersForSaving());
									bw.newLine();
								}
							}
							mainView.teamsSaved = true;
							bw.close();
						}

					} catch (IOException m) {
						m.printStackTrace();
						System.out.println("IOException");
					}
					System.out.println("Saved to " + selectedFile);
				}
			}
		}
	}

	/**
	 * When the User selects the Load option in the File menu, a previous status
	 * of the lists is brought the Main Control.
	 */
	private class LoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!mainView.placesSaved) {
				String[] possibleValues = { "Discard", "Export", "Save" };
				int selectedValue = JOptionPane.showOptionDialog(null, "Choose one", "Places Not Saved",
						JOptionPane.WARNING_MESSAGE, 2, null, possibleValues, possibleValues[2]);
				if (selectedValue >= 0) {
					if (possibleValues[selectedValue].equals("Save")) {

						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter(
								"Comma Separated Values", "csv");
						chooser.addChoosableFileFilter(commaSeparatedFilter);
						chooser.setFileFilter(commaSeparatedFilter);
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File selectedFile = chooser.getSelectedFile();

							try {
								FileWriter outfile = new FileWriter(selectedFile);
								BufferedWriter bw = new BufferedWriter(outfile);
								for (City c : DataBaseModel.getJListOfCities().values()) {
									bw.write(c.getCityName() + "; " + c.getStateName() + "; " + c.getLatitude() + "; "
											+ c.getLongitude());
									bw.newLine();
								}
								mainView.placesSaved = true;
								bw.close();
							} catch (IOException io) {
								io.printStackTrace();
							}
						}
					} else if (possibleValues[selectedValue].equals("Export")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter binaryFilter = new FileNameExtensionFilter("Binary Files", "bin");
						chooser.addChoosableFileFilter(binaryFilter);
						chooser.setFileFilter(binaryFilter);
						// openFileChooser
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							mainView.peopleSaved = true;
							mainView.placesSaved = true;
							mainView.teamsSaved = true;
							File selectedFile = chooser.getSelectedFile();

							try {
								System.out.println("Exporting......");
								write = new WriteAndReadFile(model, selectedFile.getName(), true);
							} catch (ClassNotFoundException c) {
								System.out.println("ClassNotFound");
							} catch (IOException i) {
								i.printStackTrace();
								System.out.println("IOException");
							}

						}
						System.out.println("You have Exported");
					}

					else {
						mainView.placesSaved = true;
					}
				}
			}
			if (!mainView.peopleSaved) {
				String[] possibleValues = { "Discard", "Export", "Save" };
				int selectedValue = JOptionPane.showOptionDialog(null, "Choose one", "People Not Saved",
						JOptionPane.WARNING_MESSAGE, 2, null, possibleValues, possibleValues[2]);
				if (selectedValue >= 0) {
					if (possibleValues[selectedValue].equals("Save")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter(
								"Comma Separated Values", "csv");
						chooser.addChoosableFileFilter(commaSeparatedFilter);
						chooser.setFileFilter(commaSeparatedFilter);
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File selectedFile = chooser.getSelectedFile();

							try {
								FileWriter outfile = new FileWriter(selectedFile);
								BufferedWriter bw = new BufferedWriter(outfile);
								for (Person p : DataBaseModel.getJListOfPersons().values()) {
									if (p.getDateOfDeath() == null) {
										bw.write(p.getTextName() + "," + p.getDateOfBirth() + ","
												+ p.getCityOfBirth().getCityName() + "," + p.getStateOfBirth());

									} else {

										bw.write(p.getTextName() + "," + p.getDateOfBirth() + ","
												+ p.getCityOfBirth().getCityName() + "," + p.getStateOfBirth() + ","
												+ p.getDateOfDeath());
									}
									bw.newLine();

								}
								mainView.peopleSaved = true;
								bw.close();
							} catch (IOException io) {
								io.printStackTrace();
							}
						}
					} else if (possibleValues[selectedValue].equals("Export")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter binaryFilter = new FileNameExtensionFilter("Binary Files", "bin");
						chooser.addChoosableFileFilter(binaryFilter);
						chooser.setFileFilter(binaryFilter);
						// openFileChooser
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							mainView.peopleSaved = true;
							mainView.placesSaved = true;
							mainView.teamsSaved = true;
							File selectedFile = chooser.getSelectedFile();

							try {
								System.out.println("Exporting......");
								write = new WriteAndReadFile(model, selectedFile.getName(), true);
							} catch (ClassNotFoundException c) {
								System.out.println("ClassNotFound");
							} catch (IOException i) {
								i.printStackTrace();
								System.out.println("IOException");
							}

						}
						System.out.println("You have Exported");
					} else {
						mainView.peopleSaved = true;
					}
				}
			}
			if (!mainView.teamsSaved) {
				String[] possibleValues = { "Discard", "Export", "Save" };
				int selectedValue = JOptionPane.showOptionDialog(null, "Choose one", "Teams Not Saved",
						JOptionPane.WARNING_MESSAGE, 2, null, possibleValues, possibleValues[2]);
				if (selectedValue >= 0) {
					if (possibleValues[selectedValue].equals("Save")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter(
								"Comma Separated Values", "csv");
						chooser.addChoosableFileFilter(commaSeparatedFilter);
						chooser.setFileFilter(commaSeparatedFilter);
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File selectedFile = chooser.getSelectedFile();

							try {
								FileWriter outfile = new FileWriter(selectedFile);
								BufferedWriter bw = new BufferedWriter(outfile);

								for (Identification id : DataBaseModel.getJListOfIdentifications().values()) {
									bw.write(id.getIdentification() + ":");
									bw.newLine();
									for (Integer year : id.getSeasonList().keySet()) {
										Season season = id.getSeasonList().get(year);
										bw.write(season.getYear() + "; " + season.getTeamName() + "; "
												+ season.getTeamCity().getCityName() + "; "
												+ season.getTeamCity().getStateName() + "; "
												+ season.getMembersForSaving());
										bw.newLine();
									}
								}
								mainView.teamsSaved = true;
								bw.close();
							} catch (IOException io) {
								io.printStackTrace();
							}
						}
					} else if (possibleValues[selectedValue].equals("Export")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter binaryFilter = new FileNameExtensionFilter("Binary Files", "bin");
						chooser.addChoosableFileFilter(binaryFilter);
						chooser.setFileFilter(binaryFilter);
						// openFileChooser
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							mainView.peopleSaved = true;
							mainView.placesSaved = true;
							mainView.teamsSaved = true;
							File selectedFile = chooser.getSelectedFile();

							try {
								System.out.println("Exporting......");
								write = new WriteAndReadFile(model, selectedFile.getName(), true);
							} catch (ClassNotFoundException c) {
								System.out.println("ClassNotFound");
							} catch (IOException i) {
								i.printStackTrace();
								System.out.println("IOException");
							}

						}
						System.out.println("You have Exported");
					} else {
						mainView.teamsSaved = true;
					}

				}
			}

			JOptionPane.showMessageDialog(null, "All data is saved. Continuing on.....", "Loading",
					JOptionPane.INFORMATION_MESSAGE);
			String[] possibleValues = { "Teams", "Players", "Places" };

			int selectedValue = JOptionPane.showOptionDialog(null, "Choose one", "Input",
					JOptionPane.INFORMATION_MESSAGE, 0, null, possibleValues, possibleValues[2]);
			if (selectedValue >= 0) {
				if (possibleValues[selectedValue].equals("Places")) {
					JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
					FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter("Comma Separated Values",
							"csv");
					chooser.addChoosableFileFilter(commaSeparatedFilter);
					chooser.setFileFilter(commaSeparatedFilter);
					int returnValue = chooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = chooser.getSelectedFile();

						System.out.println("Loading.....");

						try {
							model.readCitiesFromFile(selectedFile.getName());
							mainView.degreeOfSeparation.setEnabled(true);
							mainView.jmiSave.setEnabled(true);
							mainView.jmiExport.setEnabled(true);
							mainView.jmiPieChart.setEnabled(true);
							mainView.jmiMap.setEnabled(true);
							mainView.jmiClear.setEnabled(true);
							mainView.addPeople.setEnabled(true);
							mainView.addTeam.setEnabled(true);
							mainView.editTeam.setEnabled(true);
							mainView.deleteTeam.setEnabled(true);
							mainView.deletePeople.setEnabled(true);
							mainView.deletePlace.setEnabled(true);
							mainView.editPeople.setEnabled(true);
							mainView.editPlace.setEnabled(true);
							mainView.placesSaved = false;
						} catch (IOException exception) {
							exception.printStackTrace();
						}
						mainView.getPlaceList()
								.setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));
						mainView.getPeopleList()
								.setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));
						System.out.println("Loading Complete.");
					}
				}

				else if (possibleValues[selectedValue].equals("Players")
						&& !DataBaseModel.getJListOfCities().isEmpty()) {
					JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
					FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter("Comma Separated Values",
							"csv");
					chooser.addChoosableFileFilter(commaSeparatedFilter);
					chooser.setFileFilter(commaSeparatedFilter);
					int returnValue = chooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = chooser.getSelectedFile();

						System.out.println("Loading.....This may take up to 10 seconds");

						try {
							model.readPersonsFromFile(selectedFile.getName());
							mainView.degreeOfSeparation.setEnabled(true);
							mainView.jmiSave.setEnabled(true);
							mainView.jmiExport.setEnabled(true);
							mainView.jmiPieChart.setEnabled(true);
							mainView.jmiMap.setEnabled(true);
							mainView.addPeople.setEnabled(true);
							mainView.addTeam.setEnabled(true);
							mainView.editTeam.setEnabled(true);
							mainView.deleteTeam.setEnabled(true);
							mainView.deletePeople.setEnabled(true);
							mainView.deletePlace.setEnabled(true);
							mainView.editPeople.setEnabled(true);
							mainView.editPlace.setEnabled(true);
							mainView.peopleSaved = false;
						} catch (IOException exception) {
							exception.printStackTrace();
						}
						mainView.getPlaceList()
								.setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));
						mainView.getPeopleList()
								.setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));
						System.out.println("Loading Complete.");
					}
				}

				else if (!DataBaseModel.getJListOfCities().isEmpty() && !DataBaseModel.getJListOfPersons().isEmpty()) {
					JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
					FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter("Comma Separated Values",
							"csv");
					chooser.addChoosableFileFilter(commaSeparatedFilter);
					chooser.setFileFilter(commaSeparatedFilter);
					int returnValue = chooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = chooser.getSelectedFile();

						System.out.println("Loading.....This may take up to 10 seconds");
						mainView.degreeOfSeparation.setEnabled(true);
						Map<String, Identification> teamMap = null;
						try {

							teamMap = model.readTeamsFromFile(selectedFile.getName());
							mainView.degreeOfSeparation.setEnabled(true);
							mainView.jmiSave.setEnabled(true);
							mainView.jmiExport.setEnabled(true);
							mainView.jmiPieChart.setEnabled(true);
							mainView.jmiMap.setEnabled(true);
							mainView.addPeople.setEnabled(true);
							mainView.addTeam.setEnabled(true);
							mainView.editTeam.setEnabled(true);
							mainView.deleteTeam.setEnabled(true);
							mainView.deletePeople.setEnabled(true);
							mainView.deletePlace.setEnabled(true);
							mainView.editPeople.setEnabled(true);
							mainView.editPlace.setEnabled(true);
							mainView.teamsSaved = false;
						} catch (IOException f) {
							f.printStackTrace();
						}
						mainView.getPlaceList()
								.setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));
						mainView.getPeopleList()
								.setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));

						mainView.getSeasonListModel().clear();
						for (String id : teamMap.keySet()) {
							for (Integer year : teamMap.get(id).getSeasonList().keySet()) {
								mainView.getSeasonListModel().addElement(teamMap.get(id).getSeasonList().get(year));
							}
						}
						System.out.println("Loading Complete.");
					}
				} else {
					System.out.println(
							"You must pick Places if there are no places, and People if there are no people but there are Places.");
				}

			}

		}
	}

	/**
	 * When the User selects the Exit option in the File menu, the Container
	 * will close and the program will terminate.
	 */
	private class ImportListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!mainView.placesSaved) {
				String[] possibleValues = { "Discard", "Export", "Save" };
				int selectedValue = JOptionPane.showOptionDialog(null, "Choose one", "Places Not Saved",
						JOptionPane.WARNING_MESSAGE, 2, null, possibleValues, possibleValues[2]);
				if (selectedValue >= 0) {
					if (possibleValues[selectedValue].equals("Save")) {

						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter(
								"Comma Separated Values", "csv");
						chooser.addChoosableFileFilter(commaSeparatedFilter);
						chooser.setFileFilter(commaSeparatedFilter);
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File selectedFile = chooser.getSelectedFile();

							try {
								FileWriter outfile = new FileWriter(selectedFile);
								BufferedWriter bw = new BufferedWriter(outfile);
								for (City c : DataBaseModel.getJListOfCities().values()) {
									bw.write(c.getCityName() + "; " + c.getStateName() + "; " + c.getLatitude() + "; "
											+ c.getLongitude());
									bw.newLine();
								}
								mainView.placesSaved = true;
								bw.close();
							} catch (IOException io) {
								io.printStackTrace();
							}
						}
					} else if (possibleValues[selectedValue].equals("Export")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter binaryFilter = new FileNameExtensionFilter("Binary Files", "bin");
						chooser.addChoosableFileFilter(binaryFilter);
						chooser.setFileFilter(binaryFilter);
						// openFileChooser
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							mainView.peopleSaved = true;
							mainView.placesSaved = true;
							mainView.teamsSaved = true;
							File selectedFile = chooser.getSelectedFile();

							try {
								System.out.println("Exporting......");
								write = new WriteAndReadFile(model, selectedFile.getName(), true);
							} catch (ClassNotFoundException c) {
								System.out.println("ClassNotFound");
							} catch (IOException i) {
								i.printStackTrace();
								System.out.println("IOException");
							}

						}
						System.out.println("You have Exported");
					}

					else {
						mainView.placesSaved = true;
					}
				}
			}
			if (!mainView.peopleSaved) {
				String[] possibleValues = { "Discard", "Export", "Save" };
				int selectedValue = JOptionPane.showOptionDialog(null, "Choose one", "People Not Saved",
						JOptionPane.WARNING_MESSAGE, 2, null, possibleValues, possibleValues[2]);
				if (selectedValue >= 0) {
					if (possibleValues[selectedValue].equals("Save")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter(
								"Comma Separated Values", "csv");
						chooser.addChoosableFileFilter(commaSeparatedFilter);
						chooser.setFileFilter(commaSeparatedFilter);
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File selectedFile = chooser.getSelectedFile();

							try {
								FileWriter outfile = new FileWriter(selectedFile);
								BufferedWriter bw = new BufferedWriter(outfile);
								for (Person p : DataBaseModel.getJListOfPersons().values()) {
									if (p.getDateOfDeath() == null) {
										bw.write(p.getTextName() + "," + p.getDateOfBirth() + ","
												+ p.getCityOfBirth().getCityName() + "," + p.getStateOfBirth());

									} else {

										bw.write(p.getTextName() + "," + p.getDateOfBirth() + ","
												+ p.getCityOfBirth().getCityName() + "," + p.getStateOfBirth() + ","
												+ p.getDateOfDeath());
									}
									bw.newLine();

								}
								mainView.peopleSaved = true;
								bw.close();
							} catch (IOException io) {
								io.printStackTrace();
							}
						}
					} else if (possibleValues[selectedValue].equals("Export")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter binaryFilter = new FileNameExtensionFilter("Binary Files", "bin");
						chooser.addChoosableFileFilter(binaryFilter);
						chooser.setFileFilter(binaryFilter);
						// openFileChooser
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							mainView.peopleSaved = true;
							mainView.placesSaved = true;
							mainView.teamsSaved = true;
							File selectedFile = chooser.getSelectedFile();

							try {
								System.out.println("Exporting......");
								write = new WriteAndReadFile(model, selectedFile.getName(), true);
							} catch (ClassNotFoundException c) {
								System.out.println("ClassNotFound");
							} catch (IOException i) {
								i.printStackTrace();
								System.out.println("IOException");
							}

						}
						System.out.println("You have Exported");
					} else {
						mainView.peopleSaved = true;
					}
				}
			}
			if (!mainView.teamsSaved) {
				String[] possibleValues = { "Discard", "Export", "Save" };
				int selectedValue = JOptionPane.showOptionDialog(null, "Choose one", "Teams Not Saved",
						JOptionPane.WARNING_MESSAGE, 2, null, possibleValues, possibleValues[2]);
				if (selectedValue >= 0) {
					if (possibleValues[selectedValue].equals("Save")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter commaSeparatedFilter = new FileNameExtensionFilter(
								"Comma Separated Values", "csv");
						chooser.addChoosableFileFilter(commaSeparatedFilter);
						chooser.setFileFilter(commaSeparatedFilter);
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File selectedFile = chooser.getSelectedFile();

							try {
								FileWriter outfile = new FileWriter(selectedFile);
								BufferedWriter bw = new BufferedWriter(outfile);

								for (Identification id : DataBaseModel.getJListOfIdentifications().values()) {
									bw.write(id.getIdentification() + ":");
									bw.newLine();
									for (Integer year : id.getSeasonList().keySet()) {
										Season season = id.getSeasonList().get(year);
										bw.write(season.getYear() + "; " + season.getTeamName() + "; "
												+ season.getTeamCity().getCityName() + "; "
												+ season.getTeamCity().getStateName() + "; "
												+ season.getMembersForSaving());
										bw.newLine();
									}
								}
								mainView.teamsSaved = true;
								bw.close();
							} catch (IOException io) {
								io.printStackTrace();
							}
						}
					} else if (possibleValues[selectedValue].equals("Export")) {
						JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
						FileNameExtensionFilter binaryFilter = new FileNameExtensionFilter("Binary Files", "bin");
						chooser.addChoosableFileFilter(binaryFilter);
						chooser.setFileFilter(binaryFilter);
						// openFileChooser
						int returnValue = chooser.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							mainView.peopleSaved = true;
							mainView.placesSaved = true;
							mainView.teamsSaved = true;
							File selectedFile = chooser.getSelectedFile();

							try {
								System.out.println("Exporting......");
								write = new WriteAndReadFile(model, selectedFile.getName(), true);
							} catch (ClassNotFoundException c) {
								System.out.println("ClassNotFound");
							} catch (IOException i) {
								i.printStackTrace();
								System.out.println("IOException");
							}

						}
						System.out.println("You have Exported");
					} else {
						mainView.teamsSaved = true;
					}

				}
			}

			JOptionPane.showMessageDialog(null, "All data is saved. Continuing on....", "Importing",
					JOptionPane.INFORMATION_MESSAGE);
			JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
			FileNameExtensionFilter binaryFilter = new FileNameExtensionFilter("Binary Files", "bin");
			chooser.addChoosableFileFilter(binaryFilter);
			chooser.setFileFilter(binaryFilter);

			// openFileChooser
			int returnValue = chooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();

				try {
					System.out.println("Importing......");
					write = new WriteAndReadFile(model, selectedFile.getName(), false);
					setModel(write.getModel());
					mainView.degreeOfSeparation.setEnabled(true);
					mainView.jmiSave.setEnabled(true);
					mainView.jmiExport.setEnabled(true);
					mainView.jmiPieChart.setEnabled(true);
					mainView.jmiMap.setEnabled(true);
					mainView.jmiClear.setEnabled(true);
					mainView.addPeople.setEnabled(true);
					mainView.addTeam.setEnabled(true);
					mainView.editTeam.setEnabled(true);
					mainView.deleteTeam.setEnabled(true);
					mainView.deletePeople.setEnabled(true);
					mainView.deletePlace.setEnabled(true);
					mainView.editPeople.setEnabled(true);
					mainView.editPlace.setEnabled(true);

					mainView.getPlaceList().setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));

					mainView.getPeopleList()
							.setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));
					mainView.getSeasonListModel().clear();
					for (String id : DataBaseModel.getJListOfIdentifications().keySet()) {
						for (Integer year : DataBaseModel.getJListOfIdentifications().get(id).getSeasonList()
								.keySet()) {
							mainView.getSeasonListModel().addElement(
									DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().get(year));
						}
					}
					if (!DataBaseModel.getJListOfCities().isEmpty())
						mainView.placesSaved = false;
					if (!DataBaseModel.getJListOfPersons().isEmpty())
						mainView.peopleSaved = false;
					if (!DataBaseModel.getJListOfIdentifications().isEmpty())
						mainView.teamsSaved = false;
				} catch (ClassNotFoundException c) {
					System.out.println("ClassNotFound");
				} catch (IOException i) {
					i.printStackTrace();
					System.out.println("IOException");
				}
				System.out.println("You have Imported");
			}

		}
	}

	private class ExportListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
			FileNameExtensionFilter binaryFilter = new FileNameExtensionFilter("Binary Files", "bin");
			chooser.addChoosableFileFilter(binaryFilter);
			chooser.setFileFilter(binaryFilter);
			// openFileChooser
			int returnValue = chooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				mainView.peopleSaved = true;
				mainView.placesSaved = true;
				mainView.teamsSaved = true;
				File selectedFile = chooser.getSelectedFile();

				try {
					System.out.println("Exporting......");
					write = new WriteAndReadFile(model, selectedFile.getName(), true);
				} catch (ClassNotFoundException c) {
					System.out.println("ClassNotFound");
				} catch (IOException i) {
					i.printStackTrace();
					System.out.println("IOException");
				}

			}

			System.out.println("You have Exported");
		}
	}

	private class MapListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ImagePanel imagePanel;
			JFrame jFrame = new JFrame("Distribution of Locations of People");
			if (mainView.teamSelected == null) {
				imagePanel = new ImagePanel(mainView.peopleSelected, null);
			} else {
				List<Person> personRoster = new ArrayList<Person>(mainView.teamSelected.getRosterMap().values());
				imagePanel = new ImagePanel(personRoster, mainView.teamSelected);
			}
			jFrame.add(imagePanel);
			jFrame.setSize(591, 430);
			jFrame.setVisible(true);

		}
	}

	private class PieChartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (mainView.teamSelected != null) {
				@SuppressWarnings("unused")
				PieChart app = new PieChart(mainView.teamSelected.getRosterMap().values(),
						"Players from " + mainView.teamSelected.getTeamName());
			} else if (mainView.peopleSelected != null || !mainView.peopleSelected.isEmpty()) {
				@SuppressWarnings("unused")
				PieChart app = new PieChart(mainView.peopleSelected, "Players Selected");
			} else {
				System.out.println("You must select something");
			}

		}
	}

	private class PeopleListListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				try {
					mainView.peopleSelected = (ArrayList<Person>) mainView.getPeopleList().getSelectedValuesList();
				} catch (ClassCastException c) {
					mainView.peopleSelected.clear();
				}
			}
		}
	}

	private class PlaceListListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {

			if (e.getValueIsAdjusting()) {
				try {
					mainView.placesSelected = (ArrayList<City>) mainView.getPlaceList().getSelectedValuesList();
				} catch (ClassCastException c) {
					mainView.placesSelected.clear();
				}
			}
		}
	}

	private class TeamListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				try {
					Season season = mainView.getSeasonList().getSelectedValue();
					mainView.teamSelected = season;
				} catch (ClassCastException c) {
					mainView.teamSelected = null;
				}
			}
		}
	}

	private class TeamIDOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			teamIdentificationView.teamIdentification = teamIdentificationView.getTeamID().toUpperCase();
			if (DataBaseModel.getJListOfIdentifications().containsKey(teamIdentificationView.getTeamID())) {
				System.out.println(teamIdentificationView.getTeamID() + " is already in the list. Pulling up data.");
				addSeasonView = new AddSeasonView(model);
				setAddSeasonView(addSeasonView);
				ArrayList<Season> seasonList = new ArrayList<Season>(DataBaseModel.getJListOfIdentifications()
						.get(teamIdentificationView.teamIdentification.toUpperCase()).getSeasonList().values());
				addSeasonView.nameField.setText(seasonList.get(0).getTeamName());
				addSeasonView.citySelected = seasonList.get(0).getTeamCity();
				addSeasonView.cityComboBox.setSelectedItem(addSeasonView.citySelected);
				addSeasonView.yearSelected = seasonList.get(0).getYear();
				addSeasonView.YearSelectionButton.setText(String.valueOf(seasonList.get(0).getYear()));

			} else {
				System.out.println("Adding teamID " + teamIdentificationView.teamIdentification
						+ ".....this may take up to 10 seconds");
				Identification newIdentification = new Identification(teamIdentificationView.teamIdentification);
				MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfIdentifications(),
						newIdentification.getIdentification().toUpperCase(), newIdentification);
				addSeasonView = new AddSeasonView(model);
				setAddSeasonView(addSeasonView);

			}
			teamIdentificationView.setVisible(false);
		}

	}

	private class YearSelectionOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addSeasonView.yearSelected = (Integer) yearSelectionView.yearComboBox.getSelectedItem();
			addSeasonView.getYearSelectionButton().setText(addSeasonView.yearSelected.toString());
			yearSelectionView.setVisible(false);
			if (addSeasonView.citySelected != null) {
				addSeasonView.okButton.setEnabled(true);
			}

		}
	}

	private class PersonAddStateButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			addPersonStateView = new AddPersonStateView();
			setAddPersonStateView(addPersonStateView);
			addPersonStateView.setStateList(DataBaseModel.getJListOfStates().values().toArray(new State[0]));
		}
	}

	private class PersonAddCityButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			addPersonCityView = new AddPersonCityView();
			setAddPersonCityView(addPersonCityView);
			addPersonCityView.stateSelected = addPersonView.getStateSelected();
			addPersonCityView.setCityList(addPersonView.stateSelected.getListOfCities().values().toArray(new City[0]));
		}
	}

	private class CityAddListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String cityName = addPlaceCityView.getCityName();
			String latitude = addPlaceCityView.getLatitude();
			String longitude = addPlaceCityView.getLongitude();

			State state = new State(addPlaceStateView.getStateSelected().toUpperCase());
			state = MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfStates(), state.getStateName().toUpperCase(),
					state);

			if (!DataBaseModel.getJListOfCities()
					.containsKey(cityName.toUpperCase() + state.getStateName().toUpperCase())) {
				mainView.placesSaved = false;
				City city = new City(cityName, state, true);
				city = state.addToList(city);
				city = MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfCities(),
						cityName.toUpperCase() + state.getStateName().toUpperCase(), city);

				addPlaceStateView.stateSelected = state.getStateName();

				city.setLatitude(latitude);
				city.setLongitude(longitude);

				model.sortJListOfStates();
				model.sortJListOfCities();
				DataBaseModel.getJListOfStates().get(addPlaceStateView.stateSelected).sortListOfCities();

				if (mainView.getPlaceListModel().isEmpty()) {
					mainView.getEditPlace().setEnabled(true);
					mainView.getDeletePlace().setEnabled(true);
					mainView.getAddPeople().setEnabled(true);
					mainView.jmiSave.setEnabled(true);
					mainView.jmiExport.setEnabled(true);
					mainView.jmiClear.setEnabled(true);
				}

				mainView.getPlaceList().setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));

			} else {
				sorryView = new SorryView(cityName);
				setSorryView(sorryView);
			}
			addPlaceCityView.setVisible(false);

		}
	}

	private class StateListPlaceOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addPlaceStateView.stateSelected = (addPlaceStateView.getStateViewComboBox().getSelectedItem().toString());
			addPlaceCityView = new AddPlaceCityView();
			setAddPlaceCityView(addPlaceCityView);
			addPlaceStateView.setVisible(false);
		}
	}

	private class StateAddPersonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			State state = (State) (addPersonStateView.getStateViewComboBox().getSelectedItem());
			if (addPersonCityView != null) {
				addPersonCityView.cityListComboBox.removeAllItems();

				for (City city : state.getListOfCities().values()) {
					addPersonCityView.cityListComboBox.addItem(city);

				}
				addPersonCityView.cityListComboBox.setSelectedItem(0);
				addPersonView.citySelected = addPersonCityView.cityListComboBox.getItemAt(0);
				addPersonView.personCityButton.setText(addPersonView.citySelected.getCityName());
				addPersonView.personOKButton.setEnabled(true);
			}
			addPersonView.stateSelected = state;
			addPersonView.getStateButton().setText(state.toString());
			addPersonView.getCityButton().setEnabled(true);
			addPersonStateView.setVisible(false);
		}
	}

	private class CityAddPersonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			City city = ((City) (addPersonCityView.getCityListComboBox().getSelectedItem()));
			String cityName = city.getCityName();

			addPersonView.citySelected = city;
			addPersonView.getCityButton().setText(cityName);
			addPersonView.getOKButton().setEnabled(true);
			addPersonCityView.setVisible(false);
		}
	}

	private class CityEditPersonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			City city = ((City) (editPersonCityView.getCityListComboBox().getSelectedItem()));
			String cityName = city.getCityName();

			editPersonView.citySelected = city;
			editPersonView.getCityButton().setText(cityName);
			editPersonCityView.setVisible(false);
		}
	}

	private class EditStateOKListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String stateSelected = (String) (editPlaceStateView.getStateViewComboBox().getSelectedItem());
			if (!DataBaseModel.getJListOfCities()
					.containsKey(editPlaceStateView.cityToEdit.toUpperCase() + stateSelected.toUpperCase())
					&& !DataBaseModel.getJListOfStates().get(editPlaceStateView.stateToEdit.toUpperCase())
							.getListOfCities()
							.containsKey(editPlaceStateView.cityToEdit.toUpperCase() + stateSelected.toUpperCase())) {

				DataBaseModel.getJListOfCities().remove(
						editPlaceStateView.cityToEdit.toUpperCase() + editPlaceStateView.stateToEdit.toUpperCase());
				City cityToReplace = DataBaseModel.getJListOfStates().get(editPlaceStateView.stateToEdit.toUpperCase())
						.getListOfCities().remove(editPlaceStateView.cityToEdit.toUpperCase()
								+ editPlaceStateView.stateToEdit.toUpperCase());
				cityToReplace.setState(DataBaseModel.getJListOfStates().get(stateSelected));

				for (Person p : DataBaseModel.getJListOfPersons().values()) {

					if (p.getStateOfBirth().getStateName().equals(editPlaceStateView.stateToEdit)) {
						p.setCityOfBirth(cityToReplace);
						p.setStateOfBirth(DataBaseModel.getJListOfStates().get(stateSelected));
					}
				}

				editPlaceStateView.stateToEdit = stateSelected;

				DataBaseModel.getJListOfStates().get(stateSelected).addToList(cityToReplace);
				DataBaseModel.getJListOfCities()
						.put(editPlaceStateView.cityToEdit.toUpperCase() + stateSelected.toUpperCase(), cityToReplace);
				editPlaceStateView.cityToEdit = cityToReplace.getCityName();

				model.sortJListOfCities();
				model.sortJListOfStates();
				DataBaseModel.getJListOfStates().get(editPlaceStateView.stateToEdit).sortListOfCities();

				mainView.getPlaceList().setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));
				mainView.getPeopleList().setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));
				mainView.getSeasonListModel().clear();
				for (String id : DataBaseModel.getJListOfIdentifications().keySet()) {
					for (Integer year : DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().keySet()) {
						mainView.getSeasonListModel().addElement(
								DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().get(year));
					}
				}
				mainView.peopleSaved = false;
				editPlaceCityView = new EditPlaceCityView();
				setEditPlaceCityView(editPlaceCityView);

				editPlaceCityView.getCityNameField()
						.setText(DataBaseModel.getJListOfCities()
								.get(editPlaceStateView.cityToEdit.toUpperCase() + editPlaceStateView.stateToEdit)
								.getCityName());
				editPlaceCityView.getCityLatitudeField()
						.setText(DataBaseModel.getJListOfCities()
								.get(editPlaceStateView.cityToEdit.toUpperCase() + editPlaceStateView.stateToEdit)
								.getLatitude());
				editPlaceCityView.getCityLongitudeField()
						.setText(DataBaseModel.getJListOfCities()
								.get(editPlaceStateView.cityToEdit.toUpperCase() + editPlaceStateView.stateToEdit)
								.getLongitude());
			} else {
				sorryView = new SorryView(editPlaceStateView.cityToEdit + " " + stateSelected);
			}
			editPlaceStateView.setVisible(false);

		}

	}

	private class EditCityOKListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cityName = editPlaceCityView.getCityName();
			String latitude = editPlaceCityView.getLatitude();
			String longitude = editPlaceCityView.getLongitude();

			boolean shouldEdit = !DataBaseModel.getJListOfCities()
					.containsKey(cityName.toUpperCase() + editPlaceStateView.stateToEdit)
					&& !DataBaseModel.getJListOfStates().get(editPlaceStateView.stateToEdit.toUpperCase())
							.getListOfCities()
							.containsKey(cityName.toUpperCase() + editPlaceStateView.stateToEdit.toUpperCase());

			if (shouldEdit) {
				mainView.peopleSaved = false;
				DataBaseModel.getJListOfCities().remove(
						editPlaceStateView.cityToEdit.toUpperCase() + editPlaceStateView.stateToEdit.toUpperCase());
				City cityToReplace = DataBaseModel.getJListOfStates().get(editPlaceStateView.stateToEdit.toUpperCase())
						.getListOfCities().remove(editPlaceStateView.cityToEdit.toUpperCase()
								+ editPlaceStateView.stateToEdit.toUpperCase());
				cityToReplace.setCityName(cityName);
				cityToReplace.setLatitude(latitude);
				cityToReplace.setLongitude(longitude);

				for (Person p : DataBaseModel.getJListOfPersons().values()) {

					if (p.getCityOfBirth().getCityName().equals(editPlaceStateView.cityToEdit)) {
						p.setCityOfBirth(cityToReplace);
					}
				}

				DataBaseModel.getJListOfStates().get(editPlaceStateView.stateToEdit).addToList(cityToReplace);
				DataBaseModel.getJListOfCities().put(
						editPlaceStateView.cityToEdit.toUpperCase() + editPlaceStateView.stateToEdit.toUpperCase(),
						cityToReplace);

				DataBaseModel.getJListOfStates().get(editPlaceStateView.stateToEdit).sortListOfCities();
				model.sortJListOfCities();
				model.sortJListOfStates();

				editPlaceStateView.cityToEdit = cityToReplace.getCityName();
				mainView.getPlaceList().setListData(DataBaseModel.getJListOfCities().values().toArray(new City[0]));
				mainView.getPeopleList().setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));

			}

			else {
				sorryView = new SorryView(cityName + " " + editPlaceStateView.stateToEdit);
				setSorryView(sorryView);
			}
			editPlaceCityView.setVisible(false);
		}

	}

	private class PersonAddOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String name = addPersonView.personNameText.getText();
			String dateOfBirth = addPersonView.personBirthDateText.getText();

			State stateOfBirth = addPersonView.stateSelected;
			City cityOfBirth = addPersonView.citySelected;
			String dateOfDeath = addPersonView.personDeathText.getText();

			addPersonView.setVisible(false);

			Person personToAdd;
			if (dateOfDeath.equals("")) {
				personToAdd = new Person(name, dateOfBirth, stateOfBirth, cityOfBirth, true);
			} else {
				personToAdd = new Person(name, dateOfBirth, stateOfBirth, cityOfBirth, dateOfDeath, true);
			}
			boolean added = !DataBaseModel.getJListOfPersons().containsKey(name.toUpperCase());

			//
			// boolean added = model.addPerson(name, dateOfBirth, stateOfBirth,
			// cityOfBirth, dateOfDeath);
			// personToAdd = model.personIsInJList(name, dateOfBirth,
			// stateOfBirth, cityOfBirth,dateOfDeath,true);
			//
			//

			if (added) {
				if (!(personToAdd.calculateAge() <= 1)) {
					mainView.placesSaved = false;
					MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfPersons(), name.toUpperCase(), personToAdd);
					model.sortJListOfPersons();

					if (mainView.getPeopleListModel().isEmpty()) {
						mainView.getPieChart().setEnabled(true);
						mainView.getPieChart().setToolTipText(null);
						mainView.getMap().setEnabled(true);
						mainView.getMap().setToolTipText(null);
						mainView.getEditPeople().setEnabled(true);
						mainView.getDeletePeople().setEnabled(true);
						mainView.getAddTeam().setEnabled(true);
					}
					mainView.getPeopleList()
							.setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));
				} else {
					System.out.println("Unfortunately I am not okay with time travelers");
				}
			}

			else {
				sorryView = new SorryView(name);
				setSorryView(sorryView);
			}
		}
	}

	private class PersonEditStateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			editPersonStateView = new EditPersonStateView(model);
			setEditPersonStateView(editPersonStateView);
			editPersonStateView.stateSelected = editPersonView.stateSelected;
			editPersonStateView.setStateList(DataBaseModel.getJListOfStates().values().toArray(new State[0]));

		}
	}

	private class PersonEditCityButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			editPersonCityView = new EditPersonCityView(model);
			setEditPersonCityView(editPersonCityView);

			editPersonCityView.citySelected = editPersonView.citySelected;
			editPersonCityView.stateSelected = editPersonView.stateSelected;

			editPersonCityView.setCityList(
					DataBaseModel.getJListOfStates().get(editPersonCityView.stateSelected.getStateName().toUpperCase())
							.getListOfCities().values().toArray(new City[0]));
		}
	}

	private class PersonEditOKButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Person p = editPersonView.person;
			p.setName(editPersonView.getPersonNameText());
			p.setCityOfBirth(editPersonView.citySelected);
			p.setDateOfBirth(editPersonView.getBirthDateText());
			p.setStateOfBirth(editPersonView.citySelected.getState());
			if (editPersonView.getPersonDeathText().equals("")) {
				p.setDateOfDeath(null);
			} else {
				p.setDateOfDeath(editPersonView.getPersonDeathText());
			}
			editPersonView.setVisible(false);
			if (p.calculateAge() < 1) {
				DataBaseModel.getJListOfPersons().remove(p.getTextName().toUpperCase());
				System.out.println("Unfortunately I am not okay with time travelers");
			}
			model.sortJListOfPersons();
			mainView.peopleSaved = false;
			mainView.getPeopleList().setListData(DataBaseModel.getJListOfPersons().values().toArray(new Person[0]));
		}
	}

	private class StateEditPersonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			State state = (State) (editPersonStateView.getStateViewComboBox().getSelectedItem());
			editPersonView.stateSelected = state;

			if (editPersonCityView != null) {
				editPersonCityView.cityListComboBox.removeAllItems();

				for (City city : state.getListOfCities().values()) {
					editPersonCityView.cityListComboBox.addItem(city);
				}
				editPersonCityView.cityListComboBox.setSelectedItem(0);
				editPersonView.citySelected = editPersonCityView.cityListComboBox.getItemAt(0);
				editPersonView.personCityButton.setText(editPersonView.citySelected.getCityName());
			} else {
				City city[] = state.getListOfCities().values().toArray(new City[0]);
				editPersonView.citySelected = city[0];
				editPersonView.personCityButton.setText(editPersonView.citySelected.getCityName());
			}
			editPersonView.getStateButton().setText(state.toString());

			editPersonStateView.setVisible(false);
		}
	}

	private class DegreeSeparationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			degreeSelectionView = new DegreeSelectionView();
			setDegreeSelectionView(degreeSelectionView);
		}

	}

	private class YearSelectionViewButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			yearSelectionView = new YearSelectionView();
			setYearSelectionView(yearSelectionView);
		}
	}

	private class AddSeasonCityListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addSeasonView.citySelected = (City) addSeasonView.cityComboBox.getSelectedItem();

		}
	}

	private class AddSeasonPeopleListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				try {
					addSeasonView.personMap.clear();
					if (addSeasonView.yearSelected != null) {
						addSeasonView.peopleSelected = (ArrayList<Person>) addSeasonView.personList
								.getSelectedValuesList();
						for (Person p : addSeasonView.peopleSelected) {
							MapClass.putInMapListIfAbsent(addSeasonView.personMap, p.getTextName().toUpperCase(), p);
						}

						if (addSeasonView.yearSelected != null && addSeasonView.citySelected != null) {
							addSeasonView.okButton.setEnabled(true);
						}
					}
				} catch (ClassCastException c) {

				}
			}
		}
	}

	private class ClearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO: Something is wrong with Clearing and Map/PieChart data
			mainView.placesSaved = false;
			mainView.peopleSaved = false;
			mainView.teamsSaved = false;
//			DataBaseModel.getJListOfCities().clear();
//			DataBaseModel.getJListOfStates().clear();
//			DataBaseModel.getJListOfPersons().clear();
//			DataBaseModel.getJListOfIdentifications().clear();
			
			DataBaseModel.setJListOfCities(null);
			DataBaseModel.setJListOfIdentifications(null);
			DataBaseModel.setJListOfPeople(null);
			DataBaseModel.setJListOfStates(null);

			mainView.getPlaceListModel().clear();
			mainView.placeList.setListData(new City[0]);
			mainView.getPeopleListModel().clear();
			mainView.peopleList.setListData(new Person[0]);
			mainView.getSeasonListModel().clear();
			mainView.seasonList = new JList<Season>(mainView.getSeasonListModel());
			mainView.degreeOfSeparation.setEnabled(false);
			mainView.jmiSave.setEnabled(false);
			mainView.jmiExport.setEnabled(false);
			mainView.jmiPieChart.setEnabled(false);
			mainView.jmiMap.setEnabled(false);
			mainView.jmiClear.setEnabled(false);
			mainView.addPeople.setEnabled(false);
			mainView.addTeam.setEnabled(false);
			mainView.editTeam.setEnabled(false);
			mainView.deleteTeam.setEnabled(false);
			mainView.deletePeople.setEnabled(false);
			mainView.deletePlace.setEnabled(false);
			mainView.editPeople.setEnabled(false);
			mainView.editPlace.setEnabled(false);

		}

	}

	private class DegreeSelectionOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (degreeSelectionView.getBaseSelected() != null && degreeSelectionView.getTargetSelected() != null) {
				Person baseCase = degreeSelectionView.getBaseSelected();
				Person targetCase = degreeSelectionView.getTargetSelected();

				ArrayList<ArrayList<Person>> personList = baseCase.getShortestPath(targetCase, 0);
				degreeSelectionView.setVisible(false);
				if (personList.size() < 1) {
					JOptionPane.showMessageDialog(null, "There is no connection", "Loading",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					for (ArrayList<Person> list : personList) {

						degreeSeparationView = new DegreeSeparationView(list);
						setDegreeSeparationView(degreeSeparationView);
					}
				}
			} else {
				System.out.println("You must select a base AND a target Person");
			}
		}
	}

	private class EditCityFinishListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			editPlaceCityView.setVisible(false);
		}
	}

	private class EditStateFinishListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			editPlaceStateView.setVisible(false);
		}
	}

	private class AddSeasonOKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (addSeasonView.citySelected != null) {
				mainView.teamsSaved = false;
				Season seasonToAdd = new Season(addSeasonView.yearSelected, addSeasonView.nameField.getText(),
						addSeasonView.citySelected, addSeasonView.personMap, true);
				seasonToAdd = MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfIdentifications()
						.get(teamIdentificationView.teamIdentification).getSeasonList(), seasonToAdd.getYear(),
						seasonToAdd);
				DataBaseModel.getJListOfIdentifications().get(teamIdentificationView.teamIdentification)
						.sortSeasonList();
				model.sortJListOfIdentifications();
				mainView.getSeasonListModel().clear();
				for (String id : DataBaseModel.getJListOfIdentifications().keySet()) {
					for (Integer year : DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().keySet()) {
						mainView.getSeasonListModel().addElement(
								DataBaseModel.getJListOfIdentifications().get(id).getSeasonList().get(year));
					}
				}
				addSeasonView.setVisible(false);
				mainView.degreeOfSeparation.setEnabled(true);
			}
		}
	}

	/**
	 * Sets the Model of the program that will or is being affiliate with the
	 * Controller.
	 * <P>
	 * 
	 * @param model
	 *            The model affiliated with this Controller.
	 */
	public void setModel(DataBaseModel model) {
		this.model = model;
	}

	/**
	 * Gets the Model that will or is being affiliated with the Controller.
	 * <P>
	 * 
	 * @return StateModel the model
	 */
	public DataBaseModel getModel() {
		return model;
	}

	//

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
		this.mainView.getAddPlace().addActionListener(new AddPlaceListener());
		this.mainView.getEditPlace().addActionListener(new EditPlaceListener());
		this.mainView.getDeletePlace().addActionListener(new DeletePlaceListener());
		this.mainView.getAddPeople().addActionListener(new AddPeopleListener());
		this.mainView.getEditPeople().addActionListener(new EditPeopleListener());
		this.mainView.getDeletePeople().addActionListener(new DeletePeopleListener());
		this.mainView.getDegreeOfSeparation().addActionListener(new DegreeSeparationListener());
		this.mainView.getAddTeam().addActionListener(new AddTeamListener());
		this.mainView.getEditTeam().addActionListener(new EditTeamListener());
		this.mainView.getDeleteTeam().addActionListener(new DeleteTeamListener());
		this.mainView.getPlaceList().addListSelectionListener(new PlaceListListener());
		this.mainView.getPeopleList().addListSelectionListener(new PeopleListListener());
		this.mainView.getSeasonList().addListSelectionListener(new TeamListListener());
		this.mainView.getMap().addActionListener(new MapListener());
		this.mainView.getPieChart().addActionListener(new PieChartListener());
		this.mainView.getLoad().addActionListener(new LoadListener());
		this.mainView.getSave().addActionListener(new SaveListener());
		this.mainView.getImport().addActionListener(new ImportListener());
		this.mainView.getExport().addActionListener(new ExportListener());
		this.mainView.getClear().addActionListener(new ClearListener());

	}

	public void setAddPersonView(AddPersonView addPersonView) {
		this.addPersonView = addPersonView;

		this.addPersonView.getStateButton().addActionListener(new PersonAddStateButtonListener());
		this.addPersonView.getCityButton().addActionListener(new PersonAddCityButtonListener());
		this.addPersonView.getOKButton().addActionListener(new PersonAddOKButtonListener());
		// TODO work on cancel button
		// this.addPersonView.getCancelButton().addActionListener(new
		// CancelListener());
	}

	public void setAddPlaceStateView(AddPlaceStateView addPlaceStateView) {
		this.addPlaceStateView = addPlaceStateView;
		// TODO work on cancel button
		// this.addPlaceStateView.getCancelButton().addActionListener(new
		// CancelListener());
		this.addPlaceStateView.getOKButton().addActionListener(new StateListPlaceOKListener());
	}

	public void setAddPlaceCityView(AddPlaceCityView addPlaceCityView) {
		this.addPlaceCityView = addPlaceCityView;
		// TODO work on cancel button
		// this.addPlaceCityView.getCancelButton().addActionListener(new
		// CancelListener());
		this.addPlaceCityView.getAddButton().addActionListener(new CityAddListener());
	}

	public void setSorryView(SorryView sorryView) {
		this.sorryView = sorryView;
		// TODO work on cancel button
		// this.sorryView.getCancelButton().addActionListener(new
		// CancelListener());
	}

	public void setAddPersonStateView(AddPersonStateView addPersonStateView) {
		this.addPersonStateView = addPersonStateView;
		// TODO work on cancel button
		// this.addPersonStateView.getCancelButton().addActionListener(new
		// CancelListener());
		this.addPersonStateView.getOKButton().addActionListener(new StateAddPersonOKListener());

	}

	public void setAddPersonCityView(AddPersonCityView addPersonCityView) {
		this.addPersonCityView = addPersonCityView;
		this.addPersonCityView.getOKButton().addActionListener(new CityAddPersonOKListener());
		// TODO work on cancel button
		// this.addPersonCityView.getCancelButton().addActionListener(new
		// CancelListener());
	}

	public void setEditPlaceStateView(EditPlaceStateView editPlaceStateView) {
		this.editPlaceStateView = editPlaceStateView;
		this.editPlaceStateView.getOKButton().addActionListener(new EditStateOKListener());
		this.editPlaceStateView.getCancelButton().addActionListener(new EditStateFinishListener());
	}

	public void setEditPlaceCityView(EditPlaceCityView editPlaceCityView) {
		this.editPlaceCityView = editPlaceCityView;
		this.editPlaceCityView.getAddButton().addActionListener(new EditCityOKListener());

		this.editPlaceStateView.getCancelButton().addActionListener(new EditCityFinishListener());
	}

	public void setEditPersonView(EditPersonView editPersonView) {
		this.editPersonView = editPersonView;
		this.editPersonView.getStateButton().addActionListener(new PersonEditStateButtonListener());
		this.editPersonView.getCityButton().addActionListener(new PersonEditCityButtonListener());
		this.editPersonView.getOKButton().addActionListener(new PersonEditOKButtonListener());
		// TODO work on cancel button
		// this.addPersonView.getCancelButton().addActionListener(new
		// CancelListener());
	}

	public void setEditPersonStateView(EditPersonStateView editPersonStateView) {
		this.editPersonStateView = editPersonStateView;
		// TODO work on cancel button
		// this.editPersonStateView.getCancelButton().addActionListener(new
		// CancelListener());
		this.editPersonStateView.getOKButton().addActionListener(new StateEditPersonOKListener());
	}

	public void setEditPersonCityView(EditPersonCityView editPersonCityView) {
		this.editPersonCityView = editPersonCityView;
		// TODO work on cancel button
		// this.editPersonCityView.getCancelButton().addActionListener(new
		// CancelListener());
		this.editPersonCityView.getOKButton().addActionListener(new CityEditPersonOKListener());
	}

	public void setTeamIDView(TeamIDView teamIDView) {
		this.teamIdentificationView = teamIDView;
		// TODO work on cancel button.
		this.teamIdentificationView.getTeamIDOKButton().addActionListener(new TeamIDOKButtonListener());
	}

	public void setYearSelectionView(YearSelectionView yearSelectionView) {
		this.yearSelectionView = yearSelectionView;
		// TODO work on cancel button
		this.yearSelectionView.getYearSelectionOKButton().addActionListener(new YearSelectionOKListener());
	}

	public void setAddSeasonView(AddSeasonView addSeasonView) {
		this.addSeasonView = addSeasonView;
		// TODO work on cancel button
		this.addSeasonView.getYearSelectionButton().addActionListener(new YearSelectionViewButtonListener());
		this.addSeasonView.cityComboBox.addActionListener(new AddSeasonCityListener());
		this.addSeasonView.personList.addListSelectionListener(new AddSeasonPeopleListener());
		this.addSeasonView.okButton.addActionListener(new AddSeasonOKListener());
	}

	public void setDegreeSelectionView(DegreeSelectionView degreeSelectionView) {
		this.degreeSelectionView = degreeSelectionView;
		this.degreeSelectionView.getOkButton().addActionListener(new DegreeSelectionOKListener());
	}

	public void setDegreeSeparationView(DegreeSeparationView degreeSeparationView) {
		this.degreeSeparationView = degreeSeparationView;
	}
}