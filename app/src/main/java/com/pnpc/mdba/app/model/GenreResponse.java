package com.pnpc.mdba.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by markusmcgee on 5/21/17.
 */

public class GenreResponse {
    @SerializedName("genres")
    public List<Genre> genreList;
}
