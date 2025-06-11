// SplashScreenActivity.java
package lk.cmb.sl_university_news; // Corrected package name, ensure it matches your project's actual package

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity { // Renamed class to SplashScreenActivity for clarity

    // Duration of the splash screen in milliseconds (e.g., 3 seconds)
    private static final int SPLASH_SCREEN_DELAY = 3000; // Renamed variable for consistency

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to your activity_splash_screen.xml layout file
        setContentView(R.layout.activity_splash_screen); // Ensure this matches your XML file name

        // Use a Handler to post a delayed action.
        // This action will run after SPLASH_SCREEN_DELAY milliseconds.
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent to start the LoginScreen activity. */
                // IMPORTANT: Ensure 'login_screen.class' matches the exact class name of your login activity
                Intent loginIntent = new Intent(SplashScreenActivity.this, login_screen.class);
                // Start the LoginScreen activity
                SplashScreenActivity.this.startActivity(loginIntent);
                // Finish the current splash screen activity.
                // This prevents the user from navigating back to the splash screen using the back button.
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }
}
