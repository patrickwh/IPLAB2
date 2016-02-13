package se.kth.csc.iprog.dinnerplanner.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.swing.view.Constants;

public class DinnerModel extends Observable implements IDinnerModel {
	

	Set<Dish> dishes = new HashSet<Dish>();
	HashSet<Dish> menu=new HashSet<Dish>();
	//HashSet <Ingredient> allIngredients;
	ArrayList <Ingredient> allIngredients=new ArrayList <Ingredient>();
	ArrayList<Dish> starterList=new ArrayList<Dish>();
	ArrayList<Dish> mainList=new ArrayList<Dish>();
	ArrayList<Dish> desertList=new ArrayList<Dish>();
	
	int guestNum=0;
	/**
	 * TODO: For Lab2 you need to implement the IDinnerModel interface.
	 * When you do this you will have all the needed fields and methods
	 * for the dinner planner (number of guests, selected dishes, etc.). 
	 */
	
	
	/**
	 * The constructor of the overall model. Set the default values here
	 */
	
	private void initData()
	{
		File f=new File(Constants.dataDir);
		try 
		{
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String str="";
			String name;
			int type;
			String pic;
			String des;
			while(!(str=br.readLine()).equals("#END#"))
			{
				String[] data=str.split("#");
				name=data[0];
				type=Integer.valueOf(data[1]);
				pic=data[2];
				des=br.readLine();
				Dish d=new Dish(name,type,pic,des);
				while(!(str=br.readLine()).equals("#*#"))
				{
					String[] ing=str.split("#");
					String iname=ing[0];
					double quantity=Double.valueOf(ing[1]);
					String unit=ing[2];
					if(unit.equals("(*)")) unit="";
					double price=Double.valueOf(ing[3]);
					Ingredient ingredient=new Ingredient(iname,quantity,unit,price);
					d.addIngredient(ingredient);
				}
				this.dishes.add(d);
				if(d.getType()==Dish.STARTER) this.starterList.add(d);
				else if(d.getType()==Dish.MAIN) this.mainList.add(d);
				else this.desertList.add(d);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public DinnerModel(){
		
		initData();
		//Adding some example data, you can add more
		
	}
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishes(){
		return dishes;
	}
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishesOfType(int type){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type){
				result.add(d);
			}
		}
		return result;
	}
	
	/**
	 * Returns the set of dishes of specific type, that contain filter in their name
	 * or name of any ingredient. 
	 */
	public Set<Dish> filterDishesOfType(int type, String filter){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type && d.contains(filter)){
				result.add(d);
			}
		}
		return result;
	}

	@Override
	public int getNumberOfGuests() {
		return this.guestNum;
	}

	@Override
	public void setNumberOfGuests(int numberOfGuests) {
		this.guestNum=numberOfGuests;
		try 
		{
			this.setChanged();
			this.notifyObservers(new ChangeMessage(ChangeMessage.GuestNumChanged,this.guestNum));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Dish> getFullMenu() {
		return this.menu;
	}

	private void unionAllIngrediants()
	{
		this.allIngredients=new ArrayList <Ingredient>();
		// clear all information
		Iterator<Dish> dishesIterator=this.menu.iterator();
		while(dishesIterator.hasNext())
		{
			Dish thisDish=dishesIterator.next();
			Iterator<Ingredient> iitr=thisDish.getIngredients().iterator();
			while(iitr.hasNext())
			{
				Ingredient tmp=iitr.next();
				if(allIngredients.contains(tmp))
				{
					Ingredient t=allIngredients.get(allIngredients.indexOf(tmp));
					t.setQuantity(t.getQuantity()+tmp.getQuantity());
				}
				else
				{
					allIngredients.add(tmp);
				}
			}
		}
	}
	@Override
	public ArrayList<Ingredient> getAllIngredients() {
		this.unionAllIngrediants();
		return this.allIngredients;
	}

	@Override
	public double getTotalMenuPrice() {
		double totalPrice=0;
		Iterator <Dish> ditr=this.menu.iterator();
		while(ditr.hasNext())
		{
			totalPrice+=ditr.next().getCost();
		}
		return totalPrice;
	}

	@Override
	public void addDishToMenu(Dish dish) {
		Iterator <Dish> ditr=this.menu.iterator();
		while(ditr.hasNext())
		{
			Dish d=ditr.next();
			if(d.type==dish.type)
			{
				this.menu.remove(d);
				this.menu.add(dish);	
				try {
					this.setChanged();
					this.notifyObservers(new ChangeMessage
							(ChangeMessage.ToatalMenuCostCahnged,this.getTotalMenuPrice()));
					this.setChanged();
					this.notifyObservers(new ChangeMessage(ChangeMessage.MenuChanged,this.getMunuList()));
					this.setChanged();
					this.notifyObservers(new ChangeMessage(ChangeMessage.MenuCahngedForPreparation,
							this.getMunuListForPreparation()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}
		this.menu.add(dish);
		try 
		{
			this.setChanged();
			this.notifyObservers(new ChangeMessage
					(ChangeMessage.ToatalMenuCostCahnged,this.getTotalMenuPrice()));
			this.setChanged();
			this.notifyObservers(new ChangeMessage(ChangeMessage.MenuChanged,this.getMunuList()));
			this.setChanged();
			this.notifyObservers(new ChangeMessage(ChangeMessage.MenuCahngedForPreparation,
					this.getMunuListForPreparation()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Dish> getMunuList()
	{
		ArrayList<Dish> list=new ArrayList<Dish>();
		Iterator<Dish> itr=this.menu.iterator();
		while(itr.hasNext())
		{
			list.add(itr.next());
		}
		return list;
	}
	private ArrayList<Dish> getMunuListForPreparation()
	{
		ArrayList<Dish> list=this.getMunuList();
		boolean hasStarter=false;
		boolean hasMain=false;
		boolean hasDesert=false;
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getType()==Dish.STARTER) hasStarter=true;
    		else if(list.get(i).getType()==Dish.MAIN) hasMain=true;
    		else hasDesert=true;
		}
		if(!hasStarter) list.add(this.getNotSelect(Dish.STARTER));
		if(!hasMain) list.add(this.getNotSelect(Dish.MAIN));
		if(!hasDesert) list.add(this.getNotSelect(Dish.DESERT));
		return list;
	}
	@Override
	public void removeDishFromMenu(Dish dish) {
		if(this.menu.contains(dish))
		{
			this.menu.remove(dish);
			try 
			{
				this.setChanged();
				this.notifyObservers(new ChangeMessage
						(ChangeMessage.ToatalMenuCostCahnged,this.getTotalMenuPrice()));
				this.setChanged();
				this.notifyObservers(new ChangeMessage(ChangeMessage.MenuChanged,this.getMunuList()));
				this.setChanged();
				this.notifyObservers(new ChangeMessage(ChangeMessage.MenuCahngedForPreparation,
						this.getMunuListForPreparation()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public Dish getSelectedDish(int type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Dish> getFullListOfSpcifiedType(int type)
	{
		if(type==Dish.STARTER) return this.starterList;
		else if(type==Dish.MAIN) return this.mainList;
		else return this.desertList;
	}

	public Dish getNullDish()
	{
		return new Dish("NO RESULT",1,"noResult.jpg","No result has been found");
	}
	public Dish getNotSelect(int type)
	{
		return new Dish("NOT select",type,"noResult.jpg","You have NOT selet a dish in specified type");
	}
}
