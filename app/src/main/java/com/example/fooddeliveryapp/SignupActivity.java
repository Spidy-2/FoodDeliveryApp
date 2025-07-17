package com.example.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText signupEmailEditText, signupPasswordEditText;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        signupEmailEditText = findViewById(R.id.signupEmailEditText);
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText);
        Button signupButton = findViewById(R.id.signupButton);
        TextView loginLink = findViewById(R.id.loginLink);

        signupButton.setOnClickListener(v -> registerUser());
        loginLink.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }

    private void registerUser() {
        String email = signupEmailEditText.getText().toString().trim();
        String password = signupPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("uid", user.getUid());
                            userData.put("email", user.getEmail());
                            userData.put("createdAt", System.currentTimeMillis());

                            firestore.collection("users")
                                    .document(user.getUid())
                                    .set(userData)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(SignupActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(SignupActivity.this, "Firestore error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    });
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
