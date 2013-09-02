import com.april1985.proxycrawler.SiteDiggerCrawler;
import org.junit.Before;

public class SiteDiggerCrawlerTest extends CrawlerTestBase {
    @Before
    public void setUp() throws Exception {
        crawler = new SiteDiggerCrawler();
    }
}
