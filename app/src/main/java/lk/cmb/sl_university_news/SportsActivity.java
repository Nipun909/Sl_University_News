package lk.cmb.sl_university_news;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        // Set up action bar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sports");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}