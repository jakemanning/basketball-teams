import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.time.IllegalFieldValueException;

/**
 * PeoplAce CS 2334, Section 010 March 27, 2015
 * <P>
 * Reads in a list of persons from a file into an ArrayList, and creates an
 * object for each Person, allowing the data to be accessed and manipulated
 * <P>
 * Implements Serializable
 * </P>
 * 
 * @version 1.5
 */

public class DataBase implements Serializable {

	// /**Stores the people file as a String.*/
	// private String peopleFile;
	//
	// /**Stores the team file as a String.*/
	// private String teamFile;
	//
	// /**Stores the city file as a String.*/
	// private String cityFile;

	/** Serial ID used to implement Serializable in the DataBase Class. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a LinkedHashMap used to store all the Teams in a given data
	 * file, with the keys as Team names, and values as Teams.
	 */
	private static Map<String, Identification> identificationList = new LinkedHashMap<String, Identification>();

	private static Map<String, City> cityList = new LinkedHashMap<String, City>();

	private static Map<String, State> stateList = new LinkedHashMap<String, State>();

	private static Map<String, Person> personList = new LinkedHashMap<String, Person>();

	/**
	 * Constructor for the data files for the project.
	 *
	 */
	public DataBase() {

	}

	// /**
	// * Searches through a list by exact name to find its index
	// * <P>
	// * @param personName :The exact name of the person to be checked against
	// (String)
	// * @return whereItIs :The index of where the person to check is in the
	// ArrayList (int)
	// */
	// public int checkExactName(String personName)
	// {
	//// //Do binary search.
	////
	////
	//// //WhereItIs, the index where the key, nameOnlyPerson A person with only
	// a name is compared to the rest of the list
	// int whereItIs = -1;
	////
	//// //Checks the list of Persons for someone who the exact name as the
	// input and stores its index.
	//// for(int index = 0; index < listOfJListPersons.size();++index)
	//// {
	//// Person person = listOfJListPersons.get(index);
	//// String name = person.getName();
	////
	//// if(name.equalsIgnoreCase(personName))
	//// {
	//// whereItIs = index;
	//// }
	//// }
	////
	// return whereItIs;// whereItIs;
	// }//end of checkName method

	// /**
	// * Returns the Person entered as input by the User.
	// * @param personName Name of the Person searched for by the User.
	// * @return Person under the given name.
	// */
	// public Person getPerson(String personName)
	// {
	// //Do binary search.
	// Collections.sort(listOfPersons);//precondition for binary search
	//
	// //A person just for the purpose of finding a persons's data based on name
	// Person nameOnlyPerson = new Person(personName,"1/1/1970","Dallas,
	// TX",false);//a person just for the purpose of finding a persons's data
	// based on name
	//
	// //WhereItIs, the index where the key, nameOnlyPerson A person with only a
	// name is compared to the rest of the list
	// int whereItIs =
	// Collections.binarySearch(listOfPersons,nameOnlyPerson);//whereItIs, the
	// index where the key, nameOnlyPerson A person with only a name is compared
	// to the rest of the list
	//
	// return listOfPersons.get(whereItIs); //listOfPersons.get(whereItIs);
	// }//End of Get Person Method.

	// /**
	// * Searches through a list by partial name to create a partialPersonList
	// * <P>
	// * @param partialPersonName :The partial name of the person to be checked
	// against. (String)
	// * @return partialPersonList :A list of all matches of the partial name.
	// (ArrayList)
	// */
	// public ArrayList<Person> checkPartialName(String partialPersonName)
	// {
	// //do linear search
	// ArrayList<Person> partialPersonList = new ArrayList<Person>();
	//
	// //Checks the list of Persons that contain the String entered by the User,
	// and returns a list of matches.
	// for(int index = 0; index < listOfPersons.size();++index)
	// {
	// Person person = listOfPersons.get(index);
	// String name = person.getName();
	//
	// if(name.contains(partialPersonName))
	// {
	// partialPersonList.add(person);
	// }
	// }
	//
	// //If the Person was not found in the list, the User will be informed.
	// if(partialPersonList.size() < 1)
	// {
	// System.out.println("Sorry, " + partialPersonName + " isn't in the file: "
	// + peopleFile);
	// partialPersonList = null;
	// }
	//
	// return partialPersonList; // partialPersonList;
	//
	// }//end of Partial Name Method.

	// /**
	// * Check to see if City input from the User is in the list of City.
	// * <P>
	// * @param cityToCheck City input from the User. (String)
	// * @param stateOfBirth State of the City checked by this method
	// * @return cityToCreate City that needs to be added to the list of States.
	// (City)
	// */
	// public static City cityIsInList(String cityToCheck,State stateOfBirth)
	// {
	// //
	// //Instantiates the City
	// City cityToCreate = null;
	//
	//
	//
	// //Checks to see if the particular City is in the list
	// for(City city:listOfJListCities)
	// {
	//
	//
	// if(city != null && city.getCityName().equals(cityToCheck) &&
	// city.getStateName().equals(stateOfBirth.getStateName()))
	// {
	// cityToCreate = city;
	// }
	//
	// }
	//
	// //If City is not in the list, it is added to the list.
	// if(cityToCreate == null)
	// {
	// cityToCreate = new City(cityToCheck,stateOfBirth,true);
	// listOfJListCities.add(cityToCreate);
	// }
	//
	// return cityToCreate; // cityToCreate;
	// }

	/**
	 * Reads data from a particular file to an ArrayList of type Person
	 * <P>
	 * Algorithm:; 1. Uses a FileReader and wraps it in a BufferedReader, and
	 * uses the buffered reader to read each line of comma separated values<br>
	 * 2. Then uses the static method in the String class, split, to break apart
	 * the comma separated values. <br>
	 * 3. Then reads the individual cities into an ArrayList of type String,
	 * containing the latitude and longitude of each.<br>
	 * <P>
	 * 
	 * @param cityFile
	 *            the file from which to get input from
	 * @exception IOException
	 *                Signals that an I/O exception of some sort has occurred
	 *                (Exception)
	 * @return the Map to return, which contains all of the cities
	 */
	public Map<String, City> readCitiesFromFile(String cityFile) throws IOException {

		// Reads file and moves it to be read through the program.
		FileReader fr = new FileReader(cityFile);
		BufferedReader br = new BufferedReader(fr);

		// Reads each individual line of the Buffered Reader.
		String city = br.readLine();

		// While there is still a line to read.
		while (city != null) {
			// Splits City into its respective attributes.
			String cityArray[] = city.split("; ");
			String cityName = cityArray[0];
			String stateName = cityArray[1];
			String latitude = cityArray[2];
			String longitude = cityArray[3];

			// Adds City of the List of Cities.

			State testState = new State(stateName);
			testState = MapClass.putInMapListIfAbsent(stateList, stateName.toUpperCase(), testState);

			City testCity = new City(cityName, testState, false);
			testCity = testState.addToList(testCity);

			// Adds Latitude and Longitude to the particular City.
			testCity.setLatitude(latitude);
			testCity.setLongitude(longitude);
			MapClass.putInMapListIfAbsent(cityList, cityName.toUpperCase() + stateName.toUpperCase(), testCity);

			// Reads in next City.
			city = br.readLine();

		}

		stateList = MapClass.sortByValue(stateList);
		cityList = MapClass.sortByValue(cityList);
		sortJListOfIdentifications();
		personList = MapClass.sortByValue(personList);
		br.close();

		return cityList;
	}// End of Read Cities From File Method.

	/**
	 * Reads data from a particular file to an ArrayList of type Person
	 * <P>
	 * Algorithm:; 1. Uses a FileReader and wraps it in a BufferedReader, and
	 * uses the buffered reader to read each line of comma separated values<br>
	 * 2. Then uses the static method in the String class, split, to break apart
	 * the comma separated values. <br>
	 * 3. Then reads the individual Persons into an ArrayList of type people,
	 * based on whether the line has a date of death or not.<br>
	 * <P>
	 * 
	 * @exception IOException
	 *                Signals that an I/O exception of some sort has occurred
	 *                (Exception)
	 * @return listOfPersons :the ArrayList that is read in, only used for
	 *         testing purposes (ArrayList)
	 */
	public Map<String, Person> readPersonsFromFile(String peopleFile) throws IOException {

		// Reads file and moves it to be read through the program.
		FileReader fr = new FileReader(peopleFile);
		BufferedReader br = new BufferedReader(fr);

		// priming read
		String word = br.readLine();// priming read

		// While there is still a line to be read.
		while (word != null) {
			// Splits Person into its respective attributes.

			String array[] = word.split(",");
			String name = array[0].trim();
			String dateOfBirth = array[1].trim();
			String locationOfBirth = array[2].trim() + ", " + array[3].trim();
			String dateOfDeath = null;

			// If the person Hasn't died yet
			if (array.length < 5) {
				if (!locationOfBirth.contains("?") && !locationOfBirth.contains("0") && !array[2].trim().isEmpty()) {
					try {
						Person personCheck = new Person(name, dateOfBirth, locationOfBirth, false);

						personCheck = MapClass.putInMapListIfAbsent(personList, personCheck.getTextName().toUpperCase(),
								personCheck);
					}
					// If the date is formatted incorrectly.
					catch (ArrayIndexOutOfBoundsException exception) {
						System.out.println("Index Out Of Bounds Exception");
					} catch (IllegalFieldValueException i) {
						System.out.println("dd/mm/yyyy");
					} catch (NumberFormatException ex) {
						System.out.println("This is formatted incorrectly");
					} catch (Exception exception) {
						System.out.println(locationOfBirth + " couldn't be added because it is not in the list");
					}
				}
			}

			// If the person Has died
			else {
				dateOfDeath = array[4].trim();
				if (!locationOfBirth.contains("?") && !locationOfBirth.contains("0") && !array[2].trim().isEmpty()) {
					try {
						Person personCheck = new Person(name, dateOfBirth, locationOfBirth, dateOfDeath, false);

						personCheck = MapClass.putInMapListIfAbsent(personList, personCheck.getTextName().toUpperCase(),
								personCheck);
					}
					// If the date is formatted incorrectly.
					catch (ArrayIndexOutOfBoundsException exception) {
						System.out.println("Index Out Of Bounds Exception");
					} catch (IllegalFieldValueException i) {
						System.out.println("dd/mm/yyyy");
					} catch (NumberFormatException ex) {
						System.out.println("This is formatted incorrectly");
					} catch (Exception exception) {
						System.out.println(locationOfBirth + " couldn't be added because it is not in the list");
					}
				}
			}

			// Priming read
			word = br.readLine();
		}

		stateList = MapClass.sortByValue(stateList);
		cityList = MapClass.sortByValue(cityList);
		personList = MapClass.sortByValue(personList);
		sortJListOfIdentifications();
		br.close();

		return personList; // listOfPersons;

	}// End of Read Data From File Method.

	/**
	 * Read Team file to collect all the data on Teams.
	 * <P>
	 * Algorithm:; 1. Uses a FileReader and wraps it in a BufferedReader, and
	 * uses the buffered reader to read each line of comma separated values<br>
	 * 2. Then uses the static method in the String class, split, to break apart
	 * the comma separated values. <br>
	 * 3. Then reads the individual Team into an ArrayList of type String, each
	 * index containing the Team name, city, state, and roster..<br>
	 * <P>
	 * 
	 * @return teamHashMap a LinkedHashMap with the keys as Team names, and
	 *         values as Teams.
	 * @throws IOException
	 *             signals that an I/O exception has occurred. (Exception)
	 * @param teamFile
	 *            the file to read teams from
	 */
	public Map<String, Identification> readTeamsFromFile(String teamFile) throws IOException {
		// Reads file and moves it to be read through the program.
		FileReader fr = new FileReader(teamFile);
		BufferedReader br = new BufferedReader(fr);

		String lineInfo = br.readLine();
		Identification createdIdentification = null;

		while (lineInfo != null) {
			if (lineInfo.contains(":")) {
				String teamIdentification = lineInfo.substring(0, lineInfo.length() - 1);
				createdIdentification = new Identification(teamIdentification);
				createdIdentification = MapClass.putInMapListIfAbsent(identificationList,
						teamIdentification.toUpperCase(), createdIdentification);
			} else if (lineInfo.contains(";")) {
				String[] seasonInformation = lineInfo.split("; ");
				Integer teamYear = Integer.parseInt(seasonInformation[0]);
				String teamName = seasonInformation[1];

				State teamState = new State(seasonInformation[3]);
				teamState = MapClass.putInMapListIfAbsent(stateList, teamState.getStateName().toUpperCase(), teamState);

				City teamCity = new City(seasonInformation[2], teamState, false);
				teamCity = teamState.addToList(teamCity);
				teamCity = MapClass.putInMapListIfAbsent(cityList,
						teamCity.getCityName().toUpperCase() + teamState.getStateName().toUpperCase(), teamCity);

				Map<String, Person> roster = new LinkedHashMap<String, Person>();

				// Adds each Person from their respective Team to its roster

				for (int index = 4; index < seasonInformation.length; ++index) {
					String personName = seasonInformation[index].toUpperCase();

					boolean inAnotherTeam = false;
					for (String identification : identificationList.keySet()) {
						if (!identification.equals(createdIdentification.getIdentification())) {
							Map<Integer, Season> seasonList = identificationList.get(identification).getSeasonList();

							for (Integer year : seasonList.keySet()) {
								if (year.equals(teamYear)
										&& seasonList.get(year).getRosterMap().containsKey(personName)) {
									inAnotherTeam = true;
								}
							}
						}
					}

					if (personList.containsKey(personName) && !inAnotherTeam) {
						MapClass.putInMapListIfAbsent(roster, personName.toUpperCase(), personList.get(personName));
						MapClass.sortByValue(roster);
					}

				}

				Season lineSeason = new Season(teamYear, teamName, teamCity, roster, false);
				lineSeason = MapClass.putInMapListIfAbsent(createdIdentification.getSeasonList(), teamYear, lineSeason);

				for (Person person : roster.values()) {
					person.getSeasonList().put(teamYear, lineSeason);
				}
			}

			lineInfo = br.readLine();
		}
		sortJListOfIdentifications();
		cityList = MapClass.sortByValue(cityList);
		stateList = MapClass.sortByValue(stateList);

		br.close();

		return identificationList;
	}// End of Read Teams from File Method.

	// /**
	// * Searches for a list of cities within a certain state.
	// * <P>
	// * @param stateToSearch :The specific state to search for cities. (String)
	// * @return cities :An ArrayList of all cities in a particular state.
	// (ArrayList)
	// */
	// public String searchForCitiesInState(String stateToSearch)
	// {
	// Collections.sort(listOfJListCities);
	//
	// //Variable for storing the state to search.
	// String word = stateToSearch.toUpperCase() + ":\n";
	//
	// //Searches the list of cities for the match in state.
	// for(City c:listOfJListCities)
	// {
	// //If a match is found, add its residents to the list.
	// if(c.getState().toString().equalsIgnoreCase(stateToSearch))
	// {
	// word += searchForPeopleInCity(c.toString(),stateToSearch);
	// }
	// }
	// return word;// word;
	// }//End of Search For Cities In State Method.

	// /**
	// * Searches for a list of cities within a certain state.
	// * <P>
	// * @param stateToSearch :The specific state to search for cities. (String)
	// * @return cities :An ArrayList of all cities in a particular state.
	// (ArrayList)
	// */
	// public ArrayList<Person> searchForCitiesInStateObject(String
	// stateToSearch)
	// {
	// //Instantiation of ArrayList to hold a list of Persons and a boolean to
	// check if the correct state was found.
	// ArrayList<Person> personList = new ArrayList<Person>();
	// boolean isIn = false;
	//
	// //Looks through the list of Cities for a match in State.
	// for(City c:listOfJListCities)
	// {
	// if(c.getStateName().toString().equalsIgnoreCase(stateToSearch))
	// {
	// isIn = true;
	//
	// //Adds persons who are within the particular city.
	// for(Person p: searchForPeopleInCityObject(c.toString(),stateToSearch))
	// {
	// personList.add(p);
	// }
	// }
	// }
	//
	// Collections.sort(personList);
	//
	// //If no state matched, return null.
	// if(!isIn)
	// {
	// personList = null;
	// }
	// return personList;// personList;
	// }//End of Search For City In State Method.

	// /**
	// * Searches for a list of cities within a certain state.
	// * <P>
	// * @param stateToSearch :The specific state to search for cities. (String)
	// * @return cities :An ArrayList of all cities in a particular state.
	// (ArrayList)
	// */
	// public ArrayList<City> searchForCitiesInStateCities(String stateToSearch)
	// {
	// //Instantiation of ArrayList to hold a list of Persons and a boolean to
	// check if the correct state was found.
	// ArrayList<City> cityList = new ArrayList<City>();
	// boolean isIn = false;
	//
	// //Looks through the list of Cities for a match in State.
	// for(City c:listOfJListCities)
	// {
	// if(c.getStateName().toString().equalsIgnoreCase(stateToSearch))
	// {
	// isIn = true;
	//
	// cityList.add(c);
	// }
	// }
	//
	// Collections.sort(cityList);
	//
	// //If no state matched, return null.
	// if(!isIn)
	// {
	// cityList = null;
	// }
	// return cityList;// personList;
	// }//End of Search For City In State Method.

	// /**
	// * Searches for Persons living in the cities of a particular State.
	// * <P>
	// * @param stateToSearch State input by the User in which to search through
	// for the city. (String)
	// * @param cityToSearch City input by the User in which to display the list
	// of Persons within the city. (String)
	// * @return word Displayed attributes of Persons within a particular City.
	// (String)
	// */
	// public String searchForCityInState(String stateToSearch,String
	// cityToSearch)
	// {
	// Collections.sort(listOfJListCities);
	//
	// //Construction of String to hold the Person in a city and boolean to show
	// that one was found.
	// String word = "";
	// boolean isIn = false;
	//
	// //Searches the list of Cities for the one input by the User.
	// for(City c:listOfJListCities)
	// {
	// //If a match was found.
	// if(c.toString().equalsIgnoreCase(cityToSearch))
	// {
	// isIn = true;
	// word += searchForPeopleInCity(c.toString(),stateToSearch);
	// }
	// }
	//
	// //If no match was found.
	// if(!isIn)
	// {
	// word = null;
	// }
	//
	// return word;// word;
	// }//End of Search For City In State Method.

	// /**
	// * Searches for Persons living in the cities of a particular State.
	// * <P>
	// * @param stateToSearch State input by the User in which to search through
	// for the city. (String)
	// * @param cityToSearch City input by the User in which to display the list
	// of Persons within the city. (String)
	// * @return word Displayed attributes of Persons within a particular City.
	// (String)
	// */
	// public ArrayList<Person> searchForCityInStateObject(String
	// stateToSearch,String cityToSearch)
	// {
	// Collections.sort(listOfJListCities);
	//
	// //Construction of String to hold the Person in a city and boolean to show
	// that one was found.
	// ArrayList<Person> listToReturn = new ArrayList<Person>();
	// boolean isIn = false;
	//
	// //Searches the list of Cities for the one input by the User.
	// for(City c:listOfJListCities)
	// {
	// //If a match was found.
	// if(c.toString().equalsIgnoreCase(cityToSearch))
	// {
	// isIn = true;
	// listToReturn = searchForPeopleInCityObject(c.toString(),stateToSearch);
	// }
	// }
	//
	// //If a match was found.
	// if(!isIn)
	// {
	// listToReturn = null;
	// }
	//
	// return listToReturn;// listToReturn;
	// }//End of Search For City In State Object Method.

	// /**
	// * Searches for a partial or exact name according to user input.
	// * <P>
	// * Algorithm:<br>
	// * 1.Uses methods checkExactName(String)and checkPartialName(String)<br>
	// * <P>
	// * @param personName :The name of the person to be printed that we are
	// searching for. (String)
	// * @return a String with the Life Data in it (String)
	// */
	// public String searchForLifeData(String personName)
	// {
	// //Variable to receive the index of a specific Person.
	// int whereItIs = checkExactName(personName);//calls the checkName method.
	//
	// //If the index shows that there is not a person in the file.
	// if(whereItIs < 0)
	// {
	// return "Sorry, " + personName + " isn't in the file: " + peopleFile;
	// }
	//
	// return listOfPersons.get(whereItIs).toString();//
	// listOfPersons.get(whereItIs).toString();//otherwise, return the person at
	// the index in the ArrayList listOfPersons.
	// }//end of searchForLifeData method.
	//
	// /**
	// * Searches for a partial or exact name according to user input.
	// * <P>
	// * Algorithm:<br>
	// * 1.Uses methods checkExactName(String)and checkPartialName(String)<br>
	// * <P>
	// * @param personName :The name of the person to be printed that we are
	// searching for. (String)
	// * @return a String with the Life Data in it (String)
	// */
	// public Person searchForLifeDataObject(String personName)
	// {
	// //Variable to receive the index of a specific Person.
	// int whereItIs = checkExactName(personName);//calls the checkName method.
	//
	// //If the index shows that there is not a person in the file.
	// if(whereItIs < 0)
	// {
	// return null;
	// }
	//
	// return listOfPersons.get(whereItIs);//
	// listOfPersons.get(whereItIs);//otherwise, return the person at the index
	// in the ArrayList listOfPersons.
	// }//end of searchForLifeData method.

	// /**
	// * Searches for people in a certain city.
	// * <P>
	// * @param cityToSearch A specific city to search for people. (String)
	// * @param stateToSearch A specific state to search for people. (String)
	// * @return word A String of Person objects in a particular city. (String)
	// */
	// public String searchForPeopleInCity(String cityToSearch,String
	// stateToSearch)
	// {
	// //Sort by the Last Name Comparator
	// Collections.sort(listOfPersons, new LastNameComparator());
	//
	// //Variable of the specific city to search and ArrayList for its
	// respective Latitude and Longitude.
	// String word = cityToSearch + ":\n";
	// ArrayList<String> stringList = new ArrayList<String>();
	//
	// //Searches the list of Person's for the specified Person and adding the
	// Latitude and Longitude to the ArrayList.
	// for(Person p:listOfPersons)
	// {
	// if(p.getCityOfBirth().toString().equalsIgnoreCase(cityToSearch) &&
	// p.getStateOfBirth().toString().equalsIgnoreCase(stateToSearch))
	// {
	// stringList.add(p.getCityOfBirth().getLatitude());
	// stringList.add(p.getCityOfBirth().getLongitude());
	// word += p.toString();
	// }
	// }
	//
	// return word;// word;
	// }//End of Search For People In City Method.

	// /**
	// * Searches for people in a certain city.
	// * <P>
	// * @param cityToSearch A specific city to search for people. (String)
	// * @param stateToSearch A specific state to search for people. (String)
	// * @return word A String of Person objects in a particular city. (String)
	// */
	// public ArrayList<Person> searchForPeopleInCityObject(String
	// cityToSearch,String stateToSearch)
	// {
	// Collections.sort(listOfPersons, new LastNameComparator());
	//
	// //Construction of ArrayList to hold Persons within the matching city and
	// boolean to show a match was found.
	// ArrayList<Person> personList = new ArrayList<Person>();
	// boolean isIn = false;
	//
	// //Searches the list of Persons for a match.
	// for(Person p:listOfPersons)
	// {
	// //If a match was found.
	// if(p.getCityOfBirth().toString().equalsIgnoreCase(cityToSearch) &&
	// p.getStateOfBirth().toString().equalsIgnoreCase(stateToSearch))
	// {
	// isIn = true;
	// personList.add(p);
	// }
	// }
	//
	// //If a match was found.
	// if(!isIn)
	// {
	// personList = null;
	// }
	// return personList; // personList;
	// }//End of Search For People In City Object Method.

	// /**
	// * Gets the Team roster from the search of a specific Team name.
	// * @param teamName Name of the Team in which the User is trying to find
	// the specific roster.
	// * @return The roster of the Team that was searched for.
	// */
	// public String searchForTeamByName(String teamName)
	// {
	// //Variable to hold the name of a specific Team.
	// ID id = searchForValue(teamName);
	// return id.getTeamID();
	//
	// }//End of Search For Team By Name Method.

	// /**
	// * Gets the roster of a Team
	// * <P>
	// * @param teamID Name of a specific Team.
	// * @return The roster of the specified Team.
	// */
	// public ID searchForValue(String teamID)
	// {
	// //Set to hold the to the keys of the LinkedHashMap.
	// Set<String> keySet = idHashMap.keySet();
	//
	// //Variables to show that a match was found and a roster can be returned.
	// boolean isIn = false;
	// String key = null;
	//
	// //Search the set of Keys in the LinkedHashMap.
	// for(String s: keySet)
	// {
	// //If a the Team name input by the User matches a Team Key in the set.
	// if(s.equalsIgnoreCase(teamID))
	// {
	// key = s;
	// isIn = true;
	// }
	// }
	//
	// //If no match was found.
	// if(!isIn)
	// {
	// return null;
	// }
	// //If a match was found.
	// else
	// {
	// return idHashMap.get(key);
	// }
	//
	// }//End of Search For Value Method.

	// /**
	// * Sorts a list of persons by first or last name
	// * <P>
	// * Algorithm:<br>
	// * 1. Compares component to firstName then lastName if needed to sort the
	// ArrayList as wanted<br>
	// * <P>
	// * @param component :String which determines whether the sort will check
	// by first/last name (String)
	// * @return listOfPersons :the sorted ArrayList (ArrayList)
	// */
	// public ArrayList<Person> sortByComponent(String component)
	// {
	// //If the User wants to sort by first name.
	// if(component.equalsIgnoreCase("First"))
	// {
	// Collections.sort(listOfPersons, new FirstNameComparator());
	//
	// }
	// //If the User wants to sort by last name.
	// else if(component.equalsIgnoreCase("Last"))
	// {
	// Collections.sort(listOfPersons, new LastNameComparator());
	// }
	// //If the User wants to sort by age.
	// else if(component.equalsIgnoreCase("Age"))
	// {
	// Collections.sort(listOfPersons, new AgeComparator());
	// }
	//
	// return listOfPersons; // listOfPersons;
	// }//End of Sort By Component Method.

	// /**
	// * Check to see if State input from the User is in the list of States.
	// * <P>
	// * @param stateToCheck State input from the User. (String)
	// * @return stateToCreate State that needs to be added to the list of
	// States. (State)
	// */
	// public static State stateIsInList(String stateToCheck)
	// {
	// State stateToCreate = null;
	//
	// //Searches through the list of States.
	// for(State state:listOfStates)
	// {
	// //If a match was found.
	// if(state.toString().equals(stateToCheck))
	// {
	// stateToCreate = state;
	// }
	//
	// }
	//
	// //If a match was not found, add the State to the list of States.
	// if(stateToCreate == null)
	// {
	// stateToCreate = new State(stateToCheck);
	// listOfStates.add(stateToCreate);
	// }
	//
	// return stateToCreate;// stateToCreate;
	// }

	// public static ArrayList<State> getListOfStatesForView()
	// {
	// Collections.sort(listOfStates);
	// return listOfStates;
	// }

	// /**
	// * Adds a given person to the desired file name.
	// * <P>
	// * @param resultToPrint Result concluded by the User to be saved into the
	// File.
	// * @param fileOutput File in which to be written when saving any
	// information.
	// * @throws IOException Signals that an I/O exception has occurred.
	// (Exception)
	// */
	// public void writeFile(String resultToPrint, String fileOutput) throws
	// IOException
	// {
	//
	// FileOutputStream fileOutputStream = new FileOutputStream(fileOutput);
	// ObjectOutputStream objectOutputStream = new
	// ObjectOutputStream(fileOutputStream);
	// objectOutputStream.writeObject(resultToPrint);
	// objectOutputStream.close();
	// System.out.println("Saved to " + fileOutput);
	//
	// }

	// /**
	// * Reads in the Files to be loaded back to the program.
	// * <P>
	// * @param fileOutput Name of File that data was saved on.
	// * @return The data of the File chosen by the User.
	// * @throws IOException Signals that an I/O exception has occurred.
	// (Exception)
	// * @throws ClassNotFoundException Catch of Exception that file was not
	// found or made.
	// */
	// public String readFile(String fileOutput) throws IOException,
	// ClassNotFoundException
	// {
	// FileInputStream fileInputStream = new FileInputStream(fileOutput);
	// ObjectInputStream objectInputStream = new
	// ObjectInputStream(fileInputStream);
	// String returned = (String) objectInputStream.readObject();
	// objectInputStream.close();
	// return returned;//returned;
	// }

	// /**
	// * Inserts a Person into the list of Persons.
	// * <P>
	// * @param userPerson Person entered by the User.
	// * @return The new list of Persons.
	// */
	// public ArrayList<Person> insertPerson(Person userPerson)
	// {
	// listOfPersons.add(userPerson);
	// Collections.sort(listOfPersons);
	// return listOfPersons;
	// }

	// /**
	// * Verifies that a Person was added to the list of Persons.
	// * <P>
	// * @param person Person to be added to the list.
	// * @return The boolean of whether the Person was added.
	// */
	// public boolean addPeople(String name,String dateOfBirth,State
	// stateOfBirth,City cityOfBirth,String dateOfDeath)
	// {
	// Person personToCreate = null;
	// for(Person person:listOfJListPersons)
	// {
	//
	// if(dateOfDeath != null)
	// {
	//
	// if(person.getName().equals(name) &&
	// person.getDateOfBirth().equals(dateOfBirth) &&
	// person.getStateOfBirth().getStateName().equals(stateOfBirth.getStateName())
	// &&
	// person.getCityOfBirth().getCityName().equals(cityOfBirth.getCityName())
	// && person.getDateOfDeath().equals(dateOfDeath))
	// {
	// return false;
	// }
	// }
	// else
	// {
	// if(person.getName().equals(name) &&
	// person.getDateOfBirth().equals(dateOfBirth) &&
	// person.getStateOfBirth().getStateName().equals(stateOfBirth.getStateName())
	// &&
	// person.getCityOfBirth().getCityName().equals(cityOfBirth.getCityName()))
	// {
	// return false;
	// }
	// }
	//
	//
	// }
	// if(!dateOfDeath.equals(""))
	// {
	// personToCreate = new
	// Person(name,dateOfBirth,stateOfBirth,cityOfBirth,dateOfDeath,true);
	//
	// }
	// else
	// {
	// personToCreate = new
	// Person(name,dateOfBirth,stateOfBirth,cityOfBirth,true);
	// }
	//
	// listOfJListPersons.add(personToCreate);
	// return true;
	//
	// }

	// /**
	// * Verifies that a Person was edited in the list of Persons.
	// * <P>
	// * @param oldPerson The Person that was to be edited.
	// * @return The boolean of whether or not the person was edited.
	// */
	// public boolean editPeople(Person oldPerson,Person person)
	// {
	// int index = 0;
	// for(int i = 0; i < listOfPersons.size(); i++)
	// {
	// if(listOfPersons.get(i).getName().equals(oldPerson.getName()) &&
	// listOfPersons.get(i).getDateOfBirth().equals(oldPerson.getDateOfBirth())
	// &&
	// listOfPersons.get(i).getCityOfBirth().getCityName().equals(oldPerson.getCityOfBirth().getCityName()))
	// {
	// index = i;
	// }
	// }
	//
	// if (listOfPersons.contains(oldPerson))
	// {
	// listOfPersons.set(index,person);
	// System.out.println("True");
	// return true;
	// }
	// else
	// {
	// System.out.println("False");
	// return false;
	// }
	//
	// }

	// /**
	// * Verifies that a Person was deleted in the list of Persons.
	// * <P>
	// * @param person The Person that was to be deleted.
	// * @return The boolean of whether or not the person was deleted.
	// */
	// public boolean deletePeople(Person person)
	// {
	// for(Person p : listOfPersons)
	// {
	// if(p.getName().equals(person.getName()))
	// {
	// listOfPersons.remove(p);
	// return true;
	// }
	// }
	//
	// return false;
	// }

	// /**
	// * Verifies that a Place was added in the list of Places.
	// * <P>
	// * @param location The Person that was to be added.
	// * @return The boolean of whether or not the place was added.
	// */
	// public boolean addPlace(String cityToCheck,State state)
	// {
	//
	// //Instantiates the City
	// City cityToCreate;
	// //Checks to see if the particular City is in the list
	// for(City city:listOfJListCities)
	// {
	// if(city != null && city.getCityName().equals(cityToCheck) &&
	// city.getStateName().equals(state.getStateName()))
	// {
	// return false;
	// }
	//
	// }
	//
	// cityToCreate = new City(cityToCheck,state,true);
	// listOfJListCities.add(cityToCreate);
	//
	//
	// return true; // cityToCreate;
	//
	// }

	// /**
	// * Verifies that a Place was edited in the list of Places.
	// * <P>
	// * @param location The Person that was to be edited.
	// * @return The boolean of whether or not the place was edited.
	// */
	// public boolean editPlace(City city,String cityName,String latitude,String
	// longitude)
	// {
	// boolean shouldEdit = !cityName.equals(city.getCityName()) ||
	// !latitude.equals(city.getLatitude()) ||
	// !longitude.equals(city.getLongitude());
	//
	// return shouldEdit;
	// }

	// /**
	// * Verifies that a Place was deleted in the list of Places.
	// * <P>
	// * @param location The Person that was to be deleted.
	// * @return The boolean of whether or not the place was deleted.
	// */
	// public boolean deletePlace(String location)
	// {
	// return true;
	// }

	// /**
	// * Verifies that a Team was added in the list of Teams.
	// * <P>
	// * @param team The Person that was to be added.
	// * @return The boolean of whether or not the team was added.
	// */
	// public boolean addTeam(Season team)
	// {
	// return true;
	// }

	// /**
	// * Verifies that a Team was edited in the list of Teams.
	// * <P>
	// * @param team The Person that was to be edited.
	// * @return The boolean of whether or not the team was edited.
	// */
	// public boolean editTeam(Season team)
	// {
	// return true;
	// }

	// /**
	// * Verifies that a Team was deleted in the list of Teams.
	// * <P>
	// * @param team The Person that was to be deleted.
	// * @return The boolean of whether or not the team was deleted.
	// */
	// public boolean deleteTeam(Season team)
	// {
	// return true;
	// }

	// /**
	// * Check to see if City input from the User is in the list of City.
	// * <P>
	// * @param cityToCheck City input from the User. (String)
	// * @param stateOfBirth State of the City checked by this method
	// * @return cityToCreate City that needs to be added to the list of States.
	// (City)
	// */
	// public City cityIsInJList(String cityToCheck,State state,boolean
	// shouldEdit)
	// {
	//
	// //Instantiates the City
	// City cityToCreate = null;
	//
	// //Checks to see if the particular City is in the list
	// for(City city:listOfJListCities)
	// {
	//
	// if(city != null && city.getCityName().equals(cityToCheck) &&
	// city.getStateName().equals(state.getStateName()))
	// {
	// cityToCreate = city;
	// }
	//
	// }
	//
	// //If City is not in the list, it is added to the list.
	// if(cityToCreate == null)
	// {
	// cityToCreate = new City(cityToCheck,state,shouldEdit);
	// listOfJListCities.add(cityToCreate);
	//
	// }
	//// cityToCreate.getState().addToList(cityToCreate);
	// return cityToCreate; // cityToCreate;
	// }//End of City Is In List Method.

	// /**
	// * Check to see if State input from the User is in the list of States.
	// * <P>
	// * @param stateToCheck State input from the User. (String)
	// * @return stateToCreate State that needs to be added to the list of
	// States. (State)
	// */
	// public State stateIsInJList(String stateToCheck)
	// {
	// State stateToCreate = null;
	//
	// //Searches through the list of States.
	// for(State state:listOfJListStates)
	// {
	// //If a match was found.
	// if(state.toString().equals(stateToCheck))
	// {
	// stateToCreate = state;
	// }
	//
	// }
	//
	// //If a match was not found, add the State to the list of States.
	// if(stateToCreate == null)
	// {
	// stateToCreate = new State(stateToCheck);
	// listOfJListStates.add(stateToCreate);
	// }
	//
	// return stateToCreate;// stateToCreate;
	// }

	// /**
	// * Check to see if State input from the User is in the list of States.
	// * <P>
	// * @param stateToCheck State input from the User. (String)
	// * @return stateToCreate State that needs to be added to the list of
	// States. (State)
	// */
	// public Person personIsInJList(String name,String dateOfBirth,State
	// stateOfBirth,City cityOfBirth,String dateOfDeath,boolean shouldEdit)
	// {
	// Person personToCreate = null;
	//
	// for(Person person:listOfJListPersons)
	// {
	// if(dateOfDeath != null && !dateOfDeath.equals(""))
	// {
	// if(person.getName().equals(name) &&
	// person.getDateOfBirth().equals(dateOfBirth) &&
	// person.getStateOfBirth().getStateName().equals(stateOfBirth.getStateName())
	// &&
	// person.getCityOfBirth().getCityName().equals(cityOfBirth.getCityName())
	// && person.getDateOfDeath().equals(dateOfDeath))
	// {
	// personToCreate = person;
	// }
	// }
	// else
	// {
	// if(person.getName().equals(name) &&
	// person.getDateOfBirth().equals(dateOfBirth) &&
	// person.getStateOfBirth().getStateName().equals(stateOfBirth.getStateName())
	// &&
	// person.getCityOfBirth().getCityName().equals(cityOfBirth.getCityName()))
	// {
	// personToCreate = person;
	// }
	// }
	//
	// }
	//
	// //If a match was not found, add the State to the list of States.
	// if(personToCreate == null)
	// {
	// if(dateOfDeath != null && !dateOfDeath.equals(""))
	// {
	// personToCreate = new
	// Person(name,dateOfBirth,stateOfBirth,cityOfBirth,dateOfDeath,shouldEdit);
	// }
	// else
	// {
	// personToCreate = new
	// Person(name,dateOfBirth,stateOfBirth,cityOfBirth,shouldEdit);
	// }
	// listOfJListPersons.add(personToCreate);
	// }
	//
	// return personToCreate;
	// }

	// public ArrayList<City> getListOfCities()
	// {
	// Collections.sort(listOfJListCities);
	// return listOfJListCities;
	// }

	// public ArrayList<State> getListOfState()
	// {
	// Collections.sort(listOfStates);
	// return listOfStates;
	// }

	// public ArrayList<Person> getListOfPersons()
	// {
	// Collections.sort(listOfPersons,new LastNameComparator());
	// return listOfPersons;
	// }

	public static Map<String, City> getJListOfCities() {
		return cityList;
	}

	public static Map<String, State> getJListOfStates() {
		return stateList;
	}

	public static Map<String, Person> getJListOfPersons() {
		return personList;
	}

	public static Map<String, Identification> getJListOfIdentifications() {
		return identificationList;
	}

	public void sortJListOfCities() {
		DataBase.cityList = MapClass.sortByValue(cityList);
	}

	public void sortJListOfStates() {
		DataBase.stateList = MapClass.sortByValue(stateList);
	}

	public void sortJListOfPersons() {
		DataBase.personList = MapClass.sortByValue(personList);
	}

	public void sortJListOfIdentifications() {
		DataBase.identificationList = MapClass.sortByValue(identificationList);
		for (String id : identificationList.keySet()) {
			identificationList.get(id).sortSeasonList();
			for (Integer year : identificationList.get(id).getSeasonList().keySet()) {
				identificationList.get(id).getSeasonList().get(year).sortRosterMap();
			}
		}
	}

	public static void setJListOfCities(Map<String, City> cityList) {
		DataBase.cityList = cityList;
	}

	public static void setJListOfIdentifications(Map<String, Identification> identificationList) {
		DataBase.identificationList = identificationList;
	}

	public static void setJListOfStates(Map<String, State> stateList) {
		DataBase.stateList = stateList;
	}

	public static void setJListOfPeople(Map<String, Person> personList) {
		DataBase.personList = personList;
	}
	// public void setCityFile(String cityFile)
	// {
	// this.cityFile = cityFile;
	// }
	//
	// public void setCityList(ArrayList<City> listOfJListCities)
	// {
	// if(this.listOfJListCities == null)
	// {
	// this.listOfJListCities = new ArrayList<City>();
	// }
	// for(City c:listOfJListCities)
	// {
	// City m = cityIsInJList(c.getCityName(),c.getState(),false);
	// this.listOfJListCities.add(m);
	// }
	// }
	//
	// public void setPeopleList(ArrayList<Person> listOfJListPersons)
	// {
	// if(this.listOfJListPersons == null)
	// {
	// this.listOfJListPersons = new ArrayList<Person>();
	// }
	// for(Person p:listOfJListPersons)
	// {
	// Person m =
	// personIsInJList(p.getName(),p.getDateOfBirth(),p.getStateOfBirth(),p.getCityOfBirth(),p.getDateOfDeath(),false);
	// this.listOfJListPersons.add(m);
	// }
	// }
	//
	// public LinkedHashMap<String,ID> getTeamMap()
	// {
	// return idHashMap;
	// }
	//
	// public void putInIDMap(ID id)
	// {
	// idHashMap.putIfAbsent(id.getTeamID(), id);
	//
	// }
	//
	// public void whatIsIt(String t)
	// {
	// System.out.println(t);
	// }
}// end of DataBase Class
