import com.april1985.proxycrawler.ItmopCrawler;
import org.junit.Before;

public class ItmopCrawlerTest extends CrawlerTestBase {
    @Before
    public void setUp() throws Exception {
        crawler = new ItmopCrawler();
    }
}
