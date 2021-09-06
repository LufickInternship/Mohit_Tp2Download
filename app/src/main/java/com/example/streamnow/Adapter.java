package com.example.streamnow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    LayoutInflater inflater;
    List<Data> data;

    static {
        Collections.emptyList();
    }


    public Adapter(Context context, List<Data> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

   @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.from(parent.getContext()).inflate(R.layout.container, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        MyHolder myHolder= (MyHolder) holder;
        Data current=data.get(position);
        Glide.with(context).load(current.getDogImage()).error(R.drawable.ic_img_error).into(myHolder.ivDog);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class MyHolder extends RecyclerView.ViewHolder{

        ImageView ivDog;

        public MyHolder(View itemView) {
            super(itemView);
            ivDog= itemView.findViewById(R.id.ivDog);

        }

    }
}
