package com.capstone.capstoneProject.orderManagement.orderLineItems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.dashboard.DashboardActivity;
import com.capstone.capstoneProject.inventory.api.InventoryManagementApi;
import com.capstone.capstoneProject.orderManagement.api.OrderApi;
import com.capstone.capstoneProject.orderManagement.model.OrderItemsPojo;
import com.capstone.capstoneProject.orderManagement.model.UpdateInventoryPojo;
import com.capstone.capstoneProject.orderManagement.model.UpdateItemsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderLineItemsActivity extends AppCompatActivity {

    Context context;
    String orderId, userId, userType, username, shopId;
    RecyclerView orderLineItemsRv;
    OrderLineItemsAdapter mAdapter;
    Button updateBt;
    TextView orderIdTv;
    public static List<UpdateItemsPojo> request = new ArrayList<>();
    public static List<UpdateItemsPojo> receivedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_line_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.order_items));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        Bundle bundle = getIntent().getExtras();
        orderId = bundle.getString("orderId");
        userId = bundle.getString("userId");
        userType = bundle.getString("userType");
        username = bundle.getString("username");
        shopId = bundle.getString("shopId");
        orderLineItemsRv = findViewById(R.id.order_line_items_rv);
        orderLineItemsRv.setLayoutManager(new LinearLayoutManager(context));
        orderLineItemsRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        List<OrderItemsPojo> itemsPojoList = (List<OrderItemsPojo>)getIntent().getSerializableExtra("items");
        System.out.println("Items list "+itemsPojoList.toString());
        mAdapter = new OrderLineItemsAdapter(context, userType, userId, itemsPojoList);
        orderLineItemsRv.setAdapter(mAdapter);
        updateBt = findViewById(R.id.update_bt);
        orderIdTv = findViewById(R.id.order_id_tv);
        orderIdTv.setText(orderId);
        updateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(request.size()>0){
                    System.out.println("\nRequest -> {"+request.toString()+"}");
                    callUpdateItemsApi();
                }else {
                    Toast.makeText(context,getString(R.string.update_inv_first), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void callUpdateItemsApi() {
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){}
        OrderApi api = RetrofitBuilder.createPostLoginRetrofit().create(OrderApi.class);
        Call<String> call = api.updateOrderItems(request);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response != null && response.body() != null && response.body().equalsIgnoreCase("success")){
                    Toast.makeText(context,getString(R.string.items_updated), Toast.LENGTH_SHORT).show();
                    if(receivedItems.size()>0){
                        callUpdateInv();
                    }else {
                        updateSuccess();
                    }
                }else {
                    Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSuccess() {
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.putExtra("username", username);
        request.clear();
        startActivity(intent);
    }

    private void callUpdateInv() {
        try {
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){}
        List<UpdateInventoryPojo> req = new ArrayList<>();
        for(UpdateItemsPojo u : receivedItems){
            UpdateInventoryPojo i = new UpdateInventoryPojo();
            i.setQuantity(u.getQuantity());
            i.setDistId(u.getDistId());
            i.setInvId(u.getInvId());
            req.add(i);
        }
        InventoryManagementApi api = RetrofitBuilder.createPostLoginRetrofit().create(InventoryManagementApi.class);
        Call<String> call = api.updateInventory(shopId, req);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response != null && response.body() != null){
                    updateSuccess();
                }else {
                    Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
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
        item.setVisible(false);
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
        finish();
    }

}