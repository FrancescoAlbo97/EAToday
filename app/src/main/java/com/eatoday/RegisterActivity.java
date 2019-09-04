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

import com.eatoday.service.BackgroundTask;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,password,password2;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);
        setTitle(R.string.app_name);

        name = (EditText) findViewById(R.id.name_input);
        email = (EditText) findViewById(R.id.email_input);
        password = (EditText) findViewById(R.id.password_input);
        password2 = (EditText) findViewById(R.id.password2_input);
        registerButton = (Button) findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || password2.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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
                else if (!(password.getText().toString().equals(password2.getText().toString()))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Something wrong...");
                    builder.setMessage("Passwords are not equal!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            password.setText("");
                            password2.setText("");
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    BackgroundTask backgroundTask = new BackgroundTask((Context) RegisterActivity.this);
                    backgroundTask.execute("register", name.getText().toString().trim(), email.getText().toString().trim(), password.getText().toString().trim());
                }
            }
        });

        TextView loginTV = (TextView)findViewById(R.id.login_link);
        loginTV.setMovementMethod(LinkMovementMethod.getInstance());
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

