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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class InformationDisplayPanel extends JPanel{
	
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
	JButton preparationButton=new JButton("PreparationPanel");
	JButton ingredientsButton=new JButton("Ingredients");
	JPanel menuListPanel=new JPanel();
	JScrollPane menuScroll;
	JPanel scrollContentPanel=new JPanel();
	JPanel scrollBackgrounPanel=new JPanel();
	
	ArrayList <Dish> menu=new ArrayList <Dish>();

	public class MenuListItem extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		Dish dish=new Dish();
		int id=0;
		JLabel nameLabel=new JLabel();
		JLabel costLabel=new JLabel();
		JPanel labelPanel=new JPanel();
		JLabel imageLabel=new JLabel();
		JButton removeButton= new JButton();
		
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
			
			this.nameLabel.setText("   "+dish.getypeStr()+" : "+dish.getName());
			this.nameLabel.setPreferredSize(new Dimension(Constants.menuEntryLabelWidth,
				Constants.menuEntryLabelHeight));
			this.costLabel.setText("   Cost :$"+dish.getCost());
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
			this.removeButton.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e)
				{
					InformationDisplayPanel.this.removeFromList(MenuListItem.this.id);
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
				public void mouseEntered(MouseEvent e)
				{
					MenuListItem.this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
				}
				public void mouseExited(MouseEvent e)
				{
					MenuListItem.this.setBorder(BorderFactory.createEtchedBorder());
				}
			});
		}
	}
	
	public InformationDisplayPanel()
	{
		Dish dish=new Dish("French toast",Dish.STARTER,"toast.jpg","In a large mixing bowl, "
				+ "beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. "
				+ "Soak bread slices in the egg mixture until saturated. Heat a lightly oiled "
				+ "griddle or frying pan over medium high heat. Brown slices on both sides, "
				+ "sprinkle with cinnamon and serve hot.");
		this.menu.add(dish);
		this.menu.add(dish);
		this.menu.add(dish);
		this.menu.add(dish);
		this.menu.add(dish);
		this.menu.add(dish);
		this.menu.add(dish);
		this.menu.add(dish);
		this.menu.add(dish);
		
		Font smallTextFont=new Font ("Bodoni MT",Font.BOLD,18);
		Font xsmallTextFont=new Font ("Bodoni MT",Font.BOLD,16);
		Font largeTextFont=new Font ("��Բ",Font.BOLD,32);
		
		this.guestNumLabel.setFont(smallTextFont);
		this.guestNumLabel.setPreferredSize(new Dimension(Constants.guestNumLabelWidth,
				Constants.guestNumLabelHeight));
		this.guestNumSpinner.setPreferredSize(new Dimension(Constants.guestNumSpinnerWidth,
				Constants.guestNumLabelHeight));
		this.guestNumSpinner.setFont(smallTextFont);
		this.guestNumSpinner.setPreferredSize(new Dimension(Constants.guestNumSpinnerWidth,
				Constants.guestNumSpinnerHeight));
		
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
				
			}
		});
		this.ingredientsButton.setFont(xsmallTextFont);
		this.ingredientsButton.setPreferredSize(new Dimension(Constants.preparationButtonWidth,
				Constants.preparationButtonHeight));
		this.ingredientsButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{ // new ingredients window
				
			}
		});
		
		this.buttonPanel.setLayout(new FlowLayout());
		this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(Constants.borderMargin, 
				Constants.borderMargin, Constants.borderMargin, Constants.borderMargin));
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
	
	public void removeFromList(int id)
	{
		int itemNum=this.menu.size();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
		this.menu.remove(id);
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
		this.menu.add(d);
		itemNum=this.menu.size();
		for(int i=0;i<itemNum;i++)
			this.scrollContentPanel.add(new MenuListItem(menu.get(i),i));
		int contentHeight=Constants.menuEntryRealHeight*itemNum;
		this.scrollContentPanel.setPreferredSize(new Dimension(Constants.menuEntryWidth,
				contentHeight));
		this.revalidate();
		this.repaint();
	}
}
