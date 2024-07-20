package io.collective.endpoints;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.collective.articles.ArticleDataGateway;
import io.collective.articles.ArticleRecord;
import io.collective.restsupport.RestTemplate;
import io.collective.rss.RSS;
import io.collective.workflow.Worker;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class EndpointWorker implements Worker<EndpointTask> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate template;
    private final ArticleDataGateway gateway;

    public EndpointWorker(RestTemplate template, ArticleDataGateway gateway) {
        this.template = template;
        this.gateway = gateway;
    }

    @NotNull
    @Override
    public String getName() {
        return "ready";
    }

    @Override
    public void execute(EndpointTask task) throws IOException {
        String response = template.get(task.getEndpoint(), "application/xml");
        if (response == null) {
            logger.error("Failed to fetch data from endpoint: " + task.getEndpoint());
            return;
        }

        gateway.clear();

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RSS rss;
        try {
            rss = xmlMapper.readValue(response, RSS.class);
        } catch (IOException e) {
            logger.error("Failed to parse RSS feed from endpoint: " + task.getEndpoint(), e);
            return;
        }

        if (rss.getChannel() == null) {
            logger.error("RSS feed has no channel: " + task.getEndpoint());
            return;
        }

        List<RSS.Item> items = rss.getChannel().getItems();
        if (items == null) {
            logger.error("RSS feed channel has no items: " + task.getEndpoint());
            return;
        }

        List<ArticleRecord> articles = items.stream()
                .map(item -> new ArticleRecord(0, item.getTitle(), item.getLink(), true))
                .collect(Collectors.toList());

        for (ArticleRecord article : articles) {
            gateway.save(article);
        }
    }
}
