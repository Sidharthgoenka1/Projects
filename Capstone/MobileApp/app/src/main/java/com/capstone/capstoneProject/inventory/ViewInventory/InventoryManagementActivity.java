package com.capstone.capstoneProject.inventory.ViewInventory;

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
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.dashboard.model.InventoryMaster;
import com.capstone.capstoneProject.inventory.api.InventoryManagementApi;
import com.capstone.capstoneProject.inventory.model.InventoryDistMapping;
import com.capstone.capstoneProject.inventory.model.InventoryDistributorMappingResponsePojo;
import com.capstone.capstoneProject.inventory.model.InventoryManagementPojo;
import com.capstone.capstoneProject.inventory.model.InventoryShopMapping;
import com.capstone.capstoneProject.inventory.model.InventoryShopMappingResponsePojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryManagementActivity extends AppCompatActivity {

    Context context;
    RecyclerView inventoryManagementRv;
    InventoryManagementAdapter invAdapter;
    InventoryManagementPojo data;
    TextView totalQtyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.inventory_management));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        Bundle bundle = getIntent().getExtras();
        String userType = bundle.getString("userType");
        String userId = bundle.getString("userId");
        String wrkplaceId = bundle.getString("workplaceId");

        inventoryManagementRv = findViewById(R.id.inv_management_rv);
        totalQtyTv = findViewById(R.id.total_qty_tv);

        inventoryManagementRv.setLayoutManager(new LinearLayoutManager(context));
        inventoryManagementRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        getInvForUser(wrkplaceId, userType);
    }

    public void getInvForUser(String userId, String userType){

        data = new InventoryManagementPojo();

        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){
        }
        InventoryManagementApi api = RetrofitBuilder.createPostLoginRetrofit().create(InventoryManagementApi.class);
        if(userType.equalsIgnoreCase("D")){
            Call<InventoryDistributorMappingResponsePojo> call = api.getInventoryByDistId(Integer.parseInt(userId));
            call.enqueue(new Callback<InventoryDistributorMappingResponsePojo>() {
                @Override
                public void onResponse(Call<InventoryDistributorMappingResponsePojo> call, Response<InventoryDistributorMappingResponsePojo> response) {
                    try{
                        RetrofitBuilder.dismissProgressDialog();
                    }catch (Exception e){

                    }
                    if(response.body() != null && response.body().getStatusCode() == 200){
                        if(response.body().getInventoryDistMappingList() != null
                                && response.body().getInventoryDistMappingList().size()>0){
                                data.setDistInvList(response.body().getInventoryDistMappingList());
                                int count = 0;
                                for(InventoryDistMapping i : response.body().getInventoryDistMappingList()){
                                    count = count + i.getQuantity();
                                }
                                totalQtyTv.setText(count+"");
                                invAdapter = new InventoryManagementAdapter(data, context);
                                inventoryManagementRv.setAdapter(invAdapter);
                        }
                    }else {
                        Toast.makeText(context, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InventoryDistributorMappingResponsePojo> call, Throwable t) {
                    try{
                        RetrofitBuilder.dismissProgressDialog();
                    }catch (Exception e){
                    }
                    Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Call<InventoryShopMappingResponsePojo> call = api.getInventoryForShop(Integer.parseInt(userId));
            call.enqueue(new Callback<InventoryShopMappingResponsePojo>() {
                @Override
                public void onResponse(Call<InventoryShopMappingResponsePojo> call, Response<InventoryShopMappingResponsePojo> response) {
                    try{
                        RetrofitBuilder.dismissProgressDialog();
                    }catch (Exception e){

                    }
                    if(response.body() != null && response.body().getStatusCode() == 200){
                        if(response.body().getInventoryShopMappingList() != null
                                && response.body().getInventoryShopMappingList().size()>0){
                            data.setShopInvList(response.body().getInventoryShopMappingList());
                            int count = 0;
                            for(InventoryShopMapping i : response.body().getInventoryShopMappingList()){
                                count = count + i.getQuantity();
                            }
                            totalQtyTv.setText(count+"");
                            invAdapter = new InventoryManagementAdapter(data, context);
                            inventoryManagementRv.setAdapter(invAdapter);
                        }
                    }else {
                        Toast.makeText(context, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InventoryShopMappingResponsePojo> call, Throwable t) {
                    try{
                        RetrofitBuilder.dismissProgressDialog();
                    }catch (Exception e){

                    }
                    Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            });
        }
        invAdapter = new InventoryManagementAdapter(data, context);
        inventoryManagementRv.setAdapter(invAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InventoryManagementPojo filterPojo = new InventoryManagementPojo();
                filterPojo.setDistInvList(new ArrayList<>());
                filterPojo.setShopInvList(new ArrayList<>());
                if(query != null && query.length()>0){

                    if(data.getDistInvList() != null && data.getDistInvList().size()>0){
                        for(InventoryDistMapping a : data.getDistInvList()){
                            if(a.getMedId().toString().contains(query.trim())){
                                filterPojo.getDistInvList().add(a);
                            }else {
                                InventoryMaster m = ProjectConstants.invIdMasterMap.get(a.getMedId());
                                if(m.getName().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getDistInvList().add(a);
                                }else if(m.getDescription().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getDistInvList().add(a);
                                }else if(m.getSalts().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getDistInvList().add(a);
                                }
                            }
                        }
                        invAdapter = new InventoryManagementAdapter(filterPojo, context);
                        inventoryManagementRv.setAdapter(invAdapter);
                    }else if(data.getShopInvList() != null && data.getShopInvList().size()>0){
                        for(InventoryShopMapping a : data.getShopInvList()){
                            if(a.getMedId().toString().contains(query.trim())){
                                filterPojo.getShopInvList().add(a);
                            }else {
                                InventoryMaster m = ProjectConstants.invIdMasterMap.get(a.getMedId());
                                if(m.getName().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getShopInvList().add(a);
                                }else if(m.getDescription().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getShopInvList().add(a);
                                }else if(m.getSalts().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getShopInvList().add(a);
                                }
                            }
                        }
                        invAdapter = new InventoryManagementAdapter(filterPojo, context);
                        inventoryManagementRv.setAdapter(invAdapter);
                    }else {
                        filterPojo = data;
                        invAdapter = new InventoryManagementAdapter(filterPojo, context);
                        inventoryManagementRv.setAdapter(invAdapter);
                    }
                }else {
                    invAdapter = new InventoryManagementAdapter(data, context);
                    inventoryManagementRv.setAdapter(invAdapter);
                }
                invAdapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                InventoryManagementPojo filterPojo = new InventoryManagementPojo();
                filterPojo.setDistInvList(new ArrayList<>());
                filterPojo.setShopInvList(new ArrayList<>());
                if(query != null && query.length()>0){

                    if(data.getDistInvList() != null && data.getDistInvList().size()>0){
                        for(InventoryDistMapping a : data.getDistInvList()){
                            if(a.getMedId().toString().contains(query.trim())){
                                filterPojo.getDistInvList().add(a);
                            }else {
                                InventoryMaster m = ProjectConstants.invIdMasterMap.get(a.getMedId());
                                if(m.getName().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getDistInvList().add(a);
                                }else if(m.getDescription().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getDistInvList().add(a);
                                }else if(m.getSalts().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getDistInvList().add(a);
                                }
                            }
                        }
                        invAdapter = new InventoryManagementAdapter(filterPojo, context);
                        inventoryManagementRv.setAdapter(invAdapter);
                    }else if(data.getShopInvList() != null && data.getShopInvList().size()>0){
                        for(InventoryShopMapping a : data.getShopInvList()){
                            if(a.getMedId().toString().contains(query.trim())){
                                filterPojo.getShopInvList().add(a);
                            }else {
                                InventoryMaster m = ProjectConstants.invIdMasterMap.get(a.getMedId());
                                if(m.getName().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getShopInvList().add(a);
                                }else if(m.getDescription().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getShopInvList().add(a);
                                }else if(m.getSalts().toLowerCase().contains(query.toLowerCase())){
                                    filterPojo.getShopInvList().add(a);
                                }
                            }
                        }
                        invAdapter = new InventoryManagementAdapter(filterPojo, context);
                        inventoryManagementRv.setAdapter(invAdapter);
                    }else {
                        filterPojo = data;
                        invAdapter = new InventoryManagementAdapter(filterPojo, context);
                        inventoryManagementRv.setAdapter(invAdapter);
                    }
                }else {
                    invAdapter = new InventoryManagementAdapter(data, context);
                    inventoryManagementRv.setAdapter(invAdapter);
                }
                invAdapter.notifyDataSetChanged();
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