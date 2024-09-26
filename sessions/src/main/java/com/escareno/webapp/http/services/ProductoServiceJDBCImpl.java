package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.Categoria;
import com.escareno.webapp.http.models.Producto;
import com.escareno.webapp.http.repositories.CategoriaRepositoryImpl;
import com.escareno.webapp.http.repositories.ProductoRepositoryImpl;
import com.escareno.webapp.http.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJDBCImpl implements ProductoService {

    private Repository<Producto> repositoryJdbc;
    private Repository<Categoria> categoriaRepository;

    public ProductoServiceJDBCImpl(Connection conn) {
        this.repositoryJdbc = new ProductoRepositoryImpl(conn);
        this.categoriaRepository = new CategoriaRepositoryImpl(conn);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
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
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return categoriaRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> buscarCategoria(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }


}
