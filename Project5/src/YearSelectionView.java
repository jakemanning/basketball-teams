import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class YearSelectionView extends JFrame
{
	private static final long serialVersionUID = 1L;

	JPanel yearSelectionPanel = new JPanel(new GridLayout(2,0,0,0));
	
	
	JComboBox<Integer> yearComboBox = new JComboBox<Integer>();
	JButton yearSelectionOKButton = new JButton("OK");
	JButton yearSelectionCancelButton = new JButton("Cancel");
	Date date = new Date();
	
	public YearSelectionView()
	{
		setTitle("Year Selection");
		yearSelectionPanel.add(yearComboBox);
		setComboBox();
		JPanel buttonPanel = new JPanel(new GridLayout(0,2,0,0));
		buttonPanel.add(yearSelectionOKButton);
		buttonPanel.add(yearSelectionCancelButton);
		yearSelectionPanel.add(buttonPanel);
		
		add(yearSelectionPanel);
		setLocation(400,200);
		
		
		pack();
		setVisible(true);
	}
	
	public JButton getYearSelectionOKButton()
	{
		return yearSelectionOKButton;
	}
	
	public JButton getYearSelectionCancelButton()
	{
		return yearSelectionCancelButton;
	}
	
	public void setComboBox()
	{
		for(int yearCounter = 1946,yearEnd = 2015; yearCounter <= yearEnd;++yearCounter)
		{
			yearComboBox.addItem(yearCounter);
		}
		yearComboBox.setSelectedIndex(0);
	}
}
