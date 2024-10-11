package com.escareno.hibernateapp;

import com.escareno.hibernateapp.entity.Cliente;
import com.escareno.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateFetchLazyOneToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.find(Cliente.class, 1L);
        System.out.println(cliente.getNombre() + ", direcciones: " + cliente.getDirecciones());
        em.close();
    }
}