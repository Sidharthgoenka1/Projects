package com.capstone.capstoneProject.inventory.UpdateInventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.dashboard.DashboardActivity;
import com.capstone.capstoneProject.dashboard.model.InventoryMaster;
import com.capstone.capstoneProject.inventory.ViewInventory.InventoryManagementAdapter;
import com.capstone.capstoneProject.inventory.api.InventoryManagementApi;
import com.capstone.capstoneProject.inventory.model.InventoryDistMapping;
import com.capstone.capstoneProject.inventory.model.InventoryManagementPojo;
import com.capstone.capstoneProject.inventory.model.InventoryShopMapping;
import com.capstone.capstoneProject.inventory.model.UpdateInventoryRequestPojo;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequestWrapper;
import com.capstone.capstoneProject.orderManagement.searchProduct.PlaceOrderActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInventoryActivity extends AppCompatActivity {
    Context context;
    Spinner itemsSp;
    LinearLayout otherItemLl, itemLl, updateBtLl;
    EditText itemNameEt, itemShortDescEt, itemDescEt,itemSaltsEt, itemQtyEt, otherQtyEt;
    TextView itemNameTv, itemShortDescTv, itemDescTv, itemSaltsTv;
    Button updateBt;
    String workplaceId, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.update_inventory));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        otherItemLl = findViewById(R.id.other_inv_ll);
        itemLl = findViewById(R.id.inv_ll);
        itemNameEt = findViewById(R.id.item_name_et);
        itemShortDescEt = findViewById(R.id.short_description_et);
        itemDescEt = findViewById(R.id.description_et);
        itemSaltsEt = findViewById(R.id.salts_et);
        otherQtyEt = findViewById(R.id.other_qty_et);
        itemQtyEt = findViewById(R.id.item_qty_et);
        itemNameTv = findViewById(R.id.item_name_tv);
        itemShortDescTv = findViewById(R.id.short_description_tv);
        itemDescTv = findViewById(R.id.description_tv);
        itemSaltsTv = findViewById(R.id.salts_tv);
        updateBt = findViewById(R.id.update_bt);
        itemsSp = findViewById(R.id.inventory_sp);
        updateBtLl = findViewById(R.id.update_bt_ll);

        Bundle bundle = getIntent().getExtras();
        workplaceId = bundle.getString("workplaceId");
        username = bundle.getString("username");

        otherItemLl.setVisibility(View.GONE);
        itemLl.setVisibility(View.GONE);
        updateBtLl.setVisibility(View.GONE);

        List<String> invNameList = new ArrayList<>();
        invNameList.add(getString(R.string.please_select));
        invNameList.add(getString(R.string.other));
        invNameList.addAll(ProjectConstants.invNameMasterMap.keySet());

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, invNameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemsSp.setAdapter(adapter);

        itemsSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                System.out.println("Selected -> "+itemsSp.getSelectedItem());
                System.out.println("Selected Pos -> "+itemsSp.getSelectedItemPosition());
                if(itemsSp.getSelectedItemPosition() == 0){
                    //please select
                    otherItemLl.setVisibility(View.GONE);
                    itemLl.setVisibility(View.GONE);
                    updateBtLl.setVisibility(View.GONE);

                }else if(itemsSp.getSelectedItemPosition() == 1) {
                    //other
                    otherItemLl.setVisibility(View.VISIBLE);
                    itemLl.setVisibility(View.GONE);
                    updateBtLl.setVisibility(View.VISIBLE);
                }else {
                    //items
                    otherItemLl.setVisibility(View.GONE);
                    itemLl.setVisibility(View.VISIBLE);
                    updateBtLl.setVisibility(View.VISIBLE);

                    InventoryMaster master = ProjectConstants.invNameMasterMap.get(itemsSp.getSelectedItem().toString().trim());
                    itemNameTv.setText(master.getName());
                    itemShortDescTv.setText(master.getShortDescription());
                    itemDescTv.setText(master.getDescription());
                    itemSaltsTv.setText(master.getSalts());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                otherItemLl.setVisibility(View.GONE);
                itemLl.setVisibility(View.GONE);
                updateBtLl.setVisibility(View.GONE);
            }
        });

        updateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemsSp.getSelectedItemPosition() == 0){
                    //please select
                    Toast.makeText(context, getString(R.string.please_select_inv_to_be_updated), Toast.LENGTH_SHORT).show();
                }else if(itemsSp.getSelectedItemPosition() == 1){
                    //other
                    if(checkNulls()){
                        UpdateInventoryRequestPojo u = new UpdateInventoryRequestPojo();
                        u.setItemName(itemNameEt.getText().toString().trim());
                        u.setShortDesc(itemShortDescEt.getText().toString().trim());
                        u.setItemDesc(itemDescEt.getText().toString().trim());
                        u.setItemSalts(itemSaltsEt.getText().toString().trim());
                        u.setQty(Integer.parseInt(otherQtyEt.getText().toString().trim()));
                        u.setWorkplaceId(Integer.parseInt(workplaceId));
                        u.setItemId(-1);
                        callUpdateInvApi(u);
                    }

                }else {
                    if(itemQtyEt.getText() != null && itemQtyEt.getText().toString().length()>0 &&
                            Integer.parseInt(itemQtyEt.getText().toString())>0){
                        InventoryMaster master = ProjectConstants.invNameMasterMap
                                            .get(itemsSp.getSelectedItem().toString().trim());
                        UpdateInventoryRequestPojo u = new UpdateInventoryRequestPojo();
                        u.setItemDesc(master.getDescription());
                        u.setItemId(master.getId());
                        u.setItemName(master.getName());
                        u.setItemSalts(master.getSalts());
                        u.setWorkplaceId(Integer.parseInt(workplaceId));
                        u.setQty(Integer.parseInt(itemQtyEt.getText().toString()));
                        u.setShortDesc(master.getShortDescription());
                        callUpdateInvApi(u);

                    }else {
                        Toast.makeText(context,getString(R.string.please_enter_qty), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void callUpdateInvApi(UpdateInventoryRequestPojo req){
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){

        }
        System.out.println("Update inv req -> {"+req.toString()+"}");
        InventoryManagementApi api = RetrofitBuilder.createPostLoginRetrofit().create(InventoryManagementApi.class);
        Call<String> call = api.updateDistInv(req);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){

                }
                if(response.body() != null && response.body().equalsIgnoreCase("success")){
                    Toast.makeText(context, getString(R.string.item_updated), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DashboardActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }else {
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

    private  Boolean checkNulls(){

        if(!(itemNameEt.getText() != null && itemNameEt.getText().toString().length()>0)){
            Toast.makeText(context, getString(R.string.please_enter_inv_name), Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!(itemShortDescEt.getText() != null && itemShortDescEt.getText().toString().length()>0)){
            Toast.makeText(context,getString(R.string.please_enter_short_desc), Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!(itemDescEt.getText() != null && itemDescEt.getText().toString().length()>0)){
            Toast.makeText(context, getString(R.string.please_enter_desc), Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!(itemSaltsEt.getText() != null && itemSaltsEt.getText().toString().length()>0)){
            Toast.makeText(context, getString(R.string.please_enter_salts), Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!(otherQtyEt.getText() != null && otherQtyEt.getText().toString().length()>0 &&
                Integer.parseInt(otherQtyEt.getText().toString())>0)){
            Toast.makeText(context, getString(R.string.please_enter_qty), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}