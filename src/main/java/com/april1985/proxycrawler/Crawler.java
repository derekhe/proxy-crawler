package com.april1985.proxycrawler;

import org.jaxen.JaxenException;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Crawler {
    abstract public List<Proxy> fetch() throws IOException, JaxenException;

    protected void setIP(Proxy proxy, String ipWithPort) {
        String[] split = ipWithPort.split(Pattern.quote(":"));

        proxy.setIP(split[0]);
        proxy.setPort(split[1]);
    }
}
