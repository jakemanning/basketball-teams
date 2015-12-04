import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditPersonStateView extends JFrame
{

	/**This serializable class's static final serialVersionUID field of type long*/
	private static final long serialVersionUID = -266563743269053729L;
	
	final static String COMBO_STATE_TITLE = "Select a State";
	
	JPanel filePanel = new JPanel(new GridLayout(2, 0, 0, 0));
	JButton personStateViewOKButton = new JButton("OK");
	JComboBox<State> stateViewComboBox = new JComboBox<State>();
	JButton cancelButton = new JButton("Cancel");
	State stateSelected;
	ArrayList<State> listOfUsedStates = new ArrayList<State>();
	
	DataBaseModel model;
	
	EditPersonStateView(DataBaseModel model)
	{
		setTitle("State Selection");
		this.model = model;
		filePanel.add(stateViewComboBox);
		JPanel buttonPanel = new JPanel(new GridLayout(0,2,0,0));
		buttonPanel.add(personStateViewOKButton);
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
		return personStateViewOKButton;
	}
	
	public JComboBox<State> getStateViewComboBox()
	{
		return stateViewComboBox;
	}

	public void setStateList(State state[])
	{
		stateViewComboBox.removeAllItems();
		for(int index = 0; index < state.length; ++index)
		{
			State example = state[index];
			
			if(!example.getListOfCities().isEmpty())
			{
				stateViewComboBox.addItem(example);
				listOfUsedStates.add(example);
			}
		}
		int indexToSelect = -1;
		
		for(int index = 0;index < listOfUsedStates.size();++index)
		{
			if(listOfUsedStates.get(index).getStateName().equals(stateSelected.getStateName()))
			{
				indexToSelect = index;
			}
		}
		
		if(indexToSelect >= 0)
		{
			stateViewComboBox.setSelectedIndex(indexToSelect);
		}

	}
}
