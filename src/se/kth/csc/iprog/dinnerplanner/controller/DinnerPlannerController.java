package se.kth.csc.iprog.dinnerplanner.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.model.ChangeMessage;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.dynamicData.DishImage;
import se.kth.csc.iprog.dinnerplanner.model.dynamicData.GetDataThread;
import se.kth.csc.iprog.dinnerplanner.swing.view.IngredientPanel;
import se.kth.csc.iprog.dinnerplanner.swing.view.ListAllPanel;
import se.kth.csc.iprog.dinnerplanner.swing.view.MainView;
import se.kth.csc.iprog.dinnerplanner.swing.view.PreparationPanel;

public class DinnerPlannerController implements ActionListener, ChangeListener, Observer
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
			panel.setSearch(false,null);
			model.resetSearchPage(panel.type);
			return;
		}
		else
		{
			panel.setSearch(true,name);
			model.loadMoreSearchResult(panel.type, name);
		}
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
		//new DishDropTargetListener(pp,model);
	}
	
	public DinnerPlannerController(DinnerModel argm,MainView argv)
	{
		this.model=argm;
		this.view=argv;
		// register this controller as an observer of the dinner model
		model.addObserver(this);
		model.addObserver(view.progress);
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
		model.addObserver(ip);
		ip.creatAndShowGUI(model.getAllIngredients());
	}

	@Override
	public void stateChanged(ChangeEvent ae) {

		if(ae.getSource()==view.informationPanel.guestNumSpinner)
		{
			JSpinner js= (JSpinner) ae.getSource();
			setGuestNum((Integer)js.getValue());
		}
	}


	@Override
	public void update(Observable obs, Object obj) {
		ChangeMessage cm=(ChangeMessage) obj;
		if(cm.getType()==ChangeMessage.dishLoaded)
		{
			Dish d=(Dish) cm.getData();
			if(d.getType()<0)
			{
				d.setType(0-d.getType());
				if(d.getType()==Dish.STARTER) view.starterPanel.addNewDishToList(d);
				else if(d.getType()==Dish.MAIN) view.mainPanel.addNewDishToList(d);
				else view.desertPanel.addNewDishToList(d);	
			}
			else 
			{
				if(d.getType()==Dish.STARTER) view.starterPanel.addNewDishToList(d);
				else if(d.getType()==Dish.MAIN) view.mainPanel.addNewDishToList(d);
				else view.desertPanel.addNewDishToList(d);		
			}
			GetDataThread gdt=new GetDataThread(model,d.getRid(),d.getName(),d.getType(),
						d.getImage(),GetDataThread.GET_IMAGE);
			gdt.run();
		}	
		else if(cm.getType()==ChangeMessage.imageLoaded)
		{
			DishImage di=(DishImage) cm.getData();
			if(di.getType()==Dish.STARTER) view.starterPanel.loadNemImage(di.getName(), di.getPicName());
			else if(di.getType()==Dish.MAIN) view.mainPanel.loadNemImage(di.getName(), di.getPicName());
			else view.desertPanel.loadNemImage(di.getName(), di.getPicName());
		}
		else if(cm.getType()==ChangeMessage.loadingStateChanged)
		{
			if(!(boolean) cm.getData())
			{
				Timer tim=new Timer();
				long pause=2000;
				tim.schedule(new TimerTask() {
					@Override
					public void run() {
						view.progress.setPreferredSize(new Dimension(0,0));
						view.leftPanel.validate();
						view.leftPanel.repaint();
					}
				},pause);
			}
		}
		else if(cm.getType()==ChangeMessage.listLoaded)
		{
			@SuppressWarnings("unchecked")
			ArrayList<Integer> list=(ArrayList<Integer>) cm.getData();
			//System.out.println(" ****** type "+list.get(0)+"  "+list.get(1));
			if(list.get(0)==0-Dish.STARTER)
			{
				view.starterPanel.clearList();
			}
			else if(list.get(0)==0-Dish.MAIN)
			{
				view.mainPanel.clearList();
			}
			else if(list.get(0)==0-Dish.DESERT)
			{
				view.desertPanel.clearList();
			}
		}
		else if(cm.getType()==ChangeMessage.internetConnectionFailure)
		{
			JOptionPane.showMessageDialog(null,"Internet Failure, Please Try Offline Service !",
					"Internet Failure",JOptionPane.ERROR_MESSAGE);
			view.progress.setPreferredSize(new Dimension(0,0));
			view.leftPanel.validate();
			view.leftPanel.repaint();
		}
	}

}
