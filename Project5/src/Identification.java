import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PeoplAce CS 2334, Section 010 March 27, 2015
 * <P>
 * Creates each Team by compiling all the needed attributes. This includes a
 * Team Name, City and an ArrayList of team members.
 * </P>
 * 
 * @version 1.0
 */
public class Identification implements Serializable, Comparable<Identification> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String teamIdentification;

	private Map<Integer, Season> seasonList = new LinkedHashMap<Integer, Season>();

	/**
	 * Constructs a Team object which contains the name of the team, the city of
	 * the team, and the state of the team.
	 * <P>
	 * 
	 * @param teamIdentification
	 *            the input to take in when creating new Identifications for
	 *            teams
	 */
	public Identification(String teamIdentification) {
		this.teamIdentification = teamIdentification;

	}// End of Team Constructor.

	public String getIdentification() {
		return teamIdentification;
	}

	public Season addToSeasonMap(Season seasonToAdd) {
		int year = seasonToAdd.getYear();
		Season season = seasonList.putIfAbsent(year, seasonToAdd);
		return season;
	}
	//
	// public void setSortedMap(Map<Integer,Season> seasonMap)
	// {
	// this.seasonMap = seasonMap;
	// }

	public void sortSeasonList() {
		seasonList = MapClass.sortByValue(seasonList);
	}

	public Map<Integer, Season> getSeasonList() {
		return seasonList;
	}

	/**
	 * Overrides the toString() method in the object Superclass to print out all
	 * of the Life History Data
	 * <P>
	 * 
	 * @return a String containing the life History Data in a Person (String)
	 */
	public String toString() {
		return teamIdentification;
	}// end of To String Method.

	@Override
	public int compareTo(Identification identification) {
		return this.teamIdentification.compareTo(identification.teamIdentification);
	}

}// End of Team Class.
