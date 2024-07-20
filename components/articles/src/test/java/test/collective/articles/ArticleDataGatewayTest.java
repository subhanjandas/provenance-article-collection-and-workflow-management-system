package test.collective.articles;

import io.collective.articles.ArticleDataGateway;
import io.collective.articles.ArticleRecord;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArticleDataGatewayTest {
    ArticleDataGateway gateway = new ArticleDataGateway(List.of(
            new ArticleRecord(10101, "Programming Languages InfoQ Trends Report - October 2019 4", "link1", true),
            new ArticleRecord(10102, "Single Page Applications and ASP.NET Core 3.0 2", "link2", false),
            new ArticleRecord(10103, "Google Open-Sources ALBERT Natural Language Model", "link3", true),
            new ArticleRecord(10104, "Ahead of re:Invent, Amazon Updates AWS Lambda", "link4", false),
            new ArticleRecord(10105, "Electron Desktop JavaScript Framework Finds a New Home", "link5", true),
            new ArticleRecord(10106, "Ryan Kitchens on Learning from Incidents at Netflix, the Role of SRE, and Sociotechnical Systems", "link6", true)
    ));

    @Test
    public void findAll() {
        List<ArticleRecord> all = gateway.findAll();
        assertEquals(6, all.size());

        List<ArticleRecord> available = gateway.findAvailable();
        assertEquals(4, available.size());
    }
}
