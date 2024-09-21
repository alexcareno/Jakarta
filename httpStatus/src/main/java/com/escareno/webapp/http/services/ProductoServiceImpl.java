package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computación", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 1000),
                new Producto(3L, "teclado mecánico", "computación", 4000));
    }
}
