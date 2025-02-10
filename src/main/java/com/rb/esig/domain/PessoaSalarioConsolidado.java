package com.rb.esig.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoa_salario_consolidado")
public class PessoaSalarioConsolidado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome_pessoa")
    private String nomePessoa;
    @Column(name = "nome_cargo")
    private String nomeCargo;
    private double salario;

    public PessoaSalarioConsolidado() {
    }

    public PessoaSalarioConsolidado(String nomePessoa, String nomeCargo, double salario) {
        this.nomePessoa = nomePessoa;
        this.nomeCargo = nomeCargo;
        this.salario = salario;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
