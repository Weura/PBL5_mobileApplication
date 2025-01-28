package com.example.checkmate.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.checkmate.NetworkUtils;
import com.example.checkmate.data.LoginRepository;
import com.example.checkmate.R;

import com.example.checkmate.data.api.ApiClient;
import com.example.checkmate.data.api.ApiService;
import com.example.checkmate.data.model.LoginRequest;
import com.example.checkmate.data.model.LoginResponse;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    private ApiService apiService;

    public LoginViewModel(LoginRepository instance) {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password, Context context) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            loginResult.postValue(new LoginResult(R.string.error_no_internet));
            return;
        }

        LoginRequest loginRequest = new LoginRequest(username, password);

        apiService.loginUser(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    String userName = loginRequest.getUsername();

                    if (userName == null || userName.isEmpty()) {
                        userName = "Guest";
                    }

                    LoggedInUserView userView = new LoggedInUserView("User " + loginResponse.getUserId(), loginResponse.getUserId());
                    loginResult.postValue(new LoginResult(userView));
                } else {
                    Log.d("POLACZENIEPROBLEMS", "onResponse else");
                    loginResult.postValue(new LoginResult(R.string.login_failed));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("LOGINLogamiks", "on failure " + call + " " + t);
//                if (t instanceof java.net.UnknownHostException) {
//                    loginResult.postValue(new LoginResult(R.string.error_no_internet));
//                } else if (t instanceof SocketTimeoutException) {
//                    Log.d("LOGINLogamiks", "SocketTimeoutException: Could not connect to server.");
//                    loginResult.postValue(new LoginResult(R.string.error_connection_timeout));
//                } else {
//                    loginResult.postValue(new LoginResult(R.string.login_failed));
//                }
//            }
                loginResult.postValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    public void showTimeoutError(Context context) {
        Toast.makeText(context, "Connection timeout. Please try again later.", Toast.LENGTH_LONG).show();
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    private boolean isUserNameValid(String username) {
        return username != null && !username.trim().isEmpty();
    }

    private boolean isPasswordValid(String password) {
        return password != null && !password.trim().isEmpty();
    }
}
