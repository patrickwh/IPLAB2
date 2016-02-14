package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;


public class TableHeaderRender extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 1L;
	int headerHeight;
	int [] rowWidths;
	public TableHeaderRender(int [] width,int height)
	{
		this.headerHeight=height;
		this.rowWidths=width;
	}
	JComponent cell;
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	          boolean hasFocus, int row, int column)
	{
		cell = (JComponent)super.getTableCellRendererComponent 
		        ( table, value,isSelected, hasFocus, row, column);
		cell.setBackground(Color.LIGHT_GRAY);
		cell.setPreferredSize(new Dimension(rowWidths[column],headerHeight));
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		Font headerFont=new  Font("Segoe Print",Font.BOLD,18);
		cell.setFont(headerFont);
		return cell;	
	}
}
