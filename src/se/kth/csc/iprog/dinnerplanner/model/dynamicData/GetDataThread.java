package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class GetDataThread extends Thread{

	DinnerModel model;
	int pageNumOrId;
	int type;
	int operation;
	String name;
	String url;
	
	public final static int GET_LIST=1;
	public final static int GET_DISH=2;
	public final static int GET_IMAGE=3;
	public final static int GET_SEARCH_LIST=4;
	
	public GetDataThread(DinnerModel model,int pgOrId,int tp,int opt)
	{
		this.model=model;
		this.type=tp;
		this.operation=opt;
		this.pageNumOrId=pgOrId;
	}
	
	public GetDataThread(DinnerModel model,int id,String name,int type,String picUrl,int opt)
	{
		this.model=model;
		this.name=name;
		this.url=picUrl;
		this.pageNumOrId=id;
		this.type=type;
		this.operation=GET_IMAGE;
	}
	
	public GetDataThread(DinnerModel model,String kw,int pg,int type,int opt)
	{
		this.pageNumOrId=pg;
		this.model=model;
		this.name=kw;
		this.operation=opt;
		this.type=type;
	}
	@Override
	public void run() {

		GetRemoteData grd=new GetRemoteData();
		grd.addObserver(this.model);
		if(this.operation==GET_LIST) grd.getDishListByType(pageNumOrId, GetRemoteData.RPP, type);
		else if(operation==GET_DISH) grd.getDish(pageNumOrId,type);
		else if(operation==GET_IMAGE) grd.getImage(pageNumOrId, name, type, url);
		else if(operation==GET_SEARCH_LIST) grd.getDishListByTiltleKeyWord(pageNumOrId, 
				GetRemoteData.RPP, type, name);
		
	} 	
}
