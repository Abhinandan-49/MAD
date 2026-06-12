package com.techiguru.fitmylife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    //  declare the  java object  based  on ui  widget
    EditText et_emailId , et_password;
    TextView tv_forgetPassword ,  tv_createAccount;
    Button bt_login;

    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        sp=getSharedPreferences("mypref",0);

        bt_login= findViewById(R.id.bt_login);
        et_emailId= findViewById(R.id.et_emailId);
        et_password= findViewById(R.id.et_password);
        tv_forgetPassword= findViewById(R.id.tv_forgetPassword);
        tv_createAccount= findViewById(R.id.tv_createAccount);

        //To  click  on  the  login button

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the data from shared preference and pass into string
                String email = sp.getString("email", null);
                String password= sp.getString("password",null);

                //To validate the Email and password
                if (et_emailId.getText().toString().equals(email)
                        && et_password.getText().toString().equals(password)) {
                    Intent main= new Intent(LoginActivity.this,MainActivity.class);
                    //To start the intent
                    startActivity(main);
                    finish();
                } else if (et_emailId.getText().toString().isEmpty()
                        || et_password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid id or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tv_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forget = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forget);
            }
        });

        tv_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register= new Intent(LoginActivity.this,RegisterActivity.class);
                //To start the intent
                startActivity(register);
                finish();
            }
        });


        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

}