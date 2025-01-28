package com.example.checkmate.data;

import com.example.checkmate.data.api.ApiClient;
import com.example.checkmate.data.api.ApiService;
import com.example.checkmate.data.model.LoggedInUser;
import com.example.checkmate.data.model.LoginRequest;
import com.example.checkmate.data.model.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

public class LoginDataSource {

    private ApiService apiService;

    public Result<LoggedInUser> login(String username, String password) {
        try {
            // Przygotowanie danych do wysłania w body requestu
            LoginRequest loginRequest = new LoginRequest(username, password);

            // Wykonanie żądania do backendu
            Call<LoginResponse> call = apiService.loginUser(loginRequest);
            Response<LoginResponse> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                // Zwrócenie poprawnie zalogowanego użytkownika
                LoginResponse loginResponse = response.body();

                // Jeśli userName jest null lub pusty, nie przekazujemy go w obiekcie LoggedInUser
                String userName = loginRequest.getUsername();
                if (userName == null || userName.isEmpty()) {
                    userName = "Guest";
                }

                LoggedInUser user = new LoggedInUser(
                        loginResponse.getUserId(),
                        userName // Przekazujemy userName tylko jeśli jest dostępne
                );

                return new Result.Success<>(user);
            } else {
                // Obsługa błędnej odpowiedzi z serwera
                return new Result.Error(new IOException("Error logging in: " + response.message()));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}

