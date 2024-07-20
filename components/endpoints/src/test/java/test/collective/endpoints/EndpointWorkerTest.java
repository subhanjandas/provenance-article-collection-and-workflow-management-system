package test.collective.endpoints;

import io.collective.articles.ArticleDataGateway;
import io.collective.articles.ArticleRecord;
import io.collective.endpoints.EndpointTask;
import io.collective.endpoints.EndpointWorker;
import io.collective.restsupport.RestTemplate;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EndpointWorkerTest {
    @Test
    public void finder() throws IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<rss version=\"2.0\">\n" +
                "    <channel>\n" +
                "        <title>InfoQ</title>\n" +
                "        <link>https://www.infoq.com</link>\n" +
                "        <description>InfoQ feed</description>\n" +
                "        <item>\n" +
                "            <title>Presentation: InfraCoding with Terraform: Writing Tests for Infrastructure-as-Code</title>\n" +
                "            <link>https://www.infoq.com/presentations/iac-terraform-testing/?utm_campaign=infoq_content&amp;utm_source=infoq&amp;utm_medium=feed&amp;utm_term=global</link>\n" +
                "            <description>&lt;img src=\"https://res.infoq.com/presentations/iac-terraform-testing/en/headerimage/Peterbig-1583861132792.JPG\"/&gt;&lt;p&gt;Peter Souter discusses some approaches for testing Infrastructure-as-code, with a focus on Terraform, covering the benefits, basic linting and formatting, unit testing, and spec testing.&lt;/p&gt; &lt;i&gt;By Peter Souter&lt;/i&gt;</description>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>GitLab 2020 Remote Work Report Highlights Key Motivators for Remote Work</title>\n" +
                "            <link>https://www.infoq.com/news/2020/03/gitlab-2020-remote-work-report/?utm_campaign=infoq_content&amp;utm_source=infoq&amp;utm_medium=feed&amp;utm_term=global</link>\n" +
                "            <description>&lt;img src=\"https://res.infoq.com/news/2020/03/gitlab-2020-remote-work-report/en/headerimage/gitlab-2020-remote-work-report-1583878895911.jpg\"/&gt;&lt;p&gt;GitLab has just released its 2020 Remote Work Report, which provides a thorough perspective on remote work from the viewpoint of both remote workers and employers. Based on over 3,000 respondents across various industries and roles, the report also aims to give a glimpse of what remote work might look like in future. InfoQ has taken the chance to speak with GitLab head of remote Darren Murph.&lt;/p&gt; &lt;i&gt;By Sergio De Simone&lt;/i&gt;</description>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Presentation: ML/AI Panel</title>\n" +
                "            <link>https://www.infoq.com/presentations/panel-ml-tooling/?utm_campaign=infoq_content&amp;utm_source=infoq&amp;utm_medium=feed&amp;utm_term=global</link>\n" +
                "            <description>&lt;img src=\"https://res.infoq.com/presentations/panel-ml-tooling/en/headerimage/Chrisbig-1583859513926.jpg\"/&gt;&lt;p&gt;M. Casbon, C. Albon, P. Bailey, A. Unruh, J. Andrews discuss what makes ML different from other types of applications and why it requires special tooling. They also talk about where the low-hanging fruit is and how they recommend acquiring those initial quick wins.&lt;/p&gt; &lt;i&gt;By Chris Albon, Paige Bailey, Amy Unruh, June Andrews, Melanie Warrick&lt;/i&gt;</description>\n" +
                "        </item>\n" +
                "    </channel>\n" +
                "</rss>";

        RestTemplate mockTemplate = mock(RestTemplate.class);
        when(mockTemplate.get("https://feed.infoq.com/", "application/xml")).thenReturn(xml);

        ArticleDataGateway gateway = new ArticleDataGateway();
        EndpointWorker worker = new EndpointWorker(mockTemplate, gateway);
        worker.execute(new EndpointTask("https://feed.infoq.com/"));

        List<ArticleRecord> articles = gateway.findAll();
        assertEquals(3, articles.size());
    }
}
