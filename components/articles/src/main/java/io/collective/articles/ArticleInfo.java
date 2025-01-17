package io.collective.articles;

public class ArticleInfo {
    private int id;
    private String title;

    // No-argument constructor needed for deserialization
    public ArticleInfo() {}

    public ArticleInfo(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
