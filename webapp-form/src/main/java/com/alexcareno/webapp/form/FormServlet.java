package com.alexcareno.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/registro")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        boolean habilitar = req.getParameter("habilitar") != null && req.getParameter("habilitar").equals(("on"));
        String secreto = req.getParameter("secreto");

        Map<String, String> errores = new HashMap<>();

        if (username == null || username.isBlank()) {
            errores.put("username", "username requerido");
        }
        if (password == null || password.isBlank()) {
            errores.put("password", "password requerido");
        }
        if (email == null || !email.contains("@")) {
            errores.put("email", "email requerido y debe ser correo válido");
        }
        if (pais == null || pais.isBlank()) {
            errores.put("pais", "pais requerido");
        }
        if (lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes", "lenguajes requerido al menos 1");
        }
        if (roles == null || roles.length == 0) {
            errores.put("roles", "roles requerido al menos 1");
        }
        if (idioma == null) {
            errores.put("idioma", "idioma requerido");
        }


        if (errores.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Resultado Form</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Resultado Form</h1>");

                out.println("        <ul>");
                out.println("            <li>Username: " + username + "</li>");
                out.println("            <li>Email: " + email + "</li>");
                out.println("            <li>Password: " + password + "</li>");
                out.println("            <li>Password: " + pais + "</li>");

                out.println("            <li>Lenguajes: <ul>");
                Arrays.asList(lenguajes).forEach(lenguaje -> {
                    out.println("            <li>" + lenguaje + "</li>");
                });
                out.println("            </ul></li>");

                out.println("            <li>Lenguajes: <ul>");
                Arrays.asList(roles).forEach(rol -> {
                    out.println("            <li>" + rol + "</li>");
                });
                out.println("            </ul></li>");

                out.println("            <li>Idioma: " + idioma + "</li>");
                out.println("            <li>Habilitado: " + habilitar + "</li>");
                out.println("            <li>Secreto: " + secreto + "</li>");
                out.println("        </ul>");

                out.println("    </body>");
                out.println("</html>");
            }
        } else {
                /* errores.forEach(error -> {
                    out.println("<li>" + error + "</li>");
                });
                out.println("<p><a href=\"/webapp-form/index.jsp\">Volver</a></p>"); */
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
