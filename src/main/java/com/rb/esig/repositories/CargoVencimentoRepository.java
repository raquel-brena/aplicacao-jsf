package com.rb.esig.repositories;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CargoVencimentoRepository {

    private EntityManager entityManager;

    public CargoVencimentoRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Pessoa> findAll() {
        return entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                .getResultList();
    }
}
