package test.collective.articles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.collective.articles.ArticleDataGateway;
import io.collective.articles.ArticleInfo;
import io.collective.articles.ArticleRecord;
import io.collective.articles.ArticlesController;
import io.collective.restsupport.BasicApp;
import io.collective.restsupport.RestTemplate;
import org.eclipse.jetty.server.handler.HandlerList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArticlesControllerTest {
    private ArticleDataGateway gateway = new ArticleDataGateway();

    BasicApp app = new BasicApp(8888) {
        protected HandlerList handlerList() {
            HandlerList list = new HandlerList();
            list.addHandler(new ArticlesController(new ObjectMapper(), gateway));
            return list;
        }
    };

    @Before
    public void setUp() throws Exception {
        gateway.save(new ArticleRecord(10101, "Programming Languages InfoQ Trends Report - October 2019 4", "https://link1", true));
        gateway.save(new ArticleRecord(10102, "Single Page Applications and ASP.NET Core 3.0 2", "https://link2", false));
        gateway.save(new ArticleRecord(10103, "Google Open-Sources ALBERT Natural Language Model", "https://link3", true));
        gateway.save(new ArticleRecord(10104, "Ahead of re:Invent, Amazon Updates AWS Lambda", "https://link4", false));
        gateway.save(new ArticleRecord(10105, "Electron Desktop JavaScript Framework Finds a New Home", "https://link5", true));
        gateway.save(new ArticleRecord(10106, "Ryan Kitchens on Learning from Incidents at Netflix, the Role of SRE, and Sociotechnical Systems", "https://link6", true));
        app.start();
    }

    @After
    public void tearDown() throws Exception {
        app.stop();
    }

    @Test
    public void findAll() throws IOException {
        RestTemplate template = new RestTemplate();
        String response = template.get("http://localhost:8888/articles", "application/json");
        List<ArticleInfo> entries = new ObjectMapper().readValue(response, new TypeReference<List<ArticleInfo>>() {});
        assertEquals(6, entries.size());
    }

    @Test
    public void findAvailable() throws IOException {
        RestTemplate template = new RestTemplate();
        String response = template.get("http://localhost:8888/available", "application/json");
        List<ArticleInfo> entries = new ObjectMapper().readValue(response, new TypeReference<List<ArticleInfo>>() {});
        assertEquals(4, entries.size());
    }
}
