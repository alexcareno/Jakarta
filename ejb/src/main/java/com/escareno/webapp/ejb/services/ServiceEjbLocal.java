package com.escareno.webapp.ejb.services;

import jakarta.ejb.Local;
import com.escareno.webapp.ejb.models.Producto;

import java.util.List;

@Local
public interface ServiceEjbLocal {
    String saludar(String nombre);
    List<Producto> listar();
    Producto crear(Producto producto);
}
