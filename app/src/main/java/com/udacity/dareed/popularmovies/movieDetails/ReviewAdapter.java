package com.udacity.dareed.popularmovies.movieDetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.udacity.dareed.popularmovies.R;
import com.udacity.dareed.popularmovies.models.Review;
import com.udacity.dareed.popularmovies.models.Trailer;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> implements View.OnClickListener{

    List<Review> reviews = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.reviews = reviews;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ReviewViewHolder(layoutInflater.inflate(R.layout.list_item_review, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder reviewViewHolder, int position) {
        reviewViewHolder.bind(reviews.get(position), this);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    @Override
    public void onClick(View v) {
        Trailer trailer = (Trailer) v.getTag();
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Trailer.Tools.buildYoutubeUrl(trailer))));
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.content)
        TextView content;
        @InjectView(R.id.author)
        TextView author;
        View rootView;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.inject(this, itemView);
        }

        public void bind(Review review, View.OnClickListener onClickListener) {
            content.setText(review.content);
            author.setText(author.getContext().getString(R.string.review_by, review.author));
            rootView.setTag(review);
            rootView.setOnClickListener(onClickListener);
        }
    }
}
