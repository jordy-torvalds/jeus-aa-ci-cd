package com.jordy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.logging.Logger;

@Configuration
public class WebAppInitializer implements WebApplicationInitializer {

    private static final String CONFIG_LOCATION = "com.jordy.config";
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING_URL = "/";

    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);

        container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher = container
                .addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(context));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING_URL);
    }
}
