package com.rb.esig.controller;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.services.PessoaService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class PessoaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Pessoa pessoaDTO = new Pessoa();

    @Inject
    private PessoaService service;


    public PessoaBean() {
    }

    public void save() {
        this.service.save(pessoaDTO);
    }

    public List<Pessoa> findAll() {
        return service.findAll();
    }

    public Pessoa getPessoa() {
        return pessoaDTO;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoaDTO = pessoa;
    }


}
