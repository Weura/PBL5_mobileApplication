package com.example.checkmate.data.api;
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
    // Logowanie
    @POST("/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

}
