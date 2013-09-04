import com.april1985.proxycrawler.Crawler;
import com.april1985.proxycrawler.Proxy;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public abstract class CrawlerTestBase {
    protected Crawler crawler;

    protected void checkIP(List<Proxy> proxyList) {
        for (Proxy proxy : proxyList) {
            String ip = proxy.getIP();

            String[] split = ip.split(Pattern.quote("."));
            assertThat(split.length, is(4));
            assertThat(Integer.parseInt(split[0]), allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
            assertThat(Integer.parseInt(split[1]), allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
            assertThat(Integer.parseInt(split[2]), allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
            assertThat(Integer.parseInt(split[3]), allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(255)));
        }
    }

    protected void checkPort(List<Proxy> proxyList)
    {
        for(Proxy proxy: proxyList)
        {
            String port = proxy.getPort();
            assertThat(Integer.parseInt(port), is(greaterThan(0)));
        }
    }

    @Test
    public void should_get_proxy_list() throws IOException {
        List<Proxy> proxies = crawler.fetch();

        assertThat(proxies.size(), Is.is(greaterThan(50)));
    }

    @Test
    public void should_get_port() throws IOException {
        List<Proxy> proxies = crawler.fetch();

        checkPort(proxies);
    }

    @Test
    public void should_get_ip() throws IOException {
        List<Proxy> proxies = crawler.fetch();

        checkIP(proxies);
    }
}
