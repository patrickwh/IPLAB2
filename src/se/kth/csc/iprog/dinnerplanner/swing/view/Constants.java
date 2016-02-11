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
	public static String dataDir="./initData/data.txt";
	public static int verticalScrollbarUnit=20;
	public static int informationPanelWidth=width-dividerLocation;
	public static int guestNumLabelHeight=35;
	public static int guestNumLabelWidth=150;
	public static int costLabelHeight=35;
	public static int borderMargin=10;
	public static int guestNumSpinnerWidth=informationPanelWidth-guestNumLabelWidth-2*borderMargin;
	public static int guestNumSpinnerHeight=30;
	public static int informationPanelHeight=guestNumLabelHeight+costLabelHeight+2*borderMargin;
	public static int dinnerMenuHeight=70;
	public static int preparationButtonHeight=40;
	public static int dinnerMenuPanelHaight=height-preparationButtonHeight-
			informationPanelHeight-2*borderMargin;
	public static int preparationButtonWidth=(informationPanelWidth-6*borderMargin)/2;
	public static int menuEntryWidth=informationPanelWidth-borderMargin*5-5;
	public static int menuEntryHeight=50;
	public static int menuListHeight=dinnerMenuPanelHaight-dinnerMenuHeight-70;
	public static int menuEntryPicWidth=menuEntryHeight;
	public static int menuEntryLabelHeight=menuEntryHeight/2;
	public static int menuEntryLabelWidth=menuEntryWidth-2*menuEntryPicWidth;
	public static int largeBorderMargin=20;
	public static int menuEntryRealHeight=menuEntryHeight+5;
	public static int dishNameDisplayWindowWidth=600;
	public static int dishNameDisplayWindowHeight=600;
	public static int dishNameBorder=20;
	public static int dishNameInformationPanelHeight=dishNameDisplayWindowHeight/3;
	public static int dishNameImageWidth=dishNameInformationPanelHeight-2*dishNameBorder;
	public static int dishNameNamePanelWidth=dishNameDisplayWindowWidth-dishNameInformationPanelHeight;
	public static int dishNameNameHeight=dishNameInformationPanelHeight/2;
	public static int dishNameSplitHeight=dishNameDisplayWindowHeight-dishNameInformationPanelHeight;
	public static int dishNameDividerLocation=dishNameDisplayWindowWidth/2;
	public static int dishNameDescriptionWidth=dishNameDisplayWindowWidth/2;
	public static int dishNameNamePanelBorderWidth=20;
	public static int dishNameTableColumnWidth1=(dishNameDescriptionWidth/7)*3;
	public static int dishNameTableColumnWidth2=(dishNameDescriptionWidth/7)*2;
	public static int dishNameTableRowHeight=40;
}
