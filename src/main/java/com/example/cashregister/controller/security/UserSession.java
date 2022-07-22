package com.example.cashregister.controller.security;


import com.example.cashregister.entity.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * Session for user
 * */
public class UserSession {

    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
       if(loginedUser==null){
           loginedUser=new User(0,null,null,null,null,"unknown");
       }
        return loginedUser;
    }


}
