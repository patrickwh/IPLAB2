package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ListAllPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	JTextField searchText=new JTextField();
	JScrollPane scroll;
	JButton searchButton=new JButton("Search");
	JPanel searchPanel=new JPanel();
	JPanel scrollContentPnael=new JPanel();
	int dishNum=10;
	DishDisplayPanel [] dishPanels;
	JPanel scrollBackgroundPanel=new JPanel();
	 
	public  ListAllPanel()
	{
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
		
		this.dishPanels=new DishDisplayPanel[this.dishNum];
		for(int i=0;i<this.dishNum;i++)
		{
			this.dishPanels[i]=new DishDisplayPanel(i);
			this.scrollContentPnael.add(this.dishPanels[i]);
		}
		
		this.scroll=new JScrollPane(this.scrollContentPnael,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scroll.setBounds(0,0,Constants.tabWidth, Constants.scrollHeight);
		this.scroll.getVerticalScrollBar().setUnitIncrement(Constants.verticalScrollbarUnit);
		this.add(this.scrollBackgroundPanel,BorderLayout.CENTER);
		this.scrollBackgroundPanel.setLayout(new BorderLayout());
		this.scrollBackgroundPanel.add(this.scroll, BorderLayout.CENTER);
	}
}
