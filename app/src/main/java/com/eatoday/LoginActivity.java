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

import com.eatoday.helper.DatabaseHelper;
import com.eatoday.service.AccessLoader;
import com.eatoday.util.Constant;


import java.util.concurrent.CountDownLatch;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button loginButton;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        setTitle(R.string.app_name);

        email = (EditText) findViewById(R.id.email_input);
        password = (EditText) findViewById(R.id.password_input);
        loginButton = (Button) findViewById(R.id.btn_login);
        Intent intent = getIntent();
        if (intent.hasExtra(Constant.KEY_EMAIL)){
            String nameFromIntent = getIntent().getStringExtra(Constant.KEY_EMAIL);
            email.setText(nameFromIntent);
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


                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    //String e = email.getText().toString().trim();
                    //String p = password.getText().toString().trim();
                    /*
                    if (!databaseHelper.checkUser(e, p)) {
                        PreferenceUtils.saveEmail(e, (Context) LoginActivity.this);
                        PreferenceUtils.savePassword(p, (Context)  LoginActivity.this);
                        Intent accountsIntent = new Intent(LoginActivity.this, MainActivity.class);
                        accountsIntent.putExtra("EMAIL", email.getText().toString().trim());
                        emptyInputEditText();
                        startActivity(accountsIntent);
                        finish();
                    }
*/
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

    private void emptyInputEditText(){
        email.setText(null);
        password.setText(null);
    }

}
