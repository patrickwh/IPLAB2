package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import se.kth.csc.iprog.dinnerplanner.model.ChangeMessage;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

public class IngredientPanel extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;

    Font tableFont = new Font("Gill Sans MT", Font.PLAIN, 16);
    Font tableFontSmall = new Font("Gill Sans MT", Font.PLAIN, 14);
    Dish tempStarter;
    Dish tempMain;
    Dish temphDesert;
    String temp1;
    Ingredient tempS;
    ArrayList<Ingredient> list=new ArrayList<Ingredient>();
    JTable table = new JTable();
    JTableHeader theader;

    public void creatAndShowGUI(ArrayList<Ingredient> list) {
        JFrame frame = new JFrame("Dinner Planner - Ingredients");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds((Constants.widthDf-Constants.ingredientPanelWidth)/2, 
        		(Constants.heightDf-Constants.ingredientPanelHeight)/2,        		
        		Constants.ingredientPanelWidth, Constants.ingredientPanelHeight);
        Image img = this.getToolkit().getImage(Constants.windowIconDir);
        frame.setIconImage(img);
        this.setPreferredSize(new Dimension(Constants.ingredientPanelWidth,
        		Constants.ingredientPanelHeight));
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //Create and set up the content pane.
        //IngredientPanel newContentPane = new IngredientPanel();
        this.setOpaque(false); //content panes must be opaque
        frame.setContentPane(this); //Display the window.
        this.list=list;
        IngredientTableCellRender renderer = new IngredientTableCellRender();

        String headers[] = {"Ingredient", "Quantity", "Cost"};
        
        DefaultTableModel dtm=(DefaultTableModel) table.getModel();
        dtm.setColumnIdentifiers(headers);
        this.refreshIngredientsTable(list); 
        Font tableFont=new  Font("Gill Sans MT",Font.PLAIN,20);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);
        table.setDefaultRenderer(Object.class, renderer);
        //table.setFillsViewportHeight(true);
        //table.setAutoCreateRowSorter(true);
        table.setRowHeight(Constants.dishNameTableRowHeight);
        this.table.getColumn("Ingredient").setPreferredWidth(Constants.ingredientPanelTableWidth1);
		this.table.getColumn("Quantity").setPreferredWidth(Constants.ingredientPanelTableWidth2);
		this.table.getColumn("Cost").setPreferredWidth(Constants.ingredientPanelTableWidth2);
        int height=Constants.dishNameTableRowHeight;
		int [] width={Constants.ingredientPanelTableWidth1,Constants.ingredientPanelTableWidth2,
				Constants.ingredientPanelTableWidth2};
        TableHeaderRender hrender=new TableHeaderRender(width,height);
		this.theader=this.table.getTableHeader();
		this.theader.setDefaultRenderer(hrender);
		this.theader.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int columnNo=IngredientPanel.this.table.columnAtPoint(e.getPoint());
				IngredientPanel.this.sortTbaleByKey(columnNo);
			}
		});

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(Constants.ingredientPanelWidth,
        		Constants.ingredientPanelHeight));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setFont(tableFont);
        frame.pack();
        frame.setVisible(true);
    }

    private class ListComparator implements Comparator<Ingredient>
	{
		int sortType=0;
		public ListComparator(int t)
		{
			this.sortType=t;
		}
		@Override
		public int compare(Ingredient ing1, Ingredient ing2) {
			if(this.sortType==0) return ing1.getName().compareTo(ing2.getName());
			else if(this.sortType==1) return Double.compare(ing1.getQuantity(), ing2.getQuantity());
			else return Double.compare(ing1.getPrice(), ing2.getPrice());
		}
	}
    
	public void sortTbaleByKey(int key)
	{
		ListComparator comp=new ListComparator(key);
		Collections.sort(list, comp);
		int num=this.table.getRowCount();
		DefaultTableModel dtm=(DefaultTableModel)this.table.getModel();
		for(int i=0;i<num;i++) dtm.removeRow(0);
		num=list.size();
		String[] tableContent=new String[3];
		for(int i=0;i<num;i++)
		{
			Ingredient ing=list.get(i);
			tableContent[0]=ing.getName();
			tableContent[1]=""+ing.getQuantity()+" "+ing.getUnit();
			tableContent[2]="$ "+ing.getPrice();
			dtm.addRow(tableContent);
		}
		this.validate();
		this.repaint();
	}

	// set the background color

    public void refreshIngredientsTable(ArrayList<Ingredient> list)
    {
    	int num=this.table.getRowCount();
    	DefaultTableModel dtm=(DefaultTableModel) table.getModel();
    	for(int i=0;i<num;i++) dtm.removeRow(0);
    	String[] data=new String [3];
    	num=list.size();
    	for(int i=0;i<num;i++)
    	{
    		Ingredient tmp=list.get(i);
    		data[0]=tmp.getName();
    		data[1]=tmp.getQuantity()+" "+tmp.getUnit();
    		data[2]="$ "+tmp.getPrice();
    		dtm.addRow(data);
    	}
    	this.validate();
    	this.repaint();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(Observable obs, Object obj) {
        ChangeMessage cm=(ChangeMessage) obj;
        if(cm.getType()==ChangeMessage.ingredientsCahnged)
        {
            this.refreshIngredientsTable((ArrayList<Ingredient>)cm.getData());
        }
    }
}


