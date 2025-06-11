// login_screen.java
package lk.cmb.sl_university_news; // IMPORTANT: Ensure this matches your actual package name

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent; // Required for Intents
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView; // Required for TextView
import android.widget.Toast;

public class login_screen extends AppCompatActivity {

    // Declare UI elements
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView createAccountButton; // Declare the TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen); // Ensure your layout file is named activity_login.xml

        // Initialize UI elements by finding them by their IDs from the layout
        usernameEditText = findViewById(R.id.username_editText);
        passwordEditText = findViewById(R.id.password_editText);
        loginButton = findViewById(R.id.login_button);
        createAccountButton = findViewById(R.id.create_account_button); // Initialize the TextView

        // Set an OnClickListener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty()) {
                    Toast.makeText(login_screen.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(login_screen.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(login_screen.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    // TODO: Implement actual login logic here
                }
            }
        });

        // Set an OnClickListener for the "Create an account" TextView
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SignUpScreenActivity
                // IMPORTANT: Ensure 'SignUpScreenActivity.class' is the exact class name of your sign-up activity
                Intent signUpIntent = new Intent(login_screen.this, SignUpScreenActivity.class);
                startActivity(signUpIntent); // Start the sign-up activity
            }
        });
    }
}