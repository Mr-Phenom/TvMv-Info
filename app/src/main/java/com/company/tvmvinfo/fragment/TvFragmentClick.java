package com.company.tvmvinfo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.activity.FullDetailsActivity;
import com.company.tvmvinfo.adapter.AdapterClassSearch;
import com.company.tvmvinfo.modelClass.MovieListHomeApi;
import com.company.tvmvinfo.modelClass.Result;
import com.company.tvmvinfo.retrofit.RetrofitTvMv;
import com.company.tvmvinfo.retrofit.TvMvApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvFragmentClick extends Fragment {

    String category;
    RecyclerView rv;
    AdapterClassSearch adapter;
    List<Result> list=new ArrayList<>();


    public static TvFragmentClick getInstance(String category)
    {
        return new TvFragmentClick(category);
    }
    public TvFragmentClick(String category) {
        // Required empty public constructor
        if(category.equals("now_playing"))
        {
            this.category="airing_today";
        }
        else if(category.equals("upcoming"))
        {
            this.category="on_the_air";
        }
        else
            this.category=category;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tv_click, container, false);

        rv=v.findViewById(R.id.recyclerViewTvClick);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getClick(category);
        adapter=new AdapterClassSearch(list);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterClassSearch.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getActivity(), FullDetailsActivity.class);
                intent.putExtra("idForMovie",id);
                intent.putExtra("typeOfDetails","tv");
                startActivity(intent);
            }
        });

        return v;
    }

    public void getClick(String category)
    {
        TvMvApi tvMvApi = RetrofitTvMv.getClient().create(TvMvApi.class);
        for(int i=1;i<=20;i++)
        {
            Call<MovieListHomeApi> call = tvMvApi.listOfMoreInfo("tv",category,i);
            call.enqueue(new Callback<MovieListHomeApi>() {
                @Override
                public void onResponse(Call<MovieListHomeApi> call, Response<MovieListHomeApi> response) {
                    list.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<MovieListHomeApi> call, Throwable t) {

                }
            });
        }

    }
}