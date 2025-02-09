package com.rb.esig.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CargoVencimento> cargoVencimentos = new ArrayList<>();

    public Cargo () {}
    public Cargo (String cargoNome) {}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CargoVencimento> getCargoVencimentos() {
        return cargoVencimentos;
    }

    public void setCargoVencimentos(List<CargoVencimento> cargoVencimentos) {
        this.cargoVencimentos = cargoVencimentos;
    }
}
