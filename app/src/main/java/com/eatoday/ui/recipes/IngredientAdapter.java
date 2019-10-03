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

import com.eatoday.R;
import com.eatoday.model.Ingredient;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredients;
    private ItemClicked activity;
    private Context context;
    private Activity thisClass;


    public IngredientAdapter(Activity thisClass, Context context, ArrayList<Ingredient> list){
        this.ingredients = list;
        this.activity = (ItemClicked) context;
        this.context = context;
        this.thisClass = thisClass;
    }

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvIngredientName;
        TextView tvIngredientDescription;
        ImageView showDetails;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvIngredientName = itemView.findViewById(R.id.tvIngredientName);
            tvIngredientDescription = itemView.findViewById(R.id.ingredient_description);
            showDetails = itemView.findViewById(R.id.show_ingredient_details);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(ingredients.indexOf((Ingredient) view.getTag()));
                    if(tvIngredientDescription.getVisibility() == View.VISIBLE){
                        tvIngredientDescription.setVisibility(View.GONE);
                        showDetails.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    }else{
                        tvIngredientDescription.setVisibility(View.VISIBLE);
                        showDetails.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.itemView.setTag(ingredient);
        holder.tvIngredientName.setText(ingredient.getName());
        holder.tvIngredientDescription.setText(ingredient.getDescriptionToString());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
