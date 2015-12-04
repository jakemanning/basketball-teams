import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.ListSelectionListener;

public class DataBaseModel extends DataBase implements Serializable {
	/** Serial Version ID in which to save data. */
	private static final long serialVersionUID = 4122110188850607025L;

	/**
	 * ArrayList of Action Listeners in which to hold the constructed Action
	 * Listeners.
	 */
	private ArrayList<ActionListener> actionListenerList;

	/**
	 * ArrayList of Action Listeners in which to hold the constructed Action
	 * Listeners.
	 */
	private ArrayList<ListSelectionListener> listListenerList;

	/**
	 * Constructor to take in the list of Files as in the regular DataBase
	 * Class.
	 */
	public DataBaseModel() {
		super();
	}

	
	/**
	 * Movement of the event that the User pressed the Edit Button of People.
	 */
	public void editPerson() {
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Edit"));
	}

	/**
	 * Movement of the event that the User pressed the Edit Button of Place.
	 */
	public void editPlace() {
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "EditPlace"));
	}
	

	/**
	 * Register an action event listener.
	 * <P>
	 * 
	 * @param l
	 *            Action listener that will be listened for by its respective
	 *            listener.
	 */
	public synchronized void addActionListener(ActionListener l) {
		System.out.println(l.toString());
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}

	/**
	 * Remove an action event listener.
	 * <P>
	 * 
	 * @param l
	 *            Action listener that will be listened for by its respective
	 *            listener.
	 */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}

	/**
	 * Remove an action event listener.
	 * <P>
	 * 
	 * @param l
	 *            Action listener that will be listened for by its respective
	 *            listener.
	 */
	public synchronized void addListSelectionListener(ListSelectionListener l) {
		if (listListenerList == null)
			listListenerList = new ArrayList<ListSelectionListener>();
		listListenerList.add(l);
	}

	/**
	 * Fire event.
	 * <P>
	 * 
	 * @param e
	 *            Action listener that will be listened for by its respective
	 *            listener.
	 */
	@SuppressWarnings("unchecked")
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;

		synchronized (this) {
			if (actionListenerList == null)
				return;
			list = (ArrayList<ActionListener>) actionListenerList.clone();
		}

		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}
}
