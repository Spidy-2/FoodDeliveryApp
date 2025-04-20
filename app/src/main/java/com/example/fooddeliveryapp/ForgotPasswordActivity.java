package com.example.fooddeliveryapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText forgotEmailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotEmailEditText = findViewById(R.id.forgotEmailEditText);
        Button resetButton = findViewById(R.id.resetPasswordButton);

        resetButton.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword() {
        String email = forgotEmailEditText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (sendPasswordResetEmail(email)) {
            Toast.makeText(ForgotPasswordActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(ForgotPasswordActivity.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean sendPasswordResetEmail(String email) {
        return email.equals("test@example.com");
    }
}