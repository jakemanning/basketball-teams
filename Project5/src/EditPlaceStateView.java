import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditPlaceStateView extends JFrame
{

	/**This serializable class's static final serialVersionUID field of type long*/
	private static final long serialVersionUID = 1L;
	
	final static String COMBO_STATE_TITLE = "Select a State";
	
	JPanel filePanel = new JPanel(new GridLayout(2, 0, 0, 0));
	String[] stringLabels = {"AL","AK","AR","AZ","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WI","WV"};/*listOfStates.toArray(new State[0];*/
	JButton placeStateViewOKButton = new JButton("OK");
	JComboBox<String> stateViewComboBox = new JComboBox<String>(stringLabels);
	JButton cancelButton = new JButton("Finish");
	String stateToEdit;
	String cityToEdit;
	
	EditPlaceStateView(String stateToEdit)
	{
		setTitle("Editing: " + stateToEdit);

		filePanel.add(stateViewComboBox);
		JPanel buttonPanel = new JPanel(new GridLayout(0,2,0,0));
		buttonPanel.add(placeStateViewOKButton);
		buttonPanel.add(cancelButton);
		filePanel.add(buttonPanel);
		
		add(filePanel);
		setLocation(400,200);
		
		pack();
		setVisible(true);
	}
	
	public JButton getCancelButton()
	{
		return cancelButton;
	}
	
	public JButton getOKButton()
	{
		return placeStateViewOKButton;
	}
	
	public JComboBox<String> getStateViewComboBox()
	{
		return stateViewComboBox;
	}
	
	public String getStateSelected()
	{
		return stateToEdit;
	}
	
	public void setEditPlaceComboBoxIndex(State state)
	{
		int set = -1;
		for(int index = 0;index < stateViewComboBox.getItemCount();++index)
		{
			if(stateViewComboBox.getItemAt(index).equals(state.getStateName()))
			{
				set = index;
			}
		}
		if(set > -1)
		{
			this.stateViewComboBox.setSelectedIndex(set);
		}

	}
	
}
