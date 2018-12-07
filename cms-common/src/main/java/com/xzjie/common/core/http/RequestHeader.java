package com.xzjie.common.core.http;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Request Headers
 */
public class RequestHeader {


   // private static Map<String,String> headers =new HashMap<String,String>();
    public static List<String> headers = new LinkedList<String>();

    static {
        //headers.put("Accept","Accept");
        headers.add("Accept");
        headers.add("Accept-Charset");
        headers.add("Accept-Encoding");
        headers.add("Accept-Language");
        headers.add("Accept-Ranges");
        headers.add("Authorization");
        headers.add("Cache-Control");
        headers.add("Connection");
        headers.add("Cookie");
        headers.add("Content-Length");
        headers.add("Content-Type");
        headers.add("Date");
        headers.add("Expect");
        headers.add("From");
        headers.add("Host");
        headers.add("If-Match");
        headers.add("If-Modified-Since");
        headers.add("If-None-Match");
        headers.add("If-Range");
        headers.add("If-Unmodified-Since");
        headers.add("Max-Forwards");
        headers.add("Pragma");
        headers.add("Proxy-Authorization");
        headers.add("Range");
        headers.add("Referer");
        headers.add("TE");
        headers.add("Upgrade");
        headers.add("User-Agent");
        headers.add("Via");
        headers.add("Warning");
    }
}
