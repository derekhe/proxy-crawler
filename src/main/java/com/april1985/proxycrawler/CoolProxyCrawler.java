package com.april1985.proxycrawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jaxen.JaxenException;

import java.io.IOException;
import java.util.List;

public class CoolProxyCrawler extends Crawler {
    @Override
    public List<Proxy> fetch() throws IOException, JaxenException {
        WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);
        HtmlPage page = (HtmlPage) webClient.getPage("http://old.cool-proxy.net/index.php?action=anonymous-proxy-list&page=0");

        List<Proxy> proxies = getProxies(page.asText());

        List<HtmlAttr> proxyListUrls = page.getByXPath("/html/body/div[2]/div/div[3]/table/tbody/tr[22]/td/a/@href");
        for (HtmlAttr proxyListUrl : proxyListUrls) {
            String url = "http://old.cool-proxy.net/" + proxyListUrl.getNodeValue();
            HtmlPage proxyListPages = (HtmlPage)webClient.getPage(url);
            proxies.addAll(getProxies(proxyListPages.asText()));
        }

        return proxies;
    }
}
