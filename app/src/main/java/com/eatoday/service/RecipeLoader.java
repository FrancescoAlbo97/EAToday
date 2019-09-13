package com.eatoday.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.eatoday.model.Ingredient;
import com.eatoday.model.Recipe;
import com.eatoday.model.RecipeCollection;
import com.eatoday.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class RecipeLoader extends AsyncTask<String, Void, String> {

    private Context context;
    private Activity activity;
    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;
    public ArrayList<Recipe> arrayList = new ArrayList<>();
    private CountDownLatch latch;

    public RecipeLoader(Context context, CountDownLatch latch) {
        this.context = context;
        this.activity = (Activity) context;
        this.latch = latch;
    }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Connecting to server");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... getString) {

        try {
            URL url = new URL(Constant.URL_RECIPE + getString[0]);
            String json = connectionResult(url);
            RecipeCollection.recipesList = arrayList;
            latch.countDown();
            return json;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String connectionResult(URL url) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            int code = httpURLConnection.getResponseCode();
            if (code == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                Thread.sleep(2000);
                httpURLConnection.disconnect();
                String json = stringBuilder.toString().trim();
                progressDialog.dismiss();
                serializeJson(json);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder (context);
            builder.setTitle("Connection failed");
            builder.setMessage("Please check your connection");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
/*
    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);

        progressDialog.dismiss();

        JSONArray jsonArray = null;
        ArrayList<Recipe> arrayList = new ArrayList<>();
        try {
            jsonArray = new JSONArray(json);
            for(int i=0; i < jsonArray.length(); i++){
                arrayList.add(new Recipe(jsonArray.getJSONObject(i).getString("name")));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        RecipeCollection.recipesList = arrayList;
    }*/

    private void serializeJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0; i < jsonArray.length(); i++){
                JSONArray ingredients = jsonArray.getJSONObject(i).getJSONArray("ingredients");
                ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
                for( int j = 0; j < ingredients.length(); j++){
                    ingredientArrayList.add(new Ingredient(ingredients.getJSONObject(j).getString("id"),
                            ingredients.getJSONObject(j).getString("name"),
                            ingredients.getJSONObject(j).getString("unit"),
                            ingredients.getJSONObject(j).getString("availability"),
                            ingredients.getJSONObject(j).getString("price"),
                            ingredients.getJSONObject(j).getString("store")));
                }
                arrayList.add(new Recipe(jsonArray.getJSONObject(i).getString("name"),
                        jsonArray.getJSONObject(i).getString("time"),
                        jsonArray.getJSONObject(i).getString("difficulty"),
                        jsonArray.getJSONObject(i).getString("type"),
                        jsonArray.getJSONObject(i).getString("description"),
                        ingredientArrayList));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    private void showDialog(String title, String message, String code){
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                //gestire problema di chiusura comunque vada

                activity.finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
