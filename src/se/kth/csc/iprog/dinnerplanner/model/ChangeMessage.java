package se.kth.csc.iprog.dinnerplanner.model;

public class ChangeMessage
{
	public static int GuestNumChanged=0;
	public static int MenuChanged=1;
	public static int ToatalMenuCostCahnged=2;
	int type;
	Object value;
	public ChangeMessage (int t,Object v) throws Exception
	{
		if(t!=GuestNumChanged&&t!=MenuChanged&&t!=ToatalMenuCostCahnged)
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