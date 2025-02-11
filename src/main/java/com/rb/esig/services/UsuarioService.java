package com.rb.esig.services;

import com.rb.esig.domain.Usuario;
import com.rb.esig.repositories.UsuarioRepository;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;

@Dependent
public class UsuarioService implements Serializable {
    private static final long serialVersionUID = 1L;

    private UsuarioRepository repository;
    private CargoService cargoService;

    public UsuarioService() {
        this.cargoService = new CargoService();
        this.repository = new UsuarioRepository();
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }
}
