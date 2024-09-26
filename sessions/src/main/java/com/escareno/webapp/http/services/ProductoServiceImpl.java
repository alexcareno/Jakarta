package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.Categoria;
import com.escareno.webapp.http.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computación", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 1000),
                new Producto(3L, "teclado mecánico", "computación", 4000));
    }

    @Override
    public Optional<Producto> buscar(String nombre) {
        return listar().stream().filter(p -> p.getNombre().equals(nombre)).findFirst();
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
        return List.of();
    }

    @Override
    public Optional<Categoria> buscarCategoria(Long id) {
        return Optional.empty();
    }
}
