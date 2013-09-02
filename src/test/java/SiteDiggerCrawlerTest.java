import com.april1985.proxycrawler.Crawler;
import com.april1985.proxycrawler.Proxy;
import com.april1985.proxycrawler.SiteDiggerCrawler;
import org.jaxen.JaxenException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SiteDiggerCrawlerTest {

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
    public void should_get_right_ip_from_site_digger() throws IOException, JaxenException {
        List<Proxy> proxyList = crawler.fetch();

        for (Proxy proxy : proxyList) {
            String ip = proxy.getIP();
            checkIP(ip);
        }
    }

    @Test
    public void should_get_right_port_from_site_digger() throws IOException, JaxenException {
        List<Proxy> proxyList = crawler.fetch();

        for (Proxy proxy : proxyList) {
            String port = proxy.getPort();
            assertThat(Integer.parseInt(port), is(greaterThan(0)));
        }
    }

    private void checkIP(String ip) {
        String[] split = ip.split(Pattern.quote("."));
        assertThat(split.length, is(4));
        assertThat(Integer.parseInt(split[0]), allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
        assertThat(Integer.parseInt(split[1]), allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
        assertThat(Integer.parseInt(split[2]), allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
        assertThat(Integer.parseInt(split[3]), allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
    }
}
