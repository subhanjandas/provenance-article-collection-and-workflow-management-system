package io.collective.articles;

public class ArticleRecord {
    private final int id;
    private final String title;
    private final String link;
    private final boolean available;

    public ArticleRecord(int id, String title, String link, boolean available) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public boolean isAvailable() {
        return available;
    }
}
