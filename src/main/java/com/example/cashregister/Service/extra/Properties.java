package com.example.cashregister.Service.extra;


import java.io.IOException;

public class Properties {
   private  static final java.util.Properties prop = new java.util.Properties();

    static {
        try {
            prop.load(Properties.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String pr){
        return prop.getProperty(pr);
    }
}
