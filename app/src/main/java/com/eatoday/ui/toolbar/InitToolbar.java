package com.eatoday.ui.toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.eatoday.FilterActivity;
import com.eatoday.LoginActivity;
import com.eatoday.MainActivity;
import com.eatoday.R;
import com.eatoday.model.User;
import com.eatoday.util.PreferenceUtils;
import com.google.android.material.navigation.NavigationView;

public class InitToolbar  {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Activity activity;
    private Intent intent;

    public InitToolbar(Activity activity){
        this.activity = activity;
        toolbar = activity.findViewById(R.id.toolbar);
        drawerLayout = activity.findViewById(R.id.drawer_layout);
        navigationView = activity.findViewById(R.id.navigationView);
    }

    public void init(ActionBar a){
        final ActionBar actionBar = a;
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        if (User.getIsLog()) {
            navigationView.getMenu().findItem(R.id.nav2).setTitle(R.string.menu_2l);
            navigationView.getMenu().findItem(R.id.nav2).setIcon(R.drawable.ic_user);
        } else {
            navigationView.getMenu().findItem(R.id.nav5).setVisible(false);
        }
    }

    public boolean setOnNavigationItemSelected(@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()){

            case R.id.nav1:
                menuItem.setChecked(true);
                //displayMessage("ciao"+menuItem.getItemId());
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav2:
                menuItem.setChecked(true);
                if (User.getIsLog()){
                    //TODO FARE IL MODIFICA PROFILO
                }else{
                    intent = new Intent(activity, LoginActivity.class);
                }
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav3:
                menuItem.setChecked(true);
                intent = new Intent(activity, FilterActivity.class);
                drawerLayout.closeDrawers();
                menuItem.setChecked(false);
                return true;
            case R.id.nav4:
                menuItem.setChecked(true);
                //displayMessage("ciao"+menuItem.getItemId());
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav5:
                menuItem.setChecked(true);
                PreferenceUtils.logout(activity);
                drawerLayout.closeDrawers();
                intent = new Intent(activity, MainActivity.class);
                //TODO SISTEMARE IL CHECKED
                return true;
        }
        return false;
    }



    private void displayMessage(String message){
        //Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    public boolean setOnOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return false;
    }


    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public void setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
    }


    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
