import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * PeoplAce CS 2334, Section 010 March 27, 2015
 * <P>
 * Contains data on people with a name, date of birth, city and state of birth,
 * age at the present time; or when the person died, and the date of the
 * person's death, if included.
 * </P>
 * 
 * @version 1.1
 */

public class Person implements Comparable<Person>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Stores the age of each person from date of birth to present or date of
	 * death, as an int
	 */
	private int age;

	/** Stores the city of each person as a String */
	private City cityOfBirth;

	/** Stores the date of birth of each person as a String */
	private String dateOfBirth;

	/** Stores the date of death of each person as a String */
	private String dateOfDeath;

	/** Stores the first name of each person as a String */
	private String firstName;

	private String fullName;

	/** Stores the last name of each person as a String */
	private String lastName;

	/** Stores the last name of each person as a String */
	private String middleName;

	/** Stores the state of each person as a String */
	private State stateOfBirth;

	private String plainText;

	private boolean shouldEdit;
	
	private static int shortestPathSize = Integer.MAX_VALUE;

	private Map<Integer, Season> seasonList = new LinkedHashMap<Integer, Season>();
	
	public Season season;

	/**
	 * A Constructor which allows a person to be initialized with any desired
	 * name, date of birth, city and state of birth, and age at the present
	 * time.
	 * <P>
	 * 
	 * @param name
	 *            :the name of the person (String)
	 * @param dateOfBirth
	 *            :the date of the person (String)
	 * @param locationOfBirth
	 *            :the city and state of the person (String)
	 * @throws ArrayIndexOutOfBoundsException
	 *             to check if the date of birth or date of death is read in
	 *             correctly
	 */
	public Person(String name, String dateOfBirth, String locationOfBirth, boolean shouldEdit)
			throws ArrayIndexOutOfBoundsException, IllegalFieldValueException, NumberFormatException {

		this.shouldEdit = shouldEdit;
		// Split aspects of the Person to the respective variables.
		String totalName[] = name.split(" ");
		int nameSize = totalName.length;

		this.firstName = totalName[0];

		if (nameSize > 1) {
			this.lastName = totalName[totalName.length - 1];
			this.fullName = this.lastName + "," + this.firstName;
			this.plainText = this.firstName;
			if (nameSize > 2) {
				this.middleName = " ";

				// Checks to see if the Person has a middle Name.
				for (int index = 1; index < totalName.length - 1; ++index) {
					this.middleName += totalName[index];

					if (index != totalName.length - 2) {
						this.middleName += " ";
					}
				}
				this.fullName += this.middleName;
				this.plainText += this.middleName;
			}
			this.plainText += " " + this.lastName;

		} else {
			this.lastName = this.firstName;
			this.fullName = this.lastName;
			this.plainText = this.lastName;

		}

		this.dateOfBirth = dateOfBirth;

		// Splits city and state of birth in their respective attributes.
		String[] totalLocation = locationOfBirth.split(", ");

		this.stateOfBirth = new State(totalLocation[1]);
		this.stateOfBirth = MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfStates(),
				this.stateOfBirth.getStateName().toUpperCase(), this.stateOfBirth);

		this.cityOfBirth = new City(totalLocation[0], this.stateOfBirth, this.shouldEdit);
		this.cityOfBirth = this.stateOfBirth.addToList(this.cityOfBirth);

		this.cityOfBirth = MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfCities(),
				this.cityOfBirth.getCityName().toUpperCase() + this.stateOfBirth.getStateName().toUpperCase(),
				this.cityOfBirth);

		this.age = calculateAge();

	}// end of Person constructor

	/**
	 * A Constructor which allows a person to be initialized with any desired
	 * name, date of birth, city and state of birth, age when the person died,
	 * and date when the person died.
	 * <P>
	 * 
	 * @param name
	 *            :the name of the person (String)
	 * @param dateOfBirth
	 *            :the date of the person (String)
	 * @param locationOfBirth
	 *            :the city and state of the person(String)
	 * @param dateOfDeath
	 *            :the date of death of the person (String)
	 * @throws ArrayIndexOutOfBoundsException
	 *             :to check if the date of birth or date of death is read in
	 *             correctly (Exception)
	 */
	public Person(String name, String dateOfBirth, String locationOfBirth, String dateOfDeath, boolean shouldEdit)
			throws ArrayIndexOutOfBoundsException, IllegalFieldValueException, NumberFormatException {

		this.shouldEdit = shouldEdit;
		// Split aspects of the Person to the respective variables.
		String totalName[] = name.split(" ");
		int nameSize = totalName.length;
		this.firstName = totalName[0];

		if (nameSize > 1) {
			this.lastName = totalName[totalName.length - 1];
			this.fullName = this.lastName + "," + this.firstName;
			this.plainText = this.firstName;
			if (nameSize > 2) {
				this.middleName = " ";

				// Checks to see if the Person has a middle Name.
				for (int index = 1; index < totalName.length - 1; ++index) {
					this.middleName += totalName[index];

					if (index != totalName.length - 2) {
						this.middleName += " ";
					}
				}
				this.fullName += this.middleName;
				this.plainText += this.middleName;
			}
			this.plainText += " " + this.lastName;

		} else {
			this.lastName = this.firstName;
			this.fullName = this.lastName;
			this.plainText = this.lastName;

		}

		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;

		// Splits city and state of birth in their respective attributes.
		String[] totalLocation = locationOfBirth.split(", ");

		this.stateOfBirth = new State(totalLocation[1]);
		this.stateOfBirth = MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfStates(),
				this.stateOfBirth.getStateName().toUpperCase(), this.stateOfBirth);

		this.cityOfBirth = new City(totalLocation[0], this.stateOfBirth, this.shouldEdit);
		this.cityOfBirth = this.stateOfBirth.addToList(this.cityOfBirth);

		this.cityOfBirth = MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfCities(),
				this.cityOfBirth.getCityName().toUpperCase() + this.stateOfBirth.getStateName().toUpperCase(),
				this.cityOfBirth);
		this.age = calculateAge();

	}// end of Person constructor

	/**
	 * A Constructor which allows a person to be initialized with any desired
	 * name, date of birth, city and state of birth, and age at the present
	 * time.
	 * <P>
	 * 
	 * @param name
	 *            :the name of the person (String)
	 * @param dateOfBirth
	 *            :the date of the person (String)
	 * @param locationOfBirth
	 *            :the city and state of the person (String)
	 * @param stateOfBirth
	 *            the state of the person
	 * @param shouldEdit
	 *            to test whether the person should be edited or not
	 * @throws ArrayIndexOutOfBoundsException
	 *             to check if the date of birth or date of death is read in
	 *             correctly
	 */
	public Person(String name, String dateOfBirth, State stateOfBirth, City cityOfBirth, boolean shouldEdit)
			throws ArrayIndexOutOfBoundsException, IllegalFieldValueException, NumberFormatException {
		this.shouldEdit = shouldEdit;
		// Split aspects of the Person to the respective variables.
		String totalName[] = name.split(" ");
		int nameSize = totalName.length;
		this.firstName = totalName[0];

		if (nameSize > 1) {
			this.lastName = totalName[totalName.length - 1];
			this.fullName = this.lastName + "," + this.firstName;
			this.plainText = this.firstName;
			if (nameSize > 2) {
				this.middleName = " ";

				// Checks to see if the Person has a middle Name.
				for (int index = 1; index < totalName.length - 1; ++index) {
					this.middleName += totalName[index];

					if (index != totalName.length - 2) {
						this.middleName += " ";
					}
				}
				this.fullName += this.middleName;
				this.plainText += this.middleName;
			}
			this.plainText += " " + this.lastName;

		} else {
			this.lastName = this.firstName;
			this.fullName = this.lastName;
			this.plainText = this.lastName;

		}

		this.dateOfBirth = dateOfBirth;

		this.stateOfBirth = stateOfBirth;
		this.cityOfBirth = cityOfBirth;
		this.age = calculateAge();// calls the calculateAge method

	}// end of Person constructor

	/**
	 * A Constructor which allows a person to be initialized with any desired
	 * name, date of birth, city and state of birth, age when the person died,
	 * and date when the person died.
	 * <P>
	 * 
	 * @param name
	 *            :the name of the person
	 * @param dateOfBirth
	 *            :the date of the person
	 * @param stateOfBirth
	 *            :the state of the person
	 * @param cityOfBirth
	 *            the city of the person
	 * @param dateOfDeath
	 *            :the date of death of the person
	 * @throws ArrayIndexOutOfBoundsException
	 *             :to check if the date of birth or date of death is read in
	 *             correctly
	 */
	public Person(String name, String dateOfBirth, State stateOfBirth, City cityOfBirth, String dateOfDeath,
			boolean shouldEdit)
					throws ArrayIndexOutOfBoundsException, IllegalFieldValueException, NumberFormatException {
		this.shouldEdit = shouldEdit;
		// Split aspects of the Person to the respective variables.
		String totalName[] = name.split(" ");
		int nameSize = totalName.length;
		this.firstName = totalName[0];

		if (nameSize > 1) {
			this.lastName = totalName[totalName.length - 1];
			this.fullName = this.lastName + "," + this.firstName;
			this.plainText = this.firstName;
			if (nameSize > 2) {
				this.middleName = " ";

				// Checks to see if the Person has a middle Name.
				for (int index = 1; index < totalName.length - 1; ++index) {
					this.middleName += totalName[index];

					if (index != totalName.length - 2) {
						this.middleName += " ";
					}
				}
				this.fullName += this.middleName;
				this.plainText += this.middleName;
			}
			this.plainText += " " + this.lastName;

		} else {
			this.lastName = this.firstName;
			this.fullName = this.lastName;
			this.plainText = this.lastName;

		}

		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;

		this.stateOfBirth = stateOfBirth;
		this.cityOfBirth = cityOfBirth;

		this.age = calculateAge();// calls the calculateAge method

	}// end of Person constructor

	public void setCityOfBirth(City cityOfBirth) {
		this.cityOfBirth = cityOfBirth;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public String getDateOfDeath() {
		return this.dateOfDeath;
	}

	public void setStateOfBirth(State stateOfBirth) {
		this.stateOfBirth = stateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		calculateAge();
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
		calculateAge();
	}

	public void setName(String fullName) {

		String totalName[] = fullName.split(" ");
		int nameSize = totalName.length;

		this.firstName = totalName[0];

		if (nameSize > 1) {
			this.lastName = totalName[totalName.length - 1];
			this.fullName = this.lastName + "," + this.firstName;
			this.plainText = this.firstName;
			if (nameSize > 2) {
				this.middleName = " ";

				// Checks to see if the Person has a middle Name.
				for (int index = 1; index < totalName.length - 1; ++index) {
					this.middleName += totalName[index];

					if (index != totalName.length - 2) {
						this.middleName += " ";
					}
				}
				this.fullName += this.middleName;
				this.plainText += this.middleName;
			}
			this.plainText += " " + this.lastName;

		} else {
			this.lastName = this.firstName;
			this.fullName = this.lastName;
			this.plainText = this.lastName;

		}
	}

	/**
	 * Calculates age of a Person object
	 * <P>
	 * Algorithm:<br>
	 * 1. Uses the static method in the String Class to break apart the date of
	 * birth and date of death if present. <br>
	 * 2. Uses try and catch to check if an ArrayIndexOutOfBoundsException is
	 * reached.<br>
	 * 3. Uses the inherent classes in the Joda Framework to calculate the
	 * difference in years between a period of time.<br>
	 * <P>
	 * 
	 * @return number of years between date of birth and the present, or date of
	 *         death if dead. 0 if the person has no readable date of birth or
	 *         date of Death (int)
	 * @throws ArrayIndexOutOfBoundsException
	 *             to check if the date of birth or date of death is read in
	 *             correctly (Exception)
	 */
	public int calculateAge() throws ArrayIndexOutOfBoundsException, IllegalFieldValueException, NumberFormatException {

		// If the person hasn't died yet
		if (this.dateOfDeath == null) {
			if (!this.dateOfBirth.equals("00/00/0000")) {
				String splitAge[] = this.dateOfBirth.split("/");
				int year = Integer.parseInt(splitAge[2]);
				int month = Integer.parseInt(splitAge[1]);
				int day = Integer.parseInt(splitAge[0]);

				LocalDate birthdate = new LocalDate(year, month, day); // Birth
																		// date
				LocalDate now = new LocalDate(); // Today's date
				Period period;

				period = new Period(birthdate, now, PeriodType.yearMonthDay());

				return period.getYears();
			}
		}
		// If person has died.
		else {
			if (!this.dateOfBirth.equals("00/00/0000")) {

				String splitAge[] = this.dateOfBirth.split("/");
				int year = Integer.parseInt(splitAge[2]);
				int month = Integer.parseInt(splitAge[1]);
				int day = Integer.parseInt(splitAge[0]);

				LocalDate birthDate = new LocalDate(year, month, day); // Birth
																		// date

				splitAge = this.dateOfDeath.split("/");
				year = Integer.parseInt(splitAge[2]);
				month = Integer.parseInt(splitAge[1]);
				day = Integer.parseInt(splitAge[0]);

				LocalDate deathDate = new LocalDate(year, month, day); // Death
																		// date

				Period period;

				period = new Period(birthDate, deathDate, PeriodType.yearMonthDay());

				return period.getYears();
			}
		}

		return 0;// returns default value which tells the toString method to
					// "Skip"
	}// end of calculateAge method

	/**
	 * Compares the full name of all Persons within the list to be compared.
	 * <P>
	 * 
	 * @return name : Integer value of the comparison of the two names. (int)
	 */
	public int compareTo(Person o) {
		String lastName1 = this.lastName.toUpperCase();
		String firstName1 = this.firstName.toUpperCase();
		String lastName2 = o.getLastName().toUpperCase();
		String firstName2 = o.getFirstName().toUpperCase();
		if (!(lastName1.equals(lastName2))) {
			return lastName1.compareTo(lastName2);
		} else {
			return firstName1.compareTo(firstName2);
		}

	}

	/**
	 * Gets the cityOfBirth City Object, used for testing purposes
	 * <P>
	 * 
	 * @return this.cityOfBirth :the name of this person object (City)
	 */
	public City getCityOfBirth() {
		return this.cityOfBirth;
	}

	/**
	 * Gets first Name, used for testing purposes
	 * <P>
	 * 
	 * @return this.firstName:the name of this person object (String)
	 */
	public String getFirstName() {
		return this.firstName;
	}

	public String getTextName() {
		return this.plainText;
	}

	/**
	 * Gets last Name, used for testing purposes
	 * <P>
	 * 
	 * @return this.lastName :the name of this person object(String)
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Gets middle Name, used for testing purposes
	 * <P>
	 * 
	 * @return this.middleName :the name of this person object (String)
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Gets fullName, used for testing purposes
	 * <P>
	 * 
	 * @return this.fullName :the name of this person object (String)
	 */
	public String getCommaName() {
		return this.fullName;
	}

	/**
	 * Gets the stateOfBirth State object, used for testing purposes
	 * <P>
	 * 
	 * @return this.stateOfBirth :the name of this State object (State)
	 */
	public State getStateOfBirth() {
		return this.stateOfBirth;
	}

	public boolean getShouldEdit() {
		return this.shouldEdit;
	}

	public Map<Integer, Season> getSeasonList() {
		this.seasonList = MapClass.sortByValue(this.seasonList);
		return this.seasonList;
	}

	public ArrayList<Person> getNeighbors() {
		Map<String, Person> playedInMap = new LinkedHashMap<String, Person>();

		for (Season season : seasonList.values()) {
			for (String personKey : season.getRosterMap().keySet()) {

				if (!personKey.equalsIgnoreCase(this.plainText)) {
					MapClass.putInMapListIfAbsent(playedInMap, personKey, season.getRosterMap().get(personKey));
					this.season = season;
				}
			}
		}

		ArrayList<Person> personList = new ArrayList<Person>(playedInMap.values());
		return personList;
	}

	public ArrayList<ArrayList<Person>> getShortestPath(Person target, int degrees) {
		if (degrees > 2) {
			return null;
		}

		// BaseCase

		if (target.getTextName().equalsIgnoreCase(this.plainText)) {
			ArrayList<ArrayList<Person>> temporaryArrayList = new ArrayList<ArrayList<Person>>();
			ArrayList<Person> temporaryPlayer = new ArrayList<Person>();
			temporaryPlayer.add(target);
			temporaryArrayList.add(temporaryPlayer);
			return temporaryArrayList;
		}

		ArrayList<Person> neighbors = this.getNeighbors();
		ArrayList<ArrayList<Person>> listOfNeighborPaths = new ArrayList<ArrayList<Person>>();

		for (Person neighbor : neighbors) {
			ArrayList<ArrayList<Person>> tempList = neighbor.getShortestPath(target, degrees + 1);
			if (tempList != null) {
				listOfNeighborPaths.addAll(tempList);
			}
		}
		
		ArrayList<Person> shortestPath = new ArrayList<Person>();
		ArrayList<ArrayList<Person>> shortestPathList = new ArrayList<ArrayList<Person>>();
		
		if(listOfNeighborPaths.size() > 0) {
			shortestPath = listOfNeighborPaths.get(0);
			Person.shortestPathSize = shortestPath.size();
			for (ArrayList<Person> path : listOfNeighborPaths) {
				if (path.size() < shortestPath.size()) {
					shortestPath = path;
					Person.shortestPathSize = shortestPath.size();

				}
			}
			
			for(ArrayList<Person> secondPath: listOfNeighborPaths) {
				
				if(Person.shortestPathSize == secondPath.size()) {
					secondPath.add(0,this);
					if (!secondPath.contains(target)) {
						secondPath.add(target);
					}
					shortestPathList.add(secondPath);
				}
			}
			
		}
		return shortestPathList;
	}

	/**
	 * Overrides the toString() method in the object Superclass to print out all
	 * of the Life History Data
	 * <P>
	 * 
	 * @return a String containing the life History Data in a Person (String)
	 */
	@Override
	public String toString() {
		String stringToReturn = this.fullName + "," + this.dateOfBirth + "," + this.cityOfBirth.getCityName() + ","
				+ this.stateOfBirth;

		if (this.dateOfDeath == null && this.age > 0)// if the person hasn't
														// died yet, and has a
														// positive age.
		{
			return stringToReturn;
		}

		else if (this.age > 0)// if the person Has died, and has a positive age.
		{
			return stringToReturn + "," + this.dateOfDeath;
		}

		return "WARNING, DATA IS INCORRECT OR NON-EXISTENT.";// if the person's
																// age is
																// negative
	}// end of toString method

}// end of Person Class
