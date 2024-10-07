package com.escareno.hibernateapp.services;

import com.escareno.hibernateapp.entity.Cliente;
import com.escareno.hibernateapp.repositories.CRUDRepository;
import com.escareno.hibernateapp.repositories.ClienteRepository;
import jakarta.persistence.EntityManager;

import com.escareno.hibernateapp.services.ClienteService;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService{
    private EntityManager em;
    private CRUDRepository<Cliente> repository;

    public ClienteServiceImpl(EntityManager em) {
        this.em = em;
        this.repository = new ClienteRepository(em);
    }

    @Override
    public List<Cliente> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Cliente> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Cliente cliente) {

        try {
            em.getTransaction().begin();
            repository.guardar(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
