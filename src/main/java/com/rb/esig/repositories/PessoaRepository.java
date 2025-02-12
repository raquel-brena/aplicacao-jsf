package com.rb.esig.repositories;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PessoaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public PessoaRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Pessoa> findAll() {
        return entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                .getResultList();
    }


    public Optional<Pessoa> findPessoaByUsuario(String usuario) {
        try {
            TypedQuery<Pessoa> query = entityManager.createQuery(
                    "SELECT u FROM Pessoa u WHERE u.usuario = :usuario",
                    Pessoa.class
            );
            query.setParameter("usuario", usuario);
            return Optional.of(query.getSingleResult());
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public boolean existePessoaPorEmailOuUsuario(String email, String usuario) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(p) FROM Pessoa p WHERE p.email = :email OR p.usuario = :usuario", Long.class
        );
        query.setParameter("email", email);
        query.setParameter("usuario", usuario);

        return query.getSingleResult() > 0;
    }

    public Optional<Pessoa> findById(Long id) {
        try {
            TypedQuery<Pessoa> query = entityManager.createQuery(
                    "SELECT u FROM Pessoa u WHERE u.id = :id",
                    Pessoa.class
            );

            query.setParameter("id", id);

            return Optional.of(query.getSingleResult());
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public void save(Pessoa pessoa) {
        entityManager.getTransaction().begin();
        entityManager.persist(pessoa);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Pessoa u SET u.ativo = false WHERE u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

}
