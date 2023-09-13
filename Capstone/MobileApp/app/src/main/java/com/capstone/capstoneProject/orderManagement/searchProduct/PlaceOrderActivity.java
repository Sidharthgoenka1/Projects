package com.capstone.capstoneProject.orderManagement.searchProduct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.orderManagement.api.OrderApi;
import com.capstone.capstoneProject.orderManagement.model.AvailableDistForInventory;
import com.capstone.capstoneProject.orderManagement.model.AvailableDistForInventoryWrapper;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequest;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequestWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceOrderActivity extends AppCompatActivity {

    Context context;
    String username, userType, userId, workplaceId;
    RecyclerView searchProductRv;
    List<AvailableDistForInventory> masterList;
    PlaceOrderAdapter recyclerAdapter;
    EditText searchEt;
    ImageView searchIv;
    public static CreateOrderRequestWrapper createOrderRequestWrapper = new CreateOrderRequestWrapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.search_product));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        userType = bundle.getString("userType");
        userId = bundle.getString("userId");
        workplaceId = bundle.getString("workplaceId");
        searchProductRv = findViewById(R.id.search_product_rv);
        masterList = new ArrayList<>();
        searchIv = findViewById(R.id.search_iv);
        searchEt = findViewById(R.id.search_et);
        Button viewCartBt = findViewById(R.id.view_cart_bt);

        searchProductRv.setLayoutManager(new LinearLayoutManager(context));
        searchProductRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

//        for(int i = 1; i<9; i++){
//            AvailableDistForInventory s = new AvailableDistForInventory();
//            s.setInvName(i+" item");
//            s.setInvDesc(i+" desc");
//            s.setwAddress("address ");
//            s.setwContact("67534"+(i*11111));
//            s.setDistId(1+i+"");
//            s.setDistName(i+ " name");
//
//            masterList.add(s);
//        }
//
//        recyclerAdapter = new SearchProductAdapter(masterList, context);
//        searchProductRv.setAdapter(recyclerAdapter);

        viewCartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(createOrderRequestWrapper.getMessage().getCreateOrderRequestItemList() != null &&
                    createOrderRequestWrapper.getMessage().getCreateOrderRequestItemList().size()>0) {
                    Intent intent = new Intent(context, ViewCartActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }else {
                    Toast.makeText(context, getString(R.string.cart_is_empty), Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchEt.getText() != null && searchEt.getText().toString().length()>0){
                    callSearchProdApi(searchEt.getText().toString().trim());
                }else {
                    Toast.makeText(context, getString(R.string.please_enter_item_name), Toast.LENGTH_SHORT).show();
                }
            }
        });

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setShopId(Integer.parseInt(workplaceId));
        createOrderRequest.setUserType(userType);
        createOrderRequest.setComment("Creating Order");
        createOrderRequest.setUpdatedBy(Integer.parseInt(userId));
        createOrderRequest.setWorkplaceId(Integer.parseInt(workplaceId));
        createOrderRequest.setCreateOrderRequestItemList(new ArrayList<>());

        createOrderRequestWrapper.setMessage(createOrderRequest);
    }

    public void callSearchProdApi(String invName){
        try {
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){
            e.printStackTrace();
        }

        OrderApi api = RetrofitBuilder.createPostLoginRetrofit().create(OrderApi.class);
        Call<AvailableDistForInventoryWrapper> call = api.searchItem(invName);
        call.enqueue(new Callback<AvailableDistForInventoryWrapper>() {
            @Override
            public void onResponse(Call<AvailableDistForInventoryWrapper> call, Response<AvailableDistForInventoryWrapper> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){

                }
                if(response != null && response.body().getStatusCode() ==200){
                    AvailableDistForInventoryWrapper wrapper = response.body();
                    if(wrapper.getAvailableInventoryResponseList() != null && wrapper.getAvailableInventoryResponseList().size()>0) {
                        masterList = wrapper.getAvailableInventoryResponseList();
                        recyclerAdapter = new PlaceOrderAdapter(masterList, context);
                        searchProductRv.setAdapter(recyclerAdapter);
                    }else {
                        Toast.makeText(context, getString(R.string.item_not_found), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AvailableDistForInventoryWrapper> call, Throwable t) {
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
        getMenuInflater().inflate(R.menu.menu1, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<AvailableDistForInventory> filterList = new ArrayList<>();
                if(query != null && query.length()>0){
                    for(AvailableDistForInventory s : masterList){
                        if(s.getInvName().toLowerCase().contains(query.toLowerCase())){
                            filterList.add(s);
                        }else if(s.getInvDesc().toLowerCase().contains(query.toLowerCase())){
                            filterList.add(s);
                        }
                    }
                    recyclerAdapter = new PlaceOrderAdapter(filterList, context);
                    searchProductRv.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                }else {
                    recyclerAdapter = new PlaceOrderAdapter(masterList, context);
                    searchProductRv.setAdapter(recyclerAdapter);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                List<AvailableDistForInventory> filterList = new ArrayList<>();
                if(query != null && query.length()>0){
                    for(AvailableDistForInventory s : masterList){
                        if(s.getInvName().toLowerCase().contains(query.toLowerCase())){
                            filterList.add(s);
                        }else if(s.getInvDesc().toLowerCase().contains(query.toLowerCase())){
                            filterList.add(s);
                        }
                    }
                    recyclerAdapter = new PlaceOrderAdapter(filterList, context);
                    searchProductRv.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                }else {
                    recyclerAdapter = new PlaceOrderAdapter(masterList, context);
                    searchProductRv.setAdapter(recyclerAdapter);
                }
                return true;
            }
        });
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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.back_message)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        createOrderRequestWrapper = new CreateOrderRequestWrapper();
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}