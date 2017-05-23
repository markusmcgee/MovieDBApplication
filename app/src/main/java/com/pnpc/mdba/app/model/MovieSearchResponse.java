package com.pnpc.mdba.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by markusmcgee on 5/22/17.
 */

public class MovieSearchResponse {
    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public List<Movie> movieList;

    @SerializedName("total_results")
    public int totlResults;

    @SerializedName("total_pages")
    public int totalPages;

}
