package com.escareno.webapp.http.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> listar() throws SQLException;
    T porId(Long id) throws SQLException;
    void guardar(T item) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
