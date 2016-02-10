package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class DishDropTargetListener implements DropTargetListener{
	
	InformationDisplayPanel listPanel;
	DropTarget dropTarget;
	
	public DishDropTargetListener(InformationDisplayPanel panel)
	{
		this.listPanel=panel;
		this.dropTarget=new DropTarget(listPanel,DnDConstants.ACTION_COPY,this,true,null);
		//(Component c,int ops, DropTargetListener dtl,boolean act,FlavorMap fm)     
	}

	@Override
	public void dragEnter(DropTargetDragEvent e) {
		
	}

	@Override
	public void dragExit(DropTargetEvent e) {
		
	}

	@Override
	public void dragOver(DropTargetDragEvent e) {
		
	}

	@Override
	public void drop(DropTargetDropEvent e) {
		try{
			Transferable tr=e.getTransferable();
			Dish dish=(Dish) tr.getTransferData(DishTransferable.dishFlavor);
			if(e.isDataFlavorSupported(DishTransferable.dishFlavor))
			{
				e.acceptDrop(DnDConstants.ACTION_COPY);
				this.listPanel.addToList(dish);
				e.dropComplete(true);
				return;
			}
		}catch (Exception except)
		{
			except.printStackTrace();
			e.rejectDrop();
			
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent e) {
		
	}

}
