package com.pnpc.mdba.app.service;

import com.pnpc.mdba.app.model.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by markusmcgee on 5/19/17.
 */

public interface MovieDBClient {

    @GET("/3/movie/{id}")
    Observable<Movie> getMovie(@Path("id") int movieId, @Query("api_key") String apiKey);

//    @GET("/movie/550")
//    Observable<Movie> getMovies();

}
