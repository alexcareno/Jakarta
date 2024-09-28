package com.escareno.webapp.http.listeners;

import com.escareno.webapp.http.models.Carro;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("contextInitialized");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "algún valor global de la app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("destruyendo la app");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("requestInitialized");
        sre.getServletRequest().setAttribute("mensaje", "request inicializado");
        sre.getServletRequest().setAttribute("title", "Catálogo servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("destruyendo el request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("sessionCreated");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("sessionDestroyed");
    }
}
