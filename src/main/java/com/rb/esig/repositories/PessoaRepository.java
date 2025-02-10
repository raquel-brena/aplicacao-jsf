package com.rb.esig.repositories;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.domain.Usuario;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PessoaRepository {

    private EntityManager entityManager;

    public PessoaRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Pessoa> findAll() {
        return entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                .getResultList();
    }

    public Optional<Usuario> findByUsuario(String usuario) {
        try {

            TypedQuery<Usuario> query = entityManager.createQuery(
                    "SELECT u FROM Usuario u WHERE u.pessoa.usuario = :usuario",
                    Usuario.class
            );

            query.setParameter("usuario", usuario);


            return Optional.of(query.getSingleResult());
        } catch (RuntimeException e) {
            return Optional.empty();
        }
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


    public Optional<Usuario> findUsuario(String usuario) {
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "SELECT u FROM Usuario u WHERE u.pessoa.usuario = :usuario",
                    Usuario.class
            );

            query.setParameter("usuario", usuario);

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

    public Usuario saveUsuario(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
        return usuario;
    }
}
