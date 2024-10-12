package com.escareno.webapp.http.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> listar() throws Exception;
    T porId(Long id) throws  Exception;
    void guardar(T item) throws Exception;
    void eliminar(Long id) throws Exception;
}
