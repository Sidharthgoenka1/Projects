package com.capstone.capstoneProject.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.register.api.RegisterApi;
import com.capstone.capstoneProject.register.model.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Context context;
    Button validateBt, continueBt;
    EditText usernameEt, passwordEt, nameEt, addressLine1Et, addressLine2Et, emailEt, contactEt;
    LinearLayout detailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.create_account);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        validateBt = findViewById(R.id.validate_bt);
        continueBt = findViewById(R.id.continue_bt);
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        nameEt = findViewById(R.id.name_et);
        addressLine1Et = findViewById(R.id.address1_et);
        addressLine2Et = findViewById(R.id.address2_et);
        emailEt = findViewById(R.id.email_et);
        contactEt = findViewById(R.id.contact_et);
        detailsLayout = findViewById(R.id.details_layout);

        detailsLayout.setVisibility(View.INVISIBLE);

        if(ProjectConstants.rolesList == null || ProjectConstants.rolesList.size() == 0){
            fetchRoles();
        }

        validateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkNulls()){
                    //Todo:ask user and call validate API
                    LoginRequest request = new LoginRequest();
                    request.setUsername(usernameEt.getText().toString());
                    request.setPassword(passwordEt.getText().toString());

                    showAlertDialogBox(request);
                }
            }
        });

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkNulls()){
                    //Todo: proceed to workplace details
                    Intent intent = new Intent(context, WorkplaceDetailsActivity.class);
                    intent.putExtra("usernameVal", usernameEt.getText().toString());
                    intent.putExtra("passwordVal", passwordEt.getText().toString());
                    intent.putExtra("nameVal", nameEt.getText().toString());
                    intent.putExtra("contactVal", contactEt.getText().toString());
                    intent.putExtra("emailVal", emailEt.getText().toString());
                    intent.putExtra("addressVal", addressLine1Et.getText().toString()+" "+addressLine2Et.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }

    public void showAlertDialogBox(LoginRequest request){
        Dialog alertDialog = new Dialog(context);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setContentView(R.layout.credential_confirmation_dialog_layout);


        TextView alertMessageTv = alertDialog.findViewById(R.id.alert_tv);
        TextView headingTv = alertDialog.findViewById(R.id.heading_tv);
        Button cancelBt = alertDialog.findViewById(R.id.cancel_bt);
        Button confirmBt = alertDialog.findViewById(R.id.confirm_bt);

        headingTv.setText(getString(R.string.alert));
        alertMessageTv.setText(getString(R.string.alert_text_message));

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: call validate API and set visibility for details layout to VISIBLE and
                // disable username and password and hide button
                validateCredentials(alertDialog,request);
            }
        });
        alertDialog.show();
    }

    public void validateCredentials(Dialog alertDialog,LoginRequest request){
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){
            e.printStackTrace();
        }
        RegisterApi registerApi = RetrofitBuilder.createPreLoginRetrofit().create(RegisterApi.class);
        Call<Boolean> call = registerApi.validateCredentials(request);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response.body()) {
                    detailsLayout.setVisibility(View.VISIBLE);
                    usernameEt.setEnabled(false);
                    passwordEt.setEnabled(false);
                    validateBt.setVisibility(View.GONE);
                }else {
                    Toast.makeText(context, getString(R.string.please_choose_another_username), Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();
            }
        });
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

    public Boolean checkNulls(){

        if(usernameEt.getText() == null || usernameEt.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, getString(R.string.please_enter_username), Toast.LENGTH_SHORT).show();
            System.out.println("Here 1");
            return false;
        }

        if(passwordEt.getText() == null || passwordEt.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
            System.out.println("Here 2");
            return false;
        }

        if(detailsLayout.getVisibility() == View.VISIBLE){
            if(nameEt.getText() == null || nameEt.getText().toString().equalsIgnoreCase("")){
                Toast.makeText(context, getString(R.string.please_enter_name), Toast.LENGTH_SHORT).show();
                System.out.println("Here 3");
                return false;
            }

            if(emailEt.getText() == null || emailEt.getText().toString().equalsIgnoreCase("")){
                Toast.makeText(context, getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
                System.out.println("Here 4");
                return false;
            }

            if(contactEt.getText() == null || contactEt.getText().toString().equalsIgnoreCase("")){
                Toast.makeText(context, getString(R.string.please_enter_contact), Toast.LENGTH_SHORT).show();
                System.out.println("Here 5");
                return false;
            }

            if(contactEt.getText().toString().length() !=10){
                Toast.makeText(context, getString(R.string.please_enter_10_digit_contact), Toast.LENGTH_SHORT).show();
                System.out.println("Here 6");
                return false;
            }

            if(addressLine1Et.getText() == null || addressLine1Et.getText().toString().equalsIgnoreCase("")){
                Toast.makeText(context, getString(R.string.please_enter_address), Toast.LENGTH_SHORT).show();
                System.out.println("Here 7");
                return false;
            }

            if(addressLine2Et.getText() == null){
                addressLine2Et.setText("");
            }
        }
        return true;
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

}