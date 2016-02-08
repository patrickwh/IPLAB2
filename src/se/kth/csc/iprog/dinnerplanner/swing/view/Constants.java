package se.kth.csc.iprog.dinnerplanner.swing.view;

public class Constants {
	
	public static int interDishDisplayMargin=10;
	public static int widthDf=1600;
	public static int heightDf=900;
	public static int width=900;
	public static int height=600;
	public static int dividerLocation=(width/3)*2;
	public static int tabWidth=dividerLocation;
	public static int searchFieldHeight=50;
	public static int searchButtonWidth=100;
	public static int searchTextWidth=tabWidth-searchButtonWidth;
	public static int scrollHeight=height-searchFieldHeight;
	public static int dishNumInARow=3;
	public static int dishDisplayWidth=(tabWidth-interDishDisplayMargin*(dishNumInARow+1))
			/(dishNumInARow);
	public static int dishNameDisplayLabelHeight=30;
	public static int dishDisplayHeight=dishDisplayWidth+dishNameDisplayLabelHeight;
	public static String homeDir="./";
	public static String pictureDir="images/";
	public static String pictureSuffix=".jpg";
	public static int verticalScrollbarUnit=20;
	public static int informationPanelWidth=width-dividerLocation;
	public static int guestNumLabelHeight=50;
	public static int guestNumLabelWidth=150;
	public static int borderMargin=10;
	public static int guestNumSpinnerWidth=informationPanelWidth-guestNumLabelWidth-2*borderMargin;
	public static int guestNumSpinnerHeight=30;
	public static int informationPanelHeight=guestNumLabelHeight*2+2*borderMargin;
	
}
