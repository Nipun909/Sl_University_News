package lk.cmb.sl_university_news; // Corrected package name

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to your activity_splash_screen.xml layout file
        setContentView(R.layout.activity_splash_screen); // Ensure this matches your XML file name

        // Use a Handler to post a delayed action.
        // This action will run after SPLASH_DISPLAY_LENGTH milliseconds.
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent to start the MainActivity. */
                Intent mainIntent = new Intent(splash_screen.this, MainActivity.class);
                // Start the MainActivity
                splash_screen.this.startActivity(mainIntent);
                // Finish the current splash screen activity.
                // This prevents the user from navigating back to the splash screen using the back button.
                splash_screen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
