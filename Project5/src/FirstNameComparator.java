import java.util.Comparator;

/**
* PeoplAce
* CS 2334, Section 010
* March 27, 2015
* <P>
* FirstNameComparator compares one first name to another first name in specific ways
* </P>
* Implements Comparator.
* @version 1.0
*/
public class FirstNameComparator implements Comparator<Person>
{
	/**
	* Compares two people's cities
	* <P>
	* @param person :a person who contains a first and last name (Person)
	* @param anotherPerson :another person who contains a first and a last name (Person)
	* @return positive if it is after what you're comparing, 0 if at same place, negative if it is before (int)
	*/
	public int compare(Person person, Person anotherPerson)
	{
		String lastName1 = person.getLastName();
		String firstName1 = person.getFirstName();
		
		String lastName2 = anotherPerson.getLastName();
		String firstName2 = anotherPerson.getFirstName();
		
		if(!(firstName1.equals(firstName2)))
		{
			return firstName1.compareTo(firstName2);
		}
		
		else
		{
			return lastName1.compareTo(lastName2);
		}
	}
}//End of First Name Comparator.
