package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class DishTransferable implements Transferable{

	protected static DataFlavor dishFlavor=new 
			DataFlavor(Dish.class,"A dish object");
	protected static DataFlavor [] supportedFlavors=
		{dishFlavor,DataFlavor.stringFlavor};
	Dish dish=new Dish();
	
	public DishTransferable(Dish d)
	{
		this.dish=d;
	}
	
	@Override
	public Object getTransferData(DataFlavor df)
			throws UnsupportedFlavorException, IOException {
		if (df.equals(dishFlavor))
	         return dish;
	    else if (df.equals(DataFlavor.stringFlavor)) 
	         return dish.toString();
	    else 
	         throw new UnsupportedFlavorException(df);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor df) {
		int num=supportedFlavors.length;
		boolean support=false;
		for(int i=0;i<num;i++)
		{
			if(df.equals(supportedFlavors[i]))
			{
				support=true;
				break;
			}
		}
		return support;
	}

}
