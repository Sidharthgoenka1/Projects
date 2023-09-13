package com.capstone.capstoneProject.dashboard;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.dashboard.api.DashboardApi;
import com.capstone.capstoneProject.dashboard.model.InventoryMaster;
import com.capstone.capstoneProject.dashboard.model.InventoryMasterResponsePojo;
import com.capstone.capstoneProject.dashboard.model.OrderStatusMaster;
import com.capstone.capstoneProject.inventory.UpdateInventory.UpdateInventoryActivity;
import com.capstone.capstoneProject.inventory.ViewInventory.InventoryManagementActivity;
import com.capstone.capstoneProject.login.LoginActivity;
import com.capstone.capstoneProject.orderManagement.viewOrders.ViewOrderActivity;
import com.capstone.capstoneProject.register.api.RegisterApi;
import com.capstone.capstoneProject.register.model.RegisterRequest;
import com.capstone.capstoneProject.orderManagement.searchProduct.PlaceOrderActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    Context context;
    RegisterRequest userDetails;
    Button searchProductBt, invManagementBt,ordersBt, updateInvBt;
    LinearLayout updateInvLl, searchProdLl, invMgmtLl, ordersLl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.dashboard));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");

        searchProductBt = findViewById(R.id.search_product_bt);
        invManagementBt = findViewById(R.id.inventory_management_bt);
        ordersBt = findViewById(R.id.orders_bt);
        updateInvBt = findViewById(R.id.update_inv_bt);

        updateInvLl = findViewById(R.id.update_inv_ll);
        searchProdLl = findViewById(R.id.search_prod_ll);
        invMgmtLl = findViewById(R.id.inv_mgmt_ll);
        ordersLl = findViewById(R.id.orders_ll);

        getUserDetails(username);

    }

    public void getUserDetails(String username){

        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){

        }
        DashboardApi api = RetrofitBuilder.createPostLoginRetrofit().create(DashboardApi.class);
        Call<RegisterRequest> call = api.getUserDetails(username);
        call.enqueue(new Callback<RegisterRequest>() {
            @Override
            public void onResponse(Call<RegisterRequest> call, Response<RegisterRequest> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){

                }
                if(response != null){
                    userDetails = response.body();
                    fetchOrderStatusMaster();
                    fetchInventoryMaster();
                    if(userDetails.getUserType().equalsIgnoreCase("s")){
                        searchProdLl.setVisibility(View.VISIBLE);
                    }else {
                        searchProdLl.setVisibility(View.GONE);
                    }
                    ordersBt.setVisibility(View.VISIBLE);
                    invManagementBt.setVisibility(View.VISIBLE);
                    searchProductBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, PlaceOrderActivity.class);
                            intent.putExtra("username", userDetails.getUsername());
                            intent.putExtra("userId",userDetails.getUserId()+"");
                            intent.putExtra("userType", userDetails.getUserType());
                            intent.putExtra("workplaceId", userDetails.getWorkplaceId()+"");
                            startActivity(intent);
                        }
                    });


                    invManagementBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, InventoryManagementActivity.class);
                            intent.putExtra("username", userDetails.getUsername());
                            intent.putExtra("userId",userDetails.getUserId()+"");
                            intent.putExtra("userType", userDetails.getUserType());
                            intent.putExtra("workplaceId", userDetails.getWorkplaceId()+"");
                            startActivity(intent);
                        }
                    });


                    ordersBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, ViewOrderActivity.class);
                            intent.putExtra("username", userDetails.getUsername());
                            intent.putExtra("workplaceId", userDetails.getWorkplaceId()+"");
                            intent.putExtra("userId",userDetails.getUserId()+"");
                            intent.putExtra("userType", userDetails.getUserType());
                            startActivity(intent);
                        }
                    });


                    if(userDetails.getUserType().equalsIgnoreCase("d")){
                        updateInvBt.setVisibility(View.VISIBLE);
                    }else {
                        updateInvBt.setVisibility(View.GONE);
                    }
                    updateInvBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, UpdateInventoryActivity.class);
                            intent.putExtra("workplaceId", userDetails.getWorkplaceId()+"");
                            intent.putExtra("username", userDetails.getUsername());
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<RegisterRequest> call, Throwable t) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){

                }
                Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(context, ProfileActivity.class);

                intent.putExtra("username", userDetails.getUsername());
                intent.putExtra("address",userDetails.getAddress());
                intent.putExtra("contactNo",userDetails.getContactNo());
                intent.putExtra("email",userDetails.getEmail());
                intent.putExtra("name",userDetails.getName());
                intent.putExtra("role",userDetails.getRole());
                intent.putExtra("userType",userDetails.getUserType());
                intent.putExtra("wAddress",userDetails.getWorkplaceAddress());
                intent.putExtra("wContact",userDetails.getWorkplaceContactNo());
                intent.putExtra("wId",userDetails.getWorkplaceId()+"");
                intent.putExtra("wName",userDetails.getWorkplaceName());
                intent.putExtra("userId",userDetails.getUserId()+"");

                startActivity(intent);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.log_out:
                onBackPressed();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchOrderStatusMaster(){
        ProjectConstants.orderParentStatusMasterMap.clear();
        ProjectConstants.orderStatusMasterMap.clear();
        ProjectConstants.orderStatusNameMasterMap.clear();
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){
            e.printStackTrace();
        }
        RegisterApi registerApi = RetrofitBuilder.createPostLoginRetrofit().create(RegisterApi.class);
        Call<List<String>> call = registerApi.getOrderStatus();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response != null && response.body().size()>0){
                    List<String> statusPojoList = response.body();
                    for(String s : statusPojoList){
                        String[] i = s.split("-");
                        OrderStatusMaster o = new OrderStatusMaster();
                        o.setStatusId(Integer.parseInt(i[0].trim()));
                        o.setStatusVal(i[1].trim());
                        o.setParentId(Integer.parseInt(i[2].trim()));

                        if(!ProjectConstants.orderStatusMasterMap.containsKey(o.getStatusId())){
                            ProjectConstants.orderStatusMasterMap.put(o.getStatusId(),o);
                        }

                        if(!ProjectConstants.orderStatusNameMasterMap.containsKey(o.getStatusVal())){
                            ProjectConstants.orderStatusNameMasterMap.put(o.getStatusVal(),o);
                        }

                        if(!ProjectConstants.orderParentStatusMasterMap.containsKey(o.getParentId())) {
                            List<OrderStatusMaster> orderStatusMasters = new ArrayList<>();
                            orderStatusMasters.add(o);
                            ProjectConstants.orderParentStatusMasterMap.put(o.getParentId(), orderStatusMasters);
                        }else {
                            ProjectConstants.orderParentStatusMasterMap.get(o.getParentId()).add(o);
                        }
                    }

                    System.out.println("Status Master Parent-> "+ProjectConstants.orderParentStatusMasterMap.toString());
                    System.out.println("Status Master -> "+ProjectConstants.orderStatusMasterMap.toString());
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

    @Override
    public void onBackPressed() {

        //Todo: add logout popup
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(getString(R.string.logout_alert));
        builder1.setCancelable(false);

        builder1.setPositiveButton(getString(R.string.logout), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Todo: develop and call logout API
                dialog.cancel();
                RetrofitBuilder.setToken(null);
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
//                finishAndRemoveTask();
            }
        });

        builder1.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public void fetchInventoryMaster(){
        ProjectConstants.invIdMasterMap.clear();
        ProjectConstants.invNameMasterMap.clear();
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){
            e.printStackTrace();
        }
        DashboardApi api = RetrofitBuilder.createPostLoginRetrofit().create(DashboardApi.class);
        Call<InventoryMasterResponsePojo> call = api.getInventoryMaster();
        call.enqueue(new Callback<InventoryMasterResponsePojo>() {
            @Override
            public void onResponse(Call<InventoryMasterResponsePojo> call, Response<InventoryMasterResponsePojo> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response.body() != null && response.body().getStatusCode() == 200){
                    if(response.body().getInventoryMasterList()!= null &&
                            response.body().getInventoryMasterList().size()>0){
                        System.out.println("Inventory master response -> {"+response.body().toString()+"}");
                        for(InventoryMaster i : response.body().getInventoryMasterList()){
                            if(!ProjectConstants.invIdMasterMap.containsKey(i.getId())){
                                ProjectConstants.invIdMasterMap.put(i.getId(),i);
                            }

                            if(!ProjectConstants.invNameMasterMap.containsKey(i.getName())){
                                ProjectConstants.invNameMasterMap.put(i.getName(),i);
                            }
                        }
                    }else {
                        Toast.makeText(context, getString(R.string.inv_master_not_found), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InventoryMasterResponsePojo> call, Throwable t) {
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