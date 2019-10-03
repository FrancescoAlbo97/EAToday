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

import com.eatoday.helper.DatabaseHelper;
import com.eatoday.service.AccessLoader;
import com.eatoday.util.PreferenceUtils;

import java.util.concurrent.CountDownLatch;

public class ProfileActivity extends AppCompatActivity {

    private EditText name,lastName,email,password,password2,address;
    private Button updateButton;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private PreferenceUtils utils = new PreferenceUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_form);
        setTitle(R.string.app_name);

        name = (EditText) findViewById(R.id.name_input_profile);
        lastName = (EditText) findViewById(R.id.last_name_input_profile);
        email = (EditText) findViewById(R.id.email_input_profile);
        password = (EditText) findViewById(R.id.password_input_profile);
        password2 = (EditText) findViewById(R.id.password2_input_profile);
        address = (EditText) findViewById(R.id.address_input_profile);
        updateButton = (Button) findViewById(R.id.btn_update);
        name.setText(AccessLoader.getUser().getName());
        lastName.setText(AccessLoader.getUser().getLastName());
        email.setText(AccessLoader.getUser().getEmail());
        address.setText(AccessLoader.getUser().getAddress());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") || lastName.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || password2.getText().toString().equals("") || address.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
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
                    AccessLoader.getUser().setName(name.getText().toString());
                    AccessLoader.getUser().setLastName(lastName.getText().toString());
                    AccessLoader.getUser().setPassword(password.getText().toString());
                    AccessLoader.getUser().setAddress(address.getText().toString());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    builder.setTitle("Dati aggiornati");
                    builder.setMessage("Ora puoi continuare la navigazione");
                    builder.setPositiveButton("HOME", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

    }
}
