package com.example.acer.myapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.myapplication.Models.Biodata;
import com.example.acer.myapplication.Models.Movie;
import com.example.acer.myapplication.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Biodata> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.firstname);
            genre = (TextView) view.findViewById(R.id.lastname);
            year = (TextView) view.findViewById(R.id.email);
        }
    }


    public MoviesAdapter(List<Biodata> moviesList) {

        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Biodata movie = moviesList.get(position);
        holder.title.setText(movie.getFName());
        holder.genre.setText(movie.getLName());
        holder.year.setText(movie.getEmail());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
