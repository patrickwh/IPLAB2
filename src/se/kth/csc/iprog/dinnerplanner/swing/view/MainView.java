package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;


public class MainView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	DinnerModel model;
	// The components of our view
	JLabel label = new JLabel();
	JButton plusButton = new JButton();
	JButton minusButton = new JButton();
	JSplitPane split=new JSplitPane();
	JTabbedPane tab=new JTabbedPane();
	public ListAllPanel starterPanel;
	public ListAllPanel mainPanel;
	public ListAllPanel desertPanel;
	public InformationDisplayPanel informationPanel;
	public ProgressBarPanel progress=new ProgressBarPanel();
	public JPanel leftPanel=new JPanel();
	
	private void init()
	{
		ArrayList<Dish> slist=new ArrayList<Dish>();
		ArrayList<Dish> mlist=new ArrayList<Dish>();
		ArrayList<Dish> dlist=new ArrayList<Dish>();
		Iterator<Dish> itr=model.getDishes().iterator();
		while(itr.hasNext())
		{
			Dish tmp=itr.next();
			if(tmp.getType()==Dish.STARTER) slist.add(tmp);
			else if(tmp.getType()==Dish.MAIN) mlist.add(tmp);
			else dlist.add(tmp);
		}
		this.informationPanel=new InformationDisplayPanel(model);
		this.starterPanel=new ListAllPanel(slist,Dish.STARTER,model);
		this.mainPanel=new ListAllPanel(mlist,Dish.MAIN,model);
		this.desertPanel=new ListAllPanel(dlist,Dish.DESERT,model);
	}
	public MainView(DinnerModel m){
		
		this.model=m;
		this.init();
		this.split.setContinuousLayout(true);
		this.split.setEnabled(true);
		this.split.setOneTouchExpandable(true);  
		this.split.setDividerLocation(Constants.dividerLocation);
		this.split.setDividerSize(15);
		this.split.setPreferredSize(new Dimension(Constants.width, Constants.height));
		this.split.setLeftComponent(leftPanel);
		this.split.setRightComponent(informationPanel);
		
		this.leftPanel.setPreferredSize(new Dimension(Constants.tabWidth,
				Constants.height));
		this.leftPanel.setLayout(new BorderLayout());
		this.leftPanel.add(tab, BorderLayout.CENTER);
		this.leftPanel.add(progress, BorderLayout.SOUTH);
		
		this.tab.setPreferredSize(new Dimension(Constants.tabWidth,Constants.height));
		this.tab.add("Starter", this.starterPanel);
		this.tab.add("Main", this.mainPanel);
		this.tab.add("Desert", this.desertPanel);
			
		this.setPreferredSize(new Dimension(Constants.width, Constants.height));
		this.setLayout(new BorderLayout());
		this.add(this.split,BorderLayout.CENTER);
		//new DishDropTargetListener(this.informationPanel);		
	}
	
	
}
