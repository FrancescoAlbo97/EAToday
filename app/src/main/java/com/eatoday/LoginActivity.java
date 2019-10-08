package com.eatoday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.eatoday.model.User;
import com.eatoday.service.AccessLoader;
import com.eatoday.util.PreferenceUtils;


import java.util.concurrent.CountDownLatch;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        setTitle(R.string.app_name);

        email = (EditText) findViewById(R.id.email_input);
        password = (EditText) findViewById(R.id.password_input);
        loginButton = (Button) findViewById(R.id.btn_login);

        Object mail = PreferenceUtils.getEmail(LoginActivity.this);
        if(mail != null){
            email.setText((String) mail);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( email.getText().toString().equals("") || password.getText().toString().equals("") ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Something wrong...");
                    builder.setMessage("Fields can't be empty!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    CountDownLatch latch = new CountDownLatch(1);
                    AccessLoader accessLoader = new AccessLoader((Context) LoginActivity.this, latch);
                    accessLoader.execute("login", email.getText().toString().trim(), password.getText().toString().trim());
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(User.getIsLog()){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Login fallito, riprovare",Toast.LENGTH_SHORT).show();
                        email.setText("");
                        password.setText("");
                    }
                }
            }
        });


        TextView registerTV = (TextView)findViewById(R.id.register_link);
        registerTV.setMovementMethod(LinkMovementMethod.getInstance());
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
