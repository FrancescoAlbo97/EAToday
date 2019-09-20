package com.eatoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eatoday.model.RecipeCollection;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView recyclerViewIngredient;
    ImageView ivImageRecipe;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        setTitle(R.string.app_name);

        ivImageRecipe = findViewById(R.id.imageViewOfRecipe);
        tvName = findViewById(R.id.tvName);

        Intent intent = getIntent();
        if (intent.hasExtra("recipeIndex")) {
            int index = getIntent().getIntExtra("recipeIndex",0);
            tvName.setText(RecipeCollection.recipesList.get(index).getName());
            Glide.with(this.getApplicationContext())
                    .load(RecipeCollection.recipesList.get(index).getImageUrl())
                    .placeholder(R.drawable.ic_broken_image_black_24dp)
                    .into(ivImageRecipe);
        }
        ViewGroup.LayoutParams layoutParams = ivImageRecipe.getLayoutParams();
        layoutParams.height = 500;
        ivImageRecipe.setLayoutParams(layoutParams);

        recyclerViewIngredient = findViewById(R.id.list_details);
        recyclerViewIngredient.setHasFixedSize(true);

/*
        Intent intent = getIntent();
        if (intent.hasExtra()) {
            String nameFromIntent = getIntent().getStringExtra(Constant.KEY_EMAIL);
            email.setText(nameFromIntent);
        }
*/
    }
}
