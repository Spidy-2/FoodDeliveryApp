package com.example.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText signupEmailEditText, signupPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupEmailEditText = findViewById(R.id.signupEmailEditText);
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText);
        Button signupButton = findViewById(R.id.signupButton);
        TextView loginLink = findViewById(R.id.loginLink);

        signupButton.setOnClickListener(v -> registerUser());
        loginLink.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }

    private void registerUser() {
        String email = signupEmailEditText.getText().toString();
        String password = signupPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (createUser()) {
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean createUser() {

        return true;
    }
}