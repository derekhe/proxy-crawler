import com.april1985.proxycrawler.Crawler;
import com.april1985.proxycrawler.IPCNCrawler;
import com.april1985.proxycrawler.Proxy;
import org.jaxen.JaxenException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IPCNCrawlerTest {
    private Crawler crawler;

    @Before
    public void setUp() throws Exception {
        crawler = new IPCNCrawler();
    }

    @Test
    public void should_get_proxy_list_from_ipcn() throws IOException, JaxenException {
        List<Proxy> proxies = crawler.fetch();

        assertThat(proxies.size(), is(greaterThan(100)));
    }
}
