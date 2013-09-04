package com.april1985.proxycrawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

public class CoolProxyCrawler extends Crawler {
    @Override
    public List<Proxy> fetch() throws IOException {
        WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);
        HtmlPage page = (HtmlPage) webClient.getPage("http://old.cool-proxy.net/index.php?action=anonymous-proxy-list&page=0");

        List<Proxy> proxies = getProxies(page.asText());

        List<DomAttr> proxyListUrls = (List<DomAttr>) page.getByXPath("/html/body/div[2]/div/div[3]/table/tbody/tr[22]/td/a/@href");
        for (DomAttr proxyListUrl : proxyListUrls) {
            String url = "http://old.cool-proxy.net/" + proxyListUrl.getNodeValue();
            HtmlPage proxyListPages = (HtmlPage)webClient.getPage(url);
            proxies.addAll(getProxies(proxyListPages.asText()));
        }

        return proxies;
    }
}
