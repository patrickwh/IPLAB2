package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class InformationDisplayPanel extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	JLabel guestNumLabel=new JLabel("Number of people");
	JPanel guestNumPanel=new JPanel();
	JSpinner guestNumSpinner=new JSpinner();
	JLabel costLabel=new JLabel("Total cost:");
	JLabel numberLabel=new JLabel("$ 0.00");
	JPanel costPanel=new JPanel();
	JPanel topPanel=new JPanel();
	JPanel dinnerMenuPanel=new JPanel();
	JLabel dinnerMenuLabel=new JLabel("Dinner Menu");
	JPanel buttonPanel= new JPanel();
	JButton preparationButton=new JButton("Preparation");
	JButton ingredientsButton=new JButton("Ingredients");
	JPanel menuListPanel=new JPanel();
	JScrollPane menuScroll;
	JPanel scrollContentPanel=new JPanel();
	JPanel scrollBackgrounPanel=new JPanel();
	
	ArrayList <Dish> menu=new ArrayList <Dish>();
	DinnerModel model;

	public class MenuListItem extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		Dish dish=new Dish();
		int id=0;
		JLabel nameLabel=new JLabel();
		JLabel costLabel=new JLabel();
		JPanel labelPanel=new JPanel();
		JLabel imageLabel=new JLabel();
		JLabel removeButton= new JLabel();

		public MenuListItem(Dish d,int id)
		{
			this.id=id;
			this.dish=d;
			this.imageLabel.setPreferredSize(new Dimension(Constants.menuEntryPicWidth,
					Constants.menuEntryPicWidth));
			Image img=new ImageIcon(Constants.homeDir+Constants.pictureDir+dish.getImage()).getImage();
			Image newImg=img.getScaledInstance(Constants.menuEntryPicWidth,
					Constants.menuEntryPicWidth, Image.SCALE_SMOOTH);
			this.imageLabel.setIcon(new ImageIcon(newImg));
			this.imageLabel.setBorder(BorderFactory.createRaisedBevelBorder());
			
			this.nameLabel.setText("   "+dish.getypeStr()+" : "+dish.getName());
			this.nameLabel.setPreferredSize(new Dimension(Constants.menuEntryLabelWidth,
				Constants.menuEntryLabelHeight));
			this.costLabel.setText("   Cost : $ "+dish.getCost());
			this.costLabel.setPreferredSize(new Dimension(Constants.menuEntryLabelWidth,
					Constants.menuEntryLabelHeight));
			this.labelPanel.setLayout(new BorderLayout());
			this.labelPanel.add(nameLabel,BorderLayout.NORTH);
			this.labelPanel.add(costLabel,BorderLayout.SOUTH);
			this.labelPanel.setPreferredSize(new Dimension(Constants.menuEntryLabelWidth,
					Constants.menuEntryHeight));
			
			img=new ImageIcon(Constants.homeDir+Constants.pictureDir+"remove.jpg").getImage();
			newImg=img.getScaledInstance(Constants.menuEntryPicWidth,
					Constants.menuEntryPicWidth, Image.SCALE_SMOOTH);
			this.removeButton.setIcon(new ImageIcon(newImg));
			this.removeButton.setPreferredSize(new Dimension(Constants.menuEntryPicWidth,
					Constants.menuEntryPicWidth));
			//this.removeButton.setBackground(Color.RED);	
			this.removeButton.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					InformationDisplayPanel.this.removeFromList(MenuListItem.this.dish);
				}
				@Override
				public void mouseEntered(MouseEvent e)
				{
					MenuListItem.this.removeButton.setBorder(
							BorderFactory.createRaisedBevelBorder());
				}
				public void mouseExited(MouseEvent e)
				{
					MenuListItem.this.removeButton.setBorder(null);
				}
			});
			
			this.setPreferredSize(new Dimension(Constants.menuEntryWidth,
				Constants.menuEntryHeight));
			this.setLayout(new BorderLayout());
			this.add(imageLabel, BorderLayout.WEST);
			this.add(this.labelPanel,BorderLayout.CENTER);
			this.add(removeButton, BorderLayout.EAST);
			this.setBorder(BorderFactory.createEtchedBorder());
			this.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					DishNameDisplayWindow dndw=new DishNameDisplayWindow(MenuListItem.this.dish,
							InformationDisplayPanel.this.getGuestNum());
					dndw.setVisible(true);
				}
				@Override
				public void mouseEntered(MouseEvent e)
				{
					MenuListItem.this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
				}
				@Override
				public void mouseExited(MouseEvent e)
				{
					MenuListItem.this.setBorder(BorderFactory.createEtchedBorder());
				}
			});
		}
	}
	
	private void transferSetToList()
	{
		int num=menu.size();
		for(int i=0;i<num;i++) menu.remove(0);
		Iterator <Dish> itr=this.model.getFullMenu().iterator();
		while(itr.hasNext())
		{
			menu.add(itr.next());
		}
	}
	
	public InformationDisplayPanel(DinnerModel m)
	{
		this.model=m;
		// register this view as one of the model's observers
		this.model.addObserver(this);
		this.transferSetToList();
		
		Font smallTextFont=new Font ("Bodoni MT",Font.BOLD,18);
		Font xsmallTextFont=new Font ("Bodoni MT",Font.BOLD,16);
		Font largeTextFont=new Font ("Forte",Font.BOLD,32);
		
		this.guestNumLabel.setFont(smallTextFont);
		this.guestNumLabel.setPreferredSize(new Dimension(Constants.guestNumLabelWidth,
				Constants.guestNumLabelHeight));
		this.guestNumSpinner.setPreferredSize(new Dimension(Constants.guestNumSpinnerWidth,
				Constants.guestNumLabelHeight));
		this.guestNumSpinner.setFont(smallTextFont);
		this.guestNumSpinner.setPreferredSize(new Dimension(Constants.guestNumSpinnerWidth,
				Constants.guestNumSpinnerHeight));
		this.guestNumSpinner.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {	
				InformationDisplayPanel.this.setTotalCost();
			}	
		});
		
		this.guestNumPanel.setPreferredSize(new Dimension(Constants.informationPanelWidth,
				Constants.guestNumLabelHeight));
		this.guestNumPanel.setLayout(new BorderLayout());
		this.guestNumPanel.add(this.guestNumLabel,BorderLayout.WEST);
		this.guestNumPanel.add(this.guestNumSpinner,BorderLayout.CENTER);
		
		this.costLabel.setFont(smallTextFont);
		this.costLabel.setPreferredSize(new Dimension(Constants.guestNumLabelWidth,
				Constants.costLabelHeight));
		this.numberLabel.setFont(smallTextFont);
		this.numberLabel.setPreferredSize(new Dimension(Constants.guestNumSpinnerWidth,
				Constants.costLabelHeight));
		this.numberLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		this.costPanel.setPreferredSize(new Dimension(Constants.informationPanelWidth,
				Constants.costLabelHeight));
		this.costPanel.setLayout(new BorderLayout());
		this.costPanel.add(costLabel, BorderLayout.WEST);
		this.costPanel.add(numberLabel, BorderLayout.CENTER);
		
		this.topPanel.setPreferredSize(new Dimension(Constants.informationPanelWidth,
				Constants.informationPanelHeight));
		this.topPanel.add(this.guestNumPanel,BorderLayout.NORTH);
		this.topPanel.add(this.costPanel,BorderLayout.SOUTH);
		this.topPanel.setLayout(new BorderLayout());
		this.topPanel.add(guestNumPanel, BorderLayout.NORTH);
		this.topPanel.add(costPanel, BorderLayout.SOUTH);	
		this.topPanel.setBorder(BorderFactory.createEmptyBorder(Constants.largeBorderMargin, 
				 Constants.borderMargin,0, Constants.borderMargin));
		
		this.dinnerMenuLabel.setFont(largeTextFont);
		this.dinnerMenuLabel.setPreferredSize(new Dimension(Constants.informationPanelWidth,
				Constants.dinnerMenuHeight));
		this.dinnerMenuLabel.setHorizontalAlignment(JLabel.CENTER);
		this.dinnerMenuLabel.setVerticalAlignment(JLabel.CENTER);
		
		int rowNum=this.menu.size();
		int contentHeight=Constants.menuEntryRealHeight*rowNum;
		this.scrollContentPanel.setPreferredSize(new Dimension(Constants.menuEntryWidth,
				contentHeight));
		this.scrollContentPanel.setLayout(new FlowLayout());
		for(int i=0;i<rowNum;i++)
			this.scrollContentPanel.add(new MenuListItem(menu.get(i),i));
		this.menuScroll=new JScrollPane(scrollContentPanel);
		this.menuScroll.setPreferredSize(new Dimension(Constants.menuEntryWidth,
				Constants.menuListHeight));
		this.menuScroll.setBounds(0,0,Constants.menuEntryWidth, Constants.menuListHeight);
		this.menuScroll.getVerticalScrollBar().setUnitIncrement(10);
		this.scrollBackgrounPanel.setLayout(new BorderLayout());
		this.scrollBackgrounPanel.setPreferredSize(new Dimension(Constants.menuEntryWidth,
				Constants.menuListHeight));
		this.scrollBackgrounPanel.add(menuScroll, BorderLayout.CENTER);		
		
		this.dinnerMenuPanel.setLayout(new BorderLayout());
		this.dinnerMenuPanel.add(this.dinnerMenuLabel, BorderLayout.NORTH);
		this.dinnerMenuPanel.add(scrollBackgrounPanel, BorderLayout.CENTER);
		this.dinnerMenuPanel.setPreferredSize(new Dimension(Constants.informationPanelWidth,
				Constants.dinnerMenuHeight));
		this.dinnerMenuPanel.setBorder(BorderFactory.createEmptyBorder(0, 
				Constants.borderMargin, 0, Constants.borderMargin));
		
		this.preparationButton.setFont(xsmallTextFont);
		this.preparationButton.setPreferredSize(new Dimension(Constants.preparationButtonWidth,
				Constants.preparationButtonHeight));
		this.preparationButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{ // new preparation window
				PreparationPanel pp=new PreparationPanel(null,null,null);
				pp.creatAndShowGUI();
			}
		});
		this.ingredientsButton.setFont(xsmallTextFont);
		this.ingredientsButton.setPreferredSize(new Dimension(Constants.preparationButtonWidth,
				Constants.preparationButtonHeight));
		this.ingredientsButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{ // new ingredients window
				IngredientPanel ip=new IngredientPanel();
				ip.creatAndShowGUI();
			}
		});
		
		this.buttonPanel.setLayout(new FlowLayout());
		this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(Constants.borderMargin, 
				0, Constants.borderMargin, 0));
		this.buttonPanel.add(preparationButton);
		this.buttonPanel.add(ingredientsButton);
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(dinnerMenuPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(Constants.informationPanelWidth,Constants.height));
		
//		topPanel.setLayout(new GridLayout(2,2));
//		this.topPanel.add(guestNumLabel);
//		this.topPanel.add(guestNumSpinner);
//		this.topPanel.add(costLabel);
//		this.topPanel.add(numberLabel);
		
	}
	
	public void removeFromList(Dish dish)
	{
		int itemNum=this.menu.size();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
		model.removeDishFromMenu(dish);
		this.transferSetToList();
		itemNum=this.menu.size();
		for(int i=0;i<itemNum;i++)
			this.scrollContentPanel.add(new MenuListItem(menu.get(i),i));
		int contentHeight=Constants.menuEntryRealHeight*itemNum;
		this.scrollContentPanel.setPreferredSize(new Dimension(Constants.menuEntryWidth,
				contentHeight));
		this.revalidate();
		this.repaint();
	}
	
	public void addToList(Dish d)
	{
		int itemNum=this.menu.size();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
		model.addDishToMenu(d);
		this.transferSetToList();
		itemNum=this.menu.size();
		for(int i=0;i<itemNum;i++)
			this.scrollContentPanel.add(new MenuListItem(menu.get(i),i));
		int contentHeight=Constants.menuEntryRealHeight*itemNum;
		this.scrollContentPanel.setPreferredSize(new Dimension(Constants.menuEntryWidth,
				contentHeight));
		this.revalidate();
		this.repaint();
	}
	
	public int getGuestNum()
	{
		return (Integer) this.guestNumSpinner.getValue();
	}
	
	public void setTotalCost()
	{
		String str="$ ";
		int num=(Integer) this.guestNumSpinner.getValue();
		str+=num*model.getTotalMenuPrice();
		this.numberLabel.setText(str);
	}

	@Override
	public void update(Observable obs, Object obj) {
		
	}
}
