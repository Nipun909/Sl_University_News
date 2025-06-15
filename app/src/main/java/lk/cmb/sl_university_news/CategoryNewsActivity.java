// File: app/src/main/java/lk/cmb/sl_university_news/CategoryNewsActivity.java

package lk.cmb.sl_university_news;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryNewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsCardAdapter adapter;
    private List<NewsItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_news);

        // Read category from intent
        String category = getIntent().getStringExtra("category");

        // Toolbar setup
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(category);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // RecyclerView + adapter
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        adapter = new NewsCardAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Query /News where title == category
        DatabaseReference newsRef = FirebaseDatabase
                .getInstance()
                .getReference("News");

        newsRef.orderByChild("title")
                .equalTo(category)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snap) {
                        list.clear();
                        for (DataSnapshot ds : snap.getChildren()) {
                            String t   = ds.child("title").getValue(String.class);
                            String img = ds.child("imageUrl").getValue(String.class);
                            String b   = ds.child("body").getValue(String.class);
                            if (t != null && img != null && b != null) {
                                list.add(new NewsItem(t, img, b));
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                    @Override public void onCancelled(@NonNull DatabaseError e) { }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
