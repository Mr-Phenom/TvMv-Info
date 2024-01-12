package com.company.tvmvinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.adapter.AdapterClassDetails;
import com.company.tvmvinfo.adapter.AdapterClassSearch;
import com.company.tvmvinfo.modelClass.Cast;
import com.company.tvmvinfo.modelClass.CastOfMovies;
import com.company.tvmvinfo.modelClass.Genre;
import com.company.tvmvinfo.modelClass.MovieDetails;
import com.company.tvmvinfo.modelClass.MovieListHomeApi;
import com.company.tvmvinfo.modelClass.ProductionCompany;
import com.company.tvmvinfo.retrofit.RetrofitTvMv;
import com.company.tvmvinfo.retrofit.TvMvApi;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullDetailsActivity extends AppCompatActivity {

    TextView title,tagline,genre,duration,overview,originalTitle,originalLanguage,budget,production,release,episodes;
    RecyclerView rv;
    ImageView poster;
    int id;
    String type;
    AdapterClassDetails adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_details);
        title=findViewById(R.id.textViewTitleDetails);
        tagline=findViewById(R.id.textViewTaglineDetails);
        genre=findViewById(R.id.textViewGenreDetails);
        duration=findViewById(R.id.textViewDurationDetials);
        overview=findViewById(R.id.textViewOverviewDetails);
        originalTitle=findViewById(R.id.textViewOriginalTitleDetails);
        originalLanguage=findViewById(R.id.textViewOriginalLanguage);
        budget=findViewById(R.id.textViewBudgetDetails);
        production=findViewById(R.id.textViewProductionDetails);
        release=findViewById(R.id.textViewReleaseDateDetails);
        episodes=findViewById(R.id.textViewEpisodeDetials);
        poster=findViewById(R.id.imageViewDetails);

        rv=findViewById(R.id.recyclerViewDetails);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        id = intent.getIntExtra("idForMovie",0);
        type=intent.getStringExtra("typeOfDetails");
        getFullDetails(type,id);

    }

    public void getFullDetails(String type,int id)
    {
        Log.d("Ashce1st", String.valueOf(id));
        TvMvApi tvMvApi = RetrofitTvMv.getClient().create(TvMvApi.class);
        Call<MovieDetails> call= tvMvApi.detailsOfMovie(type,id);
        Log.d("Ashce1st2",type+" ");
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                Log.d("Ashce1st3","ashche");
                if(type.equals("movie"))
                {
                    Log.d("Ashce2nd","ashche");
                    title.setText(response.body().getTitle().toString());
                    tagline.setText(""+response.body().getTagline().toString());
                    overview.setText(Html.fromHtml("<b><u>>Overview</u></b>: <br/>" + response.body().getOverview().toString()));
                    originalLanguage.setText(Html.fromHtml("<b><u>>Original Language:</u></b> " + response.body().getOriginalLanguage().toString()));
                    originalTitle.setText(Html.fromHtml("<b><u>>Original title:</u> </b><br/>" + response.body().getOriginalTitle().toString()));
                    duration.setText(Html.fromHtml("<b><u>>Duration:</u> </b>" + response.body().getRuntime().toString()+" Minutes"));
                    budget.setText(Html.fromHtml("<b><u>>Budget:</u> </b>" + response.body().getBudget()));
                    release.setText(Html.fromHtml("<b><u>>Release:</u> </b>" + response.body().getReleaseDate().toString()));


                    String a = "";
                    List<Genre> b = response.body().getGenres();
                    for(int i=0;i<b.size()-1;i++)
                    {
                        a=a + b.get(i).getName().toString()+", ";
                    }
                    a=a + b.get(b.size()-1).getName().toString()+".";
                    genre.setText(Html.fromHtml("<b><u>>Genre:</u> </b>" + a));

                    String c = "";
                    List<ProductionCompany> d = response.body().getProductionCompanies();
                    for(int i=0;i<d.size()-1;i++)
                    {
                        c=c + d.get(i).getName().toString()+", ";
                    }
                    c=c + d.get(d.size()-1).getName().toString()+".";
                    production.setText(Html.fromHtml("<b><u>>Productions:</u> </b><br/>" + c));

                    Picasso.get().load("https://image.tmdb.org/t/p/original/" + response.body().getPosterPath())
                            .placeholder(R.drawable.tvmv_logo).into(poster);

                }
                else
                {
                    Log.d("Ashce3d","ashche");
                    title.setText(response.body().getName().toString());
                    tagline.setText(""+response.body().getTagline());
                    overview.setText(Html.fromHtml("<b><u>>Overview:</u> </b>" + response.body().getOverview().toString()));
                    originalLanguage.setText(Html.fromHtml("<b><u>>Original Language:</u> </b>" + response.body().getOriginalLanguage().toString()));
                    originalTitle.setText(Html.fromHtml("<b><u>>Original title:</u> </b>" + response.body().getOriginalName().toString()));
                    duration.setText(Html.fromHtml("<b><u>>Total Seasons:</u> </b>" + response.body().getSeasons()));
                    budget.setText(Html.fromHtml("<b><u>>Budget:</u> </b>" + response.body().getBudget()));
                    release.setText(Html.fromHtml("<b><u>>Release:</u> </b>" + response.body().getFirstAirDate().toString()));
                    episodes.setText(Html.fromHtml("<b><u>>Total Episodes:</u> </b>" + response.body().getEpisodes()));
                    episodes.setVisibility(View.VISIBLE);

                    String a = "";
                    List<Genre> b = response.body().getGenres();
                    for(int i=0;i<b.size()-1;i++)
                    {
                        a=a + b.get(i).getName().toString()+", ";
                    }
                    a=a + b.get(b.size()-1).getName().toString()+".";
                    genre.setText(Html.fromHtml("<b><u>>Genre:</u> </b>" + a));

                    String c = "";
                    List<ProductionCompany> d = response.body().getProductionCompanies();
                    for(int i=0;i<d.size()-1;i++)
                    {
                        c=c + d.get(i).getName().toString()+", ";
                    }
                    c=c + d.get(d.size()-1).getName().toString()+".";
                    production.setText(Html.fromHtml("<b><u>>Productions:</u> </b>" + c));

                    Picasso.get().load("https://image.tmdb.org/t/p/original/" + response.body().getPosterPath().toString())
                            .placeholder(R.drawable.tvmv_logo).into(poster);
                }

            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });

        Call<CastOfMovies> call1 = tvMvApi.getCasts(type,id);
        call1.enqueue(new Callback<CastOfMovies>() {
            @Override
            public void onResponse(Call<CastOfMovies> call, Response<CastOfMovies> response) {
                Log.d("cast size","size is"+response.body().getCast().size());
                List<Cast> casts = response.body().getCast();
                adapter = new AdapterClassDetails(casts);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CastOfMovies> call, Throwable t) {

            }
        });
    }
}