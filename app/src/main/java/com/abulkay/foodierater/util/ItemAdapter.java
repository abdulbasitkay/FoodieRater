package com.abulkay.foodierater.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abulkay.foodierater.R;

import java.util.List;

/**
 * Created by akay on 4/3/16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder> {

    private View.OnClickListener clickListener;
    private Context context;
    private List<Item> items;
    private Item item;

    public ItemAdapter(View.OnClickListener clickListener, Context context, List<Item> items) {
        this.clickListener = clickListener;
        this.context = context;
        this.items = items;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        view.setOnClickListener(clickListener);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final Item item = items.get(position);

        holder.mealName.setText(item.getMealName());
        //mage
        holder.rating.setRating(item.getRating());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView mealName;
        protected RatingBar rating;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.mealImage);
            this.mealName = (TextView) itemView.findViewById(R.id.mealTitle);
            this.rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }
    }
}
