package com.rb.esig.controller;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.domain.dtos.PessoaBeanDTO;
import com.rb.esig.services.PessoaService;
import org.primefaces.PrimeFaces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class PessoaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private PessoaBeanDTO pessoaDTO;
    @Inject
    private PessoaService service;
    @Inject
    private AuthBean authBean;

    public PessoaBean() {
        this.pessoaDTO = new PessoaBeanDTO();
    }

    public void save() {
        //if (dto.getId() != null) {
        // this.service.update(dto);
        //} else {
        this.service.save(pessoaDTO);
        //}
        PrimeFaces.current().executeScript("PF('dlgCadastro').hide()");
        this.pessoaDTO = new PessoaBeanDTO();
    }

    public List<Pessoa> findAll() {
        return service.findAll();
    }

    public void delete(Long id, Long usuarioLogadoId) {
        boolean deletadoEstaLogado = this.service.delete(id, usuarioLogadoId);

        if (deletadoEstaLogado) authBean.logout();
    }

    public PessoaBeanDTO getPessoa() {
        return pessoaDTO;
    }

    public void setPessoa(PessoaBeanDTO pessoa) {
        this.pessoaDTO = pessoa;
    }
}
