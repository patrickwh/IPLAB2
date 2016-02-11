package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

public class DishNameDisplayWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Dish dish;
	int guestNum=0;
	String[] header={"Ingredient","Quantity","Cost"};
	JPanel content=new JPanel();
	JPanel nameInformationPanel=new JPanel();
	JLabel image=new JLabel();
	JPanel imagePanel=new JPanel();
	JPanel namePanel=new JPanel();
	JLabel name=new JLabel();
	JLabel cost=new JLabel();
	JSplitPane split=new JSplitPane();
	JScrollPane descriptionScroll;
	JScrollPane ingredientScroll;
	JTextArea description=new JTextArea();
	JTable table;
	IngredientTableCellRender render=new IngredientTableCellRender();
	JTableHeader theader;
	
	private class TableHeaderRender extends DefaultTableCellRenderer
	{
		private static final long serialVersionUID = 1L;
		JComponent cell;
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		          boolean hasFocus, int row, int column)
		{
			cell = (JComponent)super.getTableCellRendererComponent 
			        ( table, value,isSelected, hasFocus, row, column);
			cell.setBackground(Color.LIGHT_GRAY);
			if(column==0) cell.setPreferredSize(new Dimension(Constants.dishNameTableColumnWidth1,
					Constants.dishNameTableRowHeight));
			else cell.setPreferredSize(new Dimension(Constants.dishNameTableColumnWidth2,
					Constants.dishNameTableRowHeight));
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			return cell;	
		}
	}
	public DishNameDisplayWindow(Dish d,int num)
	{
		this.dish=d;
		this.guestNum=num;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Font nameFont=new Font("Informal Roman",Font.BOLD,38);
		Font costFont=new Font("Segoe Print",Font.BOLD,20);
		Font tableFont=new  Font("Gill Sans MT",Font.PLAIN,20);
		
		this.setBounds((Constants.widthDf-Constants.dishNameDisplayWindowWidth)/2,
				(Constants.heightDf-Constants.dishNameDisplayWindowHeight)/2, 
				Constants.dishNameDisplayWindowWidth, Constants.dishNameDisplayWindowHeight);
		this.setContentPane(this.content);
		this.content.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
				Constants.dishNameDisplayWindowHeight));
		this.content.setLayout(new BorderLayout());
		this.content.add(nameInformationPanel, BorderLayout.NORTH);
		this.content.add(split,BorderLayout.CENTER);
		
		Image img=new ImageIcon(Constants.homeDir+Constants.pictureDir+this.dish.getImage()).getImage();
		Image newImg=img.getScaledInstance(Constants.dishNameImageWidth, Constants.dishNameImageWidth, 
				Image.SCALE_SMOOTH);
		this.image.setIcon(new ImageIcon(newImg));
		this.image.setBorder(BorderFactory.createRaisedBevelBorder());
		this.imagePanel.setPreferredSize(new Dimension(Constants.dishNameInformationPanelHeight,
				Constants.dishNameInformationPanelHeight));
		this.imagePanel.setLayout(new BorderLayout());
		this.imagePanel.add(image,BorderLayout.CENTER);
		this.imagePanel.setBorder(BorderFactory.createEmptyBorder(Constants.dishNameBorder, 
				Constants.dishNameBorder, Constants.dishNameBorder, Constants.dishNameBorder));
		
		this.name.setText(dish.getName());
		this.name.setFont(nameFont);
		this.name.setPreferredSize(new Dimension(Constants.dishNameNamePanelWidth,
				Constants.dishNameNameHeight));
		this.cost.setPreferredSize(new Dimension(Constants.dishNameNamePanelWidth,
				Constants.dishNameNameHeight));
		this.cost.setText("$ "+(dish.getCost()*guestNum)+"  for "+this.guestNum+" persons");
		this.cost.setFont(costFont);
		this.namePanel.setPreferredSize(new Dimension(Constants.dishNameNamePanelWidth,
				Constants.dishNameInformationPanelHeight));
		this.namePanel.setLayout(new BorderLayout());
		this.namePanel.add(name, BorderLayout.NORTH);
		this.namePanel.add(cost,BorderLayout.SOUTH);
		this.namePanel.setBorder(BorderFactory.createEmptyBorder
				(Constants.dishNameNamePanelBorderWidth, Constants.dishNameNamePanelBorderWidth,
						Constants.dishNameNamePanelBorderWidth, Constants.dishNameNamePanelBorderWidth));
		
		this.nameInformationPanel.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
				Constants.dishNameInformationPanelHeight));
		this.nameInformationPanel.setLayout(new BorderLayout());
		this.nameInformationPanel.add(this.imagePanel, BorderLayout.WEST);
		this.nameInformationPanel.add(this.namePanel, BorderLayout.CENTER);
		
		this.description.setEditable(false);
		this.description.setLineWrap(true);
		this.description.setText(dish.getDescription());
		this.description.setFont(costFont);
		this.description.setOpaque(false);// Translucent background
		this.descriptionScroll=new JScrollPane(this.description,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.descriptionScroll.setPreferredSize(new Dimension(Constants.dishNameDescriptionWidth,
				Constants.dishNameSplitHeight));
		this.description.setSelectionStart(0);
		this.description.setSelectionEnd(0);
		this.descriptionScroll.getVerticalScrollBar().setValue(0);
		
		
		this.table=new JTable(){
			private static final long serialVersionUID = 1L;

			public TableCellRenderer getCellRenderer(int row, int column) 
            {
              return render;
            }
	    };
		DefaultTableModel dtm=(DefaultTableModel)this.table.getModel();
		dtm.setColumnIdentifiers(header);
		TableHeaderRender hrender=new TableHeaderRender();
		this.theader=this.table.getTableHeader();
		this.theader.setDefaultRenderer(hrender);
		this.theader.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int columnNo=DishNameDisplayWindow.this.table.columnAtPoint(e.getPoint());
				DishNameDisplayWindow.this.sortTbaleByKey(columnNo);
			}
		});
		this.table.getColumn("Ingredient").setPreferredWidth(Constants.dishNameTableColumnWidth1);
		this.table.getColumn("Quantity").setPreferredWidth(Constants.dishNameTableColumnWidth2);
		this.table.getColumn("Cost").setPreferredWidth(Constants.dishNameTableColumnWidth2);
		this.table.setOpaque(false);
		this.table.setRowHeight(Constants.dishNameTableRowHeight);
		this.table.setFont(tableFont);
		
		Iterator <Ingredient> itr=dish.getIngredients().iterator();
		String[] tableContent=new String[3];
		while(itr.hasNext())
		{
			Ingredient ing=itr.next();
			tableContent[0]=ing.getName();
			tableContent[1]=""+ing.getQuantity()+" "+ing.getUnit();
			tableContent[2]="$ "+ing.getPrice();
			dtm.addRow(tableContent);
		}
		
		this.ingredientScroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.ingredientScroll.setPreferredSize(new Dimension(Constants.dishNameTableWidth,
				Constants.dishNameSplitHeight));
		
		this.split.setPreferredSize(new Dimension(Constants.dishNameDisplayWindowWidth,
				Constants.dishNameInformationPanelHeight));
		this.split.setDividerLocation(Constants.dishNameDividerLocation);
		this.split.setLeftComponent(descriptionScroll);
		this.split.setRightComponent(ingredientScroll);
		this.split.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
		Iterator <Ingredient> itr=dish.getIngredients().iterator();
		ArrayList<Ingredient> list=new ArrayList<Ingredient>();
		while(itr.hasNext()) list.add(itr.next());
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
	
	public static void main(String args[])
	{
		Dish d=new Dish("NO RESULT",1,"noResult.jpg","No result has been found");
		d.addIngredient(new Ingredient("Carrot",500,"g",2.5));
		d.addIngredient(new Ingredient("Egg",2,"",4.0));
		DishNameDisplayWindow ddw=new DishNameDisplayWindow(d,3);
		ddw.setVisible(true);
	}
}
