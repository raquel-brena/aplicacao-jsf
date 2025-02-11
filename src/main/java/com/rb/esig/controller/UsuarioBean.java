package com.rb.esig.controller;

import com.rb.esig.domain.Usuario;
import com.rb.esig.domain.dtos.PessoaBeanDTO;
import com.rb.esig.services.UsuarioService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class UsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private PessoaBeanDTO pessoaDTO;

    @Inject
    private UsuarioService service;

    public UsuarioBean() {
        this.pessoaDTO = new PessoaBeanDTO();
    }

    //public void save() {
    // this.service.save(this.pessoaDTO);
    // PrimeFaces.current().executeScript("PF('dlgCadastro').hide()");
    //}

    public List<Usuario> findAll() {
        return service.findAll();
    }

    public PessoaBeanDTO getPessoa() {
        return pessoaDTO;
    }

    public void setPessoa(PessoaBeanDTO pessoa) {
        this.pessoaDTO = pessoa;
    }


}
