package com.example.manvish.retrofitexample;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by manvish on 1/17/18.
 */

public interface ApiInterface {

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/retrofit/register.php")     // API's endpoints
    public void registration(@Field("name") String name,
                             @Field("email") String email,
                             @Field("password") String password,
                             @Field("logintype") String logintype,
                             Callback<SignUpResponse> callback);

    // API's endpoints
    @GET("/retrofit/getuser.php")
    public void getUsersList(
            Callback<List<UserListResponse>> callback);

// UserListResponse is POJO class to get the data from API,
// In above method we use List<UserListResponse> because the data in our API is
// starting from JSONArray and callback is used to get the response from api
// and it will set it in our POJO class
}
