package com.rb.esig.repositories;

import com.rb.esig.domain.PessoaSalarioConsolidado;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PessoaSalarioRepository {

    private EntityManager entityManager;

    public PessoaSalarioRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<PessoaSalarioConsolidado> findAll() {
        return entityManager.createQuery("SELECT p FROM PessoaSalarioConsolidado p", PessoaSalarioConsolidado.class)
                .getResultList();
    }

    public void save (PessoaSalarioConsolidado pessoaSalario) {
        entityManager.getTransaction().begin();
        entityManager.persist(pessoaSalario);
        entityManager.getTransaction().commit();
    }
}
