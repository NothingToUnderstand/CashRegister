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
        urlPatterns0.add("/cashregister/favicon.ico");
        urlPatterns0.add("/cashregister/login");
        urlPatterns0.add("/cashregister/logout");
        urlPatterns0.add("/cashregister/");
        urlPatterns0.add("/cashregister/acc");
        urlPatterns0.add("/cashregister/update/user");
        urlPatterns0.add("/cashregister/all/reports");
        urlPatterns0.add("/cashregister/archive/receipts");
        urlPatterns0.add("/cashregister/info/archive/receipt");
        urlPatterns0.add("/cashregister/delete/user");
        urlPatterns0.add("/cashregister/error");
        mapConfig.put(Roles.ADMIN.getName(), urlPatterns0);


        List<String> urlPatterns1 = new ArrayList<String>();
        urlPatterns1.add("/cashregister/favicon.ico");
        urlPatterns1.add("/cashregister/login");
        urlPatterns1.add("/cashregister/logout");
        urlPatterns1.add("/cashregister/");
        urlPatterns1.add("/cashregister/info/product");
        urlPatterns1.add("/cashregister/all/products");
        urlPatterns1.add("/cashregister/acc");
        urlPatterns1.add("/cashregister/update/user");
        urlPatterns1.add("/cashregister/create/receipt");
        urlPatterns1.add("/cashregister/add/product");
        urlPatterns1.add("/cashregister/close/receipt");
        urlPatterns1.add("/cashregister/info/receipt");
        urlPatterns1.add("/cashregister/error");

        mapConfig.put(Roles.CASHIER.getName(), urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/cashregister/login");
        urlPatterns2.add("/cashregister/logout");
        urlPatterns2.add("/cashregister/");
        urlPatterns2.add("/cashregister/acc");
        urlPatterns2.add("/cashregister/info/product");
        urlPatterns2.add("/cashregister/info/receipt");
        urlPatterns2.add("/cashregister/update/receipt");
        urlPatterns2.add("/cashregister/delete/receipt");
        urlPatterns2.add("/cashregister/cancel/product");
        urlPatterns2.add("/cashregister/created/report");
        urlPatterns2.add("/cashregister/update/user");
        urlPatterns2.add("/cashregister/error");
        urlPatterns2.add("/cashregister/close/receipt");
        urlPatterns2.add("/favicon.ico");
        mapConfig.put(Roles.SENIOR_CASHIER.getName(), urlPatterns2);

        List<String> urlPatterns3 = new ArrayList<String>();
        urlPatterns3.add("/cashregister/login");
        urlPatterns3.add("/cashregister/logout");
        urlPatterns3.add("/cashregister/");
        urlPatterns3.add("/cashregister/create/product");
        urlPatterns3.add("/cashregister/info/product");
        urlPatterns3.add("/cashregister/update/product");
        urlPatterns3.add("/cashregister/delete/product");
        urlPatterns3.add("/cashregister/all/products");
        urlPatterns3.add("/cashregister/update/user");
        urlPatterns3.add("/cashregister/acc");
        urlPatterns3.add("/cashregister/favicon.ico");
        urlPatterns3.add("/cashregister/error");
        mapConfig.put(Roles.COMMODITY_EXPERT.getName(), urlPatterns3);

        List<String> urlPatterns4 = new ArrayList<String>();
        urlPatterns4.add("/cashregister/login");
        urlPatterns4.add("/cashregister/create/user");
        urlPatterns4.add("/cashregister/logout");
        urlPatterns4.add("/cashregister/");
        urlPatterns4.add("/cashregister/favicon.ico");
        urlPatterns4.add("/cashregister/forgotPassword");
        urlPatterns4.add("/cashregister/newPassword");
        urlPatterns4.add("/cashregister/validateOtp");
        urlPatterns4.add("/cashregister/error");

        mapConfig.put(Roles.UNKNOWN.getName(), urlPatterns4);

    }
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
    public static Set<String> getRoles() {
        return mapConfig.keySet();
    }
}
