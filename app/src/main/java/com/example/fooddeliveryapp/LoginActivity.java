package com.example.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        TextView forgotPasswordLink = findViewById(R.id.forgotPasswordLink);
        TextView signupLink = findViewById(R.id.signupLink);

        loginButton.setOnClickListener(v -> loginUser());
        signupLink.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
        forgotPasswordLink.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
    }

    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (authenticateUser(email, password)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Login failed: Incorrect email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean authenticateUser(String email, String password) {
        return email.equals("test@example.com") && password.equals("password");
    }
}