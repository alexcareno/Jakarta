package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.Producto;
import com.escareno.webapp.http.repositories.ProductoRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJDBCImpl implements ProductoService {

    private ProductoRepositoryImpl repository;

    public ProductoServiceJDBCImpl(Connection conn) {
        this.repository = new ProductoRepositoryImpl(conn);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> buscar(String nombre) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
