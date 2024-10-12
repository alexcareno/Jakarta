package com.escareno.webapp.http.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> listar() throws Exception;

    T porId(Long var1) throws Exception;

    void guardar(T var1) throws Exception;

    void eliminar(Long var1) throws Exception;
}
