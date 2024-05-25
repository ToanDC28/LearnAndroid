package com.example.lab2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignInForm extends AppCompatActivity{


    private EditText txtUsername;
    private EditText txtPassword;
    private TextView tvNotAcc;
    private Button login;

    private final String REQUIRE = "REQUIRE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in_form);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPass);
        tvNotAcc = (TextView) findViewById(R.id.notAcc);
        login = (Button) findViewById(R.id.btnSignIn);

        tvNotAcc.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        SignUp();
                    }
                })

        );
        login.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        SignIn();
                    }
                })

        );
    }

    private void SignIn(){
        if(!CheckEmpty()){
            return;
        }
        Intent intent = new Intent(this, SignInForm.class);
        startActivity(intent);
        finish();
    }
    private void SignUp(){
        Intent intent = new Intent(this, SignUpForm.class);
        startActivity(intent);
        finish();
    }
    private boolean CheckEmpty(){
        if(TextUtils.isEmpty(txtUsername.getText().toString())){
            txtUsername.setError(REQUIRE);
            return false;
        }
        else if(TextUtils.isEmpty(txtPassword.getText().toString())){
            txtPassword.setError(REQUIRE);
            return false;
        }
        return true;
    }
}