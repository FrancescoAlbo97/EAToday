package com.eatoday;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.eatoday.model.Ingredient;
import com.eatoday.model.RecipeCollection;
import com.eatoday.model.User;
import com.eatoday.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private ListView listViewIngredient;
    private ImageView ivImageRecipe;
    private TextView tvName;
    private TextView description;
    private Button btnBack;
    private Button btnOrd;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        setTitle(R.string.app_name);

        ivImageRecipe = findViewById(R.id.imageViewOfRecipe);
        tvName = findViewById(R.id.tvName);
        description = findViewById(R.id.textarea);
        btnBack = findViewById(R.id.btn_back);
        btnOrd = findViewById(R.id.btn_ord);

        Intent intent = getIntent();
        if (intent.hasExtra("recipeIndex")) {
            index = getIntent().getIntExtra("recipeIndex",0);
            tvName.setText(RecipeCollection.recipesList.get(index).getName());
            Glide.with(this.getApplicationContext())
                    .load(RecipeCollection.recipesList.get(index).getImageUrl())
                    .placeholder(R.drawable.ic_broken_image_black_24dp)
                    .into(ivImageRecipe);
            description.setText(RecipeCollection.recipesList.get(index).getDescription());
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


        btnOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder (DetailsActivity.this);
                if( User.getIsLog()){
                    builder.setTitle("Ordine pronto");
                    builder.setMessage("Grazie per aver utilizzato EAToday!");
                    builder.setPositiveButton("Home", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }else{
                    builder.setTitle("Attezione");
                    builder.setMessage("Per poter effettuare l'ordine bisogna effettuare il login.");
                    builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
