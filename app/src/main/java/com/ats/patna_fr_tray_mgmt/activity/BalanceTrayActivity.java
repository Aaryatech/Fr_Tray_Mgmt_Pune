package com.ats.patna_fr_tray_mgmt.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.adapter.BalanceTrayAdapter;
import com.ats.patna_fr_tray_mgmt.bean.TrayDetails;
import com.ats.patna_fr_tray_mgmt.common.CommonDialog;
import com.ats.patna_fr_tray_mgmt.constants.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceTrayActivity extends AppCompatActivity {

    int frId;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_tray);
        setTitle("Balance Tray");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);


        frId = getIntent().getIntExtra("frId", 0);
        Log.e("FR ID : ", "--------------------- " + frId);

        getBalTrayList(frId, 4);


    }


    public void getBalTrayList(int frId, int status) {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<TrayDetails>> trayListCall = Constants.myInterface.getBalTrayList(frId, status);
            trayListCall.enqueue(new Callback<ArrayList<TrayDetails>>() {
                @Override
                public void onResponse(Call<ArrayList<TrayDetails>> call, Response<ArrayList<TrayDetails>> response) {
                    try {
                        if (response.body() != null) {
                            ArrayList<TrayDetails> data = response.body();
                            commonDialog.dismiss();
                            Log.e("TRAY : ", "Tray Details---------------------------" + data);

                            BalanceTrayAdapter adapter = new BalanceTrayAdapter(data, BalanceTrayActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(BalanceTrayActivity.this);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);

                        } else {
                            commonDialog.dismiss();
                            Log.e("TRAY : ", " NULL");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("TRAY : ", " Exception : " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<TrayDetails>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("TRAY : ", " onFailure : " + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
