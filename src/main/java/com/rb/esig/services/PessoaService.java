package com.rb.esig.services;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.repositories.PessoaRepository;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;

@Dependent
public class PessoaService implements Serializable {
    private static final long serialVersionUID = 1L;

    private PessoaRepository repository;

    public PessoaService() {
        this.repository = new PessoaRepository();
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }
    public List<Pessoa> findAllPagineted(int page, int pageSize) {
       return repository.findAllPagineted(page, pageSize);

    }
    public void save(Pessoa pessoa) {
        repository.save(pessoa);
    }


}
