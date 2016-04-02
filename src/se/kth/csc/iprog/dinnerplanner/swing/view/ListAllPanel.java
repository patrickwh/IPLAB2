package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class ListAllPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public JTextField searchText=new JTextField();
	JScrollPane scroll;
	public JButton searchButton=new JButton("Search");
	JPanel searchPanel=new JPanel();
	public JPanel scrollContentPanel=new JPanel();
	JPanel scrollBackgroundPanel=new JPanel();
	Dish selectedItem=new Dish();
	public int type;
	ArrayList<Dish> list;
	ArrayList<Dish> searchList=new ArrayList<Dish>();
	DinnerModel model;
	boolean isSearch=false;
	String kw=null;
	
	public  ListAllPanel(ArrayList<Dish> list,int type,DinnerModel m)
	{
		this.type=type;
		this.list=list;
		this.model=m;
		Font font=new Font("Britannic", Font.BOLD,20);
		
		this.setLayout(new BorderLayout());
		this.searchPanel.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Constants.tabWidth,Constants.height));
		this.searchPanel.setPreferredSize(new Dimension(Constants.tabWidth,
				Constants.searchFieldHeight));
		this.searchButton.setPreferredSize(new Dimension(Constants.searchButtonWidth,
				Constants.searchFieldHeight));
		//this.searchButton.setFont(font);
		this.searchText.setPreferredSize( new Dimension(Constants.searchTextWidth, 
				Constants.searchFieldHeight));
		this.searchText.setFont(font);
		
		
		this.searchPanel.add(this.searchText,BorderLayout.WEST);
		this.searchPanel.add(this.searchButton,BorderLayout.CENTER);
		this.add(this.searchPanel, BorderLayout.NORTH);
		
		this.scrollContentPanel=new JPanel();	
		this.scrollBackgroundPanel.setPreferredSize(new Dimension(Constants.tabWidth,
				Constants.height));
		
		//////////////////////////
		this.scroll=new JScrollPane(this.scrollContentPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scroll.setBounds(0,0,Constants.tabWidth, Constants.scrollHeight);
		this.scroll.getVerticalScrollBar().setUnitIncrement(Constants.verticalScrollbarUnit);
		//this.scroll.setLayout(null);
		this.add(this.scrollBackgroundPanel,BorderLayout.CENTER);
		this.scrollBackgroundPanel.setLayout(new BorderLayout());
		this.scrollBackgroundPanel.add(this.scroll, BorderLayout.CENTER);
		////////////////////////////////////////////////////
		this.searchList.add(model.getAddMore(type));// add addMore icon
	}
	
	public void showThisDishOnly(Dish d,boolean search)
	{
		int itemNum=this.scrollContentPanel.getComponentCount();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
		this.scrollContentPanel.setPreferredSize(new Dimension(Constants.dishDisplayWidth,
				Constants.dishDisplayHeight));
		this.scrollContentPanel.setLayout(new GridLayout(2,Constants.dishNumInARow));
		DishDisplayPanel r=new DishDisplayPanel(d,0,model,search,kw);
		this.scrollContentPanel.add(r);
		this.scrollContentPanel.setLayout(null);
		int width= Constants.dishDisplayWidth+10;
		int height=Constants.dishDisplayHeight+10;
		r.setBounds(0, 0,width,height);//      French toast
		this.revalidate();
		this.repaint();
	}
	
	public void listAllDishes(ArrayList <Dish> dishList)
	{
		this.list=dishList;
		int itemNum=this.scrollContentPanel.getComponentCount();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
		
		int num=dishList.size();
		int remainder=num%Constants.dishNumInARow;
		int rowNum=num/Constants.dishNumInARow;
		if(remainder!=0) rowNum++;
		int contentPanelWidth=Constants.tabWidth-50;
		int contentPanelHeight=Constants.dishDisplayHeight*rowNum+
				Constants.interDishDisplayMargin*(rowNum+1);
		this.scrollContentPanel.setLayout(new GridLayout(rowNum,Constants.dishNumInARow));
		//this.scrollContentPnael.setLayout(new FlowLayout());
		this.scrollContentPanel.setPreferredSize(new Dimension(contentPanelWidth,
				contentPanelHeight));
		for(int i=0;i<num;i++)
		{
			DishDisplayPanel ddp=new DishDisplayPanel(dishList.get(i),i,model,false,kw);
			this.scrollContentPanel.add(ddp);
		}
		this.revalidate();
		this.repaint();
	}
	
	public void addNewDishToList(Dish dish)
	{
		if(!isSearch)
		{
			synchronized (list)
			{
				//list.add(list.size()-1,dish);
				int num=list.size();
				int remainder=num%Constants.dishNumInARow;
				int rowNum=num/Constants.dishNumInARow;
				if(remainder!=0) rowNum++;
				int contentPanelWidth=Constants.tabWidth-50;
				int contentPanelHeight=Constants.dishDisplayHeight*rowNum+
						Constants.interDishDisplayMargin*(rowNum+1);
				this.scrollContentPanel.setLayout(new GridLayout(rowNum,Constants.dishNumInARow));
				//this.scrollContentPnael.setLayout(new FlowLayout());
				this.scrollContentPanel.setPreferredSize(new Dimension(contentPanelWidth,
						contentPanelHeight));
							
				this.scrollContentPanel.remove(num-2);
				DishDisplayPanel ddp=new DishDisplayPanel(dish,num-2,model,isSearch,kw);
				this.scrollContentPanel.add(ddp);
				this.scrollContentPanel.add(new DishDisplayPanel(list.get(num-1),num-1,model,isSearch,kw));
				this.revalidate();
				this.repaint();
			}
		}
		else
		{
			// only add more icon in the list, show it
			if(searchList.size()==1) this.showThisDishOnly(model.getAddMore(type),isSearch);
			synchronized (searchList)
			{
				searchList.add(searchList.size()-1,dish);
				int num=searchList.size();
				int remainder=num%Constants.dishNumInARow;
				int rowNum=num/Constants.dishNumInARow;
				if(remainder!=0) rowNum++;
				int contentPanelWidth=Constants.tabWidth-50;
				int contentPanelHeight=Constants.dishDisplayHeight*rowNum+
						Constants.interDishDisplayMargin*(rowNum+1);
				this.scrollContentPanel.setLayout(new GridLayout(rowNum,Constants.dishNumInARow));
				//this.scrollContentPnael.setLayout(new FlowLayout());
				this.scrollContentPanel.setPreferredSize(new Dimension(contentPanelWidth,
						contentPanelHeight));
							
				this.scrollContentPanel.remove(num-2);
				DishDisplayPanel ddp=new DishDisplayPanel(dish,num-2,model,isSearch,kw);
				this.scrollContentPanel.add(ddp);
				this.scrollContentPanel.add(new DishDisplayPanel(searchList.get(num-1),num-1,model,isSearch,kw));
				this.revalidate();
				this.repaint();
			}
		}
	}

	public void loadNemImage(String name,String img)
	{
		if(!isSearch)
		{
			synchronized (list)
			{
				int num=list.size();
				DishDisplayPanel dsp;
				for(int i=0;i<num;i++)
				{
					dsp=(DishDisplayPanel) this.scrollContentPanel.getComponent(i);
					if(dsp.getDish().getName().equals(name)) dsp.loadNewImage(img);
				}
			}
		}
		else 
		{
			synchronized (searchList)
			{
				int num=searchList.size();
				DishDisplayPanel dsp;
				for(int i=0;i<num;i++)
				{
					dsp=(DishDisplayPanel) this.scrollContentPanel.getComponent(i);
					if(dsp.getDish().getName().equals(name)) dsp.loadNewImage(img);
				}
			}
		}
	}
	
	public void clearList()
	{
		searchList=new ArrayList<Dish>();
		searchList.add(model.getAddMore(type));
		
		this.scrollContentPanel.setPreferredSize(new Dimension(0,0));
		int itemNum=this.scrollContentPanel.getComponentCount();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
	}
	
	public void setSearch(boolean b,String kw)
	{
		System.out.println(" lisat all panel "+kw);
		this.isSearch=b;
		this.kw=kw;
	}
}
