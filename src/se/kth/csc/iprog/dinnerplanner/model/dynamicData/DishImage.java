package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

public class DishImage {
	
	String name;
	String picName;
	int type;
	
	public DishImage(String n,String pic,int t)
	{
		this.name=n;
		this.picName=pic;
		this.type=t;
	}

	public int getType()
	{
		return type;
	}
	public String getName()
	{
		return name;
	}
	public String getPicName()
	{
		return picName;
	}
}
