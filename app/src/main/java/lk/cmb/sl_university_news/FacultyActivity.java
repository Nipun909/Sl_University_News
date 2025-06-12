package lk.cmb.sl_university_news;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FacultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        // Set up action bar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Faculty");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}