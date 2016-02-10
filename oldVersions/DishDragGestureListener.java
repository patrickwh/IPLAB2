package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.swing.view.ListAllPanel.DishDisplayPanel;

public class DishDragGestureListener implements DragGestureListener{

	@Override
	public void dragGestureRecognized(DragGestureEvent e) {
		Cursor cursor=null;
		DishDisplayPanel p=(DishDisplayPanel)e.getComponent();
		
		Dish dish=p.getDish();
		if(dish.getName().equals("NO RESULT")) return;
		if(e.getDragAction()==DnDConstants.ACTION_COPY)
		{
			cursor=DragSource.DefaultCopyDrop;
		}
		e.startDrag(cursor, new DishTransferable(dish));		
	}
}
