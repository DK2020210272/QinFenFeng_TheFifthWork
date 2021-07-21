package com.example.qinfenfeng_the_fifthwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    private List<RvData> list;

    public RvAdapter(List<RvData> list){
        this.list=list;
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRv;

        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRv = itemView.findViewById(R.id.tv_rv);
        }
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        RvData rvData = list.get(position);
        holder.tvRv.setText(rvData.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
