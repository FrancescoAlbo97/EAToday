package com.eatoday;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eatoday.model.Ingredient;
import com.eatoday.model.RecipeCollection;
import com.eatoday.model.User;
import com.eatoday.ui.recipes.IngredientAdapter;
import com.eatoday.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements IngredientAdapter.ItemClicked {

    //private ListView listViewIngredient;
    private ImageView ivImageRecipe;
    private TextView tvName;
    private TextView description;
    private Button btnBack;
    private Button btnOrd;
    private int index;
    private RecyclerView recyclerViewIngredient;
    private RecyclerView.Adapter myIngredientAdapter;
    private RecyclerView.LayoutManager layoutManagerIngredient;


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

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Intent intent = getIntent();
        if (intent.hasExtra("recipeIndex")) {
            index = getIntent().getIntExtra("recipeIndex",0);
            tvName.setText(RecipeCollection.recipesList.get(index).getName());
            Glide.with(this.getApplicationContext())
                    .load(RecipeCollection.recipesList.get(index).getImageUrl())
                    .placeholder(R.drawable.ic_broken_image_black_24dp)
                    .into(ivImageRecipe);
            description.setText(RecipeCollection.recipesList.get(index).getDescription());
            ingredients = RecipeCollection.recipesList.get(index).getIngredients();
        }

        recyclerViewIngredient = findViewById(R.id.list_ingredients);
        recyclerViewIngredient.setHasFixedSize(true);
        layoutManagerIngredient = new LinearLayoutManager(this);
        recyclerViewIngredient.setLayoutManager(layoutManagerIngredient);
        myIngredientAdapter = new IngredientAdapter(this,(Context) DetailsActivity.this, ingredients);
        recyclerViewIngredient.setAdapter(myIngredientAdapter);

        ViewGroup.LayoutParams layoutParams = ivImageRecipe.getLayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int heigh = (int) (displayMetrics.widthPixels*(0.45));
        layoutParams.height = heigh;
        ivImageRecipe.setLayoutParams(layoutParams);



/*
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
*/

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

    @Override
    public void onItemClicked(int index) {

    }
}
