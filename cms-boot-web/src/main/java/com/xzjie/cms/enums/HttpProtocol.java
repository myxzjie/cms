package com.xzjie.cms.enums;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum HttpProtocol {
    HTTP, HTTPS;

    public static HttpProtocol match(String url) {
        if (url == null || url == "") {
            return null;
        }
        Pattern pattern = Pattern.compile("(http|https):\\/\\/([\\w.]+\\/?)\\S*");
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            if (url.startsWith("https:") || url.startsWith("HTTPS:")) {
                return HTTPS;
            } else if (url.startsWith("http:") || url.startsWith("http:")) {
                return HTTP;
            }
        }
        return null;
    }
}
