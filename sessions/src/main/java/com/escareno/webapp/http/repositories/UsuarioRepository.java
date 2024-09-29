package com.escareno.webapp.http.repositories;

import com.escareno.webapp.http.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario> {

    Usuario porUsername(String username) throws SQLException;



}
