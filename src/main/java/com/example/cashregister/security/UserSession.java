package com.example.cashregister.security;


import com.example.cashregister.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;


/**
 * Session for user
 * */
public class UserSession {
    private static final Logger log = Logger.getLogger(UserSession.class);

    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        log.info("User stored to session");
        session.setAttribute("loginedUser", loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        log.info("get user from session");
        User loginedUser = (User) session.getAttribute("loginedUser");
       if(loginedUser==null){
           log.warn("there is no user in session");
           loginedUser=new User(0,null,null,null,null,"unknown");
       }else{
           log.info("user in session is found");
       }
        return loginedUser;
    }


}
