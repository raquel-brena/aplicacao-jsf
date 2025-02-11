package com.rb.esig.services;

import com.rb.esig.domain.Cargo;
import com.rb.esig.repositories.CargoRepository;
import com.rb.esig.shared.exceptions.NotFoundException;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Dependent
public class CargoService implements Serializable {
    private static final long serialVersionUID = 1L;

    private CargoRepository repository;

    public CargoService() {
        this.repository = new CargoRepository();
    }

    public Cargo findById(Long id) {
        Optional<Cargo> cargo = repository.findById(id);

        if (cargo.isEmpty()) throw new NotFoundException("Cargo não encontrado: " + id);

        return cargo.get();
    }

    public Cargo findByName(String name) {
        Optional<Cargo> cargo = repository.findByName(name);

        if (cargo.isEmpty()) throw new NotFoundException("Cargo não encontrado: " + name);

        return cargo.get();
    }

    public List<Cargo> findAll() {
        return repository.findAll();
    }
}
