package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
