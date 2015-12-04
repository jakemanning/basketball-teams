import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PeoplAce
 * CS 2334, Section 010
 * March 27, 2015
 * <P>
 * Contains the City of the Person and allows this data to be sorted based on user input
 * </P>
 * Implements Comparable
 * <P>
 * @version 1.2
 */
public class City implements Comparable<City>,Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Stores the city name as a String*/
	private String cityName;

	/**Stores the latitude name as a double*/
	private String latitude;

	/**Stores one or multiple Person objects in an ArrayList*/
	private Map<String,Person> listOfPersons;

	/**Stores the longitude name as a double*/
	private String longitude;

	/**Stores the state name as a State*/
	private State state;

	private String stateName;

	private boolean shouldEdit;

	/**
	 * Initializes the city and creates an ArrayList of type Person 
	 * <P>
	 * @param cityName  City of the person. (String)
	 * @param state State of the person. (State)
	 * @param shouldEdit whether the City should be edited
	 */
	public City(String cityName,State state,boolean shouldEdit) 
	{
		this.shouldEdit = shouldEdit;
		this.stateName = state.getStateName();
		this.state = state;
		this.cityName = cityName;
		this.listOfPersons = new LinkedHashMap<String,Person>();
	}

	/**
	 * Adds the Latitude to a certain City
	 * <P>.
	 * @param latitude	The Latitude of a City.
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;

	}

	/**
	 * Adds the Longitude to a certain City.
	 * <P>
	 * @param longitude	The Longitude of a City.
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;

	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public void setState(State state)
	{
		this.state = state;
		this.stateName = state.getStateName();
	}

//	/**
//	 * Adds a person to a list of Persons.
//	 * <P>
//	 * @param personToAdd : The name of the person to add to the list. (Person)
//	 */
//	public void addToPersonList(Person personToAdd)
//	{
//		this.listOfPersons.add(personToAdd);
//	}

	/**
	 * Compares two cities to see where to place them in an Array
	 * <P>
	 * Algorithm:<br>
	 * 1. See if an object is before, after, or equal to another by comparing them using the compareTo method<br>
	 * <P>
	 * @param o the Object which is casted to a place object to be compared (Object)
	 * @return A 0 if they are the same, or a positive/negative number depending on if one is after or before the other. (int)
	 */
	public int compareTo(City o)
	{
		String state1 = this.stateName.toUpperCase();
		String city1 = this.cityName.toUpperCase();

		String state2 = (o).stateName.toUpperCase();
		String city2 =(o).cityName.toUpperCase();

		if(!(state1.equals(state2)))
		{
			return state1.compareTo(state2);
		}

		else
		{
			return city1.compareTo(city2);
		}

	}

	public boolean getShouldEdit()
	{
		return this.shouldEdit;
	}
	
	/**
	 *Gets and returns the city.
	 * <P>
	 * @return The city of a particular Person. (String)
	 */
	public String getCityName()
	{
		return this.cityName;
	}

	/**
	 * Get and return the latitude of the city.
	 * <P>
	 * @return latitude, the latitude of the city.
	 */
	public String getLatitude()
	{
		return this.latitude;
	}

	/**
	 * Get and return the longitude of the city.
	 * <P>
	 * @return longitude, the longitude of the city.
	 */
	public String getLongitude()
	{
		return this.longitude;
	}

	/**
	 * Get and return the state of a Person
	 * <P>
	 * @return the particular Person's State. (State) 
	 */
	public State getState()
	{
		return this.state;
	}

	public String getStateName()
	{
		return this.stateName;
	}

	/**
	 * Sorts the list of persons by first name.
	 * <P>
	 * Algorithm:<br>
	 * 1. Use FirstNameComparator() to sort the ArrayList by first name<br>
	 * <P>
	 * @return listOfPersons (ArrayList)
	 */
	public Map<String,Person> returnListOfPersons()
	{
		return listOfPersons;
	}

	/**
	 * Returns the city Name as a String.
	 * <P>
	 * @return : city as a String. (String)
	 */
	public String toString()
	{
		return this.state + "," + this.cityName;
	}




}
