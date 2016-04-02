package se.kth.csc.iprog.dinnerplanner.model;

public class ChangeMessage
{
	public final static int GuestNumChanged=0;
	public final static int MenuChanged=1;
	public final static int ToatalMenuCostCahnged=2;
	public final static int MenuCahngedForPreparation=3;
	public final static int ingredientsCahnged=4;
	public final static int listLoaded=5;
	public final static int dishLoaded=6;
	public final static int imageLoaded=7;
	public final static int currentLoadedNumChanged=8;
	public final static int loadingStateChanged=9;
	public final static int internetConnectionFailure=10;
	
	int type;
	Object value;
	
	public ChangeMessage (int t,Object v) throws Exception
	{
		if(t!=GuestNumChanged&&t!=MenuChanged&&t!=ToatalMenuCostCahnged&&t!=MenuCahngedForPreparation
				&&t!=ingredientsCahnged&&t!=listLoaded&&t!=dishLoaded&&t!=imageLoaded
				&&t!=currentLoadedNumChanged&&t!=loadingStateChanged&&t!=internetConnectionFailure)
			throw new Exception();
		this.type=t;
		this.value=v;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public Object getData()
	{
		return value;
	}
}
