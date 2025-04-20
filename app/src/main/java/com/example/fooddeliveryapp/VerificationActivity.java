package com.example.fooddeliveryapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private EditText verificationCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        verificationCodeEditText = findViewById(R.id.verificationCodeEditText);
        Button verifyButton = findViewById(R.id.verifyButton);

        verifyButton.setOnClickListener(v -> verifyCode());
    }

    private void verifyCode() {
        String code = verificationCodeEditText.getText().toString();

        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "Please enter the verification code", Toast.LENGTH_SHORT).show();
            return;
        }

        if (performVerification(code)) {
            Toast.makeText(this, "Verification successful!", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(this, "Verification failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean performVerification(String code) {
        return code.equals("123456");
    }
}