package com.rb.esig.repositories;

import com.rb.esig.domain.Usuario;
import com.rb.esig.infra.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    private EntityManager entityManager;

    public UsuarioRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Usuario> findAll() {
        return entityManager.createQuery("SELECT p FROM Usuario p", Usuario.class)
                .getResultList();
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


    public Usuario save(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
        return usuario;
    }
}
