import java.io.Serializable;
import java.util.Comparator;

/**
 * PeoplAce CS 2334, Section 010 March 27, 2015
 * <P>
 * FirstNameComparator compares one first name to another first name in specific
 * ways
 * </P>
 * Implements Comparator.
 * 
 * @version 1.0
 */
public class AgeComparator implements Comparator<Person>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Compares two people's cities
	 * <P>
	 * 
	 * @param person
	 *            :a person who contains a first and last name (Person)
	 * @param anotherPerson
	 *            :another person who contains a first and a last name (Person)
	 * @return positive if it is after what you're comparing, 0 if at same
	 *         place, negative if it is before (int)
	 */
	public int compare(Person person, Person anotherPerson) {
		int age1 = person.calculateAge();

		int age2 = anotherPerson.calculateAge();

		return new Integer(age1).compareTo(new Integer(age2));
	}
}
