package com.escareno.webapp.http.controllers;

import com.escareno.webapp.http.services.LoginService;
import com.escareno.webapp.http.services.LoginServiceCookieImpl;
import com.escareno.webapp.http.services.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);
        if (username.isPresent()) {
            req.getSession().invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
