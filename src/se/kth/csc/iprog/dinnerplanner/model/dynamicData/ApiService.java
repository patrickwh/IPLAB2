package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
	@Headers("Accept:application/json")
	@GET("/recipes?")
	Call<ResultInformation> getDishListByPaging(
			@Query("api_Key") String apiKey,
			@Query("pg") int pgValue,
			@Query("rpp") int rppValue);
	
	@Headers("Accept:application/json")
	@GET("/recipe/{rid}")
	Call<RecipeInformation> getDishInformationById(
			@Path("rid") int rid,
			@Query("api_Key") String apiKey);
	
	@Headers("Accept:application/json")
	@GET("/recipes?")
	Call<ResultInformation> getDishListByType(
			@Query("api_Key") String apiKey,
			@Query("any_kw") String keyWord,
			@Query("pg") int pgValue,
			@Query("rpp") int rppValue);
	
	@Headers("Accept:application/json")
	@GET("/recipes?")
	Call<ResultInformation> getDishListByTitleKeyWord(
			@Query("api_Key") String apiKey,
			@Query("title_kw") String keyWord,
			@Query("pg") int pgValue,
			@Query("rpp") int rppValue);
	
	@GET
	Call<ResponseBody> getImageByURL(
			@Url String url);
}
