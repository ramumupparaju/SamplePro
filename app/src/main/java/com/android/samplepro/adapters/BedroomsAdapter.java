package com.android.samplepro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.android.samplepro.R;


public class BedroomsAdapter extends RecyclerView.Adapter<BedroomsAdapter.ViewHolder> {

    @Override
    public BedroomsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bedroom, parent, false);
        return new BedroomsAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(BedroomsAdapter.ViewHolder holder1, final int position) {


    }


    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);


        }
    }

}
