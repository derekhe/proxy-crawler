import com.april1985.proxycrawler.IPCNCrawler;
import org.junit.Before;

public class IPCNCrawlerTest extends CrawlerTestBase {
    @Before
    public void setUp() throws Exception {
        crawler = new IPCNCrawler();
    }
}
