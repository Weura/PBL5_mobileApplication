package com.example.checkmate.data.api;
import com.example.checkmate.data.api.modelApi.Currents;
import com.example.checkmate.data.api.modelApi.Temperature;
import com.example.checkmate.data.api.modelApi.UserDevice;
import com.example.checkmate.data.api.modelApi.Voltage;
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
    Call<UserDevice> getUserDevice(@Path("user_id") int userId);

// -----------DEVICE-INFO----------------
    // get temperature of specific device
    @GET("/devices/<int:device_id>/temperature")
    Call<List<Temperature>> getDeviceTemperature(@Path("device_id") int device_id);

    // get voltage of specific device
    @GET("/devices/<int:device_id>/voltage")
    Call<List<Voltage>> getDeviceVoltage(@Path("device_id") int device_id);

    // get currents of specific device
    @GET("/devices/<int:device_id>/currents")
    Call<List<Currents>> getDeviceCurrents(@Path("device_id") int device_id);

//    currently not in database
//    /devices/<int:device_id>/humidity
}
