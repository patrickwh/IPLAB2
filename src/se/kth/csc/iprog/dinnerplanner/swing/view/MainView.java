package se.kth.csc.iprog.dinnerplanner.swing.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;


public class MainView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// The components of our view
	JLabel label = new JLabel();
	JButton plusButton = new JButton();
	JButton minusButton = new JButton();
	
	public MainView(){

		label.setText("Hello world");
		
		// Add label to the view
		this.add(label);
		
		
		// Setup the rest of the view layout
	}
	
}
