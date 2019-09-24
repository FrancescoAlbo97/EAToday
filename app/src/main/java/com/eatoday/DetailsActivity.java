package com.eatoday;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.eatoday.model.Ingredient;
import com.eatoday.model.RecipeCollection;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    ListView listViewIngredient;
    ImageView ivImageRecipe;
    TextView tvName;
    //Button btnBack;
    //Button btnOrd;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        setTitle(R.string.app_name);

        ivImageRecipe = findViewById(R.id.imageViewOfRecipe);
        tvName = findViewById(R.id.tvName);
        //btnBack = findViewById(R.id.btn_back);
        //btnOrd = findViewById(R.id.btn_ord);

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

        listViewIngredient = findViewById(R.id.list_ingredients);


        final List< String > listElementsArrayList = new ArrayList< String >();
        for(Ingredient in : RecipeCollection.recipesList.get(index).getIngredients()){
            listElementsArrayList.add(in.getName());
        }

        final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                (DetailsActivity.this, android.R.layout.simple_list_item_1,
                        listElementsArrayList);

        listViewIngredient.setAdapter(adapter);

        ViewGroup vg = listViewIngredient;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listViewIngredient.getLayoutParams();
        par.height = totalHeight + (listViewIngredient.getDividerHeight() * (adapter.getCount() - 1));
        listViewIngredient.setLayoutParams(par);
        listViewIngredient.requestLayout();
        listViewIngredient.setScrollContainer(false);

        adapter.notifyDataSetChanged();

/*
        btnOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentM = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intentM);
                finish();
            }
        });*/
/*
        Intent intent = getIntent();
        if (intent.hasExtra()) {
            String nameFromIntent = getIntent().getStringExtra(Constant.KEY_EMAIL);
            email.setText(nameFromIntent);
        }

        ViewGroup.LayoutParams par = listViewIngredient.getLayoutParams();
        par.height = listViewIngredient.getDividerHeight() * (adapter.getCount() - 1);
        listViewIngredient.setLayoutParams(par);
        listViewIngredient.requestLayout();
*/
    }

}
