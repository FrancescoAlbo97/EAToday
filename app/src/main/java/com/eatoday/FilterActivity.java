package com.eatoday;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.eatoday.model.Ingredient;
import com.eatoday.model.RecipeCollection;
import com.eatoday.service.IngredientLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class FilterActivity extends AppCompatActivity {
    //prima scarico gli ingredienti, poi con il nome, se esiste lo aggiungo, altrimenti Toast che non esiste
    ListView listViewOK;
    EditText ingredientOK;
    Button btnOK;
    ListView listViewNO;
    EditText ingredientNO;
    Button btnNO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_main);
        setTitle(R.string.app_name);

        listViewOK = findViewById(R.id.list_ingredients_ok);
        listViewNO = findViewById(R.id.list_ingredients_no);
        ingredientOK = findViewById(R.id.edit_ingredient_ok);
        ingredientNO = findViewById(R.id.edit_ingredient_no);
        btnOK = findViewById(R.id.btn_ok);
        btnNO = findViewById(R.id.btn_no);
/*
        CountDownLatch latch = new CountDownLatch(1);
        IngredientLoader ingredientLoader = new IngredientLoader((Context) FilterActivity.this, latch);
        ingredientLoader.execute();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/



/*
        Intent intent = getIntent();
        if (intent.hasExtra("recipeIndex")) {
            index = getIntent().getIntExtra("recipeIndex",0);
            tvName.setText(RecipeCollection.recipesList.get(index).getName());
            Glide.with(this.getApplicationContext())
                    .load(RecipeCollection.recipesList.get(index).getImageUrl())
                    .placeholder(R.drawable.ic_broken_image_black_24dp)
                    .into(ivImageRecipe);
        }
        ViewGroup.LayoutParams layoutParams = ivImageRecipe.getLayoutParams();
        layoutParams.height = 500;
        ivImageRecipe.setLayoutParams(layoutParams);

        recyclerViewIngredient = findViewById(R.id.list_ingredients);
        recyclerViewIngredient.setHasFixedSize(true);

        myIngredientAdapter = new IngredientAdapter(this,(Context) DetailsActivity.this, RecipeCollection.recipesList.get(index).getIngredients());
        recyclerViewIngredient.setAdapter(myIngredientAdapter);

        layoutManagerIngredient = new LinearLayoutManager(this);
        recyclerViewIngredient.setLayoutManager(layoutManagerIngredient);


        Intent intent = getIntent();
        if (intent.hasExtra()) {
            String nameFromIntent = getIntent().getStringExtra(Constant.KEY_EMAIL);
            email.setText(nameFromIntent);
        }





            RecyclerView recyclerViewIngredient;
    RecyclerView.LayoutManager layoutManagerIngredient;
    ImageView ivImageRecipe;
    TextView tvName;
    IngredientAdapter myIngredientAdapter;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        setTitle(R.string.app_name);

        ivImageRecipe = findViewById(R.id.imageViewOfRecipe);
        tvName = findViewById(R.id.tvName);

        Intent intent = getIntent();
        if (intent.hasExtra("recipeIndex")) {
            index = getIntent().getIntExtra("recipeIndex",0);
            tvName.setText(RecipeCollection.recipesList.get(index).getName());
            Glide.with(this.getApplicationContext())
                    .load(RecipeCollection.recipesList.get(index).getImageUrl())
                    .placeholder(R.drawable.ic_broken_image_black_24dp)
                    .into(ivImageRecipe);
        }
        ViewGroup.LayoutParams layoutParams = ivImageRecipe.getLayoutParams();
        layoutParams.height = 500;
        ivImageRecipe.setLayoutParams(layoutParams);

        recyclerViewIngredient = findViewById(R.id.list_ingredients);
        recyclerViewIngredient.setHasFixedSize(true);

        myIngredientAdapter = new IngredientAdapter(this,(Context) DetailsActivity.this, RecipeCollection.recipesList.get(index).getIngredients());
        recyclerViewIngredient.setAdapter(myIngredientAdapter);

        layoutManagerIngredient = new LinearLayoutManager(this);
        recyclerViewIngredient.setLayoutManager(layoutManagerIngredient);
*/
    }
}
