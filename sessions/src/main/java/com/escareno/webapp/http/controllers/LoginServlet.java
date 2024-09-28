package com.escareno.webapp.http.controllers;

import com.escareno.webapp.http.services.LoginService;
import com.escareno.webapp.http.services.LoginServiceCookieImpl;
import com.escareno.webapp.http.services.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "username";
    final static String PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        if(usernameOptional.isPresent()) {
            resp.setContentType("text/html");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html;charset=UTF-8>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Bienvenido!! " + usernameOptional.get() + "</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Bienvenido!! " + usernameOptional.get() + " you're logged in</h1>");
                out.println(" <p><a href='"+ req.getContextPath() +"/index.jsp'>volver</a></p> ");
                out.println(" <p><a href='"+ req.getContextPath() +"/logout'>cerrar sesión</a></p> ");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        if(USERNAME.equals(username) && PASSWORD.equals(password)) {

            req.getSession().setAttribute("username", username);
            req.setAttribute("title", req.getAttribute("title") + ": Login");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized, sorry");
        }
    }
}
