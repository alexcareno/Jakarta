package com.escareno.webapp.http.services;

import com.escareno.webapp.http.config.ProductoServicePrincipal;
import com.escareno.webapp.http.config.Service;
import com.escareno.webapp.http.interceptors.TransactionalJpa;
import com.escareno.webapp.http.models.entities.Categoria;
import com.escareno.webapp.http.models.entities.Producto;
import com.escareno.webapp.http.repositories.Repository;
import com.escareno.webapp.http.repositories.RepositoryJpa;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;


@Service
@ProductoServicePrincipal
@TransactionalJpa
public class ProductoServiceImpl implements ProductoService{
    @Inject
    @RepositoryJpa
    private Repository<Producto> crudRepositoryJdbc;

    @Inject
    @RepositoryJpa
    private Repository<Categoria> crudRepositoryCategoriaJdbc;

    @Override
    public List<Producto> listar() {
        try {
            return crudRepositoryJdbc.listar();
        } catch (Exception throwables) {
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
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            crudRepositoryJdbc.guardar(producto);
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            crudRepositoryJdbc.eliminar(id);
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return crudRepositoryCategoriaJdbc.listar();
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> buscarCategoria(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryCategoriaJdbc.porId(id));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
