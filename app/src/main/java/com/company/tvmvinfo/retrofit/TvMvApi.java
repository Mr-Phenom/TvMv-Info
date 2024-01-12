package com.company.tvmvinfo.retrofit;

import com.company.tvmvinfo.modelClass.CastOfMovies;
import com.company.tvmvinfo.modelClass.MovieDetails;
import com.company.tvmvinfo.modelClass.MovieListHomeApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvMvApi {
    @GET("/3/{type}/{category}?api_key=f16ecf8af1cc2a0db8e60a35e54c32fa")
    Call<MovieListHomeApi> listOfPrimaryInfo(
            @Path("type") String type,
            @Path("category") String category
    );

    @GET("/3/{type}/{category}?api_key=f16ecf8af1cc2a0db8e60a35e54c32fa")
    Call<MovieListHomeApi> listOfMoreInfo(
            @Path("type") String type,
            @Path("category") String category,
            @Query("page") int page
    );

    @GET("/3/{type}/{id}?api_key=f16ecf8af1cc2a0db8e60a35e54c32fa")
    Call<MovieDetails> detailsOfMovie(
            @Path("type") String type,
            @Path("id") int id
    );

    @GET("/3/search/{type}?api_key=f16ecf8af1cc2a0db8e60a35e54c32fa")
    Call<MovieListHomeApi> searchItem(
            @Path("type") String type,
            @Query("query") String name
    );

    @GET("/3/{type}/{id}/credits?api_key=f16ecf8af1cc2a0db8e60a35e54c32fa")
    Call<CastOfMovies> getCasts(
            @Path("type") String type,
            @Path("id") int id
    );
}

