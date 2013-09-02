import com.april1985.proxycrawler.Crawler;
import com.april1985.proxycrawler.Proxy;
import com.april1985.proxycrawler.SiteDiggerCrawler;
import org.jaxen.JaxenException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SiteDiggerCrawlerTest extends CrawlerTestBase {

    private Crawler crawler;

    @Before
    public void setUp() throws Exception {
        crawler = new SiteDiggerCrawler();
    }

    @Test
    public void should_get_proxy_lists_from_site_digger() throws IOException, JaxenException {
        List<Proxy> proxyList = crawler.fetch();

        assertThat(proxyList.size(), is(200));
    }

    @Test
    public void should_get_ip_from_site_digger() throws IOException, JaxenException {
        List<Proxy> proxyList = crawler.fetch();

        checkIP(proxyList);
    }

    @Test
    public void should_get_port_from_site_digger() throws IOException, JaxenException {
        List<Proxy> proxyList = crawler.fetch();

        checkPort(proxyList);
    }
}
