package com.escareno.webapp.http.repositories;

import com.escareno.webapp.http.models.entities.Usuario;

public interface UsuarioRepository extends Repository<Usuario> {

    Usuario porUsername(String username) throws Exception;

}
