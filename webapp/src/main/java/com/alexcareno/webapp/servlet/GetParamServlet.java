package com.alexcareno.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/param/url-get")
public class GetParamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String saludo = req.getParameter("saludo");
        String nombre = req.getParameter("nombre");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Parámetros GET de la url</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Parámetros GET de la url</h1>");
        if(saludo != null && nombre != null) {
            out.println("        <h2>El saludo enviado es: " + saludo + " " + nombre + "</h2>");
        } else if(saludo != null) {
            out.println("        <h2>El saludo enviado es: " + saludo + "</h2>");
        } else if(nombre != null) {
            out.println("        <h2>El nombre es: " + nombre + "</h2>");
        } else {
            out.println("        <h2>No se han pasado parámetros</h2>");
        }
        try {
            int code = Integer.parseInt(req.getParameter("codigo"));
            out.println("        <h3>El código es: " + code + "</h3>");
        } catch (NumberFormatException e) {
            out.println("        <h3>El código no se ha enviado</h3>");
        }
        out.println("    </body>");
        out.println("</html>");
        out.close();
    }
}
