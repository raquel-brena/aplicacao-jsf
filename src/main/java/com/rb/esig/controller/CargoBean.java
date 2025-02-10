package com.rb.esig.controller;

import com.rb.esig.domain.Cargo;
import com.rb.esig.services.CargoService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class CargoBean implements Serializable {
    private static final long serialVersionUID = 1L;


    @Inject
    private CargoService service;

    public CargoBean() {
    }

    public List<Cargo> findAll() {
        return service.findAll();
    }

}
