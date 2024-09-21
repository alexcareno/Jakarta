package com.escareno.webapp.http.controllers;

import com.escareno.webapp.http.models.Producto;
import com.escareno.webapp.http.services.ProductoService;
import com.escareno.webapp.http.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        String nombre = req.getParameter("producto");
        if(nombre == null || nombre.isEmpty()) resp.sendError(HttpServletResponse.SC_NOT_FOUND, "no se encontró el producto");
        Optional<Producto> encontrado = service.buscar(nombre);
        if(encontrado.isPresent()) {
            resp.setContentType("text/html");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html;charset=UTF-8>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Producto encontrado!</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Producto encontrado!</h1>");
                out.println("        <h1>Producto encontrado: " + encontrado.get().getNombre() +
                        " precio: $" +  encontrado.get().getPrecio() + "</h1>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "no se encontró el producto");
        }
    }
}
