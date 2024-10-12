package com.escareno.webapp.http.repositories;

import com.escareno.webapp.http.models.entities.Usuario;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

import com.escareno.webapp.http.config.Repository;

@RepositoryJpa
@Repository
public class UsuarioRepositoryJpaImpl implements UsuarioRepository {
    @Inject
    private EntityManager em;

    public UsuarioRepositoryJpaImpl() {
    }

    public List<Usuario> listar() throws Exception {
        return this.em.createQuery("from Usuario", Usuario.class).getResultList();
    }

    public Usuario porId(Long id) throws Exception {
        return (Usuario)this.em.find(Usuario.class, id);
    }

    public void guardar(Usuario usuario) throws Exception {
        if (usuario.getId() != null && usuario.getId() > 0L) {
            this.em.merge(usuario);
        } else {
            this.em.persist(usuario);
        }

    }

    public void eliminar(Long id) throws Exception {
        this.em.remove(this.porId(id));
    }

    public Usuario porUsername(String username) throws Exception {
        return (Usuario)this.em.createQuery("select u from Usuario u where u.username = :username", Usuario.class).setParameter("username", username).getSingleResult();
    }
}
