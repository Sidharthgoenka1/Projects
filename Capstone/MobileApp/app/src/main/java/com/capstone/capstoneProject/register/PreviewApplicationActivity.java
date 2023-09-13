package com.capstone.capstoneProject.register;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.login.LoginActivity;
import com.capstone.capstoneProject.register.api.RegisterApi;
import com.capstone.capstoneProject.register.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviewApplicationActivity extends AppCompatActivity {

    TextView usernameTv, nameTv, emailTv, contactNoTv, workplaceNameTv, workplaceContactNoTv, workplaceAddressTV, addressTv, userTypeTv;
    String usernameVal, passwordVal, nameVal, emailVal, contactVal, addressVal, workplaceNameVal, workplaceAddressVal, workplaceContactVal, userTypeVal;
    Button submitBt;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.create_account);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        usernameTv = findViewById(R.id.username_tv);
        nameTv = findViewById(R.id.name_tv);
        emailTv = findViewById(R.id.email_tv);
        contactNoTv = findViewById(R.id.contact_tv);
        addressTv = findViewById(R.id.address_tv);
        workplaceNameTv = findViewById(R.id.workplace_name_tv);
        workplaceAddressTV = findViewById(R.id.workplace_address_tv);
        workplaceContactNoTv = findViewById(R.id.workplace_contact_tv);
        userTypeTv = findViewById(R.id.usertype_tv);
        submitBt = findViewById(R.id.submit_bt);

        Bundle bundle = getIntent().getExtras();
        usernameVal = bundle.getString("usernameVal");
        nameVal = bundle.getString("nameVal");
        emailVal = bundle.getString("emailVal");
        contactVal = bundle.getString("contactVal");
        addressVal = bundle.getString("addressVal");
        workplaceAddressVal = bundle.getString("workplaceAddressVal");
        workplaceNameVal = bundle.getString("workplaceNameVal");
        userTypeVal = bundle.getString("userTypeVal");
        workplaceContactVal = bundle.getString("workplaceContactVal");
        passwordVal = bundle.getString("passwordVal");

        usernameTv.setText(usernameVal);
        nameTv.setText(nameVal);
        emailTv.setText(emailVal);
        contactNoTv.setText(contactVal);
        addressTv.setText(addressVal);
        workplaceAddressTV.setText(workplaceAddressVal);
        workplaceNameTv.setText(workplaceNameVal);
        userTypeTv.setText(userTypeVal);
        workplaceContactNoTv.setText(workplaceContactVal);

        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterRequest request = new RegisterRequest();
                request.setAddress(addressVal);
                request.setContactNo(contactVal);
                request.setEmail(emailVal);
                request.setName(nameVal);
                request.setPassword(passwordVal);
                request.setUsername(usernameVal);
                request.setWorkplaceAddress(workplaceAddressVal);
                request.setWorkplaceContactNo(workplaceContactVal);
                request.setWorkplaceName(workplaceNameVal);
                if(userTypeVal.equalsIgnoreCase("distributor")) {
                    request.setUserType("D");
                }else {
                    request.setUserType("S");
                }
                request.setRole(userTypeVal);

                submitCreateRequest(request);
            }
        });
    }

    public void submitCreateRequest(RegisterRequest request){
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){
            e.printStackTrace();
        }
        RegisterApi api = RetrofitBuilder.createPreLoginRetrofit().create(RegisterApi.class);
        Call<Boolean> call = api.registerUser(request);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response.body()){
                    showSuccessfulDialog();
                }else {
                    Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showSuccessfulDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(R.string.user_registeration_success);
        builder1.setCancelable(false);

        builder1.setPositiveButton(getString(R.string.login), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton("", null);

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}