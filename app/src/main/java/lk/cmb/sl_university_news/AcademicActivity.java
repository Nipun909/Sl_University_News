package lk.cmb.sl_university_news;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AcademicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);

        // Set up action bar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Academic");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}