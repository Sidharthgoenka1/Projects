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
import android.widget.Toast;

import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.dashboard.DashboardActivity;
import com.capstone.capstoneProject.orderManagement.api.OrderApi;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequestItems;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequestWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCartActivity extends AppCompatActivity {

    Context context;
    RecyclerView viewCartRv;
    ViewCartAdapter recyclerAdapter;
    Button submitBt;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.view_cart));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        viewCartRv = findViewById(R.id.view_cart_rv);
        submitBt = findViewById(R.id.submit_bt);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        viewCartRv.setLayoutManager(new LinearLayoutManager(context));
        viewCartRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerAdapter = new ViewCartAdapter(PlaceOrderActivity.createOrderRequestWrapper, context);
        viewCartRv.setAdapter(recyclerAdapter);

        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.confirmation_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                createOrder();
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
        });
    }

    public void createOrder(){
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){

        }
        OrderApi api = RetrofitBuilder.createPostLoginRetrofit().create(OrderApi.class);
        Call<String> call = api.createOrder(PlaceOrderActivity.createOrderRequestWrapper);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){

                }
                if(response != null && response.body().length()>0){
                    Toast.makeText(context, getString(R.string.order_created_successfully)+" "+response.body(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DashboardActivity.class);
                    intent.putExtra("username", username);
//                    intent.putExtra("userId",SearchProductActivity.createOrderRequestWrapper.getMessage().getUserId()+"");
//                    intent.putExtra("userType", SearchProductActivity.createOrderRequestWrapper.getMessage().getUserType());
//                    intent.putExtra("workplaceId", SearchProductActivity.createOrderRequestWrapper.getMessage().getWorkplaceId()+"");
                    PlaceOrderActivity.createOrderRequestWrapper = new CreateOrderRequestWrapper();
                    startActivity(intent);
                }else{
                    Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
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
                CreateOrderRequestWrapper filterWrapper = new CreateOrderRequestWrapper();
                if(query != null && query.length()>0){
                    for(CreateOrderRequestItems c : PlaceOrderActivity.createOrderRequestWrapper.getMessage().getCreateOrderRequestItemList()){
                        if(c.getInvName().toLowerCase().contains(query.toLowerCase())){
                            filterWrapper.getMessage().getCreateOrderRequestItemList().add(c);
                        }
                    }
                    recyclerAdapter = new ViewCartAdapter(filterWrapper, context);
                    viewCartRv.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                }else {
                    recyclerAdapter = new ViewCartAdapter(PlaceOrderActivity.createOrderRequestWrapper, context);
                    viewCartRv.setAdapter(recyclerAdapter);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                CreateOrderRequestWrapper filterWrapper = new CreateOrderRequestWrapper();
                if(query != null && query.length()>0){
                    for(CreateOrderRequestItems c : PlaceOrderActivity.createOrderRequestWrapper.getMessage().getCreateOrderRequestItemList()){
                        if(c.getInvName().toLowerCase().contains(query.toLowerCase())){
                            filterWrapper.getMessage().getCreateOrderRequestItemList().add(c);
                        }
                    }
                    recyclerAdapter = new ViewCartAdapter(filterWrapper, context);
                    viewCartRv.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                }else {
                    recyclerAdapter = new ViewCartAdapter(PlaceOrderActivity.createOrderRequestWrapper, context);
                    viewCartRv.setAdapter(recyclerAdapter);
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