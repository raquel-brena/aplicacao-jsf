package com.rb.esig.repositories;

import com.rb.esig.domain.Cargo;
import com.rb.esig.infra.utils.JpaUtil;
import com.rb.esig.shared.exceptions.BadRequestException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CargoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CargoRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Cargo> findAll() {
        try {
            return entityManager.createQuery("SELECT p FROM Cargo p", Cargo.class)
                    .getResultList();
        } catch (NumberFormatException e) {
            throw new BadRequestException("Não foi possível buscar os cargos");
        }
    }

    public Optional<Cargo> findById(Long id) {
        try {
            TypedQuery<Cargo> query = entityManager.createQuery(
                    "SELECT c FROM Cargo c WHERE c.id = :id", Cargo.class);
            query.setParameter("id", id);

            return Optional.of(query.getSingleResult());
        } catch (NumberFormatException e) {
            throw new BadRequestException("ID inválido: " + id);
        }
    }

    public Optional<Cargo> findByName(String name) {
        try {

            TypedQuery<Cargo> query = entityManager.createQuery(
                    "SELECT c FROM Cargo c WHERE c.nome = :name", Cargo.class);
            query.setParameter("name", name);

            System.out.println(query.getSingleResult());
            return Optional.of(query.getSingleResult());
        } catch (NumberFormatException e) {
            throw new BadRequestException("Houve um erro ao buscar o cargo: " + name);
        }
    }


}
