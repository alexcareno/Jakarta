package com.escareno.webapp.http.controllers;

import com.escareno.webapp.http.models.Categoria;
import com.escareno.webapp.http.models.Producto;
import com.escareno.webapp.http.services.ProductoService;
import com.escareno.webapp.http.services.ProductoServiceJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<Producto> o = service.findById(id);
            if (o.isPresent()) {
                producto = o.get();
            }
        }
        req.setAttribute("categorias", service.listarCategorias());
        req.setAttribute("producto", producto);
        req.setAttribute("title", req.getAttribute("title") + ": Formulario de productos");
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        String nombre = req.getParameter("nombre");
        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }
        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");
        Long categoriaId;
        try {
            categoriaId = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es obligatorio");
        }
        if (precio.equals(0)) {
            errores.put("precio", "El precio es obligatorio");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku es obligatorio");
        } else if (sku.length() > 10) {
            errores.put("sku", "El sku debe ser menor a 10 carácteres");
        }
        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "La fecha es obligatoria");
        }
        if (categoriaId.equals(0L)) {
            errores.put("categoria", "La categoría es obligatorio");
        }
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            fecha = null;
        }
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setSku(sku);
        producto.setFechaRegistro(fecha);
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);
        if (errores.isEmpty()) {
            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategorias());
            req.setAttribute("producto", producto);
            req.setAttribute("title", req.getAttribute("title") + ": Formulario de productos");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
