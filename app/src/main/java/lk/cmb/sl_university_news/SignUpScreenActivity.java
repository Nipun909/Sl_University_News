// SignUpScreenActivity.java
package lk.cmb.sl_university_news; // IMPORTANT: Replace 'lk.cmb.sl_university_news' with your actual project's package name

import androidx.appcompat.app.AppCompatActivity; // Base class for activities
import android.os.Bundle; // Used to pass data between activities
import android.view.View; // Base class for all UI components
import android.widget.Button; // Button UI component
import android.widget.EditText; // Editable text input UI component
import android.widget.Toast; // Used to display short messages

public class SignUpScreenActivity extends AppCompatActivity {

    // Declare UI elements as private member variables
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText emailEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view of this activity to the 'activity_sign_up.xml' layout file.
        // This links the Java code to its visual XML layout.
        setContentView(R.layout.activity_signup_screen);

        // Initialize UI elements by finding them by their IDs defined in activity_sign_up.xml.
        // It's crucial that these IDs match exactly with what's in your XML.
        usernameEditText = findViewById(R.id.username_editText_signup);
        passwordEditText = findViewById(R.id.password_editText_signup);
        confirmPasswordEditText = findViewById(R.id.confirm_password_editText_signup);
        emailEditText = findViewById(R.id.email_editText_signup);
        signUpButton = findViewById(R.id.signup_button);

        // Set an OnClickListener for the Sign Up button.
        // This listener will be triggered when the button is tapped.
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the text entered by the user from each EditText field.
                // .trim() is used to remove any leading or trailing whitespace.
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                // Perform basic input validation.
                // Check if any of the fields are empty.
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                    // Display a short message (Toast) if any field is empty.
                    Toast.makeText(SignUpScreenActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                // Check if the password and confirm password fields match.
                else if (!password.equals(confirmPassword)) {
                    // Display a message if passwords do not match.
                    Toast.makeText(SignUpScreenActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                // If all validations pass (fields are filled and passwords match).
                else {
                    // TODO: Implement your actual sign-up logic here.
                    // This is where you would typically:
                    // 1. Send the user's data (username, password, email) to a backend server.
                    // 2. Use a mobile backend service like Firebase Authentication.
                    // 3. Store the user's data locally (e.g., in a local database, though not recommended for production user data).

                    // For now, we'll just display a success message.
                    Toast.makeText(SignUpScreenActivity.this, "Sign Up successful! Welcome " + username, Toast.LENGTH_LONG).show();

                    // Optional: After successful sign-up, you might want to navigate the user
                    // back to the login screen or directly into the main application.
                    // Example to navigate back to login screen:
                    // Intent intent = new Intent(SignUpScreenActivity.this, login_screen.class);
                    // startActivity(intent);
                    // finish(); // Close the sign-up activity
                }
            }
        });
    }
}
