package com.ats.patna_fr_tray_mgmt.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.adapter.BalanceTrayAdapter;
import com.ats.patna_fr_tray_mgmt.adapter.FrRouteAdapter;
import com.ats.patna_fr_tray_mgmt.adapter.ReceiveTrayAdapter;
import com.ats.patna_fr_tray_mgmt.adapter.ReturnTrayAdapter;
import com.ats.patna_fr_tray_mgmt.adapter.TraySubmitAdapter;
import com.ats.patna_fr_tray_mgmt.bean.FrHomeData;
import com.ats.patna_fr_tray_mgmt.bean.Franchisee;
import com.ats.patna_fr_tray_mgmt.bean.Info;
import com.ats.patna_fr_tray_mgmt.bean.ReceivedTrayList;
import com.ats.patna_fr_tray_mgmt.bean.ReturnTrayList;
import com.ats.patna_fr_tray_mgmt.bean.RouteWiseData;
import com.ats.patna_fr_tray_mgmt.bean.TrayDetails;
import com.ats.patna_fr_tray_mgmt.bean.TrayMgtDetailInTray;
import com.ats.patna_fr_tray_mgmt.common.CommonDialog;
import com.ats.patna_fr_tray_mgmt.constants.Constants;
import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrayActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDay1_Date, tvDay2_Date1, tvDay2_Date2, tvDay3_Date1, tvDay3_Date2, tvDay3_Date3,
            tvDay2_Small, tvDay2_Big, tvDay2_Large, tvDay2_XL,
            tvDay3_Small1, tvDay3_Big1, tvDay3_Large1, tvDay3_XL1, tvDay3_Small2, tvDay3_Big2, tvDay3_Large2, tvDay3_XL2,
            tvSubmit, tvDay1_Small, tvDay1_Big, tvDay1_Large, tvDay1_XL,
            tvDay1_Id, tvDay2_Id, tvDay3_Id, tvTotalBal, tvBalSmall, tvBalBig, tvBalLarge;

    private EditText edDay2_Small, edDay2_Big, edDay2_Large, edDay2_XL,
            edDay3_Small, edDay3_Big, edDay3_Large, edDay3_XL;

    private LinearLayout llDay1, llDay2, llDay3, llBottom;

    int frId, tranId;
    String todaysDate;

    ArrayList<TrayDetails> day1Array = new ArrayList<>();
    ArrayList<TrayDetails> day2Array = new ArrayList<>();
    ArrayList<TrayDetails> day3Array = new ArrayList<>();

    TraySubmitAdapter adapter;
    ArrayList<TrayDetails> trayList = new ArrayList<>();
    private RecyclerView recyclerView, rvReceiveTray, rvReturnTray;
    private SwipeRefreshLayout swipeRefreshLayout;

    private TextView tvOpenSmall, tvOpenLead, tvOpenBig, tvCBalSmall, tvCBalLead, tvCBalBig;

    private FrHomeData frHomeData;

    MenuItem itemReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tray);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setTitle("Tray");

        tvDay1_Date = findViewById(R.id.tvTray_Day1_Date);
        tvDay2_Date1 = findViewById(R.id.tvTray_Day2_Date1);
        tvDay2_Date2 = findViewById(R.id.tvTray_Day2_Date2);
        tvDay3_Date1 = findViewById(R.id.tvTray_Day3_Date1);
        tvDay3_Date2 = findViewById(R.id.tvTray_Day3_Date2);
        tvDay3_Date3 = findViewById(R.id.tvTray_Day3_Date3);
        tvDay2_Small = findViewById(R.id.tvTray_Day2_Small);
        tvDay2_Big = findViewById(R.id.tvTray_Day2_Big);
        tvDay2_Large = findViewById(R.id.tvTray_Day2_Large);
        tvDay2_XL = findViewById(R.id.tvTray_Day2_XL);
        tvDay3_Small1 = findViewById(R.id.tvTray_Day3_Small1);
        tvDay3_Big1 = findViewById(R.id.tvTray_Day3_Big1);
        tvDay3_Large1 = findViewById(R.id.tvTray_Day3_Large1);
        tvDay3_XL1 = findViewById(R.id.tvTray_Day3_XL1);
        tvDay3_Small2 = findViewById(R.id.tvTray_Day3_Small2);
        tvDay3_Big2 = findViewById(R.id.tvTray_Day3_Big2);
        tvDay3_Large2 = findViewById(R.id.tvTray_Day3_Large2);
        tvDay3_XL2 = findViewById(R.id.tvTray_Day3_XL2);
        tvSubmit = findViewById(R.id.tvTray_Submit);
        tvDay1_Small = findViewById(R.id.tvTray_Day1_Small);
        tvDay1_Big = findViewById(R.id.tvTray_Day1_Big);
        tvDay1_Large = findViewById(R.id.tvTray_Day1_Large);
        tvDay1_XL = findViewById(R.id.tvTray_Day1_XL);

        tvDay1_Id = findViewById(R.id.tvTray_Day1_Id);
        tvDay2_Id = findViewById(R.id.tvTray_Day2_Id);
        tvDay3_Id = findViewById(R.id.tvTray_Day3_Id);

        tvOpenSmall = findViewById(R.id.tvOpenSmall);
        tvOpenLead = findViewById(R.id.tvOpenLead);
        tvOpenBig = findViewById(R.id.tvOpenBig);

        tvCBalSmall = findViewById(R.id.tvCBalSmall);
        tvCBalLead = findViewById(R.id.tvCBalLead);
        tvCBalBig = findViewById(R.id.tvCBalBig);

        recyclerView = findViewById(R.id.recyclerView);
        rvReceiveTray = findViewById(R.id.rvReceiveTray);
        rvReturnTray = findViewById(R.id.rvReturnTray);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);


        edDay2_Small = findViewById(R.id.edTray_Day2_Small);
        edDay2_Big = findViewById(R.id.edTray_Day2_Big);
        edDay2_Large = findViewById(R.id.edTray_Day2_Large);
        edDay2_XL = findViewById(R.id.edTray_Day2_XL);
        edDay3_Small = findViewById(R.id.edTray_Day3_Small);
        edDay3_Big = findViewById(R.id.edTray_Day3_Big);
        edDay3_Large = findViewById(R.id.edTray_Day3_Large);
        edDay3_XL = findViewById(R.id.edTray_Day3_XL);

        llDay1 = findViewById(R.id.llTray_Day1);
        llDay2 = findViewById(R.id.llTray_Day2);
        llDay3 = findViewById(R.id.llTray_Day3);

        tvTotalBal = findViewById(R.id.tvTotalBal);
        tvBalSmall = findViewById(R.id.tvBalSmall);
        tvBalBig = findViewById(R.id.tvBalBig);
        tvBalLarge = findViewById(R.id.tvBalLarge);

        llBottom = findViewById(R.id.llBottom);
        llBottom.setOnClickListener(this);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.MY_PREF, MODE_PRIVATE);
        Gson gson = new Gson();
        String json2 = pref.getString("franchise", "");
        Franchisee userBean = gson.fromJson(json2, Franchisee.class);
        Log.e("User Bean : ", "---------------" + userBean);

        try {
            if (userBean != null) {
                frId = userBean.getFrId();
                tranId = getIntent().getIntExtra("tranId", 0);
                // getServerDate();
                //getTrayDetails(frId, 0);
            } else {
                // startActivity(new Intent(TrayActivity.this, LoginActivity.class));
                //  finish();
            }
        } catch (Exception e) {
            frId = 0;
        }

        tvSubmit.setOnClickListener(this);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String date = sdf.format(Calendar.getInstance().getTimeInMillis());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    if (frId > 0) {
                        getServerDate();
                        getTrayDetails(frId, 0);
                        getBalTraySum(frId, 0);

                        getFrHomeData(frId, date);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void getTrayDetails(int frId, int isSameDay) {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<TrayDetails>> trayListCall = Constants.myInterface.getTrayDetailsByFranchise(frId, isSameDay);
            trayListCall.enqueue(new Callback<ArrayList<TrayDetails>>() {
                @Override
                public void onResponse(Call<ArrayList<TrayDetails>> call, Response<ArrayList<TrayDetails>> response) {
                    try {
                        if (response.body() != null) {
                            ArrayList<TrayDetails> data = response.body();
                            commonDialog.dismiss();
                            Log.e("TRAY : ", "Tray Details---------------------------" + data);

                            trayList.clear();
                            trayList = data;

                            adapter = new TraySubmitAdapter(trayList, TrayActivity.this, tranId);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(TrayActivity.this);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);

                            day1Array.clear();
                            day2Array.clear();
                            day3Array.clear();

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    if (data.get(i).getTrayStatus() == 1) {
                                        day1Array.add(data.get(i));
                                    } else if (data.get(i).getTrayStatus() == 2) {
                                        day2Array.add(data.get(i));
                                    } else if (data.get(i).getTrayStatus() == 3) {
                                        day3Array.add(data.get(i));
                                    }
                                }
                            } else {
                                llDay1.setVisibility(View.GONE);
                                llDay2.setVisibility(View.GONE);
                                llDay3.setVisibility(View.GONE);
                                tvSubmit.setVisibility(View.GONE);
                            }

                            Log.e("Tray Data", " DAY 1 : " + day1Array);
                            Log.e("Tray Data", " DAY 2 : " + day2Array);
                            Log.e("Tray Data", " DAY 3 : " + day3Array);

                            //-------------DAY_1-----------------------------

                            if (day1Array.size() > 0) {
                                int pos = day1Array.size() - 1;

                                llDay1.setVisibility(View.VISIBLE);


                                tvDay1_Id.setText("" + day1Array.get(pos).getTranDetailId());

                                tvDay1_Small.setText("" + day1Array.get(pos).getOuttraySmall());
                                tvDay1_Big.setText("" + day1Array.get(pos).getOuttrayBig());
                                tvDay1_Large.setText("" + day1Array.get(pos).getOuttrayLead());
                                tvDay1_XL.setText("" + day1Array.get(pos).getOuttrayExtra());

                                tvDay1_Date.setText("" + day1Array.get(pos).getOuttrayDate());
                            } else {
                                tvDay1_Id.setText("0");
                            }

                            //-------------DAY_2-----------------------------

                            if (day2Array.size() > 0) {

                                int pos = day2Array.size() - 1;

                                if (todaysDate != null) {

                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                    Log.e("InTray : ", "--------DATE----" + day2Array.get(pos).getIntrayDate());
                                    Date outTrayDate = sdf.parse(day2Array.get(pos).getOuttrayDate());
                                    Date currentDate = sdf.parse(todaysDate);

                                    Log.e("In Tray Date : ", "------" + outTrayDate);
                                    Log.e("Todays Date : ", "------" + todaysDate);
                                    Log.e("Date Match : ", "------------" + currentDate.compareTo(outTrayDate));

                                  /*  if (currentDate.compareTo(outTrayDate) == 0) {
                                        tvSubmit.setVisibility(View.GONE);
                                    } else {
                                        tvSubmit.setVisibility(View.VISIBLE);
                                    }*/
                                }


                                llDay2.setVisibility(View.VISIBLE);

                                tvDay2_Id.setText("" + day2Array.get(pos).getTranDetailId());

                                tvDay2_Small.setText("" + day2Array.get(pos).getOuttraySmall());
                                tvDay2_Big.setText("" + day2Array.get(pos).getOuttrayBig());
                                tvDay2_Large.setText("" + day2Array.get(pos).getOuttrayLead());
                                tvDay2_XL.setText("" + day2Array.get(pos).getOuttrayExtra());

                                tvDay2_Date1.setText("" + day2Array.get(pos).getOuttrayDate());

                                tvDay2_Date2.setText("" + todaysDate);

                                try {
                                    int small = day2Array.get(pos).getOuttraySmall() - (day2Array.get(pos).getIntraySmall() + day2Array.get(pos).getIntraySmall1());
                                    int big = day2Array.get(pos).getOuttrayBig() - (day2Array.get(pos).getIntrayBig() + day2Array.get(pos).getIntrayBig1());
                                    int large = day2Array.get(pos).getOuttrayLead() - (day2Array.get(pos).getIntrayLead() + day2Array.get(pos).getIntrayLead1());
                                    int xl = day2Array.get(pos).getOuttrayExtra() - (day2Array.get(pos).getIntrayExtra() + day2Array.get(pos).getIntrayExtra1());

                                    edDay2_Small.setText("" + small);
                                    edDay2_Big.setText("" + big);
                                    edDay2_Large.setText("" + large);
                                    edDay2_XL.setText("" + xl);

                                } catch (Exception e) {
                                }

                            } else {
                                tvDay2_Id.setText("0");

                                edDay2_Small.setText("0");
                                edDay2_Big.setText("0");
                                edDay2_Large.setText("0");
                                edDay2_XL.setText("0");
                            }

                            //-------------DAY_3-----------------------------

                            if (day3Array.size() > 0) {
                                int pos = day3Array.size() - 1;

                                llDay3.setVisibility(View.VISIBLE);

                                tvDay3_Id.setText("" + day3Array.get(pos).getTranDetailId());

                                tvDay3_Small1.setText("" + day3Array.get(pos).getOuttraySmall());
                                tvDay3_Big1.setText("" + day3Array.get(pos).getOuttrayBig());
                                tvDay3_Large1.setText("" + day3Array.get(pos).getOuttrayLead());
                                tvDay3_XL1.setText("" + day3Array.get(pos).getOuttrayExtra());

                                tvDay3_Small2.setText("" + day3Array.get(pos).getIntraySmall());
                                tvDay3_Big2.setText("" + day3Array.get(pos).getIntrayBig());
                                tvDay3_Large2.setText("" + day3Array.get(pos).getIntrayLead());
                                tvDay3_XL2.setText("" + day3Array.get(pos).getIntrayExtra());


                                tvDay3_Date1.setText("" + day3Array.get(pos).getOuttrayDate());

                                tvDay3_Date2.setText("" + day3Array.get(pos).getIntrayDate());

                                tvDay3_Date3.setText("" + todaysDate);

                                try {
                                    int small = day3Array.get(pos).getOuttraySmall() - (day3Array.get(pos).getIntraySmall() + day3Array.get(pos).getIntraySmall1());
                                    int big = day3Array.get(pos).getOuttrayBig() - (day3Array.get(pos).getIntrayBig() + day3Array.get(pos).getIntrayBig1());
                                    int large = day3Array.get(pos).getOuttrayLead() - (day3Array.get(pos).getIntrayLead() + day3Array.get(pos).getIntrayLead1());
                                    int xl = day3Array.get(pos).getOuttrayExtra() - (day3Array.get(pos).getIntrayExtra() + day3Array.get(pos).getIntrayExtra1());

                                    edDay3_Small.setText("" + small);
                                    edDay3_Big.setText("" + big);
                                    edDay3_Large.setText("" + large);
                                    edDay3_XL.setText("" + xl);

                                } catch (Exception e) {
                                }

                            } else {
                                tvDay3_Id.setText("0");

                                edDay3_Small.setText("0");
                                edDay3_Big.setText("0");
                                edDay3_Large.setText("0");
                                edDay3_XL.setText("0");

                            }


                        } else {
                            tvSubmit.setVisibility(View.GONE);
                            commonDialog.dismiss();
                            Log.e("TRAY : ", " NULL");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("TRAY : ", " Exception : " + e.getMessage());
                        e.printStackTrace();
                        tvSubmit.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<TrayDetails>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("TRAY : ", " onFailure : " + t.getMessage());
                    t.printStackTrace();
                    tvSubmit.setVisibility(View.GONE);

                }
            });
        } else {
            tvSubmit.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tvTray_Submit) {
            try {

                int tray2Small = Integer.parseInt(tvDay2_Small.getText().toString());
                int tray2Big = Integer.parseInt(tvDay2_Big.getText().toString());
                int tray2Large = Integer.parseInt(tvDay2_Large.getText().toString());
                int tray2XL = Integer.parseInt(tvDay2_XL.getText().toString());

                int small1 = Integer.parseInt(tvDay3_Small1.getText().toString());
                int small2 = Integer.parseInt(tvDay3_Small2.getText().toString());

                int big1 = Integer.parseInt(tvDay3_Big1.getText().toString());
                int big2 = Integer.parseInt(tvDay3_Big2.getText().toString());

                int large1 = Integer.parseInt(tvDay3_Large1.getText().toString());
                int large2 = Integer.parseInt(tvDay3_Large2.getText().toString());

                int xl1 = Integer.parseInt(tvDay3_XL1.getText().toString());
                int xl2 = Integer.parseInt(tvDay3_XL2.getText().toString());

                int tray3Small = small1 - small2;
                int tray3Big = big1 - big2;
                int tray3Large = large1 - large2;
                int tray3XL = xl1 - xl2;

//                int tray3Small = (Integer.parseInt(tvDay3_Small1.getText().toString())) - (Integer.parseInt(tvDay3_Small2.getText().toString()));
//                int tray3Big = (Integer.parseInt(tvDay3_Big1.getText().toString())) - (Integer.parseInt(tvDay3_Big2.getText().toString()));
//                int tray3Large = (Integer.parseInt(tvDay3_Large1.getText().toString())) - (Integer.parseInt(tvDay3_Big2.getText().toString()));
//                int tray3XL = (Integer.parseInt(tvDay3_XL1.getText().toString())) - (Integer.parseInt(tvDay3_Big2.getText().toString()));

                Log.e("tray2Small : ", "------------" + tray2Small);
                Log.e("tray2Big : ", "------------" + tray2Big);
                Log.e("tray2Large : ", "------------" + tray2Large);
                Log.e("tray2XL : ", "------------" + tray2XL);

                Log.e("tray3Small : ", "------------(" + tvDay3_Small1.getText().toString() + "-" + tvDay3_Small2.getText().toString() + ")=" + tray3Small);
                Log.e("tray3Big : ", "------------(" + tvDay3_Big1.getText().toString() + "-" + tvDay3_Big2.getText().toString() + ")=" + tray3Big);
                Log.e("tray3Large : ", "------------(" + tvDay3_Large1.getText().toString() + "-" + tvDay3_Large2.getText().toString() + ")=" + tray3Large);
                Log.e("tray3XL : ", "------------(" + tvDay3_XL1.getText().toString() + "-" + tvDay3_XL2.getText().toString() + ")=" + tray3XL);

                //-------------------------PARAMETERS----------------------------
                int header1 = Integer.parseInt(tvDay1_Id.getText().toString());
                int header2 = Integer.parseInt(tvDay2_Id.getText().toString());
                int header3 = Integer.parseInt(tvDay3_Id.getText().toString());

                int inTray_Small = Integer.parseInt(edDay2_Small.getText().toString());
                int inTray_Big = Integer.parseInt(edDay2_Big.getText().toString());
                int inTray_Large = Integer.parseInt(edDay2_Large.getText().toString());
                int inTray_XL = Integer.parseInt(edDay2_XL.getText().toString());

                int inTray_Small1 = Integer.parseInt(edDay3_Small.getText().toString());
                int inTray_Big1 = Integer.parseInt(edDay3_Big.getText().toString());
                int inTray_Large1 = Integer.parseInt(edDay3_Large.getText().toString());
                int inTray_XL1 = Integer.parseInt(edDay3_XL.getText().toString());

                Log.e("inTray_Small : ", "------------" + inTray_Small);
                Log.e("inTray_Big : ", "------------" + inTray_Big);
                Log.e("inTray_Large : ", "------------" + inTray_Large);
                Log.e("inTray_XL : ", "------------" + inTray_XL);

                Log.e("inTray_Small1 : ", "------------" + inTray_Small1);
                Log.e("inTray_Big1 : ", "------------" + inTray_Big1);
                Log.e("inTray_Large1 : ", "------------" + inTray_Large1);
                Log.e("inTray_XL1 : ", "------------" + inTray_XL1);


                if (inTray_Small > tray2Small) {
                    edDay2_Small.setError("Error");
                    edDay2_Small.requestFocus();
                } else if (inTray_Big > tray2Big) {
                    edDay2_Big.setError("Error");
                    edDay2_Big.requestFocus();

                    edDay2_Small.setError(null);

                } else if (inTray_Large > tray2Large) {
                    edDay2_Large.setError("Error");
                    edDay2_Large.requestFocus();

                    edDay2_Small.setError(null);
                    edDay2_Big.setError(null);

                } else if (inTray_XL > tray2XL) {
                    edDay2_XL.setError("Error");
                    edDay2_XL.requestFocus();

                    edDay2_Small.setError(null);
                    edDay2_Big.setError(null);
                    edDay2_Large.setError(null);

                } else if (inTray_Small1 > tray3Small) {
                    edDay3_Small.setError("Error");
                    edDay3_Small.requestFocus();

                    edDay2_Small.setError(null);
                    edDay2_Big.setError(null);
                    edDay2_Large.setError(null);
                    edDay2_XL.setError(null);

                } else if (inTray_Big1 > tray3Big) {
                    edDay3_Big.setError("Error");
                    edDay3_Big.requestFocus();

                    edDay2_Small.setError(null);
                    edDay2_Big.setError(null);
                    edDay2_Large.setError(null);
                    edDay2_XL.setError(null);
                    edDay3_Small.setError(null);

                } else if (inTray_Large1 > tray3Large) {
                    edDay3_Large.setError("Error");
                    edDay3_Large.requestFocus();

                    edDay2_Small.setError(null);
                    edDay2_Big.setError(null);
                    edDay2_Large.setError(null);
                    edDay2_XL.setError(null);
                    edDay3_Small.setError(null);
                    edDay3_Big.setError(null);

                } else if (inTray_XL1 > tray3XL) {
                    edDay3_XL.setError("Error");
                    edDay3_XL.requestFocus();

                    edDay2_Small.setError(null);
                    edDay2_Big.setError(null);
                    edDay2_Large.setError(null);
                    edDay2_XL.setError(null);
                    edDay3_Small.setError(null);
                    edDay3_Big.setError(null);
                    edDay3_Large.setError(null);

                } else {

                    edDay2_Small.setError(null);
                    edDay2_Big.setError(null);
                    edDay2_Large.setError(null);
                    edDay2_XL.setError(null);
                    edDay3_Small.setError(null);
                    edDay3_Big.setError(null);
                    edDay3_Large.setError(null);
                    edDay3_XL.setError(null);

                    insertTrayIn(header1, header2, header3, inTray_Small, inTray_Big, inTray_Large, inTray_XL, inTray_Small1, inTray_Big1, inTray_Large1, inTray_XL1);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (view.getId() == R.id.llBottom) {

           /* Intent intent = new Intent(TrayActivity.this, BalanceTrayActivity.class);
            intent.putExtra("frId", frId);
            startActivity(intent);*/

        }
    }

    public void insertTrayIn(int header1, int header2, int header3, int small, int big, int large, int xl, int small1, int big1, int large1, int xl1) {

        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            final Call<Info> infoCall = Constants.myInterface.saveInTray(header1, header2, big, small, large, xl, header3, big1, small1, large1, xl1);
            infoCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {
                            Info info = response.body();
                            if (info.getError()) {
                                commonDialog.dismiss();
                                Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                                Log.e("Tray : Submit", "   ERROR---" + info.getMessage());
                            } else {

                                commonDialog.dismiss();
                                Toast.makeText(TrayActivity.this, "Success", Toast.LENGTH_SHORT).show();

                                llDay1.setVisibility(View.GONE);
                                llDay2.setVisibility(View.GONE);
                                llDay3.setVisibility(View.GONE);

                                getServerDate();
                                getTrayDetails(frId, 0);
                                getBalTraySum(frId, 0);

                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                            Log.e("Tray : Submit", "   NULL---");

                        }

                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                        Log.e("Tray : Submit", "   Exception---" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                    Log.e("Tray : Submit", "   ONFailure---" + t.getMessage());
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    public void getServerDate() {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<Info> infoCall = Constants.myInterface.getServerDate();
            infoCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {
                            Info data = response.body();
                            commonDialog.dismiss();
                            Log.e("TRAY : ", "Info Date---------------------------" + data);

                            todaysDate = data.getMessage();
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
                public void onFailure(Call<Info> call, Throwable t) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_tray_report);
        item.setVisible(true);
        MenuItem itemProfile = menu.findItem(R.id.menu_profile);
        itemProfile.setVisible(false);
        itemReturn = menu.findItem(R.id.menu_tray_return);
        itemReturn.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_tray_report) {

            View vItem = findViewById(R.id.menu_tray_report);

            PopupMenu popupMenu = new PopupMenu(TrayActivity.this, vItem);
            popupMenu.getMenuInflater().inflate(R.menu.report_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.menu_report1) {
                        startActivity(new Intent(TrayActivity.this, TrayReportsActivity.class));
                    } else if (menuItem.getItemId() == R.id.menu_report2) {
                        startActivity(new Intent(TrayActivity.this, FrTrayReportEightDaysActivity.class));
                    }

                    return true;
                }
            });
            popupMenu.show();


        } else if (id == R.id.menu_profile) {
            AlertDialog.Builder builder = new AlertDialog.Builder(TrayActivity.this, R.style.AlertDialogTheme);
            builder.setTitle("Logout");
            builder.setMessage("Are You Sure You Want To Logout?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.MY_PREF, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.clear();
                    editor.commit();

                    Intent intent = new Intent(TrayActivity.this, LoginActivity.class);
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
        } else if (id == R.id.menu_tray_return) {

            int totalBalTray = 0;

            try {
                totalBalTray = Integer.parseInt(tvTotalBal.getText().toString());
            } catch (Exception e) {
                totalBalTray = 0;
            }

            if (totalBalTray > 0) {
                trayReturnDialog();
            } else {
                Toast.makeText(this, "No balance trays to return", Toast.LENGTH_SHORT).show();
            }


        }

        return super.onOptionsItemSelected(item);
    }


    public void getBalTraySum(int frId, int status) {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<TrayDetails>> trayListCall = Constants.myInterface.getBalTraySum(frId, status);
            trayListCall.enqueue(new Callback<ArrayList<TrayDetails>>() {
                @Override
                public void onResponse(Call<ArrayList<TrayDetails>> call, Response<ArrayList<TrayDetails>> response) {
                    try {
                        if (response.body() != null) {
                            ArrayList<TrayDetails> data = response.body();
                            commonDialog.dismiss();
                            Log.e("TRAY : ", "Tray Details-------------BAL--------------" + data);

                            if (data.size() > 0) {

                                tvBalSmall.setText("" + data.get(0).getBalanceSmall());
                                tvBalBig.setText("" + data.get(0).getBalanceBig());
                                tvBalLarge.setText("" + data.get(0).getBalanceLead());

                                tvTotalBal.setText("" + (data.get(0).getBalanceSmall() + data.get(0).getBalanceBig() + data.get(0).getBalanceLead()));

                            } else {

                                tvBalSmall.setText("0");
                                tvBalBig.setText("0");
                                tvBalLarge.setText("0");
                                tvTotalBal.setText("0");

                            }


                        } else {
                            commonDialog.dismiss();
                            Log.e("TRAY : ", " NULL");

                            tvBalSmall.setText("0");
                            tvBalBig.setText("0");
                            tvBalLarge.setText("0");
                            tvTotalBal.setText("0");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("TRAY : ", " Exception : " + e.getMessage());
                        e.printStackTrace();

                        tvBalSmall.setText("0");
                        tvBalBig.setText("0");
                        tvBalLarge.setText("0");
                        tvTotalBal.setText("0");
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<TrayDetails>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("TRAY : ", " onFailure : " + t.getMessage());
                    t.printStackTrace();

                    tvBalSmall.setText("0");
                    tvBalBig.setText("0");
                    tvBalLarge.setText("0");
                    tvTotalBal.setText("0");

                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    public void trayReturnDialog() {
        final Dialog openDialog = new Dialog(this);
        openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        openDialog.setContentView(R.layout.dialog_tray_return);
        openDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        Window window = openDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wlp.dimAmount = 0.75f;
        wlp.x = 100;
        wlp.y = 100;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        final EditText edSmall = openDialog.findViewById(R.id.edSmall);
        final EditText edLids = openDialog.findViewById(R.id.edLids);
        final EditText edBig = openDialog.findViewById(R.id.edBig);
        TextView tvReturn = openDialog.findViewById(R.id.tvReturn);
        TextView tvCancel = openDialog.findViewById(R.id.tvCancel);

        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int small = 0, big = 0, lid = 0, balSmall = 0, balLid = 0, balBig = 0;

                try {
                    balSmall = Integer.parseInt(tvBalSmall.getText().toString().trim());
                    balLid = Integer.parseInt(tvBalLarge.getText().toString().trim());
                    balBig = Integer.parseInt(tvBalBig.getText().toString().trim());

                } catch (Exception e) {
                    e.printStackTrace();
                }


                if (edSmall.getText().toString().isEmpty()) {
                    edSmall.setError("Required");
                } else if (Integer.parseInt(edSmall.getText().toString().trim()) > balSmall) {
                    edSmall.setError("Invalid");
                } else if (edBig.getText().toString().isEmpty()) {
                    edBig.setError("Required");
                    edSmall.setError(null);
                } else if (Integer.parseInt(edBig.getText().toString().trim()) > balBig) {
                    edBig.setError("Invalid");
                    edLids.setError(null);
                } else if (edLids.getText().toString().isEmpty()) {
                    edLids.setError("Required");
                    edBig.setError(null);
                } else if (Integer.parseInt(edLids.getText().toString().trim()) > balLid) {
                    edLids.setError("Invalid");
                    edBig.setError(null);
                } else if (Integer.parseInt(edSmall.getText().toString().trim()) == 0 && Integer.parseInt(edBig.getText().toString().trim()) == 0 && Integer.parseInt(edLids.getText().toString().trim()) == 0) {
                    //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        small = Integer.parseInt(edSmall.getText().toString().trim());
                        lid = Integer.parseInt(edLids.getText().toString().trim());
                        big = Integer.parseInt(edBig.getText().toString().trim());

                        balSmall = Integer.parseInt(tvBalSmall.getText().toString().trim());
                        balLid = Integer.parseInt(tvBalLarge.getText().toString().trim());
                        balBig = Integer.parseInt(tvBalBig.getText().toString().trim());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if (trayList != null) {

                        if (trayList.size() > 0) {
                            for (int i = 0; i < trayList.size(); i++) {

                                int flag = 0;

                                if (i == (trayList.size() - 1)) {
                                    flag = 1;
                                }

                                TrayDetails model = trayList.get(i);

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                int retSmall = 0, retBig = 0, retLid = 0;

                                if (small >= model.getBalanceSmall()) {
                                    retSmall = model.getBalanceSmall();
                                } else {
                                    retSmall = small;
                                }

                                if (lid >= model.getBalanceLead()) {
                                    retLid = model.getBalanceLead();
                                } else {
                                    retLid = lid;
                                }

                                if (big >= model.getBalanceBig()) {
                                    retBig = model.getBalanceBig();
                                } else {
                                    retBig = big;
                                }

                                if (model.getTrayStatus() > 1 && model.getTrayStatus() <= 3) {

                                    TrayMgtDetailInTray trayMgtDetailInTray = new TrayMgtDetailInTray(0, model.getTranDetailId(), model.getTranId(), model.getFrId(), sdf.format(Calendar.getInstance().getTimeInMillis()), 0, retBig, retLid, tranId, retSmall);

                                    if (model.getTrayStatus() == 2) {

                                        if (retSmall <= 0 && retLid <= 0 && retBig <= 0) {
                                            Log.e("ALL ZERO", "------------------------------ ");
                                        } else {
                                            insertTrayIn(0, model.getTranDetailId(), 0, retSmall, retBig, retLid, 0, 0, 0, 0, 0, trayMgtDetailInTray, flag);
                                        }
                                    } else if (model.getTrayStatus() == 3) {

                                        if (retSmall <= 0 && retLid <= 0 && retBig <= 0) {
                                            Log.e("ALL ZERO", "------------------------------ ");
                                        } else {
                                            insertTrayIn(0, 0, model.getTranDetailId(), 0, 0, 0, 0, retSmall, retBig, retLid, 0, trayMgtDetailInTray, flag);
                                        }
                                    }


                                } else if (model.getTrayStatus() == 4) {

                                    TrayMgtDetailInTray trayMgtDetailInTray = new TrayMgtDetailInTray(0, model.getTranDetailId(), model.getTranId(), model.getFrId(), sdf.format(Calendar.getInstance().getTimeInMillis()), 0, retBig, retLid, tranId, retSmall);

                                    if (retSmall <= 0 && retLid <= 0 && retBig <= 0) {
                                        Log.e("ALL ZERO", "------------------------------ ");
                                    } else {

                                        int resSmall = model.getBalanceSmall() - retSmall;
                                        int resBig = model.getBalanceBig() - retBig;
                                        int resLead = model.getBalanceLead() - retLid;

                                        int status = 4;
                                        if (resSmall == 0 && resBig == 0 && resLead == 0) {
                                            status = 5;
                                        } else {
                                            status = 4;
                                        }


                                        updateBalTray(model.getTranDetailId(), resSmall, resBig, resLead, status, trayMgtDetailInTray, flag);
                                    }
                                }

                                small = small - retSmall;
                                big = big - retBig;
                                lid = lid - retLid;
                            }
                        }

                    }


                    // }//else
                    openDialog.dismiss();
                }


//                try {
//                    if (frId > 0) {
//                        getServerDate();
//                        getTrayDetails(frId, 0);
//                        getBalTraySum(frId, 0);
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });


        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog.dismiss();
            }
        });


        openDialog.show();
    }


    public void insertTrayIn(int header1, int header2, int header3, int small, int big, int large, int xl, int small1, int big1, int large1, int xl1, final TrayMgtDetailInTray trayMgtDetailInTray, final int flag) {

        if (Constants.isOnline(TrayActivity.this)) {
            final CommonDialog commonDialog = new CommonDialog(TrayActivity.this, "Loading", "Please Wait...");
            commonDialog.show();

            final Call<Info> infoCall = Constants.myInterface.saveInTray(header1, header2, big, small, large, xl, header3, big1, small1, large1, xl1);
            infoCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {
                            Info info = response.body();
                            if (info.getError()) {
                                commonDialog.dismiss();
                                Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                                Log.e("Tray : Submit", "   ERROR---" + info.getMessage());
                            } else {

                                commonDialog.dismiss();
                                // Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                saveTrayToNewTable(trayMgtDetailInTray, flag);

                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                            Log.e("Tray : Submit", "   NULL---");

                        }

                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                        Log.e("Tray : Submit", "   Exception---" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                    Log.e("Tray : Submit", "   ONFailure---" + t.getMessage());
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(TrayActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    public void saveTrayToNewTable(TrayMgtDetailInTray trayMgtDetailInTray, final int flag) {

        Log.e("BEAN = ", "" + trayMgtDetailInTray);

        if (Constants.isOnline(TrayActivity.this)) {
            final CommonDialog commonDialog = new CommonDialog(TrayActivity.this, "Loading", "Please Wait...");
            commonDialog.show();

            final Call<TrayMgtDetailInTray> infoCall = Constants.myInterface.saveTrayMgtDetailInTray(trayMgtDetailInTray);
            infoCall.enqueue(new Callback<TrayMgtDetailInTray>() {
                @Override
                public void onResponse(Call<TrayMgtDetailInTray> call, Response<TrayMgtDetailInTray> response) {
                    try {
                        if (response.body() != null) {
                            TrayMgtDetailInTray model = response.body();
                            if (model != null) {

                                commonDialog.dismiss();
                                Toast.makeText(TrayActivity.this, "Success", Toast.LENGTH_SHORT).show();

                                if (flag == 1) {
                                    Intent intent = new Intent(TrayActivity.this, TrayActivity.class);
                                    intent.putExtra("tranId", tranId);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }


                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                            Log.e("Tray : Submit", "   NULL---");

                        }

                    } catch (
                            Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                        Log.e("Tray : Submit", "   Exception---" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<TrayMgtDetailInTray> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                    Log.e("Tray : Submit", "   ONFailure---" + t.getMessage());
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(TrayActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    public void updateBalTray(int id, int small, int big, int lid, int status, final TrayMgtDetailInTray trayMgtDetailInTray, final int flag) {

        Log.e("SMALL = " + small, "            BIG = " + big + "            LID = " + lid + "         STATUS = " + status);

        if (Constants.isOnline(TrayActivity.this)) {
            final CommonDialog commonDialog = new CommonDialog(TrayActivity.this, "Loading", "Please Wait...");
            commonDialog.show();

            final Call<Info> infoCall = Constants.myInterface.updateBalTray(id, big, small, lid, status);
            infoCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {
                            Info info = response.body();
                            if (info.getError()) {
                                commonDialog.dismiss();
                                Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                                Log.e("Tray : Submit", "   ERROR---" + info.getMessage());
                            } else {

                                commonDialog.dismiss();
                                // Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                saveTrayToNewTable(trayMgtDetailInTray, flag);

                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                            Log.e("Tray : Submit", "   NULL---");

                        }

                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                        Log.e("Tray : Submit", "   Exception---" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(TrayActivity.this, "Unable To Process", Toast.LENGTH_SHORT).show();
                    Log.e("Tray : Submit", "   ONFailure---" + t.getMessage());
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(TrayActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    public void getFrHomeData(int frId, String date) {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<FrHomeData> infoCall = Constants.myInterface.getFrHomeData(frId, date);
            infoCall.enqueue(new Callback<FrHomeData>() {
                @Override
                public void onResponse(Call<FrHomeData> call, Response<FrHomeData> response) {
                    try {
                        if (response.body() != null) {
                            frHomeData = response.body();
                            commonDialog.dismiss();
                            Log.e("FR ROUTE DATA : ", "Info Date---------------------------" + frHomeData);


                            int opSmall = 0, opLead = 0, opBig = 0, sumRecSmall = 0, sumRecLead = 0, sumRecBig = 0, sumRetSmall = 0, sumRetLead = 0, sumRetBig = 0;

                            if (frHomeData.getOpeningCount() != null) {
                                tvOpenSmall.setText("" + frHomeData.getOpeningCount().getSmall());
                                tvOpenLead.setText("" + frHomeData.getOpeningCount().getLead());
                                tvOpenBig.setText("" + frHomeData.getOpeningCount().getBig());

                                opSmall = frHomeData.getOpeningCount().getSmall();
                                opLead = frHomeData.getOpeningCount().getLead();
                                opBig = frHomeData.getOpeningCount().getBig();
                            }

                            if (frHomeData.getReceivedTrayList() != null) {

                                ArrayList<ReceivedTrayList> recList = new ArrayList<>();
                                for (int i = 0; i < frHomeData.getReceivedTrayList().size(); i++) {


                                    recList.add(frHomeData.getReceivedTrayList().get(i));

                                    sumRecSmall = sumRecSmall + frHomeData.getReceivedTrayList().get(i).getSmall();
                                    sumRecLead = sumRecLead + frHomeData.getReceivedTrayList().get(i).getLead();
                                    sumRecBig = sumRecBig + frHomeData.getReceivedTrayList().get(i).getBig();
                                }


                                ReceiveTrayAdapter adapter = new ReceiveTrayAdapter(recList, TrayActivity.this);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(TrayActivity.this);
                                rvReceiveTray.setLayoutManager(mLayoutManager);
                                rvReceiveTray.setItemAnimator(new DefaultItemAnimator());
                                rvReceiveTray.setAdapter(adapter);

                            }


                            if (frHomeData.getReturnTrayList() != null) {

                                for (int i = 0; i < frHomeData.getReturnTrayList().size(); i++) {
                                    if (tranId == frHomeData.getReturnTrayList().get(i).getHeaderId()) {
                                        itemReturn.setVisible(false);
                                        break;
                                    } else {
                                        itemReturn.setVisible(true);
                                    }

                                }


                            }


                            if (frHomeData.getReturnTrayList() != null) {

                                ArrayList<ReturnTrayList> retList = new ArrayList<>();
                                for (int i = 0; i < frHomeData.getReturnTrayList().size(); i++) {

                                    if (tranId == frHomeData.getReturnTrayList().get(i).getHeaderId()) {
                                        //MenuItem item=get
                                        itemReturn.setVisible(false);
                                        // break;
                                    }

                                    retList.add(frHomeData.getReturnTrayList().get(i));

                                    sumRetSmall = sumRetSmall + frHomeData.getReturnTrayList().get(i).getSmall();
                                    sumRetLead = sumRetLead + frHomeData.getReturnTrayList().get(i).getLead();
                                    sumRetBig = sumRetBig + frHomeData.getReturnTrayList().get(i).getBig();
                                }


                                ReturnTrayAdapter adapter = new ReturnTrayAdapter(retList, TrayActivity.this);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(TrayActivity.this);
                                rvReturnTray.setLayoutManager(mLayoutManager);
                                rvReturnTray.setItemAnimator(new DefaultItemAnimator());
                                rvReturnTray.setAdapter(adapter);

                            }

                            tvCBalSmall.setText("" + ((opSmall + sumRecSmall) - sumRetSmall));
                            tvCBalLead.setText("" + ((opLead + sumRecLead) - sumRetLead));
                            tvCBalBig.setText("" + ((opBig + sumRecBig) - sumRetBig));

                        } else {
                            commonDialog.dismiss();
                            Log.e("FR ROUTE DATA : ", " NULL");
                            Toast.makeText(TrayActivity.this, "No Record Found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("FR ROUTE DATA : ", " Exception : " + e.getMessage());
                        e.printStackTrace();
                        Toast.makeText(TrayActivity.this, "Unable to Process!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<FrHomeData> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("FR ROUTE DATA : ", " onFailure : " + t.getMessage());
                    t.printStackTrace();
                    Toast.makeText(TrayActivity.this, "Unable to Process!", Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getServerDate();
        getTrayDetails(frId, 0);
        getBalTraySum(frId, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(Calendar.getInstance().getTimeInMillis());
        getFrHomeData(frId, date);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TrayActivity.this, FrRouteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
