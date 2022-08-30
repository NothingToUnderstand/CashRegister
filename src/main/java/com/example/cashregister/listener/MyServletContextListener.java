package com.example.cashregister.listener;

import org.apache.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(MyServletContextListener.class);
    public void contextDestroyed(ServletContextEvent sce)  {
       log.info("Context Destroyed");
    }
    public void contextInitialized(ServletContextEvent sce)  {
        log.info("Context Initialized");
    }

}