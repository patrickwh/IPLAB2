package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

public class ErrorInformation {
	public int StatusCode;
	public String Message;
	public String Data;
	
	public ErrorInformation(int sc,String mes,String data)
	{
		this.StatusCode=sc;
		this.Message=mes;
		this.Data=data;
	}
	@Override
	public String toString()
	{
		String str="";
		str="[StatusCode: "+this.StatusCode
				+"\nMessage: "+this.Message
				+"\nData: "+this.Data+"]";
		return str;
	}
}
