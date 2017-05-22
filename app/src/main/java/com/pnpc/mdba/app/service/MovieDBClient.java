package com.pnpc.mdba.app.service;

import com.pnpc.mdba.app.model.Genre;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.model.SearchResult;

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

    @GET("/genre/movie/list")
    Observable<Genre> getMovieGenres(@Query("api_key") String apiKey);

    @GET("/discover/movie")
    Observable<Genre> searchMovieDiscover(@Query("api_key") String apiKey, @Query("language") String language, @Query("with_genres") int genre, @Query("include_adult") boolean includeAdult, @Query("include_video") boolean includeVideo);

    @GET()
    Observable<SearchResult> searchMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("include_adult") boolean includeAdult, @Query("query") String search_query);


}
