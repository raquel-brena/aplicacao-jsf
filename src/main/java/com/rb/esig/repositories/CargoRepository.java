package com.rb.esig.repositories;

import com.rb.esig.domain.Cargo;
import com.rb.esig.infra.utils.JpaUtil;
import com.rb.esig.shared.exceptions.BadRequestException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CargoRepository {

    private EntityManager entityManager;

    public CargoRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Cargo> findAll() {
        return entityManager.createQuery("SELECT p FROM Cargo p", Cargo.class)
                .getResultList();
    }

    public Cargo findById(Long id) {
        try {
            TypedQuery<Cargo> query = entityManager.createQuery(
                    "SELECT c FROM Cargo c WHERE c.id = :id", Cargo.class);
            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (NumberFormatException e) {
            throw new BadRequestException("ID inválido: " + id);
        }
    }


}
