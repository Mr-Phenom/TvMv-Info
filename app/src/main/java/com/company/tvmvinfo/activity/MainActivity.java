package com.company.tvmvinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.company.tvmvinfo.R;
import com.company.tvmvinfo.adapter.AdapterClassSearch;
import com.company.tvmvinfo.retrofit.RetrofitTvMv;
import com.company.tvmvinfo.retrofit.TvMvApi;
import com.company.tvmvinfo.adapter.AdapterClassHome;
import com.company.tvmvinfo.modelClass.MovieListHomeApi;
import com.company.tvmvinfo.modelClass.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText editTextSearch;
    Button buttonSearch;
    TextView clickForMore1,clickForMore2,clickForMore3,clickForMore4;
    RecyclerView rv1,rv2,rv3,rv4;
    AdapterClassHome adapter1,adapter2,adapter3,adapter4;
    List<Result> list1;
    List<Result> list2;
    List<Result> list3;
    List<Result> list4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        editTextSearch = findViewById(R.id.editTextSearchHome);
        buttonSearch = findViewById(R.id.buttonSearchHome);
        clickForMore1 = findViewById(R.id.textViewNowPlayingClick);
        clickForMore2 = findViewById(R.id.textViewUpComingClick);
        clickForMore3 = findViewById(R.id.textViewTopRatedClick);
        clickForMore4 = findViewById(R.id.textViewPopularClick);

        list1 = new ArrayList<>();
        list2  = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();

        getMovieLists();
        Log.d("sizeoflist1","size is " + list1.size());

        //adapter1 = new AdapterClassHome(list1);

        rv1=findViewById(R.id.recyclerViewNowPlaying);
        rv2=findViewById(R.id.recyclerViewUpComing);
        rv3=findViewById(R.id.recyclerViewTopRated);
        rv4=findViewById(R.id.recyclerViewPopular);

        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        rv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        rv3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        rv4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

       // rv1.setAdapter(adapter1);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToSearch(editTextSearch.getText().toString());
            }
        });
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_DONE||actionId==EditorInfo.IME_ACTION_NEXT)
                {
                    goToSearch(editTextSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });

        clickForMore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClickMoreActivity.class);
                intent.putExtra("categoryForClick","now_playing");
                intent.putExtra("cat","Now Playing");
                startActivity(intent);
            }
        });
        clickForMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ClickMoreActivity.class);
                intent.putExtra("categoryForClick","upcoming");
                intent.putExtra("cat","Up Coming");
                startActivity(intent);
            }
        });
        clickForMore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ClickMoreActivity.class);
                intent.putExtra("categoryForClick","top_rated");
                intent.putExtra("cat","Top Rated");
                startActivity(intent);
            }
        });
        clickForMore4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ClickMoreActivity.class);
                intent.putExtra("categoryForClick","popular");
                intent.putExtra("cat","Popular");
                startActivity(intent);
            }
        });



    }

    public void goToSearch(String name)
    {
        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
        intent.putExtra("nameForSearch",name);
        editTextSearch.setText("");
        startActivity(intent);
    }


    public void getMovieLists()
    {
        TvMvApi tvMvApi = RetrofitTvMv.getClient().create(TvMvApi.class);
        Call<MovieListHomeApi> call1 = tvMvApi.listOfPrimaryInfo("movie","now_playing");
        call1.enqueue(new Callback<MovieListHomeApi>() {
            @Override
            public void onResponse(Call<MovieListHomeApi> call, Response<MovieListHomeApi> response) {
              adapter1 = new AdapterClassHome(response.body().getResults()) ;
              rv1.setAdapter(adapter1);
                adapter1.setOnItemClickListener(new AdapterClassSearch.OnItemClickListener() {
                    @Override
                    public void onItemClick(int id) {
                        Intent intent = new Intent(MainActivity.this, FullDetailsActivity.class);
                        intent.putExtra("idForMovie",id);
                        intent.putExtra("typeOfDetails","movie");
                        startActivity(intent);
                    }
                });
                //Log.d("itishere", String.valueOf(response.body().getResults().size()));
            }

            @Override
            public void onFailure(Call<MovieListHomeApi> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        Call<MovieListHomeApi> call2 = tvMvApi.listOfPrimaryInfo("movie","upcoming");
        call2.enqueue(new Callback<MovieListHomeApi>() {
            @Override
            public void onResponse(Call<MovieListHomeApi> call, Response<MovieListHomeApi> response) {
                list2 = response.body().getResults();
                adapter2 = new AdapterClassHome(list2);
                rv2.setAdapter(adapter2);
                adapter2.setOnItemClickListener(new AdapterClassSearch.OnItemClickListener() {
                    @Override
                    public void onItemClick(int id) {
                        Intent intent = new Intent(MainActivity.this, FullDetailsActivity.class);
                        intent.putExtra("idForMovie",id);
                        intent.putExtra("typeOfDetails","movie");
                        startActivity(intent);
                    }
                });
                Log.d("itishere", String.valueOf(response.body().getResults().size()));
                Log.d("itishere2", String.valueOf(list2.size()));
            }

            @Override
            public void onFailure(Call<MovieListHomeApi> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        Call<MovieListHomeApi> call3 = tvMvApi.listOfPrimaryInfo("movie","top_rated");
        call3.enqueue(new Callback<MovieListHomeApi>() {
            @Override
            public void onResponse(Call<MovieListHomeApi> call, Response<MovieListHomeApi> response) {
                list3 = response.body().getResults();
                adapter3 = new AdapterClassHome(list3);
                rv3.setAdapter(adapter3);
                adapter3.setOnItemClickListener(new AdapterClassSearch.OnItemClickListener() {
                    @Override
                    public void onItemClick(int id) {
                        Intent intent = new Intent(MainActivity.this, FullDetailsActivity.class);
                        intent.putExtra("idForMovie",id);
                        intent.putExtra("typeOfDetails","movie");
                        startActivity(intent);
                    }
                });
                //Log.d("itishere", String.valueOf(response.body().getResults().size()));
            }

            @Override
            public void onFailure(Call<MovieListHomeApi> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        Call<MovieListHomeApi> call4 = tvMvApi.listOfPrimaryInfo("movie","popular");
        call4.enqueue(new Callback<MovieListHomeApi>() {
            @Override
            public void onResponse(Call<MovieListHomeApi> call, Response<MovieListHomeApi> response) {
                list4 = response.body().getResults();
                adapter4 = new AdapterClassHome(list4);
                rv4.setAdapter(adapter4);
                adapter4.setOnItemClickListener(new AdapterClassSearch.OnItemClickListener() {
                    @Override
                    public void onItemClick(int id) {
                        Intent intent = new Intent(MainActivity.this, FullDetailsActivity.class);
                        intent.putExtra("idForMovie",id);
                        intent.putExtra("typeOfDetails","movie");
                        startActivity(intent);
                    }
                });
                //Log.d("itishere", String.valueOf(response.body().getResults().size()));
                //Log.d("poster path",response.body().getResults().get(1).getPosterPath());
            }

            @Override
            public void onFailure(Call<MovieListHomeApi> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

}