package com.udacity.dareed.popularmovies.movieDetails;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.udacity.dareed.popularmovies.R;
import com.udacity.dareed.popularmovies.models.Trailer;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> implements View.OnClickListener{

    List<Trailer> trailers = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public TrailerAdapter(Context context, List<Trailer> trailers) {
        this.trailers = trailers;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new TrailerViewHolder(layoutInflater.inflate(R.layout.list_item_trailer, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder trailerViewHolder, int position) {
        trailerViewHolder.bind(trailers.get(position), this);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    @Override
    public void onClick(View v) {
        Trailer trailer = (Trailer) v.getTag();
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Trailer.Tools.buildYoutubeUrl(trailer))));
    }

    static class TrailerViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.title)
        TextView title;
        View rootView;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.inject(this, itemView);
        }

        public void bind(Trailer trailer, View.OnClickListener onClickListener) {
            title.setText(trailer.name);
            rootView.setTag(trailer);
            rootView.setOnClickListener(onClickListener);
        }
    }
}

