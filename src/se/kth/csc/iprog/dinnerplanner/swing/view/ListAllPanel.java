package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
	JPanel scrollContentPnael=new JPanel();
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
			this.ID=id;
			this.dish=d;
			this.dishIcon=new ImageIcon(Constants.homeDir+Constants.pictureDir+dish.getImage());
			this.dishImageLabel=new JLabel();		
			this.dishNameLabel.setText(dish.getName());
			
			this.setPreferredSize(new Dimension(Constants.dishDisplayWidth+Constants.interDishDisplayMargin,
					Constants.dishDisplayHeight+Constants.interDishDisplayMargin));
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
					DishDisplayPanel.this.setBorder(BorderFactory.createRaisedBevelBorder());
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
					ListAllPanel.this.setSlectedItem(DishDisplayPanel.this.ID);
					System.out.println(" draged in main "+e.getComponent());
				}
			});
		}

	}
	public  ListAllPanel()
	{
		//////////////////////////////////////////////////
		
		this.init();
		
		/////////////////////////////////////////////////
		this.setLayout(new BorderLayout());
		this.searchPanel.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Constants.tabWidth,Constants.height));
		this.searchPanel.setPreferredSize(new Dimension(Constants.tabWidth,
				Constants.searchFieldHeight));
		this.searchButton.setPreferredSize(new Dimension(Constants.searchButtonWidth,
				Constants.searchFieldHeight));
		this.searchText.setPreferredSize( new Dimension(Constants.searchTextWidth, 
				Constants.searchFieldHeight));
		
		this.searchPanel.add(this.searchText,BorderLayout.WEST);
		this.searchPanel.add(this.searchButton,BorderLayout.CENTER);
		this.add(this.searchPanel, BorderLayout.NORTH);
		
		int remainder=this.dishNum%Constants.dishNumInARow;
		int rowNum=this.dishNum/Constants.dishNumInARow;
		if(remainder!=0) rowNum++;
		int contentPanelWidth=Constants.tabWidth-50;
		int contentPanelHeight=Constants.dishDisplayHeight*rowNum+
				Constants.interDishDisplayMargin*(rowNum+1);
		this.scrollContentPnael=new JPanel(new GridLayout(rowNum,Constants.dishNumInARow));
		//this.scrollContentPnael.setLayout(new FlowLayout());
		this.scrollContentPnael.setPreferredSize(new Dimension(contentPanelWidth,
				contentPanelHeight));
		this.scrollBackgroundPanel.setPreferredSize(new Dimension(Constants.tabWidth,
				Constants.height));
		
		for(int i=0;i<this.dishNum;i++)
		{
			this.scrollContentPnael.add(new DishDisplayPanel(this.dishList.get(i),i));
		}
		
		this.scroll=new JScrollPane(this.scrollContentPnael,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scroll.setBounds(0,0,Constants.tabWidth, Constants.scrollHeight);
		this.scroll.getVerticalScrollBar().setUnitIncrement(Constants.verticalScrollbarUnit);
		this.add(this.scrollBackgroundPanel,BorderLayout.CENTER);
		this.scrollBackgroundPanel.setLayout(new BorderLayout());
		this.scrollBackgroundPanel.add(this.scroll, BorderLayout.CENTER);
	}
	
	public void setSlectedItem(int id)
	{
		this.selectedItem=this.dishList.get(id);
	}
}
