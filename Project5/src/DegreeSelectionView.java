import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class DegreeSelectionView extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel fullPanel = new JPanel(new GridLayout(2,0,0,0));
	
	JPanel buttonPanel = new JPanel();
	JPanel scrollPanels = new JPanel(new GridLayout(0,2,0,0));
	
	JPanel basePanel;
	JLabel baseLabel = new JLabel("Base");
	JList<Person> basePersonList = new JList<Person>();
	JScrollPane baseScrollPanel = new JScrollPane(basePersonList);
	Person basePerson;
	
	JPanel targetPanel;
	JLabel targetLabel = new JLabel("Target");
	JList<Person> targetPersonList = new JList<Person>();
	JScrollPane targetScrollPanel = new JScrollPane(targetPersonList);
	Person targetPerson;
	
	JButton okButton = new JButton("Ok");
	
	public DegreeSelectionView()
	{
		setTitle("Pick a base case and a target");
		
		fullPanel = new JPanel();
		basePanel = new JPanel();
		targetPanel = new JPanel();
		fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
		basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
		targetPanel.setLayout(new BoxLayout(targetPanel, BoxLayout.Y_AXIS));
		basePersonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		targetPersonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		Map<String,Person> totalRosterMap = new LinkedHashMap<String,Person>();
		for(Identification id:DataBaseModel.getJListOfIdentifications().values())
		{
			for(Season season:id.getSeasonList().values())
			{
				for(String personKey:season.getRosterMap().keySet())
				{
					MapClass.putInMapListIfAbsent(totalRosterMap, personKey, season.getRosterMap().get(personKey));
				}
			}
		}
		
		ArrayList<Person> personList = new ArrayList<Person>(totalRosterMap.values());
		Collections.sort(personList);
		basePersonList.setListData(personList.toArray(new Person[0]));
		basePanel.add(baseLabel);
		basePanel.add(baseScrollPanel);
		
		targetPersonList.setListData(personList.toArray(new Person[0]));
		targetPanel.add(targetLabel);
		targetPanel.add(targetScrollPanel);
		
		scrollPanels.add(basePanel);
		scrollPanels.add(targetPanel);
		
		buttonPanel.add(okButton);
		
		fullPanel.add(scrollPanels);
		fullPanel.add(buttonPanel);
		
		add(fullPanel);
		setLocation(400,200);
			
		pack();
		setVisible(true);
	}
	
	public Person getBaseSelected()
	{
		return this.basePersonList.getSelectedValue();
	}
	
	public Person getTargetSelected()
	{
		return this.targetPersonList.getSelectedValue();
	}
	
	public JButton getOkButton()
	{
		return okButton;
	}
}
