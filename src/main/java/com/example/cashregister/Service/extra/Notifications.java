package com.example.cashregister.Service.extra;

public class Notifications {
    private static String message;
    private static String errormessage;


    public static void setMessage(String message) {
        Notifications.message=message;
    }


    public  static void setErrormessage(String errormessage) {
        Notifications.errormessage = errormessage;
    }

    protected static String getMessage() {
        String a=message;
        message=null;
        return a;
    }

    protected static String getErrormessage() {
        String a=errormessage;
        errormessage=null;
        return a;
    }


}
