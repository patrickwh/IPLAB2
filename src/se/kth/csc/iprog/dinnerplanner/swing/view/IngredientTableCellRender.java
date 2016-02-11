package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class IngredientTableCellRender extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
          boolean hasFocus, int row, int column)
	{
		Component cell = super.getTableCellRendererComponent 
	               ( table, value,isSelected, hasFocus, row, column);
		if (row % 2 == 0) cell.setBackground(Color.gray);
	    else cell.setBackground(Color.LIGHT_GRAY);
	    return cell;	
	}
}
