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

import com.eatoday.model.Recipe;
import com.eatoday.model.RecipeCollection;
import com.eatoday.model.User;
import com.eatoday.service.RecipeLoader;
import com.eatoday.ui.recipes.RecipeAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ItemClicked{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    RecyclerView recyclerViewRecipe;
    RecyclerView.Adapter myRecipeAdapter;
    RecyclerView.LayoutManager layoutManagerRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        final ActionBar actionBar = this.getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        recyclerViewRecipe = findViewById(R.id.list);
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
        //ArrayList<Recipe> arrayList = new ArrayList<>();
        //arrayList = RecipeCollection.recipesList;
        myRecipeAdapter = new RecipeAdapter(this,(Context) MainActivity.this, RecipeCollection.recipesList);
        recyclerViewRecipe.setAdapter(myRecipeAdapter);

        drawerLayout = this.findViewById(R.id.drawer_layout);
        navigationView = this.findViewById(R.id.navigationView);

        //User.setIsLog(true);
        if (User.getIsLog()){
            navigationView.getMenu().findItem(R.id.nav2).setTitle(R.string.app_name);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav1:
                        menuItem.setChecked(true);
                        displayMessage("ciao"+menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav2:
                        menuItem.setChecked(true);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav3:
                        menuItem.setChecked(true);
                        displayMessage("ciao"+menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav4:
                        menuItem.setChecked(true);
                        displayMessage("ciao"+menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });
    }

    private void displayMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int index) {
        //Toast.makeText(this,"item selected" + recipes.get(index).toString(),Toast.LENGTH_SHORT).show();
    }
}
