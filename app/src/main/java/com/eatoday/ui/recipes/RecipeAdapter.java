package com.eatoday.ui.recipes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eatoday.R;
import com.eatoday.model.Recipe;
import com.eatoday.util.Constant;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<Recipe> recipes;
    ItemClicked activity;
    Context context;
    Activity thisClass;


    public RecipeAdapter(Activity thisClass, Context context, ArrayList<Recipe> list){
        this.recipes = list;
        this.activity = (ItemClicked) context;
        this.context = context;
        this.thisClass = thisClass;
    }

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivImageRecipe;
        TextView tvRecipeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImageRecipe = itemView.findViewById(R.id.ivImageRecipe);
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(recipes.indexOf((Recipe) view.getTag()));
                }
            });
        }
    }
    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        String name = recipes.get(position).getName();
        holder.itemView.setTag(recipes.get(position));
        holder.tvRecipeName.setText(name);
        String name_ = name.replaceAll(" ","_");
        Glide.with(thisClass.getApplicationContext())
                .load(Constant.IMAGES_PATH + name_ + Constant.PNG)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.ivImageRecipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
