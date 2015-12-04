import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PeoplAce CS 2334, Section 010 March 27, 2015
 * <P>
 * Contains the Place, including the city and state, and allows this data to be
 * sorted based on user input
 * </P>
 * Implements Comparable.
 * 
 * @version 1.0
 */

public class State implements Comparable<State>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Stores the cities of a state in an ArrayList of type City */
	private Map<String, City> listOfCities;

	/** Stores the state of each as a String */
	private String stateName;

	/**
	 * Constructs a State Object from the String stateOfBirth and an ArrayList
	 * of City objects
	 * <P>
	 * 
	 * @param stateName
	 *            The state. (String)
	 */
	public State(String stateName) {
		this.stateName = stateName;
		listOfCities = new LinkedHashMap<String, City>();
	}

	/**
	 * Compares two State Objects alphabetically to see where to place an object
	 * that needs to be added
	 * <P>
	 * 
	 * @param o
	 *            :the Object which is casted to a place object to be compared.
	 *            (Object)
	 * @return Numerical value in which to indicate the order the States should
	 *         appear. (int)
	 */
	public int compareTo(State o) {
		return this.stateName.toUpperCase().compareTo(o.stateName.toUpperCase());
	}

	/**
	 * Get and return the state Name
	 * <P>
	 * 
	 * @return state Name (String)
	 */
	public String getStateName() {
		return this.stateName;
	}

	/**
	 * Create the list of cities that are accessed through the State class.
	 * <P>
	 * 
	 * @param cityToAdd
	 *            :City of the particular Person. (City)
	 * @return cityOfBirth : City as a City Object. (City)
	 */
	public City addToList(City cityToAdd) {
		City city = MapClass.putInMapListIfAbsent(listOfCities,
				cityToAdd.getCityName().toUpperCase() + cityToAdd.getStateName().toUpperCase(), cityToAdd);

		return city;
	}// End of List Method.

	/**
	 * Returns the list of cities that is needed when searching through an
	 * entire state.
	 * <P>
	 * 
	 * @return List of all the cities within a particular state. (ArrayList)
	 */
	public Map<String, City> getListOfCities() {
		return listOfCities;
	}// End of Return List of Cities Method.

	public void sortListOfCities() {
		this.listOfCities = MapClass.sortByValue(listOfCities);
	}

	/**
	 * Converts any passed parameter into a String type.
	 * <P>
	 * 
	 * @return String type of values passed through the method. (String)
	 */
	public String toString() {
		return this.stateName;
	}// End of To String Method.
}
