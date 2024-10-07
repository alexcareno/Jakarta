package com.escareno.hibernateapp.repositories;

import java.util.List;

public interface CRUDRepository<T> {
    List<T> listar();
    T porId(Long id);
    void guardar(T t);
    void eliminar(Long id);
}
