package com.pnpc.mdba.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pnpc.mdba.app.R;
import com.pnpc.mdba.app.model.Genre;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.model.MovieSearchResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markusmcgee on 5/21/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String TAG = "GenreAdapter";
    private final MovieListener movieListener;
    private MovieSearchResponse response;

    public interface MovieListener {
        void onMovieClick(int movie);

    }

    public MovieAdapter(MovieListener movieListener) {
        this.movieListener = movieListener;
    }

    List<Movie> movieList;

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        if (movie.getOverview() != null)
            holder.overview.setText(movie.getOverview());

        if (movie.getTitle() != null)
            holder.title.setText(movie.getTitle());

        if (movie.getVoteAverage() != null)
            holder.voteCount.setText(movie.getVoteAverage().toString());

        if (movie.getPopularity() != null)
            holder.popularity.setText(movie.getPopularity().toString());

        if (movie.getReleaseDate() != null)
            holder.releaseDate.setText(movie.getReleaseDate());

        if (movie.getId() != null)
            holder.setMovieId(movie.getId());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setData(MovieSearchResponse response) {
        if (this.movieList == null)
            this.movieList = new ArrayList<>();
        else
            this.movieList.clear();

        this.response = response;
        this.movieList.addAll(response.movieList);

        notifyDataSetChanged();
    }

    public void addDataItem(Movie movie){
        this.movieList.add(movie);
        notifyDataSetChanged();
    }

    class MovieViewHolder extends ViewHolder implements View.OnClickListener {

        @BindView(R.id.overview)
        TextView overview;

        @BindView(R.id.release_date)
        TextView releaseDate;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.popularity)
        TextView popularity;

        @BindView(R.id.vote_count)
        TextView voteCount;

        private int movieId = 0;

        MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            movieListener.onMovieClick(movieId);

        }

        void setMovieId(int movieId) {
            this.movieId = movieId;
        }
    }
}
