package com.capstone.capstoneProject.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.register.api.RegisterApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkplaceDetailsActivity extends AppCompatActivity {

    Context context;
    EditText workplaceNameEt, workplaceAddressEt1, workplaceAddressEt2, workplaceContactEt;
    Spinner userTypeSp;
    Button continueBt;
    String usernameVal, passwordVal, nameVal, emailVal, contactVal, addressVal;
    List<String> userRoles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplace_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.create_account);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        Bundle bundle = getIntent().getExtras();
        usernameVal = bundle.getString("usernameVal");
        passwordVal = bundle.getString("passwordVal");
        nameVal = bundle.getString("nameVal");
        emailVal = bundle.getString("emailVal");
        contactVal = bundle.getString("contactVal");
        addressVal = bundle.getString("addressVal");

        workplaceAddressEt1 = findViewById(R.id.workplace_address1_et);
        workplaceAddressEt2 = findViewById(R.id.workplace_address2_et);
        workplaceNameEt = findViewById(R.id.workplace_name_et);
        workplaceContactEt = findViewById(R.id.workplace_contact_et);
        userTypeSp = findViewById(R.id.user_type_sp);
        continueBt = findViewById(R.id.continue_bt);

        userRoles.add(getString(R.string.please_select_user_type));
//        userRoles.add("Distributor");
//        userRoles.add("Shop");
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, ProjectConstants.rolesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSp.setAdapter(adapter);

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkNulls()){
                    Intent intent = new Intent(context, PreviewApplicationActivity.class);
                    intent.putExtra("usernameVal", usernameVal);
                    intent.putExtra("passwordVal", passwordVal);
                    intent.putExtra("nameVal", nameVal);
                    intent.putExtra("contactVal", contactVal);
                    intent.putExtra("emailVal", emailVal);
                    intent.putExtra("addressVal", addressVal);
                    intent.putExtra("workplaceNameVal", workplaceNameEt.getText().toString());
                    intent.putExtra("userTypeVal", userTypeSp.getSelectedItem().toString());
                    intent.putExtra("workplaceContactVal", workplaceContactEt.getText().toString());
                    intent.putExtra("workplaceAddressVal", workplaceAddressEt1.getText().toString()+" "+workplaceAddressEt2.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu1, menu);
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

        if(userTypeSp.getSelectedItem() == null || userTypeSp.getSelectedItemPosition() == 0){
            Toast.makeText(context, getString(R.string.please_select_user_type), Toast.LENGTH_SHORT).show();
            System.out.println("Here 1");
            return false;
        }

        if(workplaceNameEt.getText() == null || workplaceNameEt.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, getString(R.string.please_enter_workplace_name), Toast.LENGTH_SHORT).show();
            System.out.println("Here 2");
            return false;
        }

        if(workplaceContactEt.getText() == null || workplaceContactEt.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, getString(R.string.please_enter_workplace_contact_number), Toast.LENGTH_SHORT).show();
            System.out.println("Here 3");
            return false;
        }

        if(workplaceContactEt.getText().toString().length() !=10){
            Toast.makeText(context, getString(R.string.please_enter_10_digit_contact), Toast.LENGTH_SHORT).show();
            System.out.println("Here 4");
            return false;
        }

        if(workplaceAddressEt1.getText() == null || workplaceAddressEt1.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, getString(R.string.please_enter_workplace_address), Toast.LENGTH_SHORT).show();
            System.out.println("Here 5");
            return false;
        }

        if(workplaceAddressEt2.getText() == null){
            workplaceAddressEt2.setText("");
        }
        return true;
    }
}