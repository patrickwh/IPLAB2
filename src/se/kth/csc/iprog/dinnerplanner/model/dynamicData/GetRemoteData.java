package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRemoteData {

	public final String endPoint="http://api.bigoven.com";
	
	public void getData()
	{
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(endPoint)
				.build();
		ApiService api=retrofit.create(ApiService.class);
		Call<ErrorInformation>  call=api.getDishInformation();
		call.enqueue(new Callback<ErrorInformation>() {
			@Override
			public void onResponse(Call<ErrorInformation> call, Response<ErrorInformation> res) {
				ErrorInformation ei=res.body();
				System.out.println(ei.toString());
			}
			@Override
			public void onFailure(Call<ErrorInformation> call, Throwable thr) {
				System.err.println(" Fail!!! ");
				thr.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		GetRemoteData grd=new GetRemoteData();
		grd.getData();
	}
}
