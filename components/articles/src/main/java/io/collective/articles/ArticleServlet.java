package io.collective.start;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> articles = new ArrayList<>();
        // Add sample articles
        articles.add("Article 1");
        articles.add("Article 2");

        resp.setContentType("application/json");
        resp.getWriter().write(articles.toString());
    }
}
