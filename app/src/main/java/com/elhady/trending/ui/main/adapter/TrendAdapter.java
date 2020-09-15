package com.elhady.trending.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elhady.trending.R;
import com.elhady.trending.model.TrendModel;

import java.util.ArrayList;
import java.util.List;

public class TrendAdapter extends RecyclerView.Adapter<TrendAdapter.TrendViewHolder> {

    public void setTrendsList(ArrayList<TrendModel> trendsList) {
        this.trendsList = trendsList;
        notifyDataSetChanged();
    }

    private ArrayList<TrendModel> trendsList = new ArrayList<>();

    @NonNull
    @Override
    public TrendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trend_item, parent, false);
        return new TrendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendViewHolder holder, int position) {
        holder.authorTV.setText(trendsList.get(position).getAuthor());
        holder.nameTV.setText(trendsList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return trendsList.size();
    }



    class TrendViewHolder extends RecyclerView.ViewHolder {
        TextView authorTV, nameTV;
        public TrendViewHolder(@NonNull View itemView) {
            super(itemView);
            authorTV = itemView.findViewById(R.id.author);
            nameTV = itemView.findViewById(R.id.name);

        }
    }
}
