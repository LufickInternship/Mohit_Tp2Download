package com.example.streamnow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    List<Data> data;

    static {
        Collections.emptyList();
    }


    public Adapter(Context context, List<Data> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container, parent,false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder= (MyHolder) holder;
        Data current=data.get(position);
        Glide.with(context).load("https://dog.ceo/api/breeds/image/random" + current.DogImage)
                .placeholder(R.drawable.ic_img_error)
                .error(R.drawable.ic_img_error)
                .into(myHolder.ivDog);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class MyHolder extends RecyclerView.ViewHolder{

        ImageView ivDog;

        public MyHolder(View itemView) {
            super(itemView);
            ivDog= (ImageView) itemView.findViewById(R.id.ivDog);

        }

    }
}
