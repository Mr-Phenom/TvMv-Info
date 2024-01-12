package com.company.tvmvinfo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.activity.FullDetailsActivity;
import com.company.tvmvinfo.adapter.AdapterClassSearch;
import com.company.tvmvinfo.modelClass.MovieListHomeApi;
import com.company.tvmvinfo.retrofit.RetrofitTvMv;
import com.company.tvmvinfo.retrofit.TvMvApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvFragmentSearch extends Fragment {
    RecyclerView rvTv;
    AdapterClassSearch adapter2;
    String name;
    TextView warning;
    public static TvFragmentSearch getInstance(String name)
    {
        return new TvFragmentSearch(name);
    }


    public TvFragmentSearch(String name) {
        // Required empty public constructor
        this.name=name;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tv_search, container, false);

        rvTv=view.findViewById(R.id.recyclerViewTvSearch);
        warning=view.findViewById(R.id.textViewWarning2);
        rvTv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getItems(name);


        return view;
    }

    public void getItems(String name)
    {
        TvMvApi tvMvApi = RetrofitTvMv.getClient().create(TvMvApi.class);
        Call<MovieListHomeApi> call2= tvMvApi.searchItem("tv",name);
        call2.enqueue(new Callback<MovieListHomeApi>() {
            @Override
            public void onResponse(Call<MovieListHomeApi> call, Response<MovieListHomeApi> response) {
                adapter2 = new AdapterClassSearch(response.body().getResults());
                rvTv.setAdapter(adapter2);
                adapter2.setOnItemClickListener(new AdapterClassSearch.OnItemClickListener() {
                    @Override
                    public void onItemClick(int id) {
                        Intent intent = new Intent(getActivity(), FullDetailsActivity.class);
                        intent.putExtra("idForMovie",id);
                        intent.putExtra("typeOfDetails","tv");
                        startActivity(intent);
                    }
                });
                if(response.body().getResults().size()==0)
                {
                    warning.setText("No item Found");
                    warning.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<MovieListHomeApi> call, Throwable t) {

            }
        });
    }
}