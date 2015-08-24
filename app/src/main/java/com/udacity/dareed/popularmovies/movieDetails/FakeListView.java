package com.udacity.dareed.popularmovies.movieDetails;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class FakeListView extends LinearLayout implements OnClickListener {

    private RecyclerView.Adapter adapter;
    private OnFakeListItemClickListener onItemClickListener;

    public FakeListView(Context context) {
        this(context, null, 0);
    }

    public FakeListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FakeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(VERTICAL);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        populate();
    }

    public void setOnItemClickListener(OnFakeListItemClickListener listener) {
        onItemClickListener = listener;
    }

    private void populate() {
        for (int i = 0; i < adapter.getItemCount(); i++) {
            RecyclerView.ViewHolder viewHolder = adapter.createViewHolder(this, 0);
            adapter.onBindViewHolder(viewHolder, i);
            addView(viewHolder.itemView);
            viewHolder.itemView.setOnClickListener(this);
        }
    }

    public interface OnFakeListItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(getRealPosition(v));
        }
    }

    private int getRealPosition(View v) {
        return this.indexOfChild(v);
    }

}
