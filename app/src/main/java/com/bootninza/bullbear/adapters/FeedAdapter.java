package com.bootninza.bullbear.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bootninza.bullbear.R;
import com.bootninza.bullbear.util.CircleTransform;
import com.bootninza.bullbear.webservices.models.Feed;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    Picasso picasso;

    public FeedAdapter(List<Feed> feedList) {
        picasso = Picasso.get();
        picasso.setLoggingEnabled(true);
        picasso.setIndicatorsEnabled(true);
        this.feedList = feedList;
    }

    List<Feed> feedList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(feedList.get(position).getPostContent());
        Log.i("Image url ", feedList.get(position).toString());
        picasso.load(null!=feedList.get(position).getImgUrls() && feedList.get(position).getImgUrls().size()>0? feedList.get(position).getImgUrls().get(0).getUrl() : "https://www.macrotrends.net/assets/images/large/stock-market-cycles-historical-chart.png")
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.getImageView());

        picasso.load(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .transform(new CircleTransform())
                .into(holder.getAvatar());
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public void updateFeedList(List<Feed> list) {
        Log.i("Feed Adapter", list.toString());
        this.feedList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private final ImageView avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_feed_description);
            imageView = itemView.findViewById(R.id.image_feed_item);
            avatar = itemView.findViewById(R.id.image_feed_item_avatar);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public ImageView getAvatar() {
            return avatar;
        }
    }
}
