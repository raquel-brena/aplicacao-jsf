package com.rb.esig.services;

import com.rb.esig.domain.Cargo;
import com.rb.esig.repositories.CargoRepository;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;

@Dependent
public class CargoService implements Serializable {
    private static final long serialVersionUID = 1L;

    private CargoRepository repository;

    public CargoService() {
        this.repository = new CargoRepository();
    }

    public Cargo findById(Long id) {
        return repository.findById(id);
    }

    public List<Cargo> findAll() {
        return repository.findAll();
    }
}
