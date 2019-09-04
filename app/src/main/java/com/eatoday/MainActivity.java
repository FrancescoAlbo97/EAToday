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
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.eatoday.model.Recipe;
import com.eatoday.ui.recipes.RecipeAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ItemClicked{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    RecyclerView recyclerViewRecipe;
    RecyclerView.Adapter myRecipeAdapter;
    RecyclerView.LayoutManager layoutManagerRecipe;
    ArrayList<Recipe> recipes;

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
        String pizza = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjM0bnjrLLkAhUyAGMBHbJPC18QjRx6BAgBEAQ&url=https%3A%2F%2Fwww.bofrost.it%2Fprodotti%2Fpizze-snack%2Fpizze%2Fpizza-la-margherita%2F&psig=AOvVaw2kcnthPL-PE7gAjRnTiWpm&ust=1567520959713443.";
        String aperitivo = "https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjlop-IrbLkAhVJ8uAKHSDfDGkQjRx6BAgBEAQ&url=https%3A%2F%2Fwww.gazzettadiparma.it%2Farchivio%2F2012%2F07%2F08%2Fnews%2Faperitivo_e_buffet_quanto_costa_la_movida_-716782%2F&psig=AOvVaw0lvOoHmdtSelDix7q-9LCV&ust=1567521039453848";
        recipes = new ArrayList<>();
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));
        recipes.add(new Recipe("aperitivo", "30", "facile","economico","vegetariano","ciaociao",aperitivo));
        recipes.add(new Recipe("pizza", "30", "facile","economico","vegetariano","ciaociao",pizza));

        myRecipeAdapter = new RecipeAdapter((Context) MainActivity.this, recipes);
        recyclerViewRecipe.setAdapter(myRecipeAdapter);

        drawerLayout = this.findViewById(R.id.drawer_layout);
        navigationView = this.findViewById(R.id.navigationView);

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
                        displayMessage("ciao"+menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
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
        Toast.makeText(this,"item selected" + recipes.get(index).toString(),Toast.LENGTH_SHORT).show();;
    }
}
