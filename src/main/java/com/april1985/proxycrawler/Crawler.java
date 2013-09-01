package com.april1985.proxycrawler;

import org.jaxen.JaxenException;

import java.io.IOException;
import java.util.List;

public interface Crawler {
    abstract public List<Proxy> fetch() throws IOException, JaxenException;
}
