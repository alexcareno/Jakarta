package com.escareno.hibernateapp;

import com.escareno.hibernateapp.entity.Cliente;
import com.escareno.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.createQuery("select c from Cliente c left outer join fetch c.direcciones left join fetch c.detalle where c.id=:id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cliente.getNombre() + ", direcciones: " + cliente.getDirecciones());
        System.out.println(cliente.getNombre() + ", detalle: " + cliente.getDetalle());
        em.close();
    }
}
