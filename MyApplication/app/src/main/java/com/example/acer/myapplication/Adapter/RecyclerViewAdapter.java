package com.example.acer.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myapplication.Models.Biodata;
import com.example.acer.myapplication.Models.ItemObject;
import com.example.acer.myapplication.R;
import com.example.acer.myapplication.Utils.RecyclerViewHolders;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Biodata> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Biodata> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.countryName.setText(itemList.get(position).getFName());
        //holder.countryPhoto.setImageResource(itemList.get(position).getImageUrl());
        Picasso.with(context).load(itemList.get(position).getImageUrl()).into(holder.countryPhoto);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
