package se.kth.csc.iprog.dinnerplanner.model;

public class ChangeMessage
{
	public static int GuestNumChanged=0;
	public static int MenuChanged=1;
	int type;
	Object value;
	public ChangeMessage (int t,Object v) throws Exception
	{
		if(t!=GuestNumChanged&&t!=MenuChanged) throw new Exception();
		this.type=t;
		this.value=v;
	}
}
