package com.developer.alfin.embededvideoapi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.alfin.embededvideoapi.DetailActivity;
import com.developer.alfin.embededvideoapi.R;
import com.developer.alfin.embededvideoapi.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<Movies> movies = new ArrayList<>();

    public void setDataMovies(ArrayList<Movies> moviesItem) {
        movies.clear();
        movies.addAll(moviesItem);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_video, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.bindata(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgView;
        TextView tvTitle, tvReleaseDate;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.image_item_movie);
            tvTitle = itemView.findViewById(R.id.tv_title_movie);
            tvReleaseDate = itemView.findViewById(R.id.tv_rd_movie);

            itemView.setOnClickListener(this);
        }

        public void bindata(Movies movies) {

            tvTitle.setText(movies.getTitle());
            tvReleaseDate.setText(movies.getReleaseDate());

            Picasso.get().load(movies.getImgMovie()).into(imgView);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            Movies movies1 = movies.get(position);

            movies1.setTitle(movies1.getTitle());
            movies1.setOverview(movies1.getOverview());

            Intent movieObject = new Intent(itemView.getContext(), DetailActivity.class);
            movieObject.putExtra("MOVIE_EXTRA", movies1);
            itemView.getContext().startActivity(movieObject);


        }
    }
}
