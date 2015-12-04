import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddPlaceStateView extends JFrame implements Serializable

{

	/**
	 * This serializable class's static final serialVersionUID field of type
	 * long
	 */
	private static final long serialVersionUID = -4925302303904402583L;

	final static String COMBO_STATE_TITLE = "Select a State";

	JPanel filePanel = new JPanel(new GridLayout(2, 0, 0, 0));
	State[] stateLabels = { new State("AL"), new State("AK"), new State("AR"), new State("AZ"), new State("CA"),
			new State("CO"), new State("CT"), new State("DC"), new State("DE"), new State("FL"), new State("GA"),
			new State("HI"), new State("ID"), new State("IL"), new State("IN"), new State("IA"), new State("KS"),
			new State("KY"), new State("LA"), new State("ME"), new State("MD"), new State("MA"), new State("MI"),
			new State("MN"), new State("MS"), new State("MO"), new State("MT"), new State("NE"), new State("NV"),
			new State("NH"), new State("NJ"), new State("NM"), new State("NY"), new State("NC"), new State("ND"),
			new State("OH"), new State("OK"), new State("OR"), new State("PA"), new State("RI"), new State("SC"),
			new State("SD"), new State("TN"), new State("TX"), new State("UT"), new State("VT"), new State("VA"),
			new State("WA"), new State("WI"), new State("WV") };
	String[] stringLabels = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
			"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
			"NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WI", "WV" };

	JButton placeStateViewOKButton = new JButton("OK");
	JComboBox<String> stateViewComboBox = new JComboBox<String>(stringLabels);
	JButton cancelButton = new JButton("Cancel");
	String stateSelected;

	AddPlaceStateView() {
		for (State state : stateLabels) {
			MapClass.putInMapListIfAbsent(DataBaseModel.getJListOfStates(), state.getStateName(), state);
		}

		setTitle("State Selection");

		stateViewComboBox.setSelectedIndex(0);
		filePanel.add(stateViewComboBox);
		JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 0, 0));
		buttonPanel.add(placeStateViewOKButton);
		buttonPanel.add(cancelButton);
		filePanel.add(buttonPanel);

		add(filePanel);
		setLocation(400, 200);

		pack();
		setVisible(true);
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getOKButton() {
		return placeStateViewOKButton;
	}

	public JComboBox<String> getStateViewComboBox() {
		return stateViewComboBox;
	}

	public String getStateSelected() {
		return stateSelected;
	}

}
