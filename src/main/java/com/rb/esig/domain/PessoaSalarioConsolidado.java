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


}
