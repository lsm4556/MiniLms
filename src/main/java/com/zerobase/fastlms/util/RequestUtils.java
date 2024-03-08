package com.zerobase.fastlms.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RequestUtils {

    public static String getClientIP(HttpServletRequest request) {
        String[] headerTypes = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        String ip = null;
        for (String headerType : headerTypes) {
            ip = request.getHeader(headerType);
            if (ip != null) {
                break;
            }
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        } else if (ip.contains(",")) {
            // If multiple IP addresses are present in the header, use the first one
            ip = ip.split(",")[0].trim();
        }

        // IPv6 주소가 주어진 경우에만 IPv4로 변환
        if (ip != null && ip.contains(":")) {
            try {
                ip = convertIPv6ToIPv4(ip);
            } catch (UnknownHostException e) {
                // IPv6 주소를 IPv4로 변환하는데 실패한 경우, 그대로 반환
            }
        }

        return ip;
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    private static String convertIPv6ToIPv4(String ipv6Address) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipv6Address);
        byte[] ipv4Bytes = inetAddress.getAddress();

        // Convert the IPv6 address to IPv4 format
        return ((int)(ipv4Bytes[12] & 0xff) + "." +
                (int)(ipv4Bytes[13] & 0xff) + "." +
                (int)(ipv4Bytes[14] & 0xff) + "." +
                (int)(ipv4Bytes[15] & 0xff));
    }
}


