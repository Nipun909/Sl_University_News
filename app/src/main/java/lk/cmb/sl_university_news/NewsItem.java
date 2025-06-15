package lk.cmb.sl_university_news;

public class NewsItem {
    private String title;
    private String imageUrl;
    private String body;

    public NewsItem() {}

    public NewsItem(String title, String imageUrl, String body) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.body = body;
    }

    public String getTitle() { return title; }

    public String getImageUrl() { return imageUrl; }

    public String getBody() { return body; }
}
