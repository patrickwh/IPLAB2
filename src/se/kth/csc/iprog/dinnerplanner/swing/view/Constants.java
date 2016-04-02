package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Toolkit;

public class Constants {
	
	public final static String homeDir="./";
	public final static String pictureDir="images/";
	public final static String pictureSuffix=".jpg";
	public final static String dataDir="./initData/data.txt";
	public final static String notSelctName="NOT select";
	public final static String notSelctDescription="You have NOT selet a dish in specified type";
	public final static String notSelctPic="noResult.jpg";
	public final static String windowIconDir="./images/icon.png";
	public final static String nullImageName="icon.png";
	public final static String addMoreImageName="add.png";
	public final static String addMoreName="Load More";
	
	public final static int noImageId=-1;
	public final static int borderMargin=10;
	public final static int interDishDisplayMargin=10;
	public final static int widthDf=Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int heightDf=Toolkit.getDefaultToolkit().getScreenSize().height;
	public final static int width=900;
	public final static int height=700;	
	public final static int dividerLocation=(width/3)*2;
	public final static int tabWidth=dividerLocation;
	public final static int progressBarPanelHeight=90;
	public final static int tabHeight=height-progressBarPanelHeight;
	public final static int progressBarPanelWidth=tabWidth;
	public final static int progressBarWidth=progressBarPanelWidth;
	public final static int progressBarHeight=(progressBarPanelHeight-2*borderMargin)/2;
	public final static int progressIndicatingTextWidth=progressBarWidth;
	public final static int progressIndicatingTextHeight=progressBarHeight;
	public final static int progressBarMin=0;
	public final static int progressBarMax=100;
	public final static int searchFieldHeight=50;
	public final static int searchButtonWidth=100;
	public final static int searchTextWidth=tabWidth-searchButtonWidth;
	public final static int scrollHeight=height-searchFieldHeight;
	public final static int dishNumInARow=3;
	public final static int dishDisplayWidth=(tabWidth-interDishDisplayMargin*(dishNumInARow+1))
			/(dishNumInARow);
	public final static int dishNameDisplayLabelHeight=50;
	public final static int dishDisplayHeight=dishDisplayWidth+dishNameDisplayLabelHeight;	
	public final static int dishTypeNum=3;
	public final static int verticalScrollbarUnit=20;
	public final static int informationPanelWidth=width-dividerLocation;
	public final static int guestNumLabelHeight=35;
	public final static int guestNumLabelWidth=150;
	public final static int costLabelHeight=35;
	public final static int guestNumSpinnerWidth=informationPanelWidth-guestNumLabelWidth-2*borderMargin;
	public final static int guestNumSpinnerHeight=30;
	public final static int informationPanelHeight=guestNumLabelHeight+costLabelHeight+2*borderMargin;
	public final static int dinnerMenuHeight=70;
	public final static int preparationButtonHeight=40;
	public final static int dinnerMenuPanelHaight=height-preparationButtonHeight-
			informationPanelHeight-2*borderMargin;
	public final static int preparationButtonWidth=(informationPanelWidth-6*borderMargin)/2;
	public final static int menuEntryWidth=informationPanelWidth-borderMargin*5-5;
	public final static int menuEntryHeight=50;
	public final static int menuListHeight=dinnerMenuPanelHaight-dinnerMenuHeight-70;
	public final static int menuEntryPicWidth=menuEntryHeight;
	public final static int menuEntryLabelHeight=menuEntryHeight/2;
	public final static int menuEntryLabelWidth=menuEntryWidth-2*menuEntryPicWidth;
	public final static int largeBorderMargin=20;
	public final static int menuEntryRealHeight=menuEntryHeight+5;
	public final static int dishNameDisplayWindowWidth=750;
	public final static int dishNameDisplayWindowHeight=600;
	public final static int dishNameBorder=20;
	public final static int dishNameInformationPanelHeight=dishNameDisplayWindowHeight/3;
	public final static int dishNameImageWidth=dishNameInformationPanelHeight-2*dishNameBorder;
	public final static int dishNameNamePanelWidth=dishNameDisplayWindowWidth-dishNameInformationPanelHeight;
	public final static int dishNameNameHeight=dishNameInformationPanelHeight/2;
	public final static int dishNameSplitHeight=dishNameDisplayWindowHeight-dishNameInformationPanelHeight;
	public final static int dishNameDividerLocation=300;
	public final static int dishNameDescriptionWidth=dishNameDividerLocation;
	public final static int dishNameTableWidth=dishNameDisplayWindowWidth-dishNameDescriptionWidth;
	public final static int dishNameNamePanelBorderWidth=20;
	public final static int dishNameTableColumnWidth2=(dishNameDividerLocation/8)*3;
	public final static int dishNameTableColumnWidth1=dishNameTableWidth-2*dishNameTableColumnWidth2;
	public final static int dishNameTableRowHeight=40;
	public final static int preparationPanelWidth=700;
	public final static int preparationPanelHeight=600;
	public final static int preparationPanelTitleHeight=preparationPanelHeight/10;
	public final static int preparationPanelNameHeight=(preparationPanelTitleHeight*2)/3;
	public final static int preparationPanelDescriptionHeight=(preparationPanelHeight-preparationPanelTitleHeight-
			3*preparationPanelNameHeight)/3;
	public final static int preparationPanelTopHeight=preparationPanelTitleHeight+preparationPanelNameHeight+
			preparationPanelDescriptionHeight;
	public final static int preparationPanelCenterHeight=preparationPanelNameHeight+preparationPanelDescriptionHeight;
	public final static int preparetionPanelBottomHeight=preparationPanelCenterHeight;
	public final static int ingredientPanelWidth=600;
	public final static int ingredientPanelHeight=600;
	public final static int ingredientPanelTableWidth1=(ingredientPanelWidth*3)/6;
	public final static int ingredientPanelTableWidth2=(ingredientPanelWidth-ingredientPanelTableWidth1)/2;
}
