package com.eatoday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.eatoday.model.RecipeCollection;
import com.eatoday.model.User;
import com.eatoday.service.AccessLoader;
import com.eatoday.service.RecipeLoader;
import com.eatoday.ui.recipes.RecipeAdapter;
import com.eatoday.ui.toolbar.InitToolbar;
import com.eatoday.util.PreferenceUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ItemClicked{

    InitToolbar initToolbar;
    RecyclerView recyclerViewRecipe;
    RecyclerView.Adapter myRecipeAdapter;
    RecyclerView.LayoutManager layoutManagerRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar = new InitToolbar(this);
        this.setSupportActionBar(initToolbar.getToolbar());
        initToolbar.init(this.getSupportActionBar());



        recyclerViewRecipe = findViewById(R.id.list_details);
        recyclerViewRecipe.setHasFixedSize(true);
        layoutManagerRecipe = new LinearLayoutManager(this);
        recyclerViewRecipe.setLayoutManager(layoutManagerRecipe);

        CountDownLatch latch = new CountDownLatch(1);
        RecipeLoader recipeLoader = new RecipeLoader((Context) MainActivity.this, latch);
        recipeLoader.execute("");

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myRecipeAdapter = new RecipeAdapter(this, (Context) MainActivity.this, RecipeCollection.recipesList);
        recyclerViewRecipe.setAdapter(myRecipeAdapter);


        String email;
        String password;

        if (PreferenceUtils.getEmail(getApplicationContext()) != null) {
            email = PreferenceUtils.getEmail(getApplicationContext());
            password = PreferenceUtils.getPassword(getApplicationContext());

            CountDownLatch latch2 = new CountDownLatch(1);
            AccessLoader accessLoader = new AccessLoader((Context) MainActivity.this, latch2);
            accessLoader.execute("login", email, password);
            try {
                latch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        initToolbar.getNavigationView().setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (initToolbar.setOnNavigationItemSelected(menuItem)){
                    startActivity(initToolbar.getIntent());
                    //TODO settare i backbutton con Intent precedente;
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(initToolbar.setOnOptionsItemSelected(item)) return true;
        else return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClicked(int index) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class );
        intent.putExtra("recipeIndex", index);
        startActivity(intent);
        //Toast.makeText(this,"item selected" + RecipeCollection.recipesList.get(index).toString(),Toast.LENGTH_SHORT).show();
    }
}
