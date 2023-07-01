package sg.edu.np.mad.practical6dbhandler;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {
    String BASE_URL = "https://npfirebasemad-262a1-default-rtdb.asia-southeast1.firebasedatabase.app/";
    @POST("users/Jack.json")
    Call<JsonObject> getWeatherForecast();
}
