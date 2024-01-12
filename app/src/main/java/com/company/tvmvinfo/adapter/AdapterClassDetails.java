package com.company.tvmvinfo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.modelClass.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterClassDetails extends RecyclerView.Adapter<AdapterClassDetails.viewHolder> {

    List<Cast> listCast;

    public AdapterClassDetails(List<Cast> listCast) {
        this.listCast = listCast;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_rv,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.name.setText(listCast.get(position).getName());
        if(listCast.get(position).getGender()==1)
        {
            holder.gender.setText("Female");
        }
        else
            holder.gender.setText("Male");
        holder.character.setText(listCast.get(position).getCharacter().toString());
        Picasso.get().load("https://image.tmdb.org/t/p/original/"+listCast.get(position).getProfilePath())
                .placeholder(R.drawable.tvmv_logo).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listCast.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,gender,character;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewItemPosterDetails);
            name=itemView.findViewById(R.id.textViewIteamNameDetails);
            gender=itemView.findViewById(R.id.textViewItemGenderDetails);
            character=itemView.findViewById(R.id.textViewItemCharacterDetails);
        }
    }
}
