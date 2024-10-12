package com.escareno.webapp.http.services;

import com.escareno.webapp.http.config.Service;
import com.escareno.webapp.http.interceptors.TransactionalJpa;
import com.escareno.webapp.http.models.entities.Usuario;
import com.escareno.webapp.http.repositories.RepositoryJpa;
import com.escareno.webapp.http.repositories.UsuarioRepository;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.Optional;

@Service
@TransactionalJpa
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;

    @Inject
    public UsuarioServiceImpl( @RepositoryJpa UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (Exception throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
