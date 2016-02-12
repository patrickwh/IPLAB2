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
	HashSet<Dish> selectedDishes= new HashSet<Dish>();
	//HashSet <Ingredient> allIngredients;
	ArrayList <Ingredient> allIngredients=new ArrayList <Ingredient>();
	
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
				System.out.println(" str "+str);
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
		this.setChanged();
		try 
		{
			this.notifyObservers(new ChangeMessage(ChangeMessage.GuestNumChanged,this.guestNum));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Dish getSelectedDish(int type) {
		Dish result=new Dish();
		Iterator<Dish> itr=this.selectedDishes.iterator();
		while(itr.hasNext())
		{
			result=itr.next();
			if(result.getType()==type) return result;
		}
		return null;
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
				return;
			}
		}
		this.menu.add(dish);
		this.setChanged();
		try 
		{
			this.notifyObservers(new ChangeMessage(ChangeMessage.MenuChanged,this.menu));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeDishFromMenu(Dish dish) {
		if(this.menu.contains(dish))
		{
			this.menu.remove(dish);
			this.setChanged();
			try 
			{
				this.notifyObservers(new ChangeMessage(ChangeMessage.MenuChanged,this.menu));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
