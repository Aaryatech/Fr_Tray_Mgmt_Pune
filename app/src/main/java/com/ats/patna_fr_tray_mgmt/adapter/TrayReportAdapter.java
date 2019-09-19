package com.ats.patna_fr_tray_mgmt.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.patna_fr_tray_mgmt.R;
import com.ats.patna_fr_tray_mgmt.bean.TrayDetails;

import java.util.ArrayList;

/**
 * Created by MAXADMIN on 3/3/2018.
 */

public class TrayReportAdapter extends RecyclerView.Adapter<TrayReportAdapter.MyViewHolder> {

    private ArrayList<TrayDetails> trayDetailsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout llOutTray, llInTray, llInTray1, llBalance, llAmount;
        public TextView tvOutTray_Small, tvOutTray_Big, tvOutTray_Large, tvOutTray_XL, tvInTray_Small, tvInTray_Big, tvInTray_Large, tvInTray_XL, tvInTray1_Small, tvInTray1_Big, tvInTray1_Large, tvInTray1_XL, tvBalTray_Small, tvBalTray_Big, tvBalTray_Large, tvBalTray_XL, tvTaxAmt, tvTaxableAmt, tvTotal, tvOutTray_Date, tvInTray_Date, tvInTray1_Date, tvBill, tvBalSmall, tvBalBig, tvBalLids, tvStatus;

        public MyViewHolder(View view) {
            super(view);
            llOutTray = view.findViewById(R.id.llTrayReport_OutTray);
            llInTray = view.findViewById(R.id.llTrayReport_InTray);
            llInTray1 = view.findViewById(R.id.llTrayReport_InTray1);
            llBalance = view.findViewById(R.id.llTrayReport_BalTray);
            llAmount = view.findViewById(R.id.llTrayReport_Amount);

            tvOutTray_Small = view.findViewById(R.id.tvOutTray_Small);
            tvOutTray_Big = view.findViewById(R.id.tvOutTray_Big);
            tvOutTray_Large = view.findViewById(R.id.tvOutTray_Large);
            tvOutTray_XL = view.findViewById(R.id.tvOutTray_XL);
            tvInTray_Small = view.findViewById(R.id.tvInTray_Small);
            tvInTray_Big = view.findViewById(R.id.tvInTray_Big);
            tvInTray_Large = view.findViewById(R.id.tvInTray_Large);
            tvInTray_XL = view.findViewById(R.id.tvInTray_XL);
            tvInTray1_Small = view.findViewById(R.id.tvInTray1_Small);
            tvInTray1_Big = view.findViewById(R.id.tvInTray1_Big);
            tvInTray1_Large = view.findViewById(R.id.tvInTray1_Large);
            tvInTray1_XL = view.findViewById(R.id.tvInTray1_XL);
            tvBalTray_Small = view.findViewById(R.id.tvBalTray_Small);
            tvBalTray_Big = view.findViewById(R.id.tvBalTray_Big);
            tvBalTray_Large = view.findViewById(R.id.tvBalTray_Large);
            tvBalTray_XL = view.findViewById(R.id.tvBalTray_XL);
            tvTaxAmt = view.findViewById(R.id.tvAmount_TaxAmt);
            tvTaxableAmt = view.findViewById(R.id.tvAmount_TaxableAmt);
            tvTotal = view.findViewById(R.id.tvAmount_Total);
            tvBill = view.findViewById(R.id.tvAmount_Bill);

            tvBalSmall = view.findViewById(R.id.tvBalSmall);
            tvBalBig = view.findViewById(R.id.tvBalBig);
            tvBalLids = view.findViewById(R.id.tvBalLids);
            tvStatus = view.findViewById(R.id.tvStatus);

            tvOutTray_Date = view.findViewById(R.id.tvOutTray_Date);
            tvInTray_Date = view.findViewById(R.id.tvInTray_Date);
            tvInTray1_Date = view.findViewById(R.id.tvInTray1_Date);
        }
    }


    public TrayReportAdapter(ArrayList<TrayDetails> trayDetailsList) {
        this.trayDetailsList = trayDetailsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_tray_report_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TrayDetails details = trayDetailsList.get(position);

        holder.tvOutTray_Date.setText("" + details.getOuttrayDate());
        holder.tvOutTray_Small.setText("" + details.getOuttraySmall());
        holder.tvOutTray_Big.setText("" + details.getOuttrayBig());
        holder.tvOutTray_Large.setText("" + details.getOuttrayLead());
        holder.tvOutTray_XL.setText("" + details.getOuttrayExtra());

        int bal_Small = details.getBalanceSmall();
        int bal_Big = details.getBalanceBig();
        int bal_Large = details.getBalanceLead();
        int bal_Xl = details.getBalanceExtra();

        //if (details.getTrayStatus() >= 4) {

        if (details.getTrayStatus() <= 4) {
            holder.tvStatus.setText("Status - PENDING");
        } else {
            holder.tvStatus.setText("Status - COMPLETED");
        }

        holder.tvBalSmall.setText("" + details.getBalanceSmall());
        holder.tvBalBig.setText("" + details.getBalanceBig());
        holder.tvBalLids.setText("" + details.getBalanceLead());


        holder.tvOutTray_Date.setText("" + details.getOuttrayDate());
        holder.tvOutTray_Small.setText("" + details.getOuttraySmall());
        holder.tvOutTray_Big.setText("" + details.getOuttrayBig());
        holder.tvOutTray_Large.setText("" + details.getOuttrayLead());

        holder.tvInTray_Small.setText("" + (details.getOuttraySmall() - details.getBalanceSmall()));
        holder.tvInTray_Big.setText("" + (details.getOuttrayBig() - details.getBalanceBig()));
        holder.tvInTray_Large.setText("" + (details.getOuttrayLead() - details.getBalanceLead()));


        /*if (bal_Small == 0 && bal_Big == 0 && bal_Large == 0) {

            int inTray1_Small = details.getIntraySmall1();
            int inTray1_Big = details.getIntrayBig1();
            int inTray1_large = details.getIntrayLead1();
            int inTray1_XL = details.getIntrayExtra1();

            if (inTray1_Small == 0 && inTray1_Big == 0 && inTray1_large == 0) {

                holder.llInTray.setVisibility(View.VISIBLE);

                if (details.getIntrayDate().equalsIgnoreCase("30-11-0002")) {
                    holder.tvInTray_Date.setText("");
                } else {
                    holder.tvInTray_Date.setText("" + details.getIntrayDate());
                }
                holder.tvInTray_Small.setText("" + details.getIntraySmall());
                holder.tvInTray_Big.setText("" + details.getIntrayBig());
                holder.tvInTray_Large.setText("" + details.getIntrayLead());
                holder.tvInTray_XL.setText("" + details.getIntrayExtra());

            } else {

                holder.llInTray.setVisibility(View.VISIBLE);

                if (details.getIntrayDate().equalsIgnoreCase("30-11-0002")) {
                    holder.tvInTray_Date.setText("");
                } else {
                    holder.tvInTray_Date.setText("" + details.getIntrayDate());
                }


                // holder.tvInTray_Date.setText("" + details.getIntrayDate());
                holder.tvInTray_Small.setText("" + details.getIntraySmall());
                holder.tvInTray_Big.setText("" + details.getIntrayBig());
                holder.tvInTray_Large.setText("" + details.getIntrayLead());
                holder.tvInTray_XL.setText("" + details.getIntrayExtra());

                holder.llInTray1.setVisibility(View.VISIBLE);

                if (details.getIntrayDate1().equalsIgnoreCase("30-11-0002")) {
                    holder.tvInTray1_Date.setText("");
                } else {
                    holder.tvInTray1_Date.setText("" + details.getIntrayDate1());
                }

                // holder.tvInTray1_Date.setText("" + details.getIntrayDate1());
                holder.tvInTray1_Small.setText("" + details.getIntraySmall1());
                holder.tvInTray1_Big.setText("" + details.getIntrayBig1());
                holder.tvInTray1_Large.setText("" + details.getIntrayLead1());
                holder.tvInTray1_XL.setText("" + details.getIntrayExtra1());

            }

        } else {
            holder.llInTray.setVisibility(View.VISIBLE);

            if (details.getIntrayDate().equalsIgnoreCase("30-11-0002")) {
                holder.tvInTray_Date.setText("");
            } else {
                holder.tvInTray_Date.setText("" + details.getIntrayDate());
            }

            //holder.tvInTray_Date.setText("" + details.getIntrayDate());
            holder.tvInTray_Small.setText("" + details.getIntraySmall());
            holder.tvInTray_Big.setText("" + details.getIntrayBig());
            holder.tvInTray_Large.setText("" + details.getIntrayLead());
            holder.tvInTray_XL.setText("" + details.getIntrayExtra());

            if (details.getTrayStatus() == 3) {
                holder.llInTray1.setVisibility(View.GONE);
            } else {
                holder.llInTray1.setVisibility(View.VISIBLE);
            }


            if (details.getIntrayDate1().equalsIgnoreCase("30-11-0002")) {
                holder.tvInTray1_Date.setText("");
            } else {
                holder.tvInTray1_Date.setText("" + details.getIntrayDate1());
            }

            //holder.tvInTray1_Date.setText("" + details.getIntrayDate1());
            holder.tvInTray1_Small.setText("" + details.getIntraySmall1());
            holder.tvInTray1_Big.setText("" + details.getIntrayBig1());
            holder.tvInTray1_Large.setText("" + details.getIntrayLead1());
            holder.tvInTray1_XL.setText("" + details.getIntrayExtra1());


            holder.llBalance.setVisibility(View.VISIBLE);
            holder.tvBalTray_Small.setText("" + details.getBalanceSmall());
            holder.tvBalTray_Big.setText("" + details.getBalanceBig());
            holder.tvBalTray_Large.setText("" + details.getBalanceLead());
            holder.tvBalTray_XL.setText("" + details.getBalanceExtra());

            if (details.getTrayStatus() == 5) {
                holder.llAmount.setVisibility(View.VISIBLE);
                holder.tvTaxableAmt.setText("Taxable Amt : " + details.getTaxableAmt());
                holder.tvTaxAmt.setText("Tax Amt : " + details.getTaxAmt());
                holder.tvTotal.setText("Total : " + details.getGrandTotal());
            }

            if (details.getDepositIsUsed() == 1) {
                holder.tvBill.setText("Bill");
            } else {
                holder.tvBill.setText("");
            }
        }*/
        //}

    }

    @Override
    public int getItemCount() {
        return trayDetailsList.size();
    }

}
