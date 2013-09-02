package com.april1985.proxycrawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jaxen.JaxenException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern ipMatcher = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+");
        Matcher matcher = ipMatcher.matcher(proxyContent);

        List<Proxy> proxyList = new ArrayList<Proxy>();
        while (matcher.find()) {
            String ipWithPorts = matcher.group();

            Proxy proxy = new Proxy();
            setIP(proxy, ipWithPorts);
            proxyList.add(proxy);
        }
        return proxyList;
    }
}
