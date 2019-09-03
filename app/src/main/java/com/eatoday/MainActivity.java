package com.eatoday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

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
}
