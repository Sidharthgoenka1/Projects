package com.capstone.capstoneProject.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.capstone.capstoneProject.R;

public class ProfileActivity extends AppCompatActivity {

    Context context;
    TextView userIdTv, usernameTv, addressTv, contactNoTv, emailTv, workplaceIdTv,
            workplaceNameTv, workplaceAddressTv, workplaceContactTv, nameTv, roleTv;
    String username, userId, address, contactNo, email, workplaceId, workplaceName,workplaceAddress,
            workplaceContact, name, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.profile));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userIdTv = findViewById(R.id.user_id_tv);
        usernameTv = findViewById(R.id.user_name_tv);
        addressTv = findViewById(R.id.user_address_tv);
        contactNoTv = findViewById(R.id.contact_tv);
        emailTv = findViewById(R.id.email_tv);
        workplaceIdTv = findViewById(R.id.workplace_id_tv);
        workplaceNameTv = findViewById(R.id.workplace_name_tv);
        workplaceAddressTv = findViewById(R.id.workplace_address_tv);
        workplaceContactTv = findViewById(R.id.workplace_contact_tv);
        nameTv = findViewById(R.id.name_tv);
        roleTv = findViewById(R.id.role_tv);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        userId = bundle.getString("userId");
        address = bundle.getString("address");
        contactNo = bundle.getString("contactNo");
        email = bundle.getString("email");
        workplaceId = bundle.getString("wId");
        workplaceName = bundle.getString("wName");
        workplaceAddress = bundle.getString("wAddress");
        workplaceContact = bundle.getString("wContact");
        name = bundle.getString("name");
        role = bundle.getString("role");

        userIdTv.setText(userId);
        usernameTv.setText(username);
        addressTv.setText(address);
        contactNoTv.setText(contactNo);
        emailTv.setText(email);
        workplaceIdTv.setText(workplaceId);
        workplaceNameTv.setText(workplaceName);
        workplaceAddressTv.setText(workplaceAddress);
        workplaceContactTv.setText(workplaceContact);
        nameTv.setText(name);
        roleTv.setText(role);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
               onBackPressed();
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