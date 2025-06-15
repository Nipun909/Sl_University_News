package lk.cmb.sl_university_news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpScreenActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, confirmPasswordEditText, emailEditText;
    private Button signUpButton;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference("Users");

        // Link UI
        usernameEditText = findViewById(R.id.username_editText_signup);
        passwordEditText = findViewById(R.id.password_editText_signup);
        confirmPasswordEditText = findViewById(R.id.confirm_password_editText_signup);
        emailEditText = findViewById(R.id.email_editText_signup);
        signUpButton = findViewById(R.id.signup_button);

        // Sign-up logic
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                    Toast.makeText(SignUpScreenActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpScreenActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Create user in Firebase Authentication
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    // Save extra info in Realtime Database
                                    String userId = mAuth.getCurrentUser().getUid();
                                    User newUser = new User(username, email);
                                    databaseRef.child(userId).setValue(newUser);

                                    Toast.makeText(SignUpScreenActivity.this, "Sign-up successful", Toast.LENGTH_SHORT).show();

                                    // Go back to login
                                    Intent intent = new Intent(SignUpScreenActivity.this, login_screen.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(SignUpScreenActivity.this, "Sign-up failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
    }

    // Model class for Firebase Database
    public static class User {
        public String username;
        public String email;

        public User() {} // Needed for Firebase

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }
}
