package com.module.user.shuyou.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitServletContextListener
        implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InitAction.init();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}