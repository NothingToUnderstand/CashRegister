package com.example.cashregister.controller.security;

import java.util.*;
/**
 *Configuration that describes links that can be visited by each role
 * */
public class SecurityConfig {
    public  static String CASHIER="cashier";
    public  static String SENIOR_CASHIER="senior cashier";
    public  static String COMMODITY_EXPERT="commodity expert";
    public  static String UNKNOWN="unknown";


    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }
    private static void init() {

        List<String> urlPatterns1 = new ArrayList<String>();
        urlPatterns1.add("/login");
        urlPatterns1.add("/logout");
        urlPatterns1.add("/");
        urlPatterns1.add("/info/product");
        urlPatterns1.add("/all/products");
        urlPatterns1.add("/update/product");
        mapConfig.put(CASHIER, urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/login");
        urlPatterns2.add("/logout");
        urlPatterns2.add("/");
        mapConfig.put(SENIOR_CASHIER, urlPatterns2);

    List<String> urlPatterns3= new ArrayList<String>();
        urlPatterns3.add("/login");
        urlPatterns3.add("/logout");
        urlPatterns3.add("/");
        urlPatterns3.add("/create/product");
        urlPatterns3.add("/all/products");
        mapConfig.put(COMMODITY_EXPERT, urlPatterns3);

        List<String> urlPatterns4 = new ArrayList<String>();
        urlPatterns4.add("/login");
        urlPatterns4.add("/signup");
        urlPatterns4.add("/logout");
        urlPatterns4.add("/");
        mapConfig.put(UNKNOWN, urlPatterns2);
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
