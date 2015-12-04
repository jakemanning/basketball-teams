import java.util.Comparator;

/**
* PeoplAce
* CS 2334, Section 010
* March 27, 2015
* <P>
* LastNameComparator compares one last name to another last name in specific ways
* </P>
* Implements Comparator.
* @version 1.0
*/
public class LastNameComparator implements Comparator<Person>
{
	/**
	* Compares two people's cities
	* <P>
	* @param person :a person who contains first or last names (Person)
	* @param anotherPerson :another person who contains first or last names (Person)
	* @return positive if it is after what you're comparing, 0 if at same place, negative if it is before (int)
	*/
	public int compare(Person person, Person anotherPerson)
	{
		String lastName1 = person.getLastName().toUpperCase();
		String firstName1 = person.getFirstName().toUpperCase();
		String lastName2 = anotherPerson.getLastName().toUpperCase();
		String firstName2 = anotherPerson.getFirstName().toUpperCase();
		
		if(!(lastName1.equals(lastName2)))
		{
			return lastName1.compareTo(lastName2);
		}
		
		else
		{
			return firstName1.compareTo(firstName2);
		}
	}
}//End of Last Name Comparator Method.

