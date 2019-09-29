package com.eatoday;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.eatoday.model.Ingredient;
import com.eatoday.model.RecipeCollection;
import com.eatoday.util.Constant;

import java.util.ArrayList;
import java.util.Iterator;


public class FilterActivity extends AppCompatActivity {

    private ListView listViewOK;
    private EditText ingredientOK;
    private Button btnOK;
    private ListView listViewNO;
    private EditText ingredientNO;
    private Button btnNO;
    private Button btnBack;
    private Button btnSearch;
    private Switch vegetarian;
    private Switch vegan;
    private SeekBar seekBar;
    private boolean isVegetarian = false;
    private boolean isVegan = false;
    private String difficulty = "";
    private ArrayList<String> listIngredientsOK;
    private ArrayList<String> listIngredientsNO;
    private ArrayList<Integer> ids;
    private ArrayList<Integer> idOK;
    private ArrayList<Integer> idNO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_main);
        setTitle(R.string.app_name);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        listViewOK = findViewById(R.id.list_ingredients_ok);
        listViewNO = findViewById(R.id.list_ingredients_no);
        ingredientOK = findViewById(R.id.edit_ingredient_ok);
        ingredientNO = findViewById(R.id.edit_ingredient_no);
        btnOK = findViewById(R.id.btn_ok);
        btnNO = findViewById(R.id.btn_no);
        btnBack = findViewById(R.id.btn_back_filter);
        btnSearch = findViewById(R.id.btn_ord_filter);
        vegetarian = findViewById(R.id.vegetarian);
        vegan = findViewById(R.id.vegan);
        seekBar = findViewById(R.id.difficulty);

        ids = new ArrayList<>();
        idOK = new ArrayList<>();
        idNO = new ArrayList<>();
        listIngredientsOK = new ArrayList<>();
        listIngredientsNO = new ArrayList<>();


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredientName = ingredientOK.getText().toString().trim();
                ArrayList<String> list = new ArrayList<String>();
                for(Ingredient in : RecipeCollection.ingredientsList){
                    if(in.toString().contains(ingredientName)){
                        list.add(in.getName());
                        ids.add(in.getId());
                    }
                }
                final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                        (FilterActivity.this, android.R.layout.simple_list_item_1,
                                list);

                AlertDialog.Builder builder=new AlertDialog.Builder(FilterActivity.this);
                if(ids.isEmpty()){
                    Toast.makeText(FilterActivity.this,"Nessuno ingrediente trovato",Toast.LENGTH_SHORT).show();
                }else{
                    builder.setTitle("Ingredienti trovati");
                    builder.setMessage("Selezionare l'ingrediente corretto");
                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String add = adapter.getItem(i);
                            int id = ids.get(i);
                            idOK.add(id);
                            ids.clear();
                            listIngredientsOK.add(add);
                            addToListView(listViewOK, listIngredientsOK);
                        }
                    };
                    builder.setAdapter(adapter,listener);
                    AlertDialog alertDialog = builder.create();
                    ListView listView = alertDialog.getListView();
                    listView.setDividerHeight(listView.getScrollBarSize());
                    listView.setEnabled(true);
                    alertDialog.setView(listView);
                    alertDialog.show();
                }
            }
        });

        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredientName = ingredientNO.getText().toString().trim();
                ArrayList<String> list = new ArrayList<String>();
                for(Ingredient in : RecipeCollection.ingredientsList){
                    if(in.toString().contains(ingredientName)){
                        list.add(in.getName());
                        ids.add(in.getId());
                    }
                }
                final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                        (FilterActivity.this, android.R.layout.simple_list_item_1,
                                list);

                AlertDialog.Builder builder=new AlertDialog.Builder(FilterActivity.this);
                if(ids.isEmpty()){
                    Toast.makeText(FilterActivity.this,"Nessuno ingrediente trovato",Toast.LENGTH_SHORT).show();
                }else{
                    builder.setTitle("Ingredienti trovati");
                    builder.setMessage("Selezionare l'ingrediente corretto");
                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String add = adapter.getItem(i);
                            int id = ids.get(i);
                            idNO.add(id);
                            ids.clear();
                            listIngredientsNO.add(add);
                            addToListView(listViewNO, listIngredientsNO);
                        }
                    };
                    builder.setAdapter(adapter,listener);
                    AlertDialog alertDialog = builder.create();
                    ListView listView = alertDialog.getListView();
                    listView.setDividerHeight(listView.getScrollBarSize());
                    listView.setEnabled(true);
                    alertDialog.setView(listView);
                    alertDialog.show();
                }
            }
        });

        vegetarian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isVegetarian = true;
                    isVegan = true;
                    vegan.setChecked(true);
                }else{
                    isVegetarian = false;
                }
            }
        });

        vegan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isVegan = true;
                }else{
                    isVegan = false;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i){
                    case (1):
                        difficulty = "facile";
                        break;
                    case (2):
                        difficulty = "medio";
                        break;
                    case (3):
                        difficulty = "difficile";
                        break;
                    default:
                        difficulty = "";
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                String get = "?";
                if(!idOK.isEmpty()){
                    get += Constant.SELECTED;
                    int size = idOK.size() - 1;
                    for(int i= 0; i < size ; i++){
                        get += String.valueOf(idOK.get(i));
                        get += ",";
                    }
                    get += String.valueOf(idOK.get(size));
                }
                if(!idNO.isEmpty()){
                    if(!get.equals("?")) get += "&";
                    get += Constant.EXCLUDED;
                    int size = idNO.size() - 1;
                    for(int i= 0; i < size ; i++){
                        get += String.valueOf(idNO.get(i));
                        get += ",";
                    }
                    get += String.valueOf(idNO.get(size));
                }
                if(isVegetarian){
                    if(!get.equals("?")) get += "&";
                    get += Constant.TYPE + "vegetariano";
                }else{
                    if(isVegan){
                        if(!get.equals("?")) get += "&";
                        get += Constant.TYPE + "vegano";
                    }
                }
                if(!difficulty.equals("")){
                    if(!get.equals("?")) get += "&";
                    get += Constant.DIFFICULTY + difficulty;
                }
                intent.putExtra("getRequest", get);
                finish();
                startActivity(intent);
            }
        });
    }


    private void addToListView(ListView listView, ArrayList<String> listElements){

        final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                (FilterActivity.this, android.R.layout.simple_list_item_1,
                        listElements);

        listView.setAdapter(adapter);

        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
        listView.setScrollContainer(false);

        adapter.notifyDataSetChanged();
        ingredientOK.getText().clear();
        ingredientNO.getText().clear();
    }

}
