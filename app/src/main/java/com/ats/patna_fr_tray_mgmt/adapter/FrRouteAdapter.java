package com.ats.patna_fr_tray_mgmt.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.activity.TrayActivity;
import com.ats.patna_fr_tray_mgmt.bean.RouteWiseData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FrRouteAdapter extends RecyclerView.Adapter<FrRouteAdapter.MyViewHolder> {

    private ArrayList<RouteWiseData> routeList;
    private Context context;

    public FrRouteAdapter(ArrayList<RouteWiseData> routeList, Context context) {
        this.routeList = routeList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate, tvVeh, tvRoute, tvTrayStatus;

        public MyViewHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tvDate);
            tvVeh = view.findViewById(R.id.tvVeh);
            tvRoute = view.findViewById(R.id.tvRoute);
            tvTrayStatus = view.findViewById(R.id.tvTrayStatus);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_fr_route, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final RouteWiseData model = routeList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        holder.tvVeh.setText("" + model.getVehNo() + " " + model.getDriverName());
        holder.tvRoute.setText("" + model.getRouteName());

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");


        try {
            Date date = sdf1.parse(model.getTranDate());
            holder.tvDate.setText("" + sdf2.format(date.getTime()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvTrayStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrayActivity.class);
                intent.putExtra("tranId", model.getTranId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }


}
