package com.rb.esig.repositories;

import com.rb.esig.domain.PessoaSalarioConsolidado;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PessoaSalarioRepository {


    @PersistenceContext
    private EntityManager entityManager;
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

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

    public CompletableFuture<Void> deleteAllAsync() {
        return CompletableFuture.runAsync(() -> {
            EntityManager em = entityManager.getEntityManagerFactory().createEntityManager();
            try {
                em.getTransaction().begin();
                em.createQuery("DELETE FROM PessoaSalarioConsolidado").executeUpdate();
                em.createNativeQuery("ALTER SEQUENCE pessoa_salario_consolidado_id_seq RESTART WITH 1").executeUpdate();
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
