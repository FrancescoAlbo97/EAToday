package com.eatoday.ui.recipes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eatoday.R;
import com.eatoday.model.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<Recipe> recipes;
    private ItemClicked activity;
    private Context context;
    private Activity thisClass;


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

        private ImageView ivImageRecipe;
        private ImageView imageVTime;
        private ImageView imageVPrice;
        private ImageView imageVDifficulty;
        private ImageView imageVType;
        private TextView tvRecipeName;
        private TextView tvRecipeTime;
        private TextView tvRecipeDifficulty;
        private TextView tvRecipePrice;
        private TextView tvRecipeType;
        private LinearLayout linearLayoutTop;
        private LinearLayout linearLayoutBottom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImageRecipe = itemView.findViewById(R.id.ivImageRecipe);
            imageVTime = itemView.findViewById(R.id.imageVTime);
            imageVPrice = itemView.findViewById(R.id.imageVPrice);
            imageVDifficulty = itemView.findViewById(R.id.imageVDifficulty);
            imageVType = itemView.findViewById(R.id.imageVType);
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
            tvRecipeTime = itemView.findViewById(R.id.tvRecipeTime);
            tvRecipeDifficulty = itemView.findViewById(R.id.tvRecipeDifficulty);
            tvRecipePrice = itemView.findViewById(R.id.tvRecipePrice);
            tvRecipeType = itemView.findViewById(R.id.tvRecipeType);
            linearLayoutTop = itemView.findViewById(R.id.layout_top);
            linearLayoutBottom = itemView.findViewById(R.id.layout_bottom);


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
        Recipe recipe = recipes.get(position);
        holder.itemView.setTag(recipe);
        holder.tvRecipeName.setText(recipes.get(position).getName());
        Glide.with(thisClass.getApplicationContext())
                .load(recipe.getImageUrl())
                .placeholder(R.drawable.ic_broken_image_black_24dp)
                .into(holder.ivImageRecipe);
        holder.tvRecipeTime.setText(recipe.getTime());
        holder.tvRecipeDifficulty.setText(recipe.getDifficulty());
        holder.tvRecipePrice.setText(recipe.getPrice());
        holder.tvRecipeType.setText(recipe.getType());
        ViewGroup.LayoutParams paramsTop = holder.linearLayoutTop.getLayoutParams();
        paramsTop.height = 115;
        holder.linearLayoutTop.setLayoutParams(paramsTop);
        ViewGroup.LayoutParams paramsBottom = holder.linearLayoutBottom.getLayoutParams();
        paramsBottom.height = 115;
        holder.linearLayoutBottom.setLayoutParams(paramsBottom);

        if(position == 0){
            holder.itemView.setPadding(0,0,0,0);
        }
        else if(position == (recipes.size() -1)){
            holder.itemView.setPadding(0,0,0,200);
        }
        /*
        ViewGroup.LayoutParams params = holder.imageVTime.getLayoutParams();
        params.width=150;
        params.height=150;
        holder.imageVTime.setLayoutParams(params);
        holder.imageVDifficulty.setLayoutParams(params);
        holder.imageVPrice.setLayoutParams(params);
        holder.imageVType.setLayoutParams(params);*/
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
