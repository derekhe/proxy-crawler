package com.april1985.proxycrawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jaxen.JaxenException;

import java.io.IOException;
import java.util.List;

public class IPCNCrawler extends Crawler {

    @Override
    public List<Proxy> fetch() throws IOException, JaxenException {
        List<Proxy> proxyList = getFromPage("http://proxy.ipcn.org/proxylist.html");
        List<Proxy> proxyLists2 = getFromPage("http://proxy.ipcn.org/proxylist2.html");

        proxyList.addAll(proxyLists2);

        return proxyList;
    }

    private List<Proxy> getFromPage(String url) throws IOException, JaxenException {
        WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);

        HtmlPage page = (HtmlPage) webClient.getPage(url);
        String proxyContent = page.getByXPath("/html/body/table/tbody/tr/td/pre/text()").get(0).toString();

        List<Proxy> proxyList = getProxies(proxyContent);

        return proxyList;
    }
}
