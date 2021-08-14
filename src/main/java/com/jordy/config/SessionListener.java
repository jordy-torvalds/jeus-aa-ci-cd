package com.jordy.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(0);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
    }
}