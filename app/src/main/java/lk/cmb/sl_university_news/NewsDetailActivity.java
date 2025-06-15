package lk.cmb.sl_university_news;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class NewsDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        ImageView newsImage = findViewById(R.id.newsDetailImage);
        TextView newsTitle = findViewById(R.id.newsDetailTitle);
        TextView newsBody = findViewById(R.id.newsDetailBody);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");

        newsTitle.setText(title);
        newsBody.setText(body);
        Glide.with(this).load(imageUrl).into(newsImage);
    }
}
