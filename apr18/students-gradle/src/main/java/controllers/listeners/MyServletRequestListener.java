package controllers.listeners;


import utils.EmailSender;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by eku on 20.04.17.
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent event) {

    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletContext context = servletRequestEvent.getServletContext();
        String adminEmail = context.getInitParameter("admin-email");
        String studentName = (String)context.getAttribute("name");
        EmailSender.send(adminEmail, "add student " + studentName);
    }

}