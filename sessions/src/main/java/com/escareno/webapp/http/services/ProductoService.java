package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> buscar(String nombre);
}
