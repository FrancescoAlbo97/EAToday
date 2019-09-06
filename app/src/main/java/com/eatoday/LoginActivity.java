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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.eatoday.service.AccessLoader;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        setTitle(R.string.app_name);

        email = (EditText) findViewById(R.id.email_input);
        password = (EditText) findViewById(R.id.password_input);
        loginButton = (Button) findViewById(R.id.btn_login);
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
                    AccessLoader accessLoader = new AccessLoader((Context) LoginActivity.this);
                    accessLoader.execute("login", email.getText().toString().trim(), password.getText().toString().trim());
                    if(accessLoader.getCode() != null && accessLoader.getCode().equals("login_true")){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("Something wrong...");
                        builder.setMessage("Please, try again");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
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
