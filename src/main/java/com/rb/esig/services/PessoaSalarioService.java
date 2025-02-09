package com.rb.esig.services;

import com.rb.esig.domain.PessoaSalarioConsolidado;
import com.rb.esig.repositories.PessoaSalarioRepository;

import javax.enterprise.context.Dependent;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;
import java.util.List;

@Dependent
public class PessoaSalarioService  implements Serializable {
    private static final long serialVersionUID = 1L;

    private PessoaSalarioRepository repository;

    public PessoaSalarioService() {
        this.repository = new PessoaSalarioRepository();
    }

    public List<PessoaSalarioConsolidado> findAll() {
        return repository.findAll();
    }

    public void save(PessoaSalarioConsolidado pessoaSalario) {
        repository.save(pessoaSalario);
    }
}
