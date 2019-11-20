package com.ac.achan.dbtv.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ac.achan.dbtv.R;
import com.ac.achan.dbtv.network.VolleySingleton;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFeedAdapter extends RecyclerView.Adapter<MainFeedAdapter.FeedViewHolder> {
    private Context context;
    private ImageLoader imageLoader;
    private ArrayList<HashMap<String, String>> contentArray;

    private AdapterListener adapterListener;

    public interface AdapterListener {
        void onItemClick(int position, String title, String date, String url, String description);
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public MainFeedAdapter(Context context, ArrayList<HashMap<String, String>> contentArray) {
        this.context = context;
        this.contentArray = contentArray;
        this.imageLoader = VolleySingleton.getInstance(this.context).getImageLoader();
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        FeedViewHolder viewHolder;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_feed_view_holder, parent, false);
        viewHolder = new FeedViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, final int position) {
        final String title = contentArray.get(position).get("title");
        final String date = contentArray.get(position).get("publishedAt");
        final String displayImage = contentArray.get(position).get("thumbnailUrl");
        final String videoId = contentArray.get(position).get("videoId");
        final String description = contentArray.get(position).get("description");

        holder.title.setText(title);
        holder.date.setText(date);
        holder.displayImage.setImageUrl(displayImage, imageLoader);

        holder.displayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainFeedAdapter: ", title + " CLICKED " + position);
                Log.d("MainFeedAdapter: ", date + " CLICKED " + position);
                Log.d("MainFeedAdapter: ", videoId + " CLICKED " + position);

                if (adapterListener != null) {
                    adapterListener.onItemClick(position, title, date, videoId, description);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return contentArray.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView date;
        protected NetworkImageView displayImage;

        public FeedViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            displayImage = (NetworkImageView) itemView.findViewById(R.id.displayImage);
        }
    }
}

