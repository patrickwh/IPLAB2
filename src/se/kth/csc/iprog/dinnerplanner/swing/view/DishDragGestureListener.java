package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

import javax.swing.BorderFactory;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class DishDragGestureListener implements DragGestureListener{

	@Override
	public void dragGestureRecognized(DragGestureEvent e) {
		DishDisplayPanel p=(DishDisplayPanel)e.getComponent();
		p.setBorder(BorderFactory.createEtchedBorder());
		Dish dish=p.getDish();
		if(dish.getName().equals("NO RESULT")||dish.getName().equals(Constants.addMoreName)) return;
		Cursor cursor=null;
		if(e.getDragAction()==DnDConstants.ACTION_COPY)
		{
			//cursor=DragSource.DefaultCopyDrop;
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image img=toolkit.getImage(Constants.homeDir+Constants.pictureDir+dish.getImage());
			cursor=toolkit.createCustomCursor(img, new Point(30,30)," a cursor");
		}
		e.startDrag(cursor, new DishTransferable(dish));		
	}
}
