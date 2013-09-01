package com.april1985.proxycrawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.jaxen.JaxenException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SiteDiggerCrawler implements Crawler {
    @Override
    public List<Proxy> fetch() throws IOException, JaxenException {
        WebClient webClient = new WebClient();
        HtmlPage page = (HtmlPage) webClient.getPage("http://www.site-digger.com/html/articles/20110516/proxieslist.html");
        List<HtmlTableRow> rows = page.getByXPath("/html/body/div[2]/div/div[2]/div/div[2]/div[3]/table/tbody/tr");

        List<Proxy> proxies = new ArrayList<Proxy>();
        for (HtmlTableRow row : rows) {
            Proxy proxy = new Proxy();
            proxies.add(proxy);
        }

        return proxies;
    }
}
