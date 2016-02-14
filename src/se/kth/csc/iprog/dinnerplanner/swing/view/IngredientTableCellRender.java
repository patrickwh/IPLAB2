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
		Color background=null,foregroud=null;
		if(isSelected)
		{
			background = Color.BLACK;
            foregroud=Color.WHITE;
		}else
		{
			if (row % 2 == 0) {
	            background = Color.GRAY;
	            foregroud=Color.LIGHT_GRAY;
	        } else {
	            background = Color.LIGHT_GRAY;
	            foregroud=Color.GRAY;
	        }
		}
		cell.setBackground(background);
        cell.setForeground(foregroud);
	    return cell;	
	}
}
