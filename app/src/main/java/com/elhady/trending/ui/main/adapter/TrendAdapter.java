package com.elhady.trending.ui.main.adapter;

import android.graphics.Color;
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
        holder.descriptionTV.setText(trendsList.get(position).getUrl());

        if(trendsList.get(position).getLanguage() != null) {
            holder.languageTV.setVisibility(View.VISIBLE);
            //holder.itemLikes.setVisibility(View.VISIBLE);
            holder.languageTV.setText(trendsList.get(position).getLanguage());
            holder.languageTV.setTextColor(Color.parseColor(trendsList.get(position).getLanguageColor()));
        } else {
            //binding.itemLikes.setVisibility(View.GONE);
            holder.languageTV.setVisibility(View.GONE);
        }

        holder.starsTV.setText(trendsList.get(position).getStars()+"");
        holder.forksTV.setText(trendsList.get(position).getForks()+"");

    }

    @Override
    public int getItemCount() {
        return trendsList.size();
    }

    public void setTrendsList(ArrayList<TrendModel> trendsList) {
        this.trendsList = trendsList;
        notifyDataSetChanged();
    }



    class TrendViewHolder extends RecyclerView.ViewHolder {
        TextView authorTV, nameTV,descriptionTV,languageTV,starsTV,forksTV;
        public TrendViewHolder(@NonNull View itemView) {
            super(itemView);
            authorTV = itemView.findViewById(R.id.author);
            nameTV = itemView.findViewById(R.id.name_reposotory);
            descriptionTV = itemView.findViewById(R.id.description);
            languageTV = itemView.findViewById(R.id.language);
            starsTV = itemView.findViewById(R.id.stars);
            forksTV = itemView.findViewById(R.id.forks);

        }
    }
}
