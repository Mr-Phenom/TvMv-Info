package com.company.tvmvinfo.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTvMv {

    private static Retrofit retrofit;
    public static Retrofit getClient()
    {
        if (retrofit==null)
        {
            retrofit= new Retrofit.Builder().baseUrl("https://api.themoviedb.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
