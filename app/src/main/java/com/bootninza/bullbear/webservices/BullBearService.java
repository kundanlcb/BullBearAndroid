package com.bootninza.bullbear.webservices;

import com.bootninza.bullbear.webservices.models.Equity;
import com.bootninza.bullbear.webservices.models.Feed;
import com.bootninza.bullbear.webservices.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BullBearService {

    @GET("/feed")
    Call<List<Feed>> getFeeds(@Header("Authorization") String token);

    @GET("/equity")
    Call<List<Equity>> getEquities(@Header("Authorization") String token);

    @POST("/feed")
    Call<Feed> addFeed(@Body Feed feed,@Header("Authorization") String token);

    @POST("/login")
    Call<User> login(@Body User user);

}
