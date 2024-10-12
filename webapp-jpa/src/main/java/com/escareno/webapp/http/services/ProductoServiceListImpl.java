package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.entities.Categoria;
import com.escareno.webapp.http.models.entities.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


// @Alternative
public class ProductoServiceListImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000));
    }

    @Override
    public Optional<Producto> buscar(String nombre) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategorias() {
        return null;
    }

    @Override
    public Optional<Categoria> buscarCategoria(Long id) {
        return Optional.empty();
    }
}
