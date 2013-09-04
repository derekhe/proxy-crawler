package com.april1985.proxycrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Crawler {

    abstract public List<Proxy> fetch() throws IOException;

    protected void setIP(Proxy proxy, String ipWithPort) {
        String[] split = ipWithPort.split(Pattern.quote(":"));

        proxy.setIP(split[0]);
        proxy.setPort(split[1]);
    }

    protected List<Proxy> getProxies(String proxyContent) {
        Matcher matcher = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+").matcher(proxyContent);

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
