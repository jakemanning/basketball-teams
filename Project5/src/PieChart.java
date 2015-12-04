import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * PeoplAce CS 2334, Section 010 March 27, 2015
 * <P>
 * Constructs the Pie Chart made from the percent of ages passed to the method.
 * <P>
 * Extends JFrame
 * </P>
 * 
 * @version 1.0
 */
public class PieChart extends JFrame {
	private static final long serialVersionUID = 1L;
	private List<Person> peopleList;
	private ArrayList<Integer> ageList;

	/**
	 * Construct a Pie Chart of ages within given data.
	 * <P>
	 * 
	 * @param rosterPassed
	 *            Team roster of Persons passed from the User.
	 * @param stringPassed
	 *            Particular instance of the Pie Chart.
	 */
	public PieChart(List<Person> rosterPassed, String stringPassed) {
		super("Distribution of Ages of " + stringPassed);
		peopleList = rosterPassed;
		setSize(800, 400);
		setLocation(70, 70);
		setVisible(true);

	}// End of Pie Chart Constructor.

	/**
	 * Paints the different ages that need to be represented in the Pie Chart.
	 */
	public void paint(Graphics graphicsObject) {
		// Formation of initial Graphic object and list of Colors.
		Graphics2D g2 = (Graphics2D) graphicsObject;
		Color[] color = { Color.red, Color.blue, Color.green, Color.orange, Color.cyan, Color.lightGray, Color.magenta,
				Color.white, Color.pink, Color.yellow };

		// Sets acceptable ratio to quality of the window and the time to
		// construct it.
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// LinkedHashMap to hold the percent of each age of the roster passed.
		LinkedHashMap<Integer, Integer> agePercentMap = createHashMap();

		// LinkedHashSet to remove duplicate in the list, in case they appear.
		LinkedHashSet<Integer> hs = new LinkedHashSet<Integer>();
		hs.addAll(ageList);
		ageList.clear();
		ageList.addAll(hs);
		String stringToPass;

		/*
		 * JTextArea object which holds the String result of the sort by first
		 * name method.
		 */
		JTextPane jp = new JTextPane();

		jp.setBackground(Color.BLACK);
		// If there are not enough colors to represent Persons in the list, more
		// are made.
		for (int keyIndex = 0, colorIndex = 0, multipleOfSize = 0, total = 0; keyIndex < agePercentMap
				.size(); ++keyIndex) {

			if (keyIndex >= color.length && (keyIndex % color.length) == 0) {
				multipleOfSize += color.length;
				for (int index = 0; index < color.length; ++index) {
					Color newColor = new Color((int) (Math.round(color[index].getRed() * .96)),
							(int) (Math.round(color[index].getGreen() * .96)),
							(int) ((Math.round(color[index].getBlue() * .96))));
					color[index] = newColor;

				}

			}

			// Sets color to respective age.
			colorIndex = keyIndex - multipleOfSize;
			graphicsObject.setColor(color[colorIndex]);

			// Matches a legend case with each age.
			stringToPass = "Teammates of Age " + ageList.get(keyIndex) + "\n";
			appendToPane(jp, stringToPass, graphicsObject.getColor());

			// Fills in the Pie Chart according the index of the percent of ages
			// and the order of the formation of ages.
			if (keyIndex == 0) {
				g2.fill(new Arc2D.Double(110, 80, 300, 300, 0, agePercentMap.get(ageList.get(keyIndex)) * 3.6,
						Arc2D.PIE));
				total += agePercentMap.get(ageList.get(keyIndex)) * 3.6;
			} else if (keyIndex == agePercentMap.size() - 1) {

				g2.fill(new Arc2D.Double(110, 80, 300, 300, total, 360 - total, Arc2D.PIE));

			} else {
				g2.fill(new Arc2D.Double(110, 80, 300, 300, total, agePercentMap.get(ageList.get(keyIndex)) * 3.6,
						Arc2D.PIE));
				total += agePercentMap.get(ageList.get(keyIndex)) * 3.6;

			}

		}

		/*
		 * JScrollPane object which holds the textOfResult object making a
		 * scroll bar for the JOptionPane
		 */
		JScrollPane scrollBar = new JScrollPane(jp);

		/* Sets the size of the scroll bar window */
		scrollBar.setPreferredSize(new Dimension(270, 400));

		/* Adds Scroll Bar for the legend */
		add(scrollBar, BorderLayout.EAST);
		setVisible(true);

	}// End of Pain Method.

	/**
	 * Adds elements to the legend
	 * 
	 * @param tp
	 *            The text Pane to be put next to the Pie Chart.
	 * @param msg
	 *            The element displaying the particular age.
	 * @param c
	 *            The color set to the element.
	 */
	private void appendToPane(JTextPane tp, String msg, Color c) {
		// Sets the Pane to basic structural settings.
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		// Sets the text format and alignment of each element.
		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		// Adds the element to the legend.
		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		tp.replaceSelection(msg);
	}// End of Append To Pane Method.

	/**
	 * Creates a Hash Map for the percentages of age in the passed list of
	 * Persons
	 * <P>
	 * 
	 * @return LinkedHashMap with the key of ages and the value of percentages.
	 */
	public LinkedHashMap<Integer, Integer> createHashMap() {
		// Constructs a new ArrayList for the Integers that will be grouped for
		// their makeup of a roster.
		ageList = new ArrayList<Integer>();

		// Adds the ages of the Persons to the AgeList.
		for (Person p : peopleList) {
			ageList.add(new Integer(p.calculateAge()));

		}
		Collections.sort(ageList);

		// Constructs an LinkedHashMap the percentages of each age present.
		LinkedHashMap<Integer, Integer> agePercentMap = new LinkedHashMap<Integer, Integer>();

		// Passes each age to the CalculateAgeDist Method
		for (Integer i : ageList) {
			int percent = calculateAgeDist(ageList, i);
			// If the percent was not already calculated, it is placed in the
			// LinkedHashMap.
			if (!agePercentMap.containsKey(i))
				;
			{
				agePercentMap.put(i, percent);
			}

		}

		return agePercentMap;
	}// End of Creat Hash Map Method.

	/**
	 * Calculates the percent present for each age in the roster.
	 * 
	 * @param ageList
	 *            ArrayList of ages.
	 * @param age
	 *            The particular in which to calculate the percent in the
	 *            ArrayList of ages.
	 * @return Percent present of passed age
	 */
	public int calculateAgeDist(ArrayList<Integer> ageList, Integer age) {
		int count = 0;

		// Increases count for the same ages in an ArrayList.
		for (Integer i : ageList) {
			if (i.equals(age)) {
				++count;
			}
		}

		// Calculates the percent.
		int percent = (int) (Math.round(((double) count / ageList.size() * 100.0)));
		return percent;
	}// End of Calculate Age Distribution Method.

}
