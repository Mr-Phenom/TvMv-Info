package com.company.tvmvinfo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.modelClass.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterClassSearch extends RecyclerView.Adapter<AdapterClassSearch.viewHolder> {
    List<Result> listResult ;
    OnItemClickListener onItemClickListener;
    public AdapterClassSearch(List<Result> listResult) {
        this.listResult = listResult;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_rv,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


       if(listResult.get(position).getTitle()!=null)
        {
            holder.title.setText(listResult.get(position).getTitle().toString());
            holder.overview.setText(listResult.get(position).getOverview().toString());
            Picasso.get().load("https://image.tmdb.org/t/p/original/"+listResult.get(position).getPosterPath()).placeholder(R.drawable.tvmv_logo)
                    .into(holder.image);
        }
        else if(listResult.get(position).getName()!=null)
        {
                holder.title.setText(listResult.get(position).getName().toString());
                holder.overview.setText(listResult.get(position).getOverview().toString());
                Picasso.get().load("https://image.tmdb.org/t/p/original/"+listResult.get(position).getPosterPath()).placeholder(R.drawable.tvmv_logo)
                        .into(holder.image);
        }


    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public interface OnItemClickListener
    {
        void onItemClick(int id);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title,overview;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.imageViewItemSearch);
            title=itemView.findViewById(R.id.textViewItemTitleSearch);
            overview=itemView.findViewById(R.id.textViewItemOverviewSearch);

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
