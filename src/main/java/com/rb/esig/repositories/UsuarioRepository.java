package com.rb.esig.repositories;

import com.rb.esig.domain.Usuario;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public UsuarioRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Usuario> findAll() {
        return entityManager.createQuery("SELECT p FROM Usuario p", Usuario.class)
                .getResultList();
    }

    public List<Usuario> findAllAtivos() {
        return entityManager.createQuery("SELECT p FROM Usuario p WHERE p.ativo = true", Usuario.class)
                .getResultList();
    }

    public Optional<Usuario> findUsuario(String usuario) {
        try {
            entityManager.clear();
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

    public Optional<Usuario> findById(Long id) {
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "SELECT u FROM Usuario u WHERE u.id = :id",
                    Usuario.class
            );

            query.setParameter("id", id);

            return Optional.of(query.getSingleResult());
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public boolean userExists(String usuario) {
        return this.findUsuario(usuario).isPresent();
    }


    public Usuario save(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
        return usuario;
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Usuario u SET u.ativo = false WHERE u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }


}
