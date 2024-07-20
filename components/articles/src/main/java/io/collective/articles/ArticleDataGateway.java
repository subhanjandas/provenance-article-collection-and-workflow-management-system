package io.collective.articles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleDataGateway {
    private final List<ArticleRecord> articles;

    public ArticleDataGateway() {
        this.articles = new ArrayList<>();
    }

    public ArticleDataGateway(List<ArticleRecord> articles) {
        this.articles = new ArrayList<>(articles);
    }

    public List<ArticleRecord> findAll() {
        return new ArrayList<>(articles);
    }

    public List<ArticleRecord> findAvailable() {
        return articles.stream().filter(ArticleRecord::isAvailable).collect(Collectors.toList());
    }

    public void save(ArticleRecord article) {
        articles.add(article);
    }

    public void clear() {
        articles.clear();
    }
}
