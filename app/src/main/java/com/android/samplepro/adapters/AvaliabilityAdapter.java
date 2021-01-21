package com.android.samplepro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.samplepro.R;


public class AvaliabilityAdapter extends RecyclerView.Adapter<AvaliabilityAdapter.ViewHolder> {

    @Override
    public AvaliabilityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bedroom, parent, false);
        return new AvaliabilityAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AvaliabilityAdapter.ViewHolder holder1, final int position) {
        holder1.tvTitle.setText("With in 10 days");


    }


    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);


        }
    }

}
