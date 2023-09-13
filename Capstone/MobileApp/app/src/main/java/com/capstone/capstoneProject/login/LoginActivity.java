package com.capstone.capstoneProject.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.dashboard.DashboardActivity;
import com.capstone.capstoneProject.login.api.LoginApi;
import com.capstone.capstoneProject.register.RegisterActivity;
import com.capstone.capstoneProject.register.api.RegisterApi;
import com.capstone.capstoneProject.register.model.LoginRequest;
import com.capstone.capstoneProject.register.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Context context;
    EditText usernameEt, passwordEt;
    Button loginBt, createBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        toolbar.setTitle(R.string.inventory_tracker);
        setSupportActionBar(toolbar);

        context = this;
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        loginBt = findViewById(R.id.login_bt);
        createBt = findViewById(R.id.signup_bt);

        fetchRoles();

        createBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkNulls()){
                    callLogin(usernameEt.getText().toString(),passwordEt.getText().toString());
                }
            }
        });
    }

    public void fetchRoles(){
        ProjectConstants.rolesList.clear();
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){
            e.printStackTrace();
        }
        RegisterApi registerApi = RetrofitBuilder.createPreLoginRetrofit().create(RegisterApi.class);
        Call<List<String>> call = registerApi.getUserRoles();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response != null && response.body().size()>0){
                    ProjectConstants.rolesList.addAll(response.body());
                    System.out.println("Roles list -> "+ProjectConstants.rolesList);
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void callLogin(String username, String password){
        //Todo: call login API
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){
            e.printStackTrace();
        }
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        LoginApi api = RetrofitBuilder.createPreLoginRetrofit().create(LoginApi.class);
        Call<LoginResponse> call = api.loginApi(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response.body() != null && response.body()!=null){
                    RetrofitBuilder.setToken(response.body().getToken());
                    Intent intent = new Intent(context, DashboardActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }else {
                    Toast.makeText(context, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public Boolean checkNulls(){

        if(usernameEt.getText() == null || usernameEt.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, getString(R.string.please_enter_username), Toast.LENGTH_SHORT).show();
            return false;
        }

        if(passwordEt.getText() == null || passwordEt.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}