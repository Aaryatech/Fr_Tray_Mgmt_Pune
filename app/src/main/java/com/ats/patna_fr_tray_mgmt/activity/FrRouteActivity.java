package com.ats.patna_fr_tray_mgmt.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.adapter.FrRouteAdapter;
import com.ats.patna_fr_tray_mgmt.adapter.TrayReportAdapter;
import com.ats.patna_fr_tray_mgmt.bean.Franchisee;
import com.ats.patna_fr_tray_mgmt.bean.RouteWiseData;
import com.ats.patna_fr_tray_mgmt.bean.TrayDetails;
import com.ats.patna_fr_tray_mgmt.common.CommonDialog;
import com.ats.patna_fr_tray_mgmt.constants.Constants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrRouteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    int frId;

    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fr_route);
        setTitle("Vehicle List");

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.MY_PREF, MODE_PRIVATE);
        Gson gson = new Gson();
        String json2 = pref.getString("franchise", "");
        Franchisee userBean = gson.fromJson(json2, Franchisee.class);
        Log.e("User Bean : ", "---------------" + userBean);

        try {
            if (userBean != null) {
                frId = userBean.getFrId();
                // getServerDate();
                //getTrayDetails(frId, 0);
            } else {
                startActivity(new Intent(FrRouteActivity.this, LoginActivity.class));
                finish();
            }
        } catch (Exception e) {
            frId = 0;
        }


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    if (frId > 0) {
                        getFrRouteData(frId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public void getFrRouteData(int frId) {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<RouteWiseData>> infoCall = Constants.myInterface.getFrData(frId);
            infoCall.enqueue(new Callback<ArrayList<RouteWiseData>>() {
                @Override
                public void onResponse(Call<ArrayList<RouteWiseData>> call, Response<ArrayList<RouteWiseData>> response) {
                    try {
                        if (response.body() != null) {
                            ArrayList<RouteWiseData> data = response.body();
                            commonDialog.dismiss();
                            Log.e("FR ROUTE DATA : ", "Info Date---------------------------" + data);

                            FrRouteAdapter adapter = new FrRouteAdapter(data, FrRouteActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FrRouteActivity.this);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);

                        } else {
                            commonDialog.dismiss();
                            Log.e("FR ROUTE DATA : ", " NULL");
                            Toast.makeText(FrRouteActivity.this, "No Record Found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("FR ROUTE DATA : ", " Exception : " + e.getMessage());
                        e.printStackTrace();
                        Toast.makeText(FrRouteActivity.this, "Unable to Process!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<RouteWiseData>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("FR ROUTE DATA : ", " onFailure : " + t.getMessage());
                    t.printStackTrace();
                    Toast.makeText(FrRouteActivity.this, "Unable to Process!", Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getFrRouteData(frId);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(FrRouteActivity.this, R.style.AlertDialogTheme);
        builder.setTitle("Exit Application?");
        builder.setMessage("");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        MenuItem itemProfile = menu.findItem(R.id.menu_profile);
        itemProfile.setVisible(true);
        MenuItem item = menu.findItem(R.id.menu_tray_report);
        item.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_profile) {

            userInfoDialog();


        }else   if (id == R.id.menu_tray_report) {
           // startActivity(new Intent(FrRouteActivity.this, TrayReportsActivity.class));
            View vItem = findViewById(R.id.menu_tray_report);

            PopupMenu popupMenu = new PopupMenu(FrRouteActivity.this, vItem);
            popupMenu.getMenuInflater().inflate(R.menu.report_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.menu_report1) {
                        startActivity(new Intent(FrRouteActivity.this, TrayReportsActivity.class));
                    } else if (menuItem.getItemId() == R.id.menu_report2) {
                        startActivity(new Intent(FrRouteActivity.this, FrTrayReportEightDaysActivity.class));
                    }

                    return true;
                }
            });
            popupMenu.show();


        }

        return super.onOptionsItemSelected(item);
    }


    public void userInfoDialog() {

        final Dialog openDialog = new Dialog(this);
        openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        openDialog.setContentView(R.layout.custom_user_info_dialog_layout);
        openDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        Window window = openDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wlp.x = 100;
        wlp.y = 100;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        TextView tvName = openDialog.findViewById(R.id.tvUserDialog_Owner);
        TextView tvMobile = openDialog.findViewById(R.id.tvUserDialog_Mobile);
        TextView tvLogout = openDialog.findViewById(R.id.tvUserDialog_Logout);
        ImageView ivImage = openDialog.findViewById(R.id.ivUserDialog_Image);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.MY_PREF, MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        Gson gson2 = new Gson();
        String json3 = pref.getString("franchise", "");
        Franchisee userBean = gson2.fromJson(json3, Franchisee.class);
        Log.e("User Bean : ", "---------------" + userBean);
        try {
            if (userBean != null) {

                tvName.setText("" + userBean.getFrName());
                tvMobile.setText("" + userBean.getFrAddress());

                try {
                    Picasso.with(openDialog.getContext()).load(Constants.FR_IMAGE_URL + userBean.getFrImage()).error(FrRouteActivity.this.getResources().getDrawable(R.drawable.logo)).into(ivImage);
                } catch (Exception e) {

                }

                tvLogout.setPaintFlags(tvLogout.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                tvLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FrRouteActivity.this, R.style.AlertDialogTheme);
                        builder.setTitle("Logout");
                        builder.setMessage("Are You Sure You Want To Logout?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.MY_PREF, MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.clear();
                                editor.commit();

                                Intent intent = new Intent(FrRouteActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                                finish();
                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                });


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        openDialog.show();

    }


}
