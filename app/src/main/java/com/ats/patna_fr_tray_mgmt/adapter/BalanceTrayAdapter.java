package com.ats.patna_fr_tray_mgmt.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.activity.BalanceTrayActivity;
import com.ats.patna_fr_tray_mgmt.bean.Info;
import com.ats.patna_fr_tray_mgmt.bean.TrayDetails;
import com.ats.patna_fr_tray_mgmt.common.CommonDialog;
import com.ats.patna_fr_tray_mgmt.constants.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceTrayAdapter extends RecyclerView.Adapter<BalanceTrayAdapter.MyViewHolder> {


    private ArrayList<TrayDetails> trayList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate, tvSmall, tvBig, tvLarge;
        public ImageView ivUpdate;

        public MyViewHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tvDate);
            tvSmall = view.findViewById(R.id.tvSmall);
            tvBig = view.findViewById(R.id.tvBig);
            tvLarge = view.findViewById(R.id.tvLarge);
            ivUpdate = view.findViewById(R.id.ivUpdate);
        }
    }

    public BalanceTrayAdapter(ArrayList<TrayDetails> trayList, Context context) {
        this.trayList = trayList;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_balance_tray, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final TrayDetails model = trayList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            holder.tvDate.setText(sdf.format(Long.valueOf(model.getIntrayDate())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvSmall.setText("" + model.getBalanceSmall());
        holder.tvBig.setText("" + model.getBalanceBig());
        holder.tvLarge.setText("" + model.getBalanceLead());

        holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new showDialog(context, model).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return trayList.size();
    }


    public class showDialog extends Dialog {

        EditText edSmall, edBig, edLarge, edXL;
        TextView tvSubmit, tvSmall, tvBig, tvLids;
        TrayDetails model;

        public showDialog(@NonNull Context context) {
            super(context);
        }

        public showDialog(@NonNull Context context, TrayDetails tModel) {
            super(context);
            this.model = tModel;
            Log.e("MODEL ", "******************* " + model);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_update_bal_tray);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            Window window = getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.dimAmount = 0.75f;
            wlp.gravity = Gravity.CENTER;
            wlp.x = 100;
            wlp.y = 100;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);

            edSmall = findViewById(R.id.edSmall);
            edBig = findViewById(R.id.edBig);
            edLarge = findViewById(R.id.edLids);
            tvSubmit = findViewById(R.id.tvSubmit);

            tvSmall = findViewById(R.id.tvSmall);
            tvBig = findViewById(R.id.tvBig);
            tvLids = findViewById(R.id.tvLids);

            tvSmall.setText("" + model.getBalanceSmall());
            tvBig.setText("" + model.getBalanceBig());
            tvLids.setText("" + model.getBalanceLead());

            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (edSmall.getText().toString().isEmpty()) {
                        edSmall.setError("required");

                    } else if (Integer.parseInt(edSmall.getText().toString().trim()) > model.getBalanceSmall()) {
                        edSmall.setError("invalid input");

                    } else if (edBig.getText().toString().isEmpty()) {
                        edSmall.setError(null);
                        edBig.setError("required");

                    } else if (Integer.parseInt(edBig.getText().toString().trim()) > model.getBalanceBig()) {
                        edSmall.setError(null);
                        edBig.setError("invalid input");

                    } else if (edLarge.getText().toString().isEmpty()) {
                        edBig.setError(null);
                        edLarge.setError("required");
                    } else if (Integer.parseInt(edLarge.getText().toString().trim()) > model.getBalanceLead()) {
                        edBig.setError(null);
                        edLarge.setError("invalid input");

                    } else {

                        edSmall.setError(null);
                        edBig.setError(null);
                        edLarge.setError(null);

                        int balSmall = model.getBalanceSmall();
                        int balBig = model.getBalanceBig();
                        int balLid = model.getBalanceLead();

                        int enteredSmall = Integer.parseInt(edSmall.getText().toString().trim());
                        int enteredBig = Integer.parseInt(edBig.getText().toString().trim());
                        int enteredLid = Integer.parseInt(edLarge.getText().toString().trim());

                        int small = balSmall - enteredSmall;
                        int big = balBig - enteredBig;
                        int lid = balLid - enteredLid;

                        int status = 4;

                        if (small == 0 && big ==0 && lid ==0) {
                            status = 5;
                        } else {
                            status = 4;
                        }


/*
                        if (small == balSmall && big == balBig && lid == balLid) {
                            status = 5;
                        } else {
                            status = 4;
                        }
*/


                        updateBalTray(model.getTranDetailId(), small, big, lid, status, model.getFrId());


                    }


                }

            });
        }

    }


    public void updateBalTray(int id, int small, int big, int lid, int status, final int frId) {

        Log.e("SMALL = " + small, "            BIG = " + big + "            LID = " + lid+"         STATUS = "+status);

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
                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                BalanceTrayActivity activity=(BalanceTrayActivity)context;
                                activity.finish();

                                Intent intent = new Intent(context, BalanceTrayActivity.class);
                                intent.putExtra("frId", frId);
                                context.startActivity(intent);


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

}