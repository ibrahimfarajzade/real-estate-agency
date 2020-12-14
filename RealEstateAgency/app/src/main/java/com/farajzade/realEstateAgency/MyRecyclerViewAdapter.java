package com.farajzade.realEstateAgency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    Context context;
    SecondActivity secondActivity;

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
        secondActivity = (SecondActivity)context;
    }

    public MyRecyclerViewAdapter(Context context, List<Product> data) {
        this.context = context;
        Commons.data=(ArrayList<Product>) data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new MyViewHolder(v);
    }

    public void modifyData(){
        notifyDataSetChanged();
        notifyItemRangeChanged(0, Commons.data.size());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //BIND DATA
        final Product pro = Commons.data.get(position);
        holder.tvItem.setText(pro.getName()+"\n");

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String msg = "Product ID: " +pro.getId()+"\nProduct Price:  "+pro.getPrice() + "\nProduct Name: " + pro.getName();
                secondActivity.displayDialog(msg, pro.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return Commons.data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnDetail;
        TextView tvItem;
        ImageView icon;

        MyViewHolder(View viewItem){
            super(viewItem);
            tvItem = viewItem.findViewById(R.id.tvItem);
            icon = viewItem.findViewById(R.id.imgViewDialog);
            btnDetail = viewItem.findViewById(R.id.btnDetail);
        }
    }
}
