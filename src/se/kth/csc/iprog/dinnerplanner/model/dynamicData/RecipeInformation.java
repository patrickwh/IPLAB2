package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

import java.util.List;

public class RecipeInformation {
	
	public int RecipeID;
	public String Title;
	public String Category;
	public String Description;
	public String ImageURL;
	public List<IngredientInformation> Ingredients;
	
	@Override
	public String toString()
	{
		String str="";
		str="[ "+this.Title+" ingerdients: \n";
		for(IngredientInformation ii:this.Ingredients)
		{
			str+=ii.Name+"\n";
		}
		str+="]\n";
		return str;
	}
//**********other properties*******************
	
//	public String Cuisine;
//	public String Category;
//	public String Subcategory;
//	public String Microcategory;
//	public double StarRating;
//	public String StarRatingIMG;
//	public String WebURL;
//	public String ImageURL;
//	public String ImageURL120;
//	public int ReviewCount;
//	public PosterInformation Poster;
	
//	public boolean IsPrivate;
//	public boolean HideFromPublicSearch;
//	public boolean IsBookmark;
//	public String BookmarkURL;
//	public int YieldNumber;
//	public double QualityScore;
//	public String CreationDate;
//	public int MaxImageSquare;
//	public int TotalTries;
//	public String HeroPhotoUrl;
}
