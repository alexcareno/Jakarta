package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.entities.Categoria;
import com.escareno.webapp.http.models.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> buscar(String nombre);

    Optional<Producto> findById(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategorias();

    Optional<Categoria> buscarCategoria(Long id);
}
