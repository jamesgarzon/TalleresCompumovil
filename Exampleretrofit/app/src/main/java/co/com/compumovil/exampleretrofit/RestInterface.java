package co.com.compumovil.exampleretrofit;

import co.com.compumovil.exampleretrofit.POJO.Model;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by james.garzon on 28/03/17.
 */

public interface RestInterface {

    @GET("/weather")
    void getWheatherReport(@Query("q") String place, @Query("appid") String appId, Callback<Model> cb);

}
