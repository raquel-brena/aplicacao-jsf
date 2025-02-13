package com.rb.esig.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Pessoa> pessoas = new ArrayList<>();

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CargoVencimento> cargoVencimentos = new ArrayList<>();

    public Cargo() {
    }

    public Cargo(String cargoNome) {
    }

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

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(getId(), cargo.getId()) && Objects.equals(getNome(), cargo.getNome()) && Objects.equals(getPessoas(), cargo.getPessoas()) && Objects.equals(getCargoVencimentos(), cargo.getCargoVencimentos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getPessoas(), getCargoVencimentos());
    }
}
