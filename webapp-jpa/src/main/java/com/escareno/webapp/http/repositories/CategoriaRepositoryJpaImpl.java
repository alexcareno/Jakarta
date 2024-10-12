package com.escareno.webapp.http.repositories;

import com.escareno.webapp.http.models.entities.Categoria;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

import com.escareno.webapp.http.config.Repository;

@RepositoryJpa
@Repository
public class CategoriaRepositoryJpaImpl implements CrudRepository<Categoria> {
    @Inject
    private EntityManager em;

    public CategoriaRepositoryJpaImpl() {
    }

    public List<Categoria> listar() throws Exception {
        return this.em.createQuery("from Categoria", Categoria.class).getResultList();
    }

    public Categoria porId(Long id) throws Exception {
        return (Categoria)this.em.find(Categoria.class, id);
    }

    public void guardar(Categoria categoria) throws Exception {
        if (categoria.getId() != null && categoria.getId() > 0L) {
            this.em.merge(categoria);
        } else {
            this.em.persist(categoria);
        }

    }

    public void eliminar(Long id) throws Exception {
        this.em.remove(this.porId(id));
    }
}
