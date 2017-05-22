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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markusmcgee on 5/21/17.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    private static final String TAG = "GenreAdapter";
    private List<Genre> genreList;

    public GenreAdapter() {
    }


    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_item_view, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        Genre genre = genreList.get(position);
        holder.setGenreId(genre.getId());
        holder.genreName.setText(genre.getName());
    }


    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public void setData(List<Genre> list) {
        if (this.genreList == null)
            this.genreList = new ArrayList<>();
        else
            this.genreList.clear();

        genreList.addAll(list);
        notifyDataSetChanged();
    }

    class GenreViewHolder extends ViewHolder implements View.OnClickListener {

        @BindView(R.id.genre_name)
        TextView genreName;

        private int genreId = 0;

        GenreViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Log.d(TAG, "debug");
            //Todo: Add Click handler logic
        }

        void setGenreId(int genreId) {
            this.genreId = genreId;
        }
    }
}
