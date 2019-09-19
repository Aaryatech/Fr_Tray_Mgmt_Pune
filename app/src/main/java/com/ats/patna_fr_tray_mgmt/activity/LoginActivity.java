package com.ats.patna_fr_tray_mgmt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.bean.Info;
import com.ats.patna_fr_tray_mgmt.bean.LoginData;
import com.ats.patna_fr_tray_mgmt.common.CommonDialog;
import com.ats.patna_fr_tray_mgmt.constants.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edUsername, edPassword;
    private TextView tvForgotPassword;
    private LinearLayout llLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.edLogin_Username);
        edPassword = findViewById(R.id.edLogin_Password);
        tvForgotPassword = findViewById(R.id.tvLogin_ForgotPass);
        llLogin = findViewById(R.id.llLogin);
        llLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.llLogin) {

            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();
            if (username.isEmpty()) {
                edUsername.setError("Required");
                edUsername.requestFocus();
            } else if (password.isEmpty()) {
                edPassword.setError("Required");
                edPassword.requestFocus();
            } else {
                doLogin(username, password);
            }
        }
    }


    public void doLogin(String frCode, String pass) {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(LoginActivity.this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<LoginData> loginDataCall = Constants.myInterface.getLogin(frCode, pass);
            loginDataCall.enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                    try {
                        if (response.body() != null) {
                            LoginData data = response.body();
                            if (data.getLoginInfo().getError()) {
                                commonDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "" + data.getLoginInfo().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("Login : ", " onError : " + data.getLoginInfo().getMessage());
                            } else {
                                commonDialog.dismiss();

                                Log.e("Login : ", " DATA : " + data);

                                SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.MY_PREF, MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(data.getFranchisee());
                                editor.putString("franchise", json);
                                editor.putInt("accessRight", data.getLoginInfo().getAccessRight());
                                editor.apply();
                                editor.apply();

                                Intent intent = new Intent(LoginActivity.this, FrRouteActivity.class);
                                startActivity(intent);
                                finish();

                              /*  String token = SharedPrefManager.getmInstance(LoginActivity.this).getDeviceToken();
                                Log.e("Token : ", "---------" + token);
                                if (data.getLoginInfo().getAccessRight() == 1) {
                                    updateUserToken(data.getFranchisee().getFrId(), token);
                                }*/

                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Unable To Login", Toast.LENGTH_SHORT).show();
                            Log.e("Login : ", " NULL");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Unable To Login", Toast.LENGTH_SHORT).show();
                        Log.e("Login : ", " Exception : " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginData> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Unable To Login", Toast.LENGTH_SHORT).show();
                    Log.e("Login : ", " onFailure : " + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }


 /*   public void updateUserToken(int userId, String token) {

        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(LoginActivity.this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<Info> infoCall = Constants.myInterface.updateFCMToken(0, userId, token);
            infoCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    Log.e("Response : ", "--------------------" + response.body());
                    commonDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {
                    Log.e("Failure : ", "---------------------" + t.getMessage());
                    t.printStackTrace();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
            });

        } else {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }*/

}
