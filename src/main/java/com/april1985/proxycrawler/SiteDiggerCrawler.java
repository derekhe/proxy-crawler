package com.april1985.proxycrawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jaxen.JaxenException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SiteDiggerCrawler extends Crawler {
    @Override
    public List<Proxy> fetch() throws IOException, JaxenException {
        WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);

        HtmlPage page = (HtmlPage) webClient.getPage("http://www.site-digger.com/html/articles/20110516/proxieslist.html");
        List<DomText> texts = page.getByXPath("/html/body/div[2]/div/div[2]/div/div[2]/div[3]/table/tbody/tr/td/text()");

        List<Proxy> proxies = new ArrayList<Proxy>();
        for(int i=0;i<texts.size();i+=3)
        {
            Proxy proxy = new Proxy();
            proxies.add(proxy);
            String ipWithPort = texts.get(i).asText();
            setIP(proxy, ipWithPort);
        }

        return proxies;
    }

}
