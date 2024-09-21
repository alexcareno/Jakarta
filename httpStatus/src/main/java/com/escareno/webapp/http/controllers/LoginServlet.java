package com.escareno.webapp.http.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "username";
    final static String PASSWORD = "password";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        if(USERNAME.equals(username) && PASSWORD.equals(password)) {
            resp.setContentType("text/html");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html;charset=UTF-8>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Login Correcto</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Login Correcto</h1>");
                out.println("        <h3>Hola " + username + "</h3>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized, sorry");
        }
    }
}
