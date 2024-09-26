package com.escareno.webapp.http.repositories;

import com.escareno.webapp.http.models.Categoria;
import com.escareno.webapp.http.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<Producto> {

    private Connection connection;

    public ProductoRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                     " INNER JOIN categorias as c ON (p.categoria_id = c.id) ORDER BY p.id ASC")) {
            while (rs.next()) {

                Producto p = getProducto(rs);

                productos.add(p);
            }
        }

        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;

        try (PreparedStatement stmt = connection.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                " INNER JOIN categorias as c On (p.categoria_id = c.id) WHERE p.id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }

        return producto;
    }

    @Override
    public void guardar(Producto item) throws SQLException {
        String sql;
        if (item.getId() != null && item.getId() > 0) {
            sql = "UPDATE productos SET nombre = ?, precio = ?, sku = ?, categoria_id = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos (nombre, precio, sku, categoria_id, fecha_registro) VALUES (?, ?, ?, ?, ?)";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getNombre());
            stmt.setInt(2, item.getPrecio());
            stmt.setString(3, item.getSku());
            stmt.setLong(4, item.getCategoria().getId());
            if (item.getId() != null && item.getId() > 0) {
                stmt.setLong(5, item.getId());
            } else {
                stmt.setDate(5, Date.valueOf(item.getFechaRegistro()));
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        }

    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}
