package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;


public class MainView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// The components of our view
	JLabel label = new JLabel();
	JButton plusButton = new JButton();
	JButton minusButton = new JButton();
	JSplitPane split=new JSplitPane();
	JTabbedPane tab=new JTabbedPane();
	ListAllPanel starterPanel=new ListAllPanel();
	ListAllPanel mainPanel=new ListAllPanel();
	ListAllPanel desertPanel=new ListAllPanel();
	InformationDisplayPanel informationPanel=new InformationDisplayPanel();
	
	public MainView(){
		this.setPreferredSize(new Dimension(Constants.width, Constants.height));
		this.add(this.split);
		this.split.setContinuousLayout(true);
		this.split.setEnabled(true);
		this.split.setOneTouchExpandable(true);  
		this.split.setDividerLocation(Constants.dividerLocation);
		this.split.setDividerSize(15);
		this.split.setPreferredSize(new Dimension(Constants.width, Constants.height));
		
		this.tab.setPreferredSize(new Dimension(Constants.tabWidth,Constants.height));
		this.tab.add("Starter", this.starterPanel);
		this.tab.add("Main", this.mainPanel);
		this.tab.add("Desert", this.desertPanel);
		
		this.split.setLeftComponent(tab);
		this.split.setRightComponent(informationPanel);
		
	}
	
}
