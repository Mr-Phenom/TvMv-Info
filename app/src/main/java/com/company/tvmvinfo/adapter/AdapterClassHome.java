package com.company.tvmvinfo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.modelClass.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterClassHome extends RecyclerView.Adapter<AdapterClassHome.ViewHolder>{

   // String category;
    List<Result> listResult = new ArrayList<>();
    //Context context;
    AdapterClassSearch.OnItemClickListener onItemClickListener;

    public AdapterClassHome(List<Result> listResult) {

        this.listResult=listResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_rv,parent,false);
        Log.d("oncreateashche", String.valueOf(listResult.size()));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(listResult.get(position).getTitle().toString());
        String poster =  listResult.get(position).getPosterPath();
        Picasso.get().load("https://image.tmdb.org/t/p/original/"+ poster)
                .placeholder(R.drawable.tvmv_logo).into(holder.imageView);

       // Log.d("textApiGot",listResult.get(position).getTitle().toString());
        //Log.d("Picasso pic","link is" + poster);
        //Log.d("Picasso title","title is" + listResult.get(position).getOriginalTitle());

    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public interface OnItemClickListener
    {
        void onItemClick(int id);
    }

    public void setOnItemClickListener(AdapterClassSearch.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageViewItemHome);
            textView=itemView.findViewById(R.id.textViewitemTitleHome);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getAdapterPosition();
                    if(onItemClickListener!=null && position!=RecyclerView.NO_POSITION)
                    {
                        onItemClickListener.onItemClick(listResult.get(position).getId());
                    }
                }
            });
        }
    }
}
