package com.example.restapilogin;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restapilogin.remote.LoginResponse;
import com.example.restapilogin.remote.RetrofitClient;
import com.example.restapilogin.remote.UserService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UserService apiInterface;

    @BindView(R.id.edtEmail) EditText et_Email;
    @BindView(R.id.edtPass) EditText et_Pass;

    String userEmail;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        apiInterface = RetrofitClient.getClient().create(UserService.class);
    }

    @OnClick(R.id.loginSub)
    public void submit(){
        if (checkValidation()) {
            if (CommonMethod.isNetworkAvailable(MainActivity.this))
                loginRetrofit2Api(userEmail, password);
            else
                CommonMethod.showAlert("Internet Connectivity Failure", MainActivity.this);
        }
    }

    private void loginRetrofit2Api(String userEmail, String password) {
        LoginResponse login = new LoginResponse(userEmail, password);
        Call<LoginResponse> call1 = apiInterface.createUser(login);
        call1.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                Log.e("m", "loginResponse 1 :" + loginResponse);
                if (loginResponse != null) {
                    Log.e("m", "getEmail :" + loginResponse.getEmail());
                    Log.e("m", "getFirstName :" + loginResponse.getFirstName());
                    Log.e("m", "getLastName :" + loginResponse.getLastName());
                    Log.e("m", "getMobile :" + loginResponse.getMobile());
                    Log.e("m", "getPassword :" + loginResponse.getPassword());

                    String responseCode = loginResponse.getResponseCode();
                    Log.e("m", "getResponseCode :" + loginResponse.getResponseCode());
                    Log.e("m", "getResponseMessage :" + loginResponse.getResponseMessage());
                    if (responseCode != null && responseCode.equals("404")) {
                        Toast.makeText(MainActivity.this, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Welcome " + loginResponse.getFirstName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure called ", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public boolean checkValidation() {
        userEmail = et_Email.getText().toString();
        password = et_Pass.getText().toString();

        if (et_Email.getText().toString().trim().equals("")) {
            CommonMethod.showAlert("E-Mail Cannot be left blank", MainActivity.this);
            return false;
        } else if (et_Pass.getText().toString().trim().equals("")) {
            CommonMethod.showAlert("password Cannot be left blank", MainActivity.this);
            return false;
        }
        return true;
    }
}