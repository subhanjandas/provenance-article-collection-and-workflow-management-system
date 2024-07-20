package io.collective.articles;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.collective.restsupport.BasicHandler;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ArticlesController extends BasicHandler {
    private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);
    private final ArticleDataGateway gateway;

    public ArticlesController(ObjectMapper mapper, ArticleDataGateway gateway) {
        super(mapper);
        this.gateway = gateway;
    }

    @Override
    public void handle(String target, Request request, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        get("/articles", List.of("application/json", "text/html"), request, servletResponse, () -> {
            List<ArticleInfo> articleInfos = getArticleInfos(gateway.findAll());
            handleIOException(servletResponse, articleInfos);
        });

        get("/available", List.of("application/json"), request, servletResponse, () -> {
            List<ArticleInfo> articleInfos = getArticleInfos(gateway.findAvailable());
            handleIOException(servletResponse, articleInfos);
        });
    }

    private List<ArticleInfo> getArticleInfos(List<ArticleRecord> articles) {
        return articles.stream()
                .map(article -> new ArticleInfo(article.getId(), article.getTitle()))
                .collect(Collectors.toList());
    }

    private void handleIOException(HttpServletResponse response, List<ArticleInfo> articles) {
        try {
            writeJsonBody(response, articles);
        } catch (IOException e) {
            logger.error("Error writing JSON response", e);
        }
    }

    private void writeJsonBody(HttpServletResponse response, List<ArticleInfo> articles) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), articles);
    }
}
