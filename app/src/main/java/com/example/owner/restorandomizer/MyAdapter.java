package com.example.owner.restorandomizer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

/**
 * Created by Owner on 1/05/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Restos> dataset;
    private ItemSelectedListner listner;

    interface ItemSelectedListner{
        void onItemSelected(Restos clicked, boolean isChecked);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CheckBox mCheckBox;

        public ViewHolder(CheckBox v){
            super(v);
            mCheckBox = v;
        }
    }

    public MyAdapter(List<Restos> dataset, ItemSelectedListner listner){
        this.dataset = dataset;
        this.listner = listner;
    }

    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        CheckBox v =(CheckBox) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_check_box, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.mCheckBox.setText(dataset.get(position).getRestoName());
        holder.mCheckBox.setChecked(dataset.get(position).getChecked());
        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onItemSelected(dataset.get(position),holder.mCheckBox.isChecked());
            }

        });
    }

    public int getItemCount(){
        return dataset.size();
    }
}

