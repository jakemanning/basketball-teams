import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class DegreeSeparationView extends JFrame
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel mainPanel = new JPanel(new GridLayout(2,0,0,0));
	JPanel secondaryPanel = new JPanel();
	JLabel label = new JLabel("People");
	
	DefaultListModel<Person> degreeModel = new DefaultListModel<Person>();
	JList<Person> degreeList = new JList<Person>(degreeModel);
	JScrollPane degreeScrollBar = new JScrollPane(degreeList);
	
	public DegreeSeparationView(ArrayList<Person> returnedList)
	{
		setTitle("Degrees of Separation");
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(label);
		secondaryPanel.add(degreeScrollBar);
		mainPanel.add(secondaryPanel);
		
		
		add(mainPanel);
		
		setLocation(400,200);
		
		
		for(Person p:returnedList)
		{
			degreeModel.addElement(p);
		}
		
		pack();
		setVisible(true);
	}
}
