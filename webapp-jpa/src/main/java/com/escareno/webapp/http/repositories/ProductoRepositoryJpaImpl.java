package com.escareno.webapp.http.repositories;

import com.escareno.webapp.http.models.entities.Producto;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import com.escareno.webapp.http.config.Repository;

@RepositoryJpa
@Repository
public class ProductoRepositoryJpaImpl implements CrudRepository<Producto> {
    @Inject
    private EntityManager em;

    public ProductoRepositoryJpaImpl() {
    }

    public List<Producto> listar() throws Exception {
        return this.em.createQuery("select p from Producto p left outer join fetch p.categoria", Producto.class).getResultList();
    }

    public Producto porId(Long id) throws Exception {
        return (Producto)this.em.find(Producto.class, id);
    }

    public void guardar(Producto producto) throws Exception {
        if (producto.getId() != null && producto.getId() > 0L) {
            this.em.merge(producto);
        } else {
            this.em.persist(producto);
        }

    }

    public void eliminar(Long id) throws Exception {
        Producto producto = this.porId(id);
        this.em.remove(producto);
    }
}
