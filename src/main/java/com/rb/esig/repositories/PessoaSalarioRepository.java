package com.rb.esig.repositories;

import com.rb.esig.domain.PessoaSalarioConsolidado;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PessoaSalarioRepository {


    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    @PersistenceContext
    private EntityManager entityManager;

    public PessoaSalarioRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<PessoaSalarioConsolidado> findAll() {
        return entityManager.createQuery("SELECT p FROM PessoaSalarioConsolidado p", PessoaSalarioConsolidado.class)
                .getResultList();
    }

    public void save(PessoaSalarioConsolidado pessoaSalario) {
        entityManager.getTransaction().begin();
        entityManager.merge(pessoaSalario);
        entityManager.getTransaction().commit();
    }

    public PessoaSalarioConsolidado findSalarioByNomePessoa(String nomePessoa) {
        for (PessoaSalarioConsolidado salarioConsolidado : this.findAll()) {
            if (Objects.equals(salarioConsolidado.getNomePessoa(), nomePessoa)) {
                return salarioConsolidado;
            }
        }
        return null;
    }

    public CompletableFuture<Void> deleteAllAsync() {
        return CompletableFuture.runAsync(() -> {
            EntityManager em = entityManager.getEntityManagerFactory().createEntityManager();
            try {
                em.getTransaction().begin();
                em.createQuery("DELETE FROM PessoaSalarioConsolidado").executeUpdate();
                em.flush();
                em.createNativeQuery("TRUNCATE TABLE pessoa_salario_consolidado RESTART IDENTITY CASCADE").executeUpdate();
                em.getTransaction().commit();
            } catch (RuntimeException e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        }, executor);
    }
}
