package com.bootninza.bullbear.adapters;

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
import com.bootninza.bullbear.webservices.models.Equity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EquityAdapter extends RecyclerView.Adapter<EquityAdapter.ViewHolder> {
    Picasso picasso;
    List<Equity> equityList = new ArrayList<>();

    public EquityAdapter(List<Equity> equityList) {
        picasso = Picasso.get();
        picasso.setLoggingEnabled(true);
        picasso.setIndicatorsEnabled(true);
        this.equityList = equityList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equity_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getEquityName().setText(equityList.get(position).getName());
        holder.getEquitySymbol().setText(equityList.get(position).getSymbol());
        picasso.load("https://m.economictimes.com/thumb/msid-79006216,width-1200,height-900,resizemode-4,imgsize-325140/wipro.jpg")
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .transform(new CircleTransform())
                .into(holder.getAvatar());

    }

    @Override
    public int getItemCount() {
        return equityList.size();
    }

    public void updateEquityList(List<Equity> list) {
        Log.i("Equity Adapter", list.toString());
        this.equityList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView equityName;
        private final TextView equitySymbol;
        private final ImageView avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            equityName = itemView.findViewById(R.id.text_equity_item_name);
            equitySymbol = itemView.findViewById(R.id.text_equity_item_symbol);
            avatar = itemView.findViewById(R.id.image_equity_item_avatar);
        }

        public TextView getEquityName() {
            return equityName;
        }

        public TextView getEquitySymbol() {
            return equitySymbol;
        }

        public ImageView getAvatar() {
            return avatar;
        }
    }
}
