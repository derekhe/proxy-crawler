import com.april1985.proxycrawler.Proxy;

import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class CrawlerTestBase {
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
}
