import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TeamIDView extends JFrame
{
	private static final long serialVersionUID = 1L;
	JPanel teamIDPanel = new JPanel(new GridLayout(3,0,0,0));
	JLabel jlTeamID = new JLabel("Enter Team ID:");
	JTextField jfTeamID = new JTextField(20);
	JButton jbTeamIDOKButton = new JButton("Add");
	JButton teamIDCancelButton = new JButton("Cancel");
	
	String teamIdentification;
	
	public TeamIDView()
	{
		setTitle("Add Team ID");
		teamIDPanel.add(jlTeamID);
		teamIDPanel.add(jfTeamID);
		JPanel buttonPanel = new JPanel(new GridLayout(0,2,0,0));
		buttonPanel.add(jbTeamIDOKButton);
		buttonPanel.add(teamIDCancelButton);
		teamIDPanel.add(buttonPanel);
		
		add(teamIDPanel);
		setLocation(400,200);
		
		pack();
		setVisible(true);
	}
	
	public String getTeamID()
	{
		return jfTeamID.getText();
	}
	
	public JButton getTeamIDOKButton()
	{
		return jbTeamIDOKButton;
	}
	
	public JButton getTeamIDCancelButton()
	{
		return teamIDCancelButton;
	}
}
