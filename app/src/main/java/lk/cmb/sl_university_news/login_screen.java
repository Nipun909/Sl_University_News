package lk.cmb.sl_university_news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class login_screen extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView createAccountButton;

    private FirebaseAuth mAuth;  // Firebase authentication instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Link UI elements
        usernameEditText = findViewById(R.id.username_editText);
        passwordEditText = findViewById(R.id.password_editText);
        loginButton = findViewById(R.id.login_button);
        createAccountButton = findViewById(R.id.create_account_button);

        // Login logic
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(login_screen.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(login_screen.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(login_screen.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login_screen.this, news_screen.class);
                                    startActivity(intent);
                                    finish(); // Close login screen
                                } else {
                                    Toast.makeText(login_screen.this, "Login failed. Check your email or password.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        // Register link (optional for signup screen)
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(login_screen.this, SignUpScreenActivity.class);
                startActivity(signUpIntent);
            }
        });
    }
}
