package test.collective.rss;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.collective.rss.RSS;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RSSTest {

    @Test
    public void testRSSParsing() throws Exception {
        // Example RSS feed for testing
        String xml = "<rss version=\"2.0\"><channel><item><title>Test Article 1</title><link>http://example.com/1</link><description>Desc 1</description></item><item><title>Test Article 2</title><link>http://example.com/2</link><description>Desc 2</description></item></channel></rss>";

        RSS rss = parseRSS(xml);
        assertEquals(2, rss.getChannel().getItems().size());
    }

    private RSS parseRSS(String xml) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(xml, RSS.class);
    }
}
