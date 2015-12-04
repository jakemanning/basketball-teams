import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SorryView extends JFrame
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel filePanel = new JPanel(new GridLayout(2,0,0,0));
	
	JLabel somethingAlreadyInLabel;
	
	JButton cancelButton = new JButton("Cancel");
	
	SorryView(String component)
	{
		setTitle("Error");
		somethingAlreadyInLabel= new JLabel("Sorry, '" + component + "' is already in the List. Try again.");
		
		filePanel.add(somethingAlreadyInLabel);
		filePanel.add(cancelButton);
		
		add(filePanel);
		setLocation(400,200);
		
		pack();
		setVisible(true);
	}
}
