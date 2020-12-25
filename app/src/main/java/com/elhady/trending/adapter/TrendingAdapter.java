package com.elhady.trending.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.elhady.trending.R;
import com.elhady.trending.model.ItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendViewHolder> {

    private Context context;
    private static List<ItemModel> itemModels = new ArrayList<>();

    public TrendingAdapter() {
    }

    @NonNull
    @Override
    public TrendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trend_item, parent, false);
        return new TrendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendViewHolder holder, int position) {
        ItemModel data = itemModels.get(position);
        String mCount = (data.getStar_count() >= 1000 ? data.getStar_count()+"" : String.valueOf(data.getStar_count()));

        if (data.getName()!= null) holder.setAuthor(data.getOwners().getName());
        if (data.getName()!= null) holder.setRepositoryName(data.getName());
        if (data.getDescription()!= null) holder.setDescription(data.getDescription());
        if (data.getOwners() != null) holder.setAvatar(data.getOwners().getAvatar_url());
        if (data.getLanguage() != null) holder.setLanguage(data.getLanguage());
        if (data.getLicenses() != null) holder.setLicense(data.getLicenses().getName());
        holder.setStarCount(mCount);

        boolean isExpandable = itemModels.get(position).isExpandable();
        holder.expandLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }


    @Override
    public int getItemCount() {
        return itemModels.size();
    }


    class TrendViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView ;
        private TextView author, name_repository,description,language,stars,licenseView;
        private ConstraintLayout constraintLayout;
        private RelativeLayout expandLayout;

        public TrendViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_profile);
            author = itemView.findViewById(R.id.author);
            name_repository = itemView.findViewById(R.id.name_repository);
            description = itemView.findViewById(R.id.description);
            language = itemView.findViewById(R.id.language);
            stars = itemView.findViewById(R.id.stars);
            licenseView = itemView.findViewById(R.id.licenseView);

            constraintLayout = itemView.findViewById(R.id.constraint_layout);
            expandLayout = itemView.findViewById(R.id.expand_layout);

//            Handling expand on item
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ItemModel expender = itemModels.get(getAdapterPosition());
                    expender.setExpandable(!expender.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
        public void setAuthor(String author) {
            this.author.setText(author);
        }

        public void setRepositoryName(String repositoryName) {
            this.name_repository.setText(repositoryName);
        }

        public void setDescription(String description) {
            this.description.setText(description);
        }

        public void setStarCount(String count) {
            this.stars.setText(count);
        }

        public void setLanguage(String language) {
            this.language.setText(language);
        }

        public void setAvatar(String avatar) {
            Picasso.get().load(avatar).into(imageView);
        }

        public void setLicense(String license) {
            this.licenseView.setText(license);
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
