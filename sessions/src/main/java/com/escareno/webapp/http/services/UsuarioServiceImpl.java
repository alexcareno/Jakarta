package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.Usuario;
import com.escareno.webapp.http.repositories.UsuarioRepository;
import com.escareno.webapp.http.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection conn) {
        this.usuarioRepository = new UsuarioRepositoryImpl(conn);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
