package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;

import javax.imageio.ImageIO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.kth.csc.iprog.dinnerplanner.model.ChangeMessage;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import se.kth.csc.iprog.dinnerplanner.swing.view.Constants;

public class GetRemoteData extends Observable{

	public final static String END_POINT="http://api.bigoven.com";
	public final static String KEY="1hg3g4Dkwr6pSt22n00EfS01rz568IR6";
	public final static String[] DISH_TYPE={"null type","Appetizer","Main Dish","Dessert"};
	public final static int RPP=9;
	
	public GetRemoteData()
	{
		
	}
	
	public ApiService establishConnection()
	{
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(END_POINT)
				.build();
		ApiService api=retrofit.create(ApiService.class);
		return api;
	}
	
	public void getImage(int rid,String name,int type,String url)
	{
		if (url==null)
		{
			GetRemoteData.this.setChanged();
			try {
				GetRemoteData.this.notifyObservers(new ChangeMessage(ChangeMessage.imageLoaded,
						new DishImage(name,Constants.noImageId+".jpg",type)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		File f=new File(Constants.homeDir+Constants.pictureDir+rid+".jpg");
		if(f.exists())
		{
			GetRemoteData.this.setChanged();
			try {
				GetRemoteData.this.notifyObservers(new ChangeMessage(ChangeMessage.imageLoaded,
						new DishImage(name,rid+".jpg",type)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		else
		{
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		ApiService api=this.establishConnection();
		Call <ResponseBody> call=api.getImageByURL(url);
		call.enqueue(new Callback<ResponseBody>(){
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable thr) {
				thr.printStackTrace();	
				GetRemoteData.this.setChanged();
				try {
					GetRemoteData.this.notifyObservers(new ChangeMessage(
							ChangeMessage.internetConnectionFailure,true));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				if(response.isSuccess())
				{
					InputStream is = response.body().byteStream();
					try 
					{
						Image img=ImageIO.read(is);
						int width=img.getWidth(null);
						int height=img.getHeight(null);
						BufferedImage bi=new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
						Graphics g = bi.getGraphics(); 
						g.drawImage(img, 0, 0, null);
						ImageIO.write(bi,"jpg",f);
					} catch (Exception e) {
						e.printStackTrace();
					}
					GetRemoteData.this.setChanged();
					try {
						GetRemoteData.this.notifyObservers(new ChangeMessage(ChangeMessage.imageLoaded,
								new DishImage(name,rid+".jpg",type)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else
				{
					System.err.println(" download image failed !! ");
					GetRemoteData.this.setChanged();
					try {
						GetRemoteData.this.notifyObservers(new ChangeMessage(ChangeMessage.imageLoaded,
								new DishImage(name,rid+".jpg",type)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			});
	}
	
	public void getDishListByType(int pg,int rpp,int type)
	{	
		ApiService api=establishConnection();
		Call<ResultInformation> call=api.getDishListByType(KEY,DISH_TYPE[type],pg,rpp);		
		call.enqueue(new Callback<ResultInformation>() {
			@Override
			public void onResponse(Call<ResultInformation> call, Response<ResultInformation> res) {
				ResultInformation ei=res.body();
				System.out.println(ei.toString());
				ArrayList<Integer> list=new ArrayList<Integer>();
				list.add(type);
				for(RecipeInformation ri:ei.Results)
				{
					list.add(ri.RecipeID);
				}
				GetRemoteData.this.setChanged();
				try {
					GetRemoteData.this.notifyObservers(new ChangeMessage(ChangeMessage.listLoaded,list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Call<ResultInformation> call, Throwable thr) {
				System.err.println(" Fail!!! ");
				thr.printStackTrace();
				GetRemoteData.this.setChanged();
				try {
					GetRemoteData.this.notifyObservers(new ChangeMessage(
							ChangeMessage.internetConnectionFailure,true));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getDishListByTiltleKeyWord(int pg,int rpp,int type,String keyWord)
	{	
		ApiService api=establishConnection();
		System.out.println(" ***** in data requesting "+type);
		Call<ResultInformation> call=api.getDishListByTitleKeyWord(KEY, keyWord,pg,rpp);	
		call.enqueue(new Callback<ResultInformation>() {
			@Override
			public void onResponse(Call<ResultInformation> call, Response<ResultInformation> res) {
				ResultInformation ei=res.body();
				System.out.println(ei.toString());
				ArrayList<Integer> list=new ArrayList<Integer>();
				list.add(new Integer(type));
				for(RecipeInformation ri:ei.Results)
				{
					list.add(ri.RecipeID);
				}
				GetRemoteData.this.setChanged();
				try {
					GetRemoteData.this.notifyObservers(new ChangeMessage(ChangeMessage.listLoaded,list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Call<ResultInformation> call, Throwable thr) {
				System.err.println(" Fail!!! ");
				thr.printStackTrace();
				GetRemoteData.this.setChanged();
				try {
					GetRemoteData.this.notifyObservers(new ChangeMessage(
							ChangeMessage.internetConnectionFailure,true));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getDish(int id,int type)
	{
		ApiService api=establishConnection();
		Call<RecipeInformation> call=api.getDishInformationById(id,KEY);		
		call.enqueue(new Callback<RecipeInformation>() {
			@Override
			public void onResponse(Call<RecipeInformation> call, Response<RecipeInformation> res) {
				RecipeInformation ri=res.body();
				//System.out.println(" ***** in data requesting "+type);
				String picName=ri.ImageURL;
				Dish newDish=new Dish(ri.Title,type,picName,ri.Description,id);
				if(ri.Ingredients!=null)
				{
					for(IngredientInformation ii:ri.Ingredients)
					{
						Ingredient ing=new Ingredient(ii.Name,ii.Quantity,ii.Unit,ii.Quantity);
						newDish.addIngredient(ing);
					}
					GetRemoteData.this.setChanged();
					try {
						GetRemoteData.this.notifyObservers(new ChangeMessage(ChangeMessage.dishLoaded,newDish));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			@Override
			public void onFailure(Call<RecipeInformation> call, Throwable thr) {
				System.err.println(" Fail!!! ");
				thr.printStackTrace();
				GetRemoteData.this.setChanged();
				try {
					GetRemoteData.this.notifyObservers(new ChangeMessage(
							ChangeMessage.internetConnectionFailure,true));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		//GetRemoteData grd=new GetRemoteData();
		//grd.getImage(123,"123",Dish.MAIN, "http://redirect.bigoven.com/pics/chex-party-mix-8.jpg");
	}
}
