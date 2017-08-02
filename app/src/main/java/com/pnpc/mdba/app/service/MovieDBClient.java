package com.pnpc.mdba.app.service;

import com.pnpc.mdba.app.model.Genre;
import com.pnpc.mdba.app.model.GenreResponse;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.model.MovieSearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by markusmcgee on 5/19/17.
 */

/*
* Name: Movie DB Client
* Description: The MovieDBClient is used by Retrofit to stub out the backend calls to be used in the this application.
* The initial setup is contained in the ApplicationModule.java file (providesRetrofit)
*/
public interface MovieDBClient {

    @GET("/3/movie/{id}")
    Observable<Movie> getMovie(@Path("id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("/3/genre/movie/list")
    Observable<GenreResponse> getMovieGenres(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("/3/discover/movie")
    Observable<Genre> searchMovieDiscover(@Query("api_key") String apiKey, @Query("language") String language, @Query("with_genres") int genre, @Query("include_adult") boolean includeAdult, @Query("include_video") boolean includeVideo);

    @GET("/3/search/movie")
    Observable<MovieSearchResponse> searchMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("include_adult") boolean includeAdult, @Query("query") String search_query, @Query("page") String page);


}
