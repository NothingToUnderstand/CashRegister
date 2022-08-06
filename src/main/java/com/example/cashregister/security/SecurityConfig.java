package com.example.cashregister.security;

import java.util.*;

/**
 * Configuration that describes links that can be visited by each role
 */
public class SecurityConfig {
    private final static String CASHIER = "cashier";
    private final static String SENIOR_CASHIER = "senior_cashier";
    private final static String COMMODITY_EXPERT = "commodity_expert";
    private final static String UNKNOWN = "unknown";
    private final static String ADMIN = "admin";


    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {
        mapConfig.put(ADMIN, null);


        List<String> urlPatterns1 = new ArrayList<String>();
        urlPatterns1.add("/favicon.ico");
        urlPatterns1.add("/login");
        urlPatterns1.add("/logout");
        urlPatterns1.add("/");
        urlPatterns1.add("/info/product");
        urlPatterns1.add("/all/products");
        urlPatterns1.add("/acc/cashier");
        urlPatterns1.add("/update/user");

        urlPatterns1.add("/create/receipt");
        urlPatterns1.add("/add/product");
        urlPatterns1.add("/close/receipt");

        mapConfig.put(CASHIER, urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/login");
        urlPatterns2.add("/logout");
        urlPatterns2.add("/");
        urlPatterns2.add("/acc/senior_cashier");

        urlPatterns2.add("/info/product");

        urlPatterns2.add("/info/receipt");
        urlPatterns2.add("/update/receipt");
        urlPatterns2.add("/delete/receipt");
        urlPatterns2.add("/cancel/product");
        urlPatterns2.add("/created/report");
        urlPatterns2.add("/update/user");


        urlPatterns2.add("/favicon.ico");
        mapConfig.put(SENIOR_CASHIER, urlPatterns2);

        List<String> urlPatterns3 = new ArrayList<String>();
        urlPatterns3.add("/login");
        urlPatterns3.add("/logout");
        urlPatterns3.add("/");
        urlPatterns3.add("/create/product");
        urlPatterns3.add("/info/product");
        urlPatterns3.add("/update/product");
        urlPatterns3.add("/delete/product");
        urlPatterns3.add("/all/products");
        urlPatterns3.add("/update/user");
        urlPatterns3.add("/acc/commodity_expert");
        urlPatterns3.add("/favicon.ico");
        mapConfig.put(COMMODITY_EXPERT, urlPatterns3);

        List<String> urlPatterns4 = new ArrayList<String>();
        urlPatterns4.add("/login");
        urlPatterns4.add("/create/user");
        urlPatterns4.add("/logout");
        urlPatterns4.add("/");
        urlPatterns4.add("/favicon.ico");

        mapConfig.put(UNKNOWN, urlPatterns4);

    }
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
    public static Set<String> getRoles() {
        return mapConfig.keySet();
    }
}
