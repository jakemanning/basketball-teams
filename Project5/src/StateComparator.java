import java.util.Comparator;

/**
 * PeoplAce CS 2334, Section 010 March 27, 2015
 * <P>
 * StateComparator compares one state to another city in specific ways
 * </P>
 * Implements Comparator;
 * 
 * @version 1.0
 */
public class StateComparator implements Comparator<City> {
	/**
	 * Compares two people's cities
	 * <P>
	 * 
	 * @param city
	 *            the city to compare
	 * @param anotherCity
	 *            the second city to compare
	 * @return positive if it is after what you're comparing, 0 if at same
	 *         place, negative if it is before
	 */
	public int compare(City city, City anotherCity) {
		String state1 = city.getStateName();
		String city1 = city.toString();

		String state2 = anotherCity.getStateName();
		String city2 = anotherCity.toString();

		if (!(state1.equals(state2))) {
			return state1.compareTo(state2);
		} else {
			return city1.compareTo(city2);
		}
	}

}// End of State Comparator Method.
