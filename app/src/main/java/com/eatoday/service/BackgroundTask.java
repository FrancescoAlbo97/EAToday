package com.eatoday.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    private Context context;
    private Activity activity;
    final private String register_URL = "http://eatoday.altervista.org/register.php";
    final private String login_URL = "http://eatoday.altervista.org/login.php";
    final private  String encoding = "UTF-8";
    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;

    public BackgroundTask(Context context) {
        this.context = context;
        this.activity = (Activity) context;
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
    protected String doInBackground(String... params) {
        String method = params[0];
        if (method.equals("register")) {
            /*
            try {
                URL url = new URL(register_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                //httpURLConnection.connect();
                OutputStream outputStream = (OutputStream) httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,encoding));
                String name = params[1];
                String email = params[2];
                String password = params[3];
                String postData = URLEncoder.encode("name",encoding)+"="+URLEncoder.encode(name,encoding)+"&"+
                        URLEncoder.encode("email",encoding)+"="+URLEncoder.encode(email,encoding)+"&"+
                        URLEncoder.encode("password",encoding)+"="+URLEncoder.encode(password,encoding);
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
/*
            String name = params[1];
            String email = params[2];
            String password = params[3];

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("name", name)
                    .add("email", email)
                    .add("password", password)
                    .build();
            Request request = new Request.Builder()
                    .url(register_URL)
                    .post(formBody)
                    .build();
            Response response = null;
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    InputStream inputStream = response.body().byteStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line + "\n");
                    }
                    bufferedReader.close();
                }
            });
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(register_URL);
            String name = params[1];
            String email = params[2];
            String password = params[3];
            try {
                String postData = URLEncoder.encode("name",encoding)+"="+URLEncoder.encode(name,encoding)+"&"+
                        URLEncoder.encode("email",encoding)+"="+URLEncoder.encode(email,encoding)+"&"+
                        URLEncoder.encode("password",encoding)+"="+URLEncoder.encode(password,encoding);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = client.execute(post);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                String result = null;
                while ((line = reader.readLine()) != null) {
                    result = line;
                }
                reader.close();
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            try {
                URL url =new URL(register_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                String name = params[1];
                String email = params[2];
                String password = params[3];
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("email", email);
                jsonObject.put("password", password);
                DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                outputStream.write(jsonObject.toString().getBytes(encoding));

                int code = httpURLConnection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    //httpURLConnection.disconnect();
                    Thread.sleep(3000);
                    return stringBuilder.toString().trim();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (method.equals("login")){
            try {
                URL url =new URL(login_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                String email = params[1];
                String password = params[2];
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("email", email);
                jsonObject.put("password", password);
                DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                outputStream.write(jsonObject.toString().getBytes(encoding));

                int code = httpURLConnection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    //httpURLConnection.disconnect();
                    Thread.sleep(3000);
                    return stringBuilder.toString().trim();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {

        progressDialog.dismiss();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
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
    }

    private void showDialog(String title, String message, String code){
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                activity.finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
