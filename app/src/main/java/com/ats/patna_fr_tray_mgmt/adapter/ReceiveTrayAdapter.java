package com.ats.patna_fr_tray_mgmt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.bean.ReceivedTrayList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ReceiveTrayAdapter extends RecyclerView.Adapter<ReceiveTrayAdapter.MyViewHolder> {


    private ArrayList<ReceivedTrayList> trayList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate, tvSmall, tvBig, tvLead;

        public MyViewHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tvDate);
            tvSmall = view.findViewById(R.id.tvSmall);
            tvBig = view.findViewById(R.id.tvBig);
            tvLead = view.findViewById(R.id.tvLead);
        }
    }

    public ReceiveTrayAdapter(ArrayList<ReceivedTrayList> trayList, Context context) {
        this.trayList = trayList;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_receive_tray_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final ReceivedTrayList model = trayList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date d=sdf1.parse(model.getTrayDate());
            holder.tvDate.setText("Received On - "+sdf.format(d.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvSmall.setText("" + model.getSmall());
        holder.tvBig.setText("" + model.getBig());
        holder.tvLead.setText("" + model.getLead());




    }

    @Override
    public int getItemCount() {
        return trayList.size();
    }


}
