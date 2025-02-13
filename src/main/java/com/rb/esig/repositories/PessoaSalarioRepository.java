package com.rb.esig.repositories;

import com.rb.esig.domain.PessoaSalarioConsolidado;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class PessoaSalarioRepository {


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
}
