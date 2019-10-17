package com.xzjie.cms.core.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class RequestHolder {
    private static final String UNKNOWN = "unknown";

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取ip地址
     */
    public static String getIp() {
        HttpServletRequest request = getHttpServletRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ip;
    }

    /**
     * 根据ip获取详细地址
     */
    public static String getCityInfo(String ip) {
//        DbSearcher searcher = null;
//        try {
//            String path = "ip2region/ip2region.db";
//            String name = "ip2region.db";
//            DbConfig config = new DbConfig();
//            File file = FileUtil.inputStreamToFile(new ClassPathResource(path).getStream(), name);
//            searcher = new DbSearcher(config, file.getPath());
//            Method method;
//            method = searcher.getClass().getMethod("btreeSearch", String.class);
//            DataBlock dataBlock;
//            dataBlock = (DataBlock) method.invoke(searcher, ip);
//            String address = dataBlock.getRegion().replace("0|", "");
//            char symbol = '|';
//            if (address.charAt(address.length() - 1) == symbol) {
//                address = address.substring(0, address.length() - 1);
//            }
//            return address.equals(YshopConstant.REGION) ? "内网IP" : address;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (searcher != null) {
//                try {
//                    searcher.close();
//                } catch (IOException ignored) {
//                }
//            }
//
//        }
        return "";
    }

    public static String getBrowser() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("User-Agent");
    }

}
