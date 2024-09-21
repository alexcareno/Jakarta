package com.escareno.webapp.http.controllers;

import com.escareno.webapp.http.models.Producto;
import com.escareno.webapp.http.services.LoginService;
import com.escareno.webapp.http.services.LoginServiceImpl;
import com.escareno.webapp.http.services.ProductoService;
import com.escareno.webapp.http.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos", "/productos.html"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        resp.setContentType("text/html");

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html;charset=UTF-8>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Listado de Productos</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Listado de Productos</h1>");
            if (cookieOptional.isPresent()) {
                out.println("<div style=\"color: blue;\">Hola"+ cookieOptional.get() +" Bienvenido!</div>");
            }

            out.println("        <table>");
            out.println("            <tr>");
            out.println("            <th>Id</th>");
            out.println("            <th>nombre</th>");
            out.println("            <th>tipo</th>");

            if (cookieOptional.isPresent()) {
                out.println("            <th>precio</th>");
            }

            out.println("            </tr>");

            productos.forEach(p -> {
                out.println("            <tr>");
                out.println("            <td>" + p.getId() + "</td>");
                out.println("            <td>" + p.getNombre() + "</td>");
                out.println("            <td>" + p.getTipo() + "</td>");

                if (cookieOptional.isPresent()) {
                    out.println("            <td>" + p.getPrecio() + "</td>");
                }
                out.println("            </tr>");
            });

            out.println("         </table>");

        }

    }
}
