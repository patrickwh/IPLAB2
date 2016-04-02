package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

import java.util.List;

public class ResultInformation {
	public int ResultCount;
	List<RecipeInformation> Results;
	
	@Override
	public String toString()
	{
		String str;
		str="[ "+this.ResultCount+" Results: \n";
		for(RecipeInformation ri:Results)
		{
			str+=ri.Title+"\n";
		}
		str+="]\n";
		return str;
	}
}
