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
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.eatoday.model.Ingredient;
import com.eatoday.model.RecipeCollection;
import com.eatoday.model.User;
import com.eatoday.service.IngredientLoader;
import com.eatoday.service.RecipeLoader;
import com.eatoday.ui.recipes.RecipeAdapter;

import com.eatoday.util.Constant;
import com.eatoday.util.PreferenceUtils;
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
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchView();
        initToolbar();

        recyclerViewRecipe = findViewById(R.id.list_details);
        recyclerViewRecipe.setHasFixedSize(true);
        layoutManagerRecipe = new LinearLayoutManager(this);
        recyclerViewRecipe.setLayoutManager(layoutManagerRecipe);

        Intent intent = getIntent();
        String get = "";
        if (intent.hasExtra("getRequest")){
            get = getIntent().getStringExtra("getRequest");
        }
        CountDownLatch latch = new CountDownLatch(1);
        RecipeLoader recipeLoader = new RecipeLoader((Context) MainActivity.this, latch);
        recipeLoader.execute(get);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myRecipeAdapter = new RecipeAdapter(this,(Context) MainActivity.this, RecipeCollection.recipesList);
        recyclerViewRecipe.setAdapter(myRecipeAdapter);

        CountDownLatch latch1 = new CountDownLatch(1);
        IngredientLoader ingredientLoader = new IngredientLoader((Context) MainActivity.this, latch1);
        ingredientLoader.execute();
        ArrayList<Ingredient> ingredients = ingredientLoader.arrayList;
        try {
            latch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void displayMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void initToolbar() {
        toolbar = this.findViewById(R.id.toolbar);
        drawerLayout = this.findViewById(R.id.drawer_layout);
        navigationView = this.findViewById(R.id.navigationView);
        this.setSupportActionBar(toolbar);

        final ActionBar actionBar = this.getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        if (User.getIsLog()){
            navigationView.getMenu().findItem(R.id.nav2).setTitle(R.string.menu_2l);
            navigationView.getMenu().findItem(R.id.nav2).setIcon(R.drawable.ic_user);
        }else{
            navigationView.getMenu().findItem(R.id.nav5).setVisible(false);
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
                        if (User.getIsLog()){

                        }else{
                            menuItem.setChecked(true);
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav3:
                        menuItem.setChecked(true);
                        menuItem.setChecked(true);
                        Intent intentF = new Intent(getApplicationContext(), FilterActivity.class);
                        startActivity(intentF);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav4:
                        menuItem.setChecked(true);
                        displayMessage("ciao"+menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav5:
                        menuItem.setChecked(true);
                        displayMessage("ciao"+menuItem.getItemId());
                        PreferenceUtils.logout(getApplicationContext());
                        Intent intentM = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentM);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });

    }

    private void initSearchView(){
        searchView = findViewById(R.id.search_by_name);
        searchView.setQueryHint("Cerca tra le ricette...");
        //searchView.expandActionView();
        searchView.setBackgroundColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                return false;
            }
        });
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
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class );
        intent.putExtra("recipeIndex", index);
        startActivity(intent);
        Toast.makeText(this,"item selected" + RecipeCollection.recipesList.get(index).toString(),Toast.LENGTH_SHORT).show();
    }
}
