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

public class RegisterActivity extends AppCompatActivity {
    EditText et_fullname , et_emailId , et_password  , et_confirmPassword;
    TextView tv_AlreadyAccount;
    Button bt_register;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        //to create or open the shared preference file with mode
        sp = getSharedPreferences("mypref", 0);
        ed = sp.edit();

        bt_register =  findViewById(R.id.bt_register);
        et_fullname=findViewById(R.id.et_fullName);
        et_emailId=findViewById(R.id.et_emailId);
        et_password=findViewById(R.id.et_password);
        et_confirmPassword=findViewById(R.id.et_confirmPassword);
        tv_AlreadyAccount = findViewById(R.id.tv_already_have_an_account);
        bt_register=findViewById(R.id.bt_register);

        tv_AlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }

        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_fullname.getText().toString();
                String email = et_emailId.getText().toString();
                String password = et_password.getText().toString();
                String confirmPassword = et_confirmPassword.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    ed.putString("name", name);
                    ed.putString("email", email);
                    ed.putString("password", password);
                    ed.apply();

                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(main);
                    finish();
                }
            }
        });

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}