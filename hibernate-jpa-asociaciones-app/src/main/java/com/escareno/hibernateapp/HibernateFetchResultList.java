package com.escareno.hibernateapp;


import com.escareno.hibernateapp.entity.Cliente;
import com.escareno.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones left outer join fetch c.detalle", Cliente.class).getResultList();

        clientes.forEach(c -> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones()));
        em.close();
    }
}

