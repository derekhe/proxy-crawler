import com.april1985.proxycrawler.Crawler;
import com.april1985.proxycrawler.Proxy;
import com.april1985.proxycrawler.SiteDiggerCrawler;
import org.jaxen.JaxenException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SiteDiggerCrawlerTest {
    @Test
    public void should_get_proxy_lists_from_site_digger() throws IOException, JaxenException {
        Crawler crawler = new SiteDiggerCrawler();

        List<Proxy> proxyList = crawler.fetch();

        assertThat(proxyList.size(), is(200));
    }

}
