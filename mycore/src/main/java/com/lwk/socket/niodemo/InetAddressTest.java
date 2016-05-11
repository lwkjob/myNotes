package com.lwk.socket.niodemo;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

/**
 * Created by dell-pc on 2015/12/23.
 */
public class InetAddressTest {
    public static void main(String[] args) throws Exception{
        InetAddress inetAddress=InetAddress.getByName("www.baidu.com");
        InetAddress inet4Address=Inet4Address.getByName("www.baidu.com");
        InetAddress inet6Address= Inet6Address.getByName("www.baidu.com");
        System.out.println(inet4Address.isReachable(5000));//是否拼通
        System.out.println(inet4Address);
        System.out.println(inet6Address);
        System.out.println(inetAddress.getHostAddress());
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getCanonicalHostName()); //Canonical 正经的
    }

}
