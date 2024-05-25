package com.example.lab2;

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

public class SignUpForm extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtConfirm;
    private TextView tvAcc;
    private Button signUp;
    private final String REQUIRE = "REQUIRE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_form);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtConfirm = (EditText) findViewById(R.id.txtConfirm);
        tvAcc = (TextView) findViewById(R.id.Acc);
        signUp = (Button) findViewById(R.id.btnSignUp);
        tvAcc.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        SignIn();
                    }
                })

        );
        signUp.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        SignUp();
                    }
                })

        );
    }

    private void SignUp(){
        if(!CheckEmpty()){
            return;
        }
        String pass = txtPassword.getText().toString();
        String conf = txtConfirm.getText().toString();
        if(!pass.equals(conf)){
            return;
        }
        Intent intent = new Intent(this, SignInForm.class);
        startActivity(intent);
        finish();
    }
    private void SignIn(){
        Intent intent = new Intent(this, SignInForm.class);
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
        else if(TextUtils.isEmpty(txtConfirm.getText().toString())){
            txtConfirm.setError(REQUIRE);
            return false;
        }
        return true;
    }
}