package se.kth.csc.iprog.dinnerplanner.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.swing.view.IngredientPanel;
import se.kth.csc.iprog.dinnerplanner.swing.view.ListAllPanel;
import se.kth.csc.iprog.dinnerplanner.swing.view.MainView;
import se.kth.csc.iprog.dinnerplanner.swing.view.PreparationPanel;

public class DinnerPlannerController implements ActionListener, ChangeListener
{
	DinnerModel model;
	MainView view;
	
	Action searchStarter=new AbstractAction(){
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent e )
		{
			DinnerPlannerController.this.doSearch(view.starterPanel,
					model.getFullListOfSpcifiedType(Dish.STARTER));
		}
	};
	Action searchMain=new AbstractAction(){
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent e )
		{
			DinnerPlannerController.this.doSearch(view.mainPanel,
					model.getFullListOfSpcifiedType(Dish.MAIN));
		}
	};
	Action searchDesert=new AbstractAction(){
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent e )
		{
			DinnerPlannerController.this.doSearch(view.desertPanel,
					model.getFullListOfSpcifiedType(Dish.DESERT));
		}
	};
	private void doSearch(ListAllPanel panel,ArrayList<Dish> dishList)
	{
		JTextField search=panel.searchText;
		String name=search.getText();
		if(name.length()==0)
		{
			panel.listAllDishes(dishList);
			return;
		}
		
		int num=dishList.size();
		for(int i=0;i<num;i++)
		{
			if(dishList.get(i).getName().toLowerCase().equals(name.toLowerCase()))
			{
				panel.showThisDishOnly(dishList.get(i));
				return;
			}
		}
		panel.showThisDishOnly(model.getNullDish());
	}
	

	private void initialListAllPanels()
	{
		view.starterPanel.listAllDishes(model.getFullListOfSpcifiedType(Dish.STARTER));
		view.mainPanel.listAllDishes(model.getFullListOfSpcifiedType(Dish.MAIN));
		view.desertPanel.listAllDishes(model.getFullListOfSpcifiedType(Dish.DESERT));
	}
	
	private void setGuestNum(int num)
	{
		model.setNumberOfGuests(num);
	}
	
	private void createPreparationWindow()
	{
		Dish starter=model.getNotSelect(Dish.STARTER);
		Dish main=model.getNotSelect(Dish.MAIN);
		Dish desert=model.getNotSelect(Dish.DESERT);
		Iterator<Dish> itr=model.getFullMenu().iterator();
		while(itr.hasNext()) 
		{
			Dish tmp=itr.next();
			if(tmp.getType()==Dish.STARTER) starter=tmp;
			else if(tmp.getType()==Dish.MAIN) main=tmp;
			else desert=tmp;
		}
		PreparationPanel pp=new PreparationPanel(starter,main,desert);
		model.addObserver(pp);
		pp.creatAndShowGUI();
	}
	
	public DinnerPlannerController(DinnerModel argm,MainView argv)
	{
		this.model=argm;
		this.view=argv;
		initialListAllPanels();
	}
	
	public void registerAllListener()
	{
		// register all listAllPanel's search listeners
		view.starterPanel.searchText.registerKeyboardAction( searchStarter,
			    "commond",KeyStroke.getKeyStroke( "ENTER" ),JComponent.WHEN_FOCUSED );
		view.mainPanel.searchText.registerKeyboardAction( searchMain,
			    "commond",KeyStroke.getKeyStroke( "ENTER" ),JComponent.WHEN_FOCUSED );
		view.desertPanel.searchText.registerKeyboardAction( searchDesert,
			    "commond",KeyStroke.getKeyStroke( "ENTER" ),JComponent.WHEN_FOCUSED );
		view.starterPanel.searchButton.addActionListener(this);
		view.mainPanel.searchButton.addActionListener(this);
		view.desertPanel.searchButton.addActionListener(this);
		// register guest number spinner change listener
		view.informationPanel.guestNumSpinner.addChangeListener(this);
		// register preparation button action listener
		view.informationPanel.preparationButton.addActionListener(this);
		// register ingredient button action listener
		view.informationPanel.ingredientsButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
	
		if(ae.getSource().equals(view.starterPanel.searchButton))
			doSearch(view.starterPanel,model.getFullListOfSpcifiedType(Dish.STARTER));
		else if(ae.getSource().equals(view.mainPanel.searchButton))
			doSearch(view.mainPanel,model.getFullListOfSpcifiedType(Dish.MAIN));
		else if(ae.getSource().equals(view.desertPanel.searchButton))
			doSearch(view.desertPanel,model.getFullListOfSpcifiedType(Dish.DESERT));
		else if(ae.getSource()==view.informationPanel.preparationButton) 
			createPreparationWindow();
		else if(ae.getSource()==view.informationPanel.ingredientsButton) 
			createIngredientWindow();
		
	}
	private void createIngredientWindow() {
		IngredientPanel ip=new IngredientPanel();
		ip.creatAndShowGUI();
	}


	@Override
	public void stateChanged(ChangeEvent ae) {

		if(ae.getSource()==view.informationPanel.guestNumSpinner)
		{
			JSpinner js= (JSpinner) ae.getSource();
			setGuestNum((Integer)js.getValue());
		}
	}

}
