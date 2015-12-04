import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.ListSelectionListener;

public class DataBaseModel extends DataBase implements Serializable
{
	/**Serial Version ID in which to save data.*/
	private static final long serialVersionUID = 4122110188850607025L;
	
	/**ArrayList of Action Listeners in which to hold the constructed Action Listeners.*/
	private ArrayList<ActionListener> actionListenerList;
	
	/**ArrayList of Action Listeners in which to hold the constructed Action Listeners.*/
	private ArrayList<ListSelectionListener> listListenerList;
	
	/**
	 * Constructor to take in the list of Files as in the regular DataBase Class.
	 */
	public DataBaseModel() 
	{
		super();
	}
	
//	/**
//	 * Movement of the event that the User pressed the Add Button of People.
//	 * @param person The person wished to be added.
//	 * @return		 The verification of success that the person was added.
//	 */
//	public boolean addPerson(String name,String dateOfBirth,State stateOfBirth,City cityOfBirth,String dateOfDeath) 
//	{
//
//		boolean success = super.addPeople(name,dateOfBirth,stateOfBirth,cityOfBirth,dateOfDeath);
//		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add"));
//		return success; // success;
//	}
//	
	/**
	 * Movement of the event that the User pressed the Edit Button of People.
	 */
	public void editPerson() 
	{
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Edit"));
	}
//	
//	/**
//	 * Movement of the event that the User pressed the Delete Button of People.
//	 * @param person The person wished to be deleted.
//	 * @return		 The verification of success that the person was deleted.
//	 */
//	public boolean deletePerson(Person person)
//	{
//		boolean success = super.deletePeople(person);
//		processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED, "Delete"));
//		return success;// success;
//	}
//	
//	/**
//	 * Movement of the event that the User pressed the Add Button of Place.
//	 * @param location The place wished to be added.
//	 * @return		The verification of success that the place was added.
//	 */
//	public boolean addPlace(String city,State state) 
//	{
//		boolean success = super.addPlace(city,state);
//		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add"));
//		return success;// success;
//	}
//	
	/**
	 * Movement of the event that the User pressed the Edit Button of Place.
	 */
	public void editPlace() 
	{
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "EditPlace"));
	}
//	
//	
//	/**
//	 * Movement of the event that the User pressed the Delete Button of Place.
//	 * @param location The place wished to be delete.
//	 * @return		The verification of success that the place was deleted.
//	 */
//	public boolean deletePlace(String location)
//	{
//		boolean success = super.deletePlace(location);
//		processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED, "Delete"));
//		return success;// success;
//	}
//	
//	/**
//	 * Movement of the event that the User pressed the Add Button of Team.
//	 * @param team The team wished to be added.
//	 * @return		The verification of success that the team was added.
//	 */
//	public boolean addTeam(Season team) 
//	{
//		boolean success = super.addTeam(team);
//		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Add"));
//		return success;// success;
//	}
//	
//	/**
//	 * Movement of the event that the User pressed the Edit Button of Team.
//	 * @param team The team wished to be edited.
//	 * @return		The verification of success that the team was edited.
//	 */
//	public boolean editTeam(Season team) 
//	{
//		boolean success = super.editTeam(team);
//		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Edit"));
//		return success;// success;
//	}
//	
//	
//	/**
//	 * Movement of the event that the User pressed the Deleting Button of Team.
//	 * @param team The team wished to be deleted.
//	 * @return		The verification of success that the team was deleted.
//	 */
//	public boolean deleteTeam(Season team)
//	{
//		boolean success = super.deleteTeam(team);
//		processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED, "Delete"));
//		return success;// success;
//	}
//	
//	/**
//	 * Movement of the event that the User pressed the Deleting Button of Team.
//	 * @param team The team wished to be deleted.
//	 * @return		The verification of success that the team was deleted.
//	 */
//	public void importCity(ArrayList<City> listOfJListCities)
//	{
//		super.setCityList(listOfJListCities);
//		processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED, "Import City"));
//		
//	}
	
	/**
	 * Register an action event listener.
	 * <P>
	 * @param l Action listener that will be listened for by its respective listener.
	 */
	public synchronized void addActionListener(ActionListener l) 
	{
		System.out.println(l.toString());
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}
	
	/**
	 * Remove an action event listener.
	 * <P>
	 * @param l Action listener that will be listened for by its respective listener.
	 */
	public synchronized void removeActionListener(ActionListener l) 
	{
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}
	
	/**
	 * Remove an action event listener.
	 * <P>
	 * @param l Action listener that will be listened for by its respective listener.
	 */
	public synchronized void addListSelectionListener(ListSelectionListener l) 
	{
		if (listListenerList == null)
			listListenerList = new ArrayList<ListSelectionListener>();
		listListenerList.add(l);
	}
	
	/**
	 * Fire event.
	 * <P>
	 * @param e Action listener that will be listened for by its respective listener.
	 */
	@SuppressWarnings("unchecked")
	private void processEvent(ActionEvent e) 
	{
		ArrayList<ActionListener> list;
		
		synchronized (this) {
			if (actionListenerList == null) return;
			list = (ArrayList<ActionListener>)actionListenerList.clone();
		}
		
		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}
	
//	/**
//	 * Fire event.
//	 * <P>
//	 * @param l ListListener listener that will be listened for by its respective listener.
//	 */
//	@SuppressWarnings("unchecked")
//	private void processEvent(ListSelectionEvent l) 
//	{
//		ArrayList<ListSelectionListener> list;
//		
//		synchronized (this) 
//		{
//			if (listListenerList == null) return;
//			list = (ArrayList<ListSelectionListener>)listListenerList.clone();
//		}
//		
//		for (int i = 0; i < list.size(); i++) {
//			ListSelectionListener listener = list.get(i);
//			listener.valueChanged(l);
//		}
//	}
}
