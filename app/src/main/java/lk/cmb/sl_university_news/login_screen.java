// login_screen.java
package lk.cmb.sl_university_news; // IMPORTANT: Replace 'com.example.your_app_name' with your actual package name

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_screen extends AppCompatActivity {

    // Declare UI elements
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to your login screen layout
        setContentView(R.layout.activity_login_screen); // IMPORTANT: Ensure your layout file is named activity_login.xml

        // Initialize UI elements by finding them by their IDs from the layout
        usernameEditText = findViewById(R.id.username_editText);
        passwordEditText = findViewById(R.id.password_editText);
        loginButton = findViewById(R.id.login_button);

        // Set an OnClickListener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text entered by the user
                String username = usernameEditText.getText().toString().trim(); // .trim() removes leading/trailing whitespace
                String password = passwordEditText.getText().toString().trim();

                // Perform basic validation
                if (username.isEmpty()) {
                    // Show a toast message if username is empty
                    Toast.makeText(login_screen.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    // Show a toast message if password is empty
                    Toast.makeText(login_screen.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                } else {
                    // Both fields are filled, proceed with login logic
                    // For now, we'll just show a success toast.
                    // In a real application, you would send these credentials to a server for authentication.
                    Toast.makeText(login_screen.this, "Login successful!", Toast.LENGTH_SHORT).show();

                    // TODO: Implement actual login logic here
                    // e.g., call an authentication API, navigate to the main application screen, etc.
                    // Example: Intent intent = new Intent(login_screen.this, MainActivity.class);
                    // startActivity(intent);
                    // finish(); // Close login activity so user can't go back with back button
                }
            }
        });
    }
}
