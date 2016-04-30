package com.example.owner.restorandomizer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

/**
 * Created by Owner on 1/05/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CheckBox mCheckBox;

        public ViewHolder(CheckBox v){
            super(v);
            mCheckBox = v;
        }
    }

    public MyAdapter(List<String> dataset){
        this.dataset = dataset;
    }

    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        CheckBox v =(CheckBox) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_check_box, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position){
        holder.mCheckBox.setText(dataset.get(position));
    }

    public int getItemCount(){
        return dataset.size();
    }
}

