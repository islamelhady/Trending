package com.elhady.trending.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elhady.trending.databinding.TrendItemBinding;
import com.elhady.trending.model.ItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendViewHolder> {

    private TrendItemBinding binding;
    private Context context;
    private static List<ItemModel> itemModels = new ArrayList<>();

    public TrendingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TrendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = TrendItemBinding.inflate(inflater, parent, false);
        return new TrendingAdapter.TrendViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendViewHolder holder, int position) {
        ItemModel data = itemModels.get(position);
        String count = (data.getStar_count() >= 1000 ? data.getStar_count()+"" : String.valueOf(data.getStar_count()));

        if (data.getName()!= null) holder.binding.author.setText(data.getOwners().getName());
        if (data.getName()!= null) holder.binding.nameRepository.setText(data.getName());
        if (data.getDescription()!= null) holder.binding.description.setText(data.getDescription());
        if (data.getOwners() != null) Picasso.get().load(data.getOwners().getAvatar_url()).into(holder.binding.imgProfile);
        if (data.getLanguage() != null) holder.binding.language.setText(data.getLanguage());
        if (data.getLicenses() != null) holder.binding.licenseView.setText(data.getLicenses().getName());
        holder.binding.stars.setText(count);

        boolean isExpandable = itemModels.get(position).isExpandable();
        holder.binding.expandLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        holder.binding.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemModel expender = itemModels.get(position);
                expender.setExpandable(!expender.isExpandable());
                notifyItemChanged(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return (itemModels == null ? 0 : itemModels.size());
    }


    class TrendViewHolder extends RecyclerView.ViewHolder {
            private TrendItemBinding binding;
            public TrendViewHolder(TrendItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }

    public void setTrendsList(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
        notifyDataSetChanged();
    }

    public void clearData() {
        itemModels = new ArrayList<>();
        notifyDataSetChanged();
    }
}
