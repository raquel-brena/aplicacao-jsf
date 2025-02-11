package com.rb.esig.controller;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.domain.PessoaSalarioConsolidado;
import com.rb.esig.services.PessoaSalarioService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class PessoaSalarioBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private PessoaSalarioService service;


    public PessoaSalarioBean() {
    }

    public List<PessoaSalarioConsolidado> findAll() {
        return service.findAll();
    }


    public void calcularSalarios(List<Pessoa> pessoas) {
        service.calcularTodosSalarios(pessoas);

    }
}
