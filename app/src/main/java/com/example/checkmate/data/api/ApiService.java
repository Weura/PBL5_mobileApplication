package com.example.checkmate.data.api;
import com.example.checkmate.data.api.modelApi.Device;
import com.example.checkmate.data.api.modelApi.UserDevice;
import com.google.gson.JsonObject;
import com.example.checkmate.data.model.LoginRequest;
import com.example.checkmate.data.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

// -------------LOGIN------------------
    @POST("/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

// -------------DEVICE------------------
    // get specific user devices
    @GET("/users/{user_id}/devices")
    Call<UserDevice> getDeviceInfo(@Path("user_id") int userId);

}
