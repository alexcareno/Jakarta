package com.escareno.webapp.http.services;

import com.escareno.webapp.http.models.Usuario;
import com.escareno.webapp.http.repositories.UsuarioRepository;
import com.escareno.webapp.http.repositories.UsuarioRepositoryImpl;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;

    @Inject
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
