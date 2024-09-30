package com.escareno.webapp.http.services;

import com.escareno.webapp.http.config.ProductoServicePrincipal;
import com.escareno.webapp.http.config.Service;
import com.escareno.webapp.http.models.Categoria;
import com.escareno.webapp.http.models.Producto;
import com.escareno.webapp.http.repositories.Repository;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
@ProductoServicePrincipal
public class ProductoServiceJDBCImpl implements ProductoService{
    @Inject
    private Repository<Producto> crudRepositoryJdbc;

    @Inject
    private Repository<Categoria> crudRepositoryCategoriaJdbc;

    @Override
    public List<Producto> listar() {
        try {
            return crudRepositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> buscar(String nombre) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            crudRepositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            crudRepositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return crudRepositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> buscarCategoria(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryCategoriaJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
