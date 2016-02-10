package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class ListAllPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	JTextField searchText=new JTextField();
	JScrollPane scroll;
	JButton searchButton=new JButton("Search");
	JPanel searchPanel=new JPanel();
	public JPanel scrollContentPanel=new JPanel();
	int dishNum=10;
	ArrayList<Dish> dishList= new ArrayList<Dish>();
	JPanel scrollBackgroundPanel=new JPanel();
	Dish selectedItem=new Dish();
	private void init()
	{
		Dish d=new Dish("French toast",Dish.STARTER,"toast.jpg","In a large mixing bowl, "
				+ "beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. "
				+ "Soak bread slices in the egg mixture until saturated. Heat a lightly oiled "
				+ "griddle or frying pan over medium high heat. Brown slices on both sides, "
				+ "sprinkle with cinnamon and serve hot.");
		int num=10;
		for(int i=0;i<num;i++)
			this.dishList.add(d);
	}
	public class DishDisplayPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		Dish dish=new Dish();
		ImageIcon dishIcon;
		JLabel dishImageLabel;
		JLabel dishNameLabel=new JLabel();
		int ID;
		
		public DishDisplayPanel(Dish d,int id)
		{
			
			this.setPreferredSize(new Dimension(Constants.dishDisplayWidth+Constants.interDishDisplayMargin,
					Constants.dishDisplayHeight+Constants.interDishDisplayMargin));
			//if(d==null) return;
			
			this.ID=id;
			this.dish=d;
			this.dishIcon=new ImageIcon(Constants.homeDir+Constants.pictureDir+dish.getImage());
			this.dishImageLabel=new JLabel();		
			this.dishNameLabel.setText(dish.getName());
			
			this.setBorder(BorderFactory.createLoweredBevelBorder());
			this.dishImageLabel.setPreferredSize(new Dimension(Constants.dishDisplayWidth,
					Constants.dishDisplayWidth));
			this.dishNameLabel.setPreferredSize(new Dimension(Constants.dishDisplayWidth,
				    Constants.dishNameDisplayLabelHeight));
			this.dishNameLabel.setHorizontalAlignment(JLabel.CENTER);
			this.dishNameLabel.setVerticalAlignment(JLabel.CENTER);
			
			Font nameFont=new Font("Britannic", Font.BOLD,20);
			this.dishNameLabel.setFont(nameFont);
			
			Image img=this.dishIcon.getImage();
			Image newImg=img.getScaledInstance
					(Constants.dishDisplayWidth,Constants.dishDisplayWidth,Image.SCALE_SMOOTH);
			ImageIcon newIcon=new ImageIcon(newImg);
			this.dishImageLabel.setIcon(newIcon);
			//this.dishImageLabel.setIcon(this.dishIcon);
			this.setLayout(new BorderLayout());
			this.add(this.dishImageLabel,BorderLayout.NORTH);
			this.add(this.dishNameLabel,BorderLayout.CENTER);
			
			this.setFocusable(true);
			this.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					System.out.println(" this is clicked "+DishDisplayPanel.this.ID);
				}
				@Override
				public void mouseEntered(MouseEvent e)
				{
					DishDisplayPanel.this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,5));
				}
				@Override
				public void mouseExited(MouseEvent e)
				{
					DishDisplayPanel.this.setBorder(BorderFactory.createLoweredBevelBorder());
				}
				@Override
				public void mouseDragged(MouseEvent e)
				{
					// when drag dish into the menu
					//ListAllPanel.this.setSlectedItem(DishDisplayPanel.this.ID);
					//System.out.println(" draged in main "+e.getComponent());
				}
			});
			
			DragSource dragSource=DragSource.getDefaultDragSource();
			dragSource.createDefaultDragGestureRecognizer(this, 
					DnDConstants.ACTION_COPY, new DishDragGestureListener());
		}
		
		public Dish getDish()
		{
			return this.dish;
		}

	}
	public  ListAllPanel()
	{
		//////////////////////////////////////////////////
		
		this.init();
		
		/////////////////////////////////////////////////
		Font font=new Font("Britannic", Font.BOLD,20);
		
		this.setLayout(new BorderLayout());
		this.searchPanel.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Constants.tabWidth,Constants.height));
		this.searchPanel.setPreferredSize(new Dimension(Constants.tabWidth,
				Constants.searchFieldHeight));
		this.searchButton.setPreferredSize(new Dimension(Constants.searchButtonWidth,
				Constants.searchFieldHeight));
		//this.searchButton.setFont(font);
		this.searchButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{ 
				ListAllPanel.this.doSearch();				
			}
		});
		this.searchText.setPreferredSize( new Dimension(Constants.searchTextWidth, 
				Constants.searchFieldHeight));
		this.searchText.setFont(font);
		
		this.searchPanel.add(this.searchText,BorderLayout.WEST);
		this.searchPanel.add(this.searchButton,BorderLayout.CENTER);
		this.add(this.searchPanel, BorderLayout.NORTH);
		
		this.scrollContentPanel=new JPanel();	
		this.scrollBackgroundPanel.setPreferredSize(new Dimension(Constants.tabWidth,
				Constants.height));
		this.listAll();
		
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
		
	}
	private void listAll()
	{
		int itemNum=this.scrollContentPanel.getComponentCount();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
		
		int num=this.dishList.size();
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
			this.scrollContentPanel.add(new DishDisplayPanel(this.dishList.get(i),i));
		}
		this.revalidate();
		this.repaint();
	}
	private void showThisDishOnly(Dish d)
	{
		int itemNum=this.scrollContentPanel.getComponentCount();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
		this.scrollContentPanel.setPreferredSize(new Dimension(Constants.dishDisplayWidth,
				Constants.dishDisplayHeight));
		this.scrollContentPanel.setLayout(new GridLayout(2,Constants.dishNumInARow));
		DishDisplayPanel r=new DishDisplayPanel(d,0);
		this.scrollContentPanel.add(r);
		this.scrollContentPanel.setLayout(null);
		int width= Constants.dishDisplayWidth+10;
		int height=Constants.dishDisplayHeight+10;
		r.setBounds(0, 0,width,height);//      French toast
		this.revalidate();
		this.repaint();
	}
	public void doSearch()
	{
		String name=this.searchText.getText();
		//Dish tmp=new Dish();
		if(name.length()==0) this.listAll();;
		int num=this.dishList.size();
		for(int i=0;i<num;i++)
		{
			if(this.dishList.get(i).getName().equals(name))
			{
				this.showThisDishOnly(dishList.get(i));
				break;
			}
		}
		this.showThisDishOnly(new Dish("NO RESULT",1,"noResult.jpg","No result has been found"));
	}
	public void setSlectedItem(int id)
	{
		this.selectedItem=this.dishList.get(id);
	}
}
