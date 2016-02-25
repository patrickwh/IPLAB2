package se.kth.csc.iprog.dinnerplanner.model.dynamicData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
	@Headers("Accept:application/json")
	@GET("/recipes?api_key=18f3cT02U9f6yRl3OKDpP8NA537kxYKu")
	Call<ErrorInformation> getDishInformation();
}
