package com.eatoday.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.eatoday.model.User;
import com.eatoday.util.Constant;
import com.eatoday.util.PreferenceUtils;

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
import java.util.concurrent.CountDownLatch;

public class AccessLoader extends AsyncTask<String, Void, String> {

    private Context context;
    private Activity activity;
    private AlertDialog.Builder builder;
    private ProgressBar progressBar;
    private CountDownLatch latch;
    private User user;


    public AccessLoader(Context context, CountDownLatch latch) {
        this.context = context;
        this.activity = (Activity) context;
        this.latch = latch;
        builder = new AlertDialog.Builder(activity);
    }
/*
    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(activity);
        progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        progressBar.setClickable(false);
        progressBar.showContextMenu();
    }*/

    @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        try {
            if (method.equals("register")) {

                URL url = new URL(Constant.URL_REGISTER);
                String name = params[1];
                String lastName = params[2];
                String email = params[3];
                String password = params[4];
                String address = params[5];
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", lastName);
                jsonObject.put("lastName", name);
                jsonObject.put("email", email);
                jsonObject.put("password", password);
                jsonObject.put("address", address);
                String json = connectionResult(url, jsonObject);
                if(onPost(json)){
                    PreferenceUtils.saveEmail(email, context);
                }
                latch.countDown();
                return json;

            } else if (method.equals("login")) {

                URL url = new URL(Constant.URL_LOGIN);
                String email = params[1];
                String password = params[2];
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", email);
                jsonObject.put("password", password);
                String json = connectionResult(url, jsonObject);

               // progressDialog.dismiss();

                if(onPost(json)){
                    saveUser(json);
                }
                latch.countDown();
                return json;
            }
        } catch (MalformedURLException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String connectionResult(URL url, JSONObject jsonObject) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            outputStream.write(jsonObject.toString().getBytes(Constant.ENCODING));

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

    private boolean onPost(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject newJsonObject = jsonArray.getJSONObject(0);
            String code = newJsonObject.getString("code");
            //String message = newJsonObject.getString("message");

            if (code.contains("true")){
                return true;
            }
            else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
/*
    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);

        progressDialog.dismiss();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject newJsonObject = jsonArray.getJSONObject(0);
            String code = newJsonObject.getString("code");
            String message = newJsonObject.getString("message");

            if (code.equals("register_true")){
                showDialog("Registration success",message,code);
            }
            else if(code.equals("register_false")){
                showDialog("Registration failed",message,code);
            }
            else if(code.equals("login_true")){
                showDialog("Login success",message,code);
            }
            else if(code.equals("login_false")){
                showDialog("Login failed",message,code);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
/*
    private void showDialog(String title, String message, String code){
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                //gestire problema di chiusura comunque vada

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/

    private void saveUser(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject userJson = jsonObject.getJSONObject("user");
        user = new User(
                userJson.getString("name"),
                userJson.getString("lastName"),
                userJson.getString("email"),
                userJson.getString("password"),
                userJson.getString("address")
                );
        PreferenceUtils.saveEmail(user.getEmail(), context);
        PreferenceUtils.savePassword(user.getPassword(), context);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
