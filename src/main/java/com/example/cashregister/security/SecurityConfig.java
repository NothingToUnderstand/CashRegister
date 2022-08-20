package com.example.cashregister.security;

import java.util.*;

/**
 * Configuration that describes links that can be visited by each role
 */
public class SecurityConfig {

    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        List<String> urlPatterns0 = new ArrayList<String>();
        urlPatterns0.add("/favicon.ico");
        urlPatterns0.add("/login");
        urlPatterns0.add("/logout");
        urlPatterns0.add("/");
        urlPatterns0.add("/acc");
        urlPatterns0.add("/update/user");
        urlPatterns0.add("/all/reports");
        urlPatterns0.add("/archive/receipts");
        urlPatterns0.add("/info/archive/receipt");

        mapConfig.put(Roles.ADMIN.getName(), urlPatterns0);


        List<String> urlPatterns1 = new ArrayList<String>();
        urlPatterns1.add("/favicon.ico");
        urlPatterns1.add("/login");
        urlPatterns1.add("/logout");
        urlPatterns1.add("/");
        urlPatterns1.add("/info/product");
        urlPatterns1.add("/all/products");
        urlPatterns1.add("/acc");
        urlPatterns1.add("/update/user");

        urlPatterns1.add("/create/receipt");
        urlPatterns1.add("/add/product");
        urlPatterns1.add("/close/receipt");
        urlPatterns1.add("/info/receipt");


        mapConfig.put(Roles.CASHIER.getName(), urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/login");
        urlPatterns2.add("/logout");
        urlPatterns2.add("/");
        urlPatterns2.add("/acc");

        urlPatterns2.add("/info/product");

        urlPatterns2.add("/info/receipt");
        urlPatterns2.add("/update/receipt");
        urlPatterns2.add("/delete/receipt");
        urlPatterns2.add("/cancel/product");
        urlPatterns2.add("/created/report");
        urlPatterns2.add("/update/user");


        urlPatterns2.add("/favicon.ico");
        mapConfig.put(Roles.SENIOR_CASHIER.getName(), urlPatterns2);

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
        urlPatterns3.add("/acc");
        urlPatterns3.add("/favicon.ico");
        mapConfig.put(Roles.COMMODITY_EXPERT.getName(), urlPatterns3);

        List<String> urlPatterns4 = new ArrayList<String>();
        urlPatterns4.add("/login");
        urlPatterns4.add("/create/user");
        urlPatterns4.add("/logout");
        urlPatterns4.add("/");
        urlPatterns4.add("/favicon.ico");

        mapConfig.put(Roles.UNKNOWN.getName(), urlPatterns4);

    }
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
    public static Set<String> getRoles() {
        return mapConfig.keySet();
    }
}
