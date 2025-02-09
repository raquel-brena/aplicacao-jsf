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

    @Inject
    private PessoaService service;
     int page = 0;
     int pageSize = 10;
    public PessoaBean() {}

    public List<Pessoa> findAll() {
        return service.findAll();
    }

    public List<Pessoa> findPagineted() {
        return service.findAllPagineted(page, pageSize);
    }

    public void nextPage() {
        page++;
    }

    public void prevPage() {
        if (page > 0) {
            page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
