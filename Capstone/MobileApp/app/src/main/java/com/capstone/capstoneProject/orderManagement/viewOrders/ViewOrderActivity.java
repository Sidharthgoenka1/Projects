package com.capstone.capstoneProject.orderManagement.viewOrders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.orderManagement.api.OrderApi;
import com.capstone.capstoneProject.orderManagement.model.OrderDataWrapper;
import com.capstone.capstoneProject.orderManagement.model.OrdersDataPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrderActivity extends AppCompatActivity {

    Context context;
    ViewOrderAdapter mAdapter;
    RecyclerView ordersRv;
    OrderDataWrapper masterData;
    String userId, userType, username, workplaceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.orders));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        ordersRv = findViewById(R.id.orders_rv);
        ordersRv.setLayoutManager(new LinearLayoutManager(context));
        ordersRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        userId = bundle.getString("userId");
        userType = bundle.getString("userType");
        workplaceId = bundle.getString("workplaceId");
        getAllOrders(userType,workplaceId,userId);
    }

    public void getAllOrders(String userType, String workplaceId, String userId){
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){}

        OrderApi api = RetrofitBuilder.createPostLoginRetrofit().create(OrderApi.class);
        Call<OrderDataWrapper> call = api.getOrdersForUser(userType, workplaceId);
        call.enqueue(new Callback<OrderDataWrapper>() {
            @Override
            public void onResponse(Call<OrderDataWrapper> call, Response<OrderDataWrapper> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){

                }
                if(response.body() != null && response.body().getStatusCode() == 200){
                    if(response.body().getMessage()!= null && response.body().getMessage().size()>0){

                        masterData = new OrderDataWrapper();
                        masterData.setStatus(response.body().getStatus());
                        masterData.setStatusCode(response.body().getStatusCode());
                        List<OrdersDataPojo> data = new ArrayList<>();
                        for(OrdersDataPojo o : response.body().getMessage()){
                            if(o.getStatusId() != null){
                                o.setStatusVal(ProjectConstants.orderStatusMasterMap.get(o.getStatusId()).getStatusVal());
                            }
                            data.add(o);
                        }
                        masterData.setMessage(data);
                        mAdapter = new ViewOrderAdapter(masterData, context,userType, userId, username, workplaceId);
                        ordersRv.setAdapter(mAdapter);
                    }else {
                        Toast.makeText(context, getString(R.string.no_orders_found), Toast.LENGTH_SHORT).show();
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<OrderDataWrapper> call, Throwable t) {
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
        getMenuInflater().inflate(R.menu.menu1, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                OrderDataWrapper filterPojo = new OrderDataWrapper();
                filterPojo.setMessage(new ArrayList<>());
                if(masterData != null && masterData.getMessage().size()>0){
                    for(OrdersDataPojo o : masterData.getMessage()){
                        if(o.getOrderId().toString().contains(query)){
                            filterPojo.getMessage().add(o);
                        }else if(o.getShopName().toLowerCase().contains(query.toLowerCase())){
                            filterPojo.getMessage().add(o);
                        }
                    }
                    mAdapter = new ViewOrderAdapter(filterPojo, context,userType, userId, username, workplaceId);
                    ordersRv.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                OrderDataWrapper filterPojo = new OrderDataWrapper();
                filterPojo.setMessage(new ArrayList<>());
                if(masterData != null && masterData.getMessage().size()>0){
                    for(OrdersDataPojo o : masterData.getMessage()){
                        if(o.getOrderId().toString().contains(query)){
                            filterPojo.getMessage().add(o);
                        }else if(o.getShopName().toLowerCase().contains(query.toLowerCase())){
                            filterPojo.getMessage().add(o);
                        }
                    }
                    mAdapter = new ViewOrderAdapter(filterPojo, context,userType, userId, username, workplaceId);
                    ordersRv.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

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
        finish();
    }
}