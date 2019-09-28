package com.eatoday.ui.recipes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIngredientName = itemView.findViewById(R.id.tvIngredientName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(ingredients.indexOf((Ingredient) view.getTag()));
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
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
