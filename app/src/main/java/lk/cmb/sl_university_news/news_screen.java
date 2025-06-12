package lk.cmb.sl_university_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class news_screen extends AppCompatActivity {

    MaterialCardView newsCard1, newsCard2, newsCard3, newsCard4;
    BottomNavigationView bottomNavigationView;
    ImageButton btnAccount;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_screen);

        // Initialize toolbar
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        // Initialize news cards
        newsCard1 = findViewById(R.id.newsCard1);
        newsCard2 = findViewById(R.id.newsCard2);
        newsCard3 = findViewById(R.id.newsCard3);
        newsCard4 = findViewById(R.id.newsCard4);

        // Initialize toolbar buttons
        btnAccount = findViewById(R.id.btnAccount);

        // Handle menu button click (navigation icon)
        toolbar.setNavigationOnClickListener(view -> {
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show();
            // TODO: Open drawer or navigation menu
        });

        // Handle news card clicks
        View.OnClickListener newsClickListener = view -> {
            Intent intent = new Intent(news_screen.this, FullNewsActivity.class);
            intent.putExtra("news_id", view.getId());
            startActivity(intent);
        };

        newsCard1.setOnClickListener(newsClickListener);
        newsCard2.setOnClickListener(newsClickListener);
        newsCard3.setOnClickListener(newsClickListener);
        newsCard4.setOnClickListener(newsClickListener);

        // Handle account button click
        btnAccount.setOnClickListener(view -> {
            Toast.makeText(this, "Account clicked", Toast.LENGTH_SHORT).show();
            // TODO: Open account/profile activity
            // Intent accountIntent = new Intent(news_screen.this, AccountActivity.class);
            // startActivity(accountIntent);
        });

        // Bottom Navigation setup
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navSport) {
                Intent intent = new Intent(news_screen.this, SportsActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.navAcademic) {
                Intent intent = new Intent(news_screen.this, AcademicActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.navFaculty) {
                Intent intent = new Intent(news_screen.this, FacultyActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });
    }
}