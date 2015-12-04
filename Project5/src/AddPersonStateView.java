import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddPersonStateView extends JFrame implements Serializable
{

	/**This serializable class's static final serialVersionUID field of type long*/
	private static final long serialVersionUID = -266563743269053729L;
	
	final static String COMBO_STATE_TITLE = "Select a State";
	
	JPanel filePanel = new JPanel(new GridLayout(2, 0, 0, 0));
	JButton personStateViewOKButton = new JButton("OK");
	JComboBox<State> stateViewComboBox = new JComboBox<State>();
	JButton cancelButton = new JButton("Cancel");
	
	AddPersonStateView()
	{
		setTitle("State Selection");

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

	public void setStateList(State[] stateList)
	{
		for(int index = 0; index < stateList.length; ++index)
		{
			State example = stateList[index];

			if(!example.getListOfCities().isEmpty())
			{
				stateViewComboBox.addItem(example);
			}
		}
		stateViewComboBox.setSelectedIndex(0);
	}

}
