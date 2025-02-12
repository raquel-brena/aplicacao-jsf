package com.rb.esig.services;

import com.rb.esig.domain.Usuario;
import com.rb.esig.repositories.UsuarioRepository;
import com.rb.esig.shared.exceptions.BadRequestException;
import com.rb.esig.shared.exceptions.NotFoundException;

import javax.enterprise.context.Dependent;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Dependent
public class UsuarioService implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private UsuarioRepository repository;


    public UsuarioService() {
        this.repository = new UsuarioRepository();
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public List<Usuario> findAllAtivos() {
        return repository.findAllAtivos();
    }

    public Usuario save(Usuario usuario) {
        return this.repository.save(usuario);
    }

    public Optional<Usuario> findByUsuario(String usuario) {
        return this.repository.findUsuario(usuario);
    }

    public boolean userExists(String usuario) {
        return this.repository.userExists(usuario);
    }


    public Usuario findById(Long id) {
        Optional<Usuario> usuarioEncontrado = this.repository.findById(id);

        if (usuarioEncontrado.isEmpty()) {
            throw new NotFoundException("Usuário não cadastrado");
        }

        return usuarioEncontrado.get();
    }

    public boolean delete(Long id, Long usuarioLogadoId) {
        Usuario usuario = this.repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuário não existe."));

        this.repository.delete(id);

        return usuario.getId() == usuarioLogadoId;
    }
}

