package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import se.kth.csc.iprog.dinnerplanner.model.ChangeMessage;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class InformationDisplayPanel extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	int guestNum=0;
	double totalMenuCost=0;
	JLabel guestNumLabel=new JLabel("Number of people");
	JPanel guestNumPanel=new JPanel();
	public JSpinner guestNumSpinner=new JSpinner();
	JLabel costLabel=new JLabel("Total cost:");
	JLabel numberLabel=new JLabel("$ 0.00");
	JPanel costPanel=new JPanel();
	JPanel topPanel=new JPanel();
	JPanel dinnerMenuPanel=new JPanel();
	JLabel dinnerMenuLabel=new JLabel("Dinner Menu");
	JPanel buttonPanel= new JPanel();
	public JButton preparationButton=new JButton("Preparation");
	public JButton ingredientsButton=new JButton("Ingredients");
	JPanel menuListPanel=new JPanel();
	JScrollPane menuScroll;
	JPanel scrollContentPanel=new JPanel();
	JPanel scrollBackgrounPanel=new JPanel();
	
	//ArrayList <Dish> menu=new ArrayList <Dish>();
	DinnerModel model;
	
	public InformationDisplayPanel(DinnerModel m)
	{
		this.model=m;
		// register this view as one of the model's observers
		this.model.addObserver(this);
		//this.transferSetToList();
		
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
		
		this.scrollContentPanel.setLayout(new FlowLayout());
		
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
		
		this.ingredientsButton.setFont(xsmallTextFont);
		this.ingredientsButton.setPreferredSize(new Dimension(Constants.preparationButtonWidth,
				Constants.preparationButtonHeight));
		
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
		
	}
	
	private void refreshMenuList(ArrayList<Dish> list)
	{
		int itemNum=scrollContentPanel.getComponentCount();
		for(int i=0;i<itemNum;i++) this.scrollContentPanel.remove(0);
		itemNum=list.size();
		for(int i=0;i<itemNum;i++)
			this.scrollContentPanel.add(new MenuListItem(list.get(i),this.guestNum,model));
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
	
	private void setTotalCost(int num)
	{
		this.guestNum=num;
		String str="$ ";
		str+=num*this.totalMenuCost;
		this.numberLabel.setText(str);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable obs, Object obj) {
		ChangeMessage cm=(ChangeMessage) obj;
		if(cm.getType()==ChangeMessage.GuestNumChanged)
		{
			int v=(Integer) cm.getData();
			this.setTotalCost(v);
			//System.out.println(" num in information "+this.guestNum);
		}else if(cm.getType()==ChangeMessage.MenuChanged)
		{
			this.refreshMenuList((ArrayList<Dish>)cm.getData());
		}else if(cm.getType()==ChangeMessage.ToatalMenuCostCahnged)
		{
			this.totalMenuCost=(Double) cm.getData();
			this.setTotalCost(this.guestNum);
		}
	}
	
}
