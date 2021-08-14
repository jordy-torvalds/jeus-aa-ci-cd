package com.jordy.config;

import org.fluentd.logger.FluentLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.String.format;

@RestController
public class TestController {

    private static FluentLogger LOG = FluentLogger.getLogger("app", "remotehost", port);

    @ResponseBody
    @RequestMapping(
            value = {"/test"},
            method = {RequestMethod.GET}
    )
    public String hello(HttpSession session, HttpServletResponse response) {
        session.setAttribute("count", getSessionAttribute(session));
        response.addCookie(new Cookie("jeusCookie", session.getId()));
        return format("System.getProperty(\"-Dspring.profiles.active=%s     |    session.getId()=%s   |   session.getAttribute(\"count\")=%s"
                , System.getProperty("spring.profiles.active")
                , session.getId()
                , session.getAttribute("count"));
    }

    private int getSessionAttribute(HttpSession session) {
        int result = 0;
        try {
            result = 1 + (int) session.getAttribute("count");
        } catch (Exception e) {
        }
        return result;
    }
}