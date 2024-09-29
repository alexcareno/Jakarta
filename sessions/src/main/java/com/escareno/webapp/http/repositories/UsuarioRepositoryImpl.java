package com.escareno.webapp.http.repositories;

import com.escareno.webapp.http.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;

        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE username = ?")) {
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setEmail(rs.getString("email"));
                }
            }
        }
        return usuario;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        return List.of();
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuario item) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
