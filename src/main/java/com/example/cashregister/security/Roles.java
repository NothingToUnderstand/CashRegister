package com.example.cashregister.security;

public enum Roles {
    CASHIER("cashier"),
    SENIOR_CASHIER("senior_cashier"),
    COMMODITY_EXPERT("commodity_expert"),
    UNKNOWN("unknown"),
    ADMIN("admin");
    private final String name;

    Roles(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
