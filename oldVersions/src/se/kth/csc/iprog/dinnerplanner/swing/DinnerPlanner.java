package se.kth.csc.iprog.dinnerplanner.swing;

import javax.swing.JFrame;

import se.kth.csc.iprog.dinnerplanner.model.*;
import se.kth.csc.iprog.dinnerplanner.swing.view.*;


public class DinnerPlanner extends JFrame {

	private static final long serialVersionUID = 1L;

	public DinnerPlanner() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBounds((Constants.widthDf-Constants.width)/2,
				(Constants.heightDf-Constants.height)/2, Constants.width, Constants.height);
	}	
	
	private static DinnerModel model = new DinnerModel();

	public DinnerModel getModel() {
		return model;
	}

	public void setModel(DinnerModel model) {
		DinnerPlanner.model = model;
	}

	public static void main(String[] args) {
		//Initiating the main JFrame
		DinnerPlanner dinnerPlanner = new DinnerPlanner();
		dinnerPlanner.setTitle("Dinner Planner");
		
		//Creating the first view
		MainView mainView = new MainView(model);
		
		//Adding the view to the main JFrame
		dinnerPlanner.getContentPane().add(mainView);
		
		//Resize it so content fits
		dinnerPlanner.pack();
		
		//and starting the JFrame
		dinnerPlanner.setVisible(true);

	}

}
