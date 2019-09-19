package com.ats.patna_fr_tray_mgmt.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.activity.BalanceTrayActivity;
import com.ats.patna_fr_tray_mgmt.activity.TrayActivity;
import com.ats.patna_fr_tray_mgmt.bean.Info;
import com.ats.patna_fr_tray_mgmt.bean.TrayDetails;
import com.ats.patna_fr_tray_mgmt.bean.TrayMgtDetailInTray;
import com.ats.patna_fr_tray_mgmt.common.CommonDialog;
import com.ats.patna_fr_tray_mgmt.constants.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraySubmitAdapter extends RecyclerView.Adapter<TraySubmitAdapter.MyViewHolder> {

    private ArrayList<TrayDetails> trayList;
    private Context context;
    private int tranId;

  /*  public TraySubmitAdapter(ArrayList<TrayDetails> trayList, Context context) {
        this.trayList = trayList;
        this.context = context;
    }*/

    public TraySubmitAdapter(ArrayList<TrayDetails> trayList, Context context, int tranId) {
        this.trayList = trayList;
        this.context = context;
        this.tranId = tranId;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout llStatus1, llStatus2, llStatus3, llStatus4;
        public TextView tvStatus1_Id, tvStatus1_Date, tvStatus1_Small, tvStatus1_Big, tvStatus1_Large;
        // public TextView tvStatus2_Id, tvStatus2_Date1, tvStatus2_Small, tvStatus2_Big, tvStatus2_Large, tvStatus2_RetDate, tvStatus2_BalSmall, tvStatus2_BalBig, tvStatus2_BalLarge;
        // public EditText edStatus2_Small, edStatus2_Big, edStatus2_Large;
        public TextView tvStatus3_Id, tvStatus3_Date1, tvStatus3_Small1, tvStatus3_Big1, tvStatus3_Large1, tvStatus3_Date2, tvStatus3_Small2, tvStatus3_Big2, tvStatus3_Large2, tvStatus3_BalSmall, tvStatus3_BalBig, tvStatus3_BalLarge, tvStatus3_RetDate;
        public EditText edStatus3_Small, edStatus3_Big, edStatus3_Large;
        //  public TextView tvStatus4_Id, tvStatus4_Date1, tvStatus4_Small1, tvStatus4_Big1, tvStatus4_Large1, tvStatus4_Date2, tvStatus4_Small2, tvStatus4_Big2, tvStatus4_Large2, tvStatus4_Date3, tvStatus4_Small3, tvStatus4_Big3, tvStatus4_Large3, tvStatus4_BalSmall, tvStatus4_BalBig, tvStatus4_BalLarge, tvStatus4_RetDate;
        //  public EditText edStatus4_Small, edStatus4_Big, edStatus4_Large;
        private TextView tvSubmit, tvSubmit3;


        public MyViewHolder(View view) {
            super(view);
            llStatus1 = view.findViewById(R.id.llStatus1);
            // llStatus2 = view.findViewById(R.id.llStatus2);
            llStatus3 = view.findViewById(R.id.llStatus3);
            //  llStatus4 = view.findViewById(R.id.llStatus4);

            tvStatus1_Id = view.findViewById(R.id.tvStatus1_Id);
            tvStatus1_Date = view.findViewById(R.id.tvStatus1_Date);
            tvStatus1_Small = view.findViewById(R.id.tvStatus1_Small);
            tvStatus1_Big = view.findViewById(R.id.tvStatus1_Big);
            tvStatus1_Large = view.findViewById(R.id.tvStatus1_Large);

         /*   tvStatus2_Id = view.findViewById(R.id.tvStatus2_Id);
            tvStatus2_Date1 = view.findViewById(R.id.tvStatus2_Date1);
            tvStatus2_Small = view.findViewById(R.id.tvStatus2_Small);
            tvStatus2_Big = view.findViewById(R.id.tvStatus2_Big);
            tvStatus2_Large = view.findViewById(R.id.tvStatus2_Large);
            tvStatus2_RetDate = view.findViewById(R.id.tvStatus2_RetDate);
            tvStatus2_BalSmall = view.findViewById(R.id.tvStatus2_BalSmall);
            tvStatus2_BalBig = view.findViewById(R.id.tvStatus2_BalBig);
            tvStatus2_BalLarge = view.findViewById(R.id.tvStatus2_BalLarge);
            tvStatus2_RetDate = view.findViewById(R.id.tvStatus2_RetDate);

            edStatus2_Small = view.findViewById(R.id.edStatus2_Small);
            edStatus2_Big = view.findViewById(R.id.edStatus2_Big);
            edStatus2_Large = view.findViewById(R.id.edStatus2_Large);
*/
            tvStatus3_Id = view.findViewById(R.id.tvStatus3_Id);
            tvStatus3_Date1 = view.findViewById(R.id.tvStatus3_Date1);
            tvStatus3_Small1 = view.findViewById(R.id.tvStatus3_Small1);
            tvStatus3_Big1 = view.findViewById(R.id.tvStatus3_Big1);
            tvStatus3_Large1 = view.findViewById(R.id.tvStatus3_Large1);
            // tvStatus3_Date2 = view.findViewById(R.id.tvStatus3_Date2);
            tvStatus3_Small2 = view.findViewById(R.id.tvStatus3_Small2);
            tvStatus3_Big2 = view.findViewById(R.id.tvStatus3_Big2);
            tvStatus3_Large2 = view.findViewById(R.id.tvStatus3_Large2);
            tvStatus3_BalSmall = view.findViewById(R.id.tvStatus3_BalSmall);
            tvStatus3_BalBig = view.findViewById(R.id.tvStatus3_BalBig);
            tvStatus3_BalLarge = view.findViewById(R.id.tvStatus3_BalLarge);
            tvStatus3_RetDate = view.findViewById(R.id.tvStatus3_RetDate);

            edStatus3_Small = view.findViewById(R.id.edStatus3_Small);
            edStatus3_Big = view.findViewById(R.id.edStatus3_Big);
            edStatus3_Large = view.findViewById(R.id.edStatus3_Large);

            edStatus3_Small.setImeOptions(EditorInfo.IME_ACTION_DONE);
            edStatus3_Big.setImeOptions(EditorInfo.IME_ACTION_DONE);
            edStatus3_Large.setImeOptions(EditorInfo.IME_ACTION_DONE);


        /*    tvStatus4_Id = view.findViewById(R.id.tvStatus4_Id);
            tvStatus4_Date1 = view.findViewById(R.id.tvStatus4_Date1);
            tvStatus4_Small1 = view.findViewById(R.id.tvStatus4_Small1);
            tvStatus4_Big1 = view.findViewById(R.id.tvStatus4_Big1);
            tvStatus4_Large1 = view.findViewById(R.id.tvStatus4_Large1);
            tvStatus4_Date2 = view.findViewById(R.id.tvStatus4_Date2);
            tvStatus4_Small2 = view.findViewById(R.id.tvStatus4_Small2);
            tvStatus4_Big2 = view.findViewById(R.id.tvStatus4_Big2);
            tvStatus4_Large2 = view.findViewById(R.id.tvStatus4_Large2);
            tvStatus4_Date3 = view.findViewById(R.id.tvStatus4_Date3);
            tvStatus4_Small3 = view.findViewById(R.id.tvStatus4_Small3);
            tvStatus4_Big3 = view.findViewById(R.id.tvStatus4_Big3);
            tvStatus4_Large3 = view.findViewById(R.id.tvStatus4_Large3);
            tvStatus4_BalSmall = view.findViewById(R.id.tvStatus4_BalSmall);
            tvStatus4_BalBig = view.findViewById(R.id.tvStatus4_BalBig);
            tvStatus4_BalLarge = view.findViewById(R.id.tvStatus4_BalLarge);
            tvStatus4_RetDate = view.findViewById(R.id.tvStatus4_RetDate);

            edStatus4_Small = view.findViewById(R.id.edStatus4_Small);
            edStatus4_Big = view.findViewById(R.id.edStatus4_Big);
            edStatus4_Large = view.findViewById(R.id.edStatus4_Large);*/

            tvSubmit = view.findViewById(R.id.tvTray_Submit);
            tvSubmit3 = view.findViewById(R.id.tvTray3_Submit);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_tray_submit, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final TrayDetails model = trayList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        if (model.getTrayStatus() == 1) {
            holder.llStatus1.setVisibility(View.VISIBLE);

            holder.tvStatus1_Date.setText("" + model.getOuttrayDate());
            holder.tvStatus1_Small.setText("" + model.getOuttraySmall());
            holder.tvStatus1_Big.setText("" + model.getOuttrayBig());
            holder.tvStatus1_Large.setText("" + model.getOuttrayLead());

        } else {
            holder.llStatus1.setVisibility(View.GONE);
        }

       /* if (model.getTrayStatus() == 2) {
            holder.llStatus2.setVisibility(View.VISIBLE);

            holder.tvStatus2_Date1.setText("" + model.getOuttrayDate());
            holder.tvStatus2_Small.setText("" + model.getOuttraySmall());
            holder.tvStatus2_Big.setText("" + model.getOuttrayBig());
            holder.tvStatus2_Large.setText("" + model.getOuttrayLead());

            holder.tvStatus2_RetDate.setText("" + sdf.format(Calendar.getInstance().getTimeInMillis()));

            holder.tvStatus2_BalSmall.setText("" + model.getBalanceSmall());
            holder.tvStatus2_BalBig.setText("" + model.getBalanceBig());
            holder.tvStatus2_BalLarge.setText("" + model.getBalanceLead());

        } else {
            holder.llStatus2.setVisibility(View.GONE);
        }

        if (model.getTrayStatus() == 3) {
            holder.llStatus3.setVisibility(View.VISIBLE);

            holder.tvStatus3_Date1.setText("" + model.getOuttrayDate());
            holder.tvStatus3_Small1.setText("" + model.getOuttraySmall());
            holder.tvStatus3_Big1.setText("" + model.getOuttrayBig());
            holder.tvStatus3_Large1.setText("" + model.getOuttrayLead());

            holder.tvStatus3_Date2.setText("" + model.getIntrayDate());
            holder.tvStatus3_Small2.setText("" + model.getIntraySmall());
            holder.tvStatus3_Big2.setText("" + model.getIntrayBig());
            holder.tvStatus3_Large2.setText("" + model.getIntrayLead());

            holder.tvStatus3_BalSmall.setText("" + model.getBalanceSmall());
            holder.tvStatus3_BalBig.setText("" + model.getBalanceBig());
            holder.tvStatus3_BalLarge.setText("" + model.getBalanceLead());

            holder.tvStatus3_RetDate.setText("" + sdf.format(Calendar.getInstance().getTimeInMillis()));

        } else {
            holder.llStatus3.setVisibility(View.GONE);
        }

        if (model.getTrayStatus() == 4) {
            holder.llStatus4.setVisibility(View.VISIBLE);

            holder.tvStatus4_Date1.setText("" + model.getOuttrayDate());
            holder.tvStatus4_Small1.setText("" + model.getOuttraySmall());
            holder.tvStatus4_Big1.setText("" + model.getOuttrayBig());
            holder.tvStatus4_Large1.setText("" + model.getOuttrayLead());

            holder.tvStatus4_Date2.setText("" + model.getIntrayDate());
            holder.tvStatus4_Small2.setText("" + model.getIntraySmall());
            holder.tvStatus4_Big2.setText("" + model.getIntrayBig());
            holder.tvStatus4_Large2.setText("" + model.getIntrayLead());

            holder.tvStatus4_Date3.setText("" + model.getIntrayDate1());
            holder.tvStatus4_Small3.setText("" + model.getIntraySmall1());
            holder.tvStatus4_Big3.setText("" + model.getIntrayBig1());
            holder.tvStatus4_Large3.setText("" + model.getIntrayLead1());

            holder.tvStatus4_BalSmall.setText("" + model.getBalanceSmall());
            holder.tvStatus4_BalBig.setText("" + model.getBalanceBig());
            holder.tvStatus4_BalLarge.setText("" + model.getBalanceLead());

            holder.tvStatus4_RetDate.setText("" + sdf.format(Calendar.getInstance().getTimeInMillis()));

        } else {
            holder.llStatus4.setVisibility(View.GONE);
        }*/


        if (model.getTrayStatus() > 1) {
            holder.llStatus3.setVisibility(View.GONE);

            holder.tvStatus3_Date1.setText("" + model.getOuttrayDate());
            holder.tvStatus3_Small1.setText("" + model.getOuttraySmall());
            holder.tvStatus3_Big1.setText("" + model.getOuttrayBig());
            holder.tvStatus3_Large1.setText("" + model.getOuttrayLead());

            //holder.tvStatus3_Date2.setText("" + model.getIntrayDate());
            holder.tvStatus3_Small2.setText("" + (model.getOuttraySmall() - model.getBalanceSmall()));
            holder.tvStatus3_Big2.setText("" + (model.getOuttrayBig() - model.getBalanceBig()));
            holder.tvStatus3_Large2.setText("" + (model.getOuttrayLead() - model.getBalanceLead()));

            holder.tvStatus3_BalSmall.setText("" + model.getBalanceSmall());
            holder.tvStatus3_BalBig.setText("" + model.getBalanceBig());
            holder.tvStatus3_BalLarge.setText("" + model.getBalanceLead());

            holder.tvStatus3_RetDate.setText("" + sdf.format(Calendar.getInstance().getTimeInMillis()));

        } else {
            holder.llStatus3.setVisibility(View.GONE);
        }


        holder.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("SUBMIT", "-----------------------------------  CLICKED");

                if (model.getTrayStatus() == 1) {

                    receiveTray(model.getTranDetailId());
                    //insertTrayIn(model.getTranDetailId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

                }
            }
        });


        holder.tvSubmit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                if (model.getTrayStatus() > 1 && model.getTrayStatus() <= 3) {

                    if (holder.edStatus3_Small.getText().toString().isEmpty()) {
                        holder.edStatus3_Small.setError("Required");
                    } else if (Integer.parseInt(holder.edStatus3_Small.getText().toString().trim()) > model.getBalanceSmall()) {
                        holder.edStatus3_Small.setError("Invalid");
                    } else if (holder.edStatus3_Big.getText().toString().isEmpty()) {
                        holder.edStatus3_Big.setError("Required");
                        holder.edStatus3_Small.setError(null);
                    } else if (Integer.parseInt(holder.edStatus3_Big.getText().toString().trim()) > model.getBalanceBig()) {
                        holder.edStatus3_Big.setError("Invalid");
                        holder.edStatus3_Small.setError(null);
                    } else if (holder.edStatus3_Large.getText().toString().isEmpty()) {
                        holder.edStatus3_Large.setError("Required");
                        holder.edStatus3_Big.setError(null);
                    } else if (Integer.parseInt(holder.edStatus3_Large.getText().toString().trim()) > model.getBalanceLead()) {
                        holder.edStatus3_Large.setError("Invalid");
                        holder.edStatus3_Big.setError(null);
                    } else if (Integer.parseInt(holder.edStatus3_Small.getText().toString().trim()) == 0 && Integer.parseInt(holder.edStatus3_Big.getText().toString().trim()) == 0 && Integer.parseInt(holder.edStatus3_Large.getText().toString().trim()) == 0) {
                        //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    } else {

                        holder.edStatus3_Small.setError(null);
                        holder.edStatus3_Big.setError(null);
                        holder.edStatus3_Large.setError(null);

                        int small = Integer.parseInt(holder.edStatus3_Small.getText().toString().trim());
                        int big = Integer.parseInt(holder.edStatus3_Big.getText().toString().trim());
                        int lead = Integer.parseInt(holder.edStatus3_Large.getText().toString().trim());

                        TrayMgtDetailInTray trayMgtDetailInTray = new TrayMgtDetailInTray(0, model.getTranDetailId(), model.getTranId(), model.getFrId(), sdf.format(Calendar.getInstance().getTimeInMillis()), 0, big, lead, tranId, small);

                        if (model.getTrayStatus() == 2) {

                            insertTrayIn(0, model.getTranDetailId(), 0, small, big, lead, 0, 0, 0, 0, 0, trayMgtDetailInTray);

                        } else if (model.getTrayStatus() == 3) {

                            insertTrayIn(0, 0, model.getTranDetailId(), 0, 0, 0, 0, small, big, lead, 0, trayMgtDetailInTray);
                        }


                    }

                } else if (model.getTrayStatus() == 4) {
                    if (holder.edStatus3_Small.getText().toString().isEmpty()) {
                        holder.edStatus3_Small.setError("Required");
                    } else if (Integer.parseInt(holder.edStatus3_Small.getText().toString().trim()) > model.getBalanceSmall()) {
                        holder.edStatus3_Small.setError("Invalid");
                    } else if (holder.edStatus3_Big.getText().toString().isEmpty()) {
                        holder.edStatus3_Big.setError("Required");
                        holder.edStatus3_Small.setError(null);
                    } else if (Integer.parseInt(holder.edStatus3_Big.getText().toString().trim()) > model.getBalanceBig()) {
                        holder.edStatus3_Big.setError("Invalid");
                        holder.edStatus3_Small.setError(null);
                    } else if (holder.edStatus3_Large.getText().toString().isEmpty()) {
                        holder.edStatus3_Large.setError("Required");
                        holder.edStatus3_Big.setError(null);
                    } else if (Integer.parseInt(holder.edStatus3_Large.getText().toString().trim()) > model.getBalanceLead()) {
                        holder.edStatus3_Large.setError("Invalid");
                        holder.edStatus3_Big.setError(null);
                    } else if (Integer.parseInt(holder.edStatus3_Small.getText().toString().trim()) == 0 && Integer.parseInt(holder.edStatus3_Big.getText().toString().trim()) == 0 && Integer.parseInt(holder.edStatus3_Large.getText().toString().trim()) == 0) {
                        //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    }else {

                        holder.edStatus3_Small.setError(null);
                        holder.edStatus3_Big.setError(null);
                        holder.edStatus3_Large.setError(null);

                        int small = Integer.parseInt(holder.edStatus3_Small.getText().toString().trim());
                        int big = Integer.parseInt(holder.edStatus3_Big.getText().toString().trim());
                        int lead = Integer.parseInt(holder.edStatus3_Large.getText().toString().trim());

                        int resSmall = model.getBalanceSmall() - small;
                        int resBig = model.getBalanceBig() - big;
                        int resLead = model.getBalanceLead() - lead;

                        int status = 4;
                        if (resSmall == 0 && resBig == 0 && resLead == 0) {
                            status = 5;
                        } else {
                            status = 4;
                        }

                        TrayMgtDetailInTray trayMgtDetailInTray = new TrayMgtDetailInTray(0, model.getTranDetailId(), model.getTranId(), model.getFrId(), sdf.format(Calendar.getInstance().getTimeInMillis()), 0, big, lead, tranId, small);

                        updateBalTray(model.getTranDetailId(), resSmall, resBig, resLead, status, trayMgtDetailInTray);


                    }
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return trayList.size();
    }

    //--------------------------------------------

    public void insertTrayIn(int header1, int header2, int header3, int small, int big, int large, int xl, int small1, int big1, int large1, int xl1, final TrayMgtDetailInTray trayMgtDetailInTray) {

        if (Constants.isOnline(context)) {
            final CommonDialog commonDialog = new CommonDialog(context, "Loading", "Please Wait...");
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
                                Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                                Log.e("Tray : Submit", "   ERROR---" + info.getMessage());
                            } else {

                                commonDialog.dismiss();
                                // Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                saveTrayToNewTable(trayMgtDetailInTray);

                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                            Log.e("Tray : Submit", "   NULL---");

                        }

                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                        Log.e("Tray : Submit", "   Exception---" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                    Log.e("Tray : Submit", "   ONFailure---" + t.getMessage());
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    public void updateBalTray(int id, int small, int big, int lid, int status, final TrayMgtDetailInTray trayMgtDetailInTray) {

        Log.e("SMALL = " + small, "            BIG = " + big + "            LID = " + lid + "         STATUS = " + status);

        if (Constants.isOnline(context)) {
            final CommonDialog commonDialog = new CommonDialog(context, "Loading", "Please Wait...");
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
                                Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                                Log.e("Tray : Submit", "   ERROR---" + info.getMessage());
                            } else {

                                commonDialog.dismiss();
                                // Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                saveTrayToNewTable(trayMgtDetailInTray);

                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                            Log.e("Tray : Submit", "   NULL---");

                        }

                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                        Log.e("Tray : Submit", "   Exception---" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                    Log.e("Tray : Submit", "   ONFailure---" + t.getMessage());
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    public void saveTrayToNewTable(TrayMgtDetailInTray trayMgtDetailInTray) {

        Log.e("BEAN = ", "" + trayMgtDetailInTray);

        if (Constants.isOnline(context)) {
            final CommonDialog commonDialog = new CommonDialog(context, "Loading", "Please Wait...");
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
                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                TrayActivity activity = (TrayActivity) context;
                                activity.finish();

                                Intent intent = new Intent(context, TrayActivity.class);
                                intent.putExtra("tranId", tranId);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(intent);


                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                            Log.e("Tray : Submit", "   NULL---");

                        }

                    } catch (
                            Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                        Log.e("Tray : Submit", "   Exception---" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<TrayMgtDetailInTray> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                    Log.e("Tray : Submit", "   ONFailure---" + t.getMessage());
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    public void receiveTray(int detailId) {

        if (Constants.isOnline(context)) {
            final CommonDialog commonDialog = new CommonDialog(context, "Loading", "Please Wait...");
            commonDialog.show();

            final Call<TrayDetails> infoCall = Constants.myInterface.receiveTray(detailId);
            infoCall.enqueue(new Callback<TrayDetails>() {
                @Override
                public void onResponse(Call<TrayDetails> call, Response<TrayDetails> response) {
                    try {
                        if (response.body() != null) {
                            TrayDetails model = response.body();
                            if (model != null) {

                                commonDialog.dismiss();
                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                TrayActivity activity = (TrayActivity) context;
                                activity.finish();

                                Intent intent = new Intent(context, TrayActivity.class);
                                intent.putExtra("tranId", tranId);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(intent);

                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                            Log.e("Tray : Submit", "   NULL---");

                        }

                    } catch (
                            Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                        Log.e("Tray : Submit", "   Exception---" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<TrayDetails> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(context, "Unable To Process", Toast.LENGTH_SHORT).show();
                    Log.e("Tray : Submit", "   ONFailure---" + t.getMessage());
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


}
