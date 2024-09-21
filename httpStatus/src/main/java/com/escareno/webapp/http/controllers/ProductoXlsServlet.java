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
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductoXlsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        resp.setContentType("text/html");

        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");
        if(esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }
        try (PrintWriter out = resp.getWriter()) {

            if(!esXls) {
                out.println("<!DOCTYPE html;charset=UTF-8>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Listado de Productos</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Listado de Productos</h1>");

                out.println("<p><a href=\"" + req.getContextPath() + "/productos.xls\">exportar a xls</a></p>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.json\">mostrar json</a></p>");
            }

            out.println("        <table>");
            out.println("            <tr>");
            out.println("            <th>Id</th>");
            out.println("            <th>nombre</th>");
            out.println("            <th>tipo</th>");
            out.println("            <th>precio</th>");
            out.println("            </tr>");

            productos.forEach(p -> {
                out.println("            <tr>");
                out.println("            <td>" + p.getId() + "</td>");
                out.println("            <td>" + p.getNombre() + "</td>");
                out.println("            <td>" + p.getTipo() + "</td>");
                out.println("            <td>" + p.getPrecio() + "</td>");
                out.println("            </tr>");
            });

            out.println("         </table>");

            if(!esXls) {
                out.println("    </body>");
                out.println("</html>");
            }

        }


    }
}
