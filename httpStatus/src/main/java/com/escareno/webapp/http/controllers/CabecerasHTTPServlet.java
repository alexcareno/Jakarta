package com.escareno.webapp.http.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHTTPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String metodoHttp = req.getMethod();
        String uri = req.getRequestURI();
        String reqUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String ip = req.getLocalAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme + "://" + host + contextPath + servletPath;
        String url2 = scheme + "://" + ip + ":" + port + contextPath + servletPath;

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Cabeceras HTTP</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Cabeceras HTTP</h1>");

            out.println("<ul>");
            out.println("<li>método http: " + metodoHttp + "</li>");
            out.println("<li>uri: " + uri + "</li>");
            out.println("<li>reqUrl: " + reqUrl + "</li>");
            out.println("<li>contextPath: " + contextPath + "</li>");
            out.println("<li>servletPath: " + servletPath + "</li>");
            out.println("<li>ipCliente: " + ipCliente + "</li>");
            out.println("<li>ip: " + ip + "</li>");
            out.println("<li>port: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>url: " + url + "</li>");
            out.println("<li>url2: " + url2 + "</li>");

            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                out.println("<li>" + headerName + ": " + req.getHeader(headerName) + "</li>");
            }

            out.println("</ul>");

            out.println("    </body>");
            out.println("</html>");
        }
    }
}
