package com.escareno.webapp.ejb.services;

import com.escareno.webapp.ejb.models.Producto;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ServiceEjbRemote {
    String saludar(String nombre);
    List<Producto> listar();
    Producto crear(Producto producto);
}
