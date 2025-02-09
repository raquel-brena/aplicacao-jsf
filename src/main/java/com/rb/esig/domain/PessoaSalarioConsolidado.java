package com.rb.esig.domain;

import javax.persistence.*;

@Entity
@Table(name = "pesssoa_salario_consolidado")
public class PessoaSalarioConsolidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_pessoa")
    private String nomePessoa;
    @Column(name = "nome_cargo")
    private String nomeCargo;
    private double salario;
    public PessoaSalarioConsolidado () {}

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
}
