package lk.cmb.sl_university_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class news_screen extends AppCompatActivity {

    private ViewPager2 breakingNewsSlider;
    private SliderAdapter sliderAdapter;

    private RecyclerView newsRecyclerView;
    private NewsCardAdapter newsCardAdapter;

    private List<NewsItem> newsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_screen);

        // ─── Toolbar Setup ─────────────────────
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Dev Info popup on menu icon
        toolbar.setNavigationOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(news_screen.this, v);
            popup.getMenuInflater().inflate(R.menu.toolbar_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_dev_info) {
                    startActivity(new Intent(news_screen.this, DevInfoActivity.class));
                    return true;
                }
                return false;
            });
            popup.show();
        });

        // ─── Account Button ─────────────────────
        ImageButton btnAccount = findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(v ->
                startActivity(new Intent(news_screen.this, UserInfoActivity.class))
        );

        // ─── Breaking News Slider ────────────────
        newsItems = new ArrayList<>();
        breakingNewsSlider = findViewById(R.id.breakingNewsSlider);
        sliderAdapter = new SliderAdapter(this, newsItems);
        breakingNewsSlider.setAdapter(sliderAdapter);

        // ─── Recommended RecyclerView ────────────
        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsCardAdapter = new NewsCardAdapter(this, newsItems);
        newsRecyclerView.setAdapter(newsCardAdapter);

        // ─── Bottom Navigation ───────────────────
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                int id = item.getItemId();
                if (id == R.id.navSport) {
                    intent = new Intent(news_screen.this, CategoryNewsActivity.class);
                    intent.putExtra("category", "Sports");
                } else if (id == R.id.navAcademic) {
                    intent = new Intent(news_screen.this, CategoryNewsActivity.class);
                    intent.putExtra("category", "Academic");
                } else if (id == R.id.navFaculty) {
                    intent = new Intent(news_screen.this, CategoryNewsActivity.class);
                    intent.putExtra("category", "Faculty");
                } else {
                    return false;
                }
                startActivity(intent);
                return true;
            }
        });

        // ─── Load News from Firebase ─────────────
        loadNewsFromArray();
    }

    private void loadNewsFromArray() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("News");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newsItems.clear();
                for (DataSnapshot newsSnapshot : snapshot.getChildren()) {
                    // Skip null entries (like index 0)
                    if (newsSnapshot.getValue() == null) continue;

                    String title = newsSnapshot.child("title").getValue(String.class);
                    String imageUrl = newsSnapshot.child("imageUrl").getValue(String.class);
                    String body = newsSnapshot.child("body").getValue(String.class);

                    if (title != null && imageUrl != null && body != null) {
                        newsItems.add(new NewsItem(title, imageUrl, body));
                    }
                }
                sliderAdapter.notifyDataSetChanged();
                newsCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("FIREBASE ERROR: " + error.getMessage());
            }
        });
    }
}
