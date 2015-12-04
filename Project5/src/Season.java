import java.io.Serializable;
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
public class Season implements Serializable, Comparable<Season> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Stores the roster of People as an ArrayList. */
	private Map<String, Person> roster;

	/** Stores the city of each team as a String. */
	private City teamCity;

	/** Stores the name of each team as a String. */
	private String teamName;

	private Integer teamYear;

	private boolean shouldEdit;

	private String rosterForSaving;

	/**
	 * Constructs a Team object which contains the name of the team, the city of
	 * the team, and the state of the team.
	 * <P>
	 * 
	 * @param teamYear
	 *            the year the season indicates for the team
	 * @param teamName
	 *            The name of each team as a String.
	 * @param teamCity
	 *            The city of each team as a String.
	 * @param roster
	 *            List of Person that belong to a specific Team.
	 * @param shouldEdit
	 *            whether the season should be edited or not; aka should be
	 *            constant
	 */
	public Season(Integer teamYear, String teamName, City teamCity, Map<String, Person> roster, boolean shouldEdit) {
		this.teamYear = teamYear;
		this.teamName = teamName;
		this.teamCity = teamCity;
		this.roster = roster;
		this.shouldEdit = shouldEdit;
	}// End of Team Constructor.

	/**
	 * Allows other classes to access the roster of people.
	 * <P>
	 * 
	 * @return roster, the arrayList of people.
	 */
	public Map<String, Person> getRosterMap() {
		return roster;
	}// End of Get Roster Method.

	/**
	 * Allows other classes to access the team city.
	 * <P>
	 * 
	 * @return teamCity, the city of the team.
	 */
	public City getTeamCity() {
		return teamCity;
	}

	public int getYear() {
		return this.teamYear;
	}

	/**
	 * Allows other classes to access the team name.
	 * <P>
	 * 
	 * @return teamName, the name of the team.
	 */
	public String getTeamName() {
		return teamName;
	}

	public boolean getShouldEdit() {
		return shouldEdit;
	}

	public void sortRosterMap() {
		this.roster = MapClass.sortByValue(roster);
	}

	@Override
	public int compareTo(Season o) {
		return this.teamYear.compareTo(o.teamYear);
	}

	public String getMembersForSaving() {
		return this.rosterForSaving;
	}

	/**
	 * Overrides the toString() method in the object Superclass to print out all
	 * of the Life History Data
	 * <P>
	 * 
	 * @return a String containing the life History Data in a Person (String)
	 */
	public String toString() {
		this.rosterForSaving = "";
		MapClass.sortByValue(roster);
		for (String personName : roster.keySet()) {
			rosterForSaving += "; " + roster.get(personName.toUpperCase()).getTextName();
		}
		return this.teamYear + "; " + this.teamName + "; " + this.teamCity + "; " + this.teamCity.getState()
				+ rosterForSaving;

	}// end of To String Method.

}// End of Team Class.
