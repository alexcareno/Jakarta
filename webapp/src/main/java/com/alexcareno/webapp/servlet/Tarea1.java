package com.alexcareno.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/webapp-tarea1")
public class Tarea1  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Tarea 1: Servlet y envío de parámetros</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Tarea 1: Servlet y envío de parámetros</h1>");

        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        if(nombre != null && apellido != null) {
            out.println("    <h2>Hola mi nombre es: " + nombre + " " + apellido + "</h2>");
        } else {
            out.println("    <h2>No se enviaron datos para saludar :(</h2>");
        }

        LocalDate fecha = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd 'de', MMMM, yyyy");
        out.println("    <h3>La fecha actual es: " + format.format(fecha) + "</h3>");


        out.println("    </body>");
        out.println("</html>");
        out.close();
    }
}
