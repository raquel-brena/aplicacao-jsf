package com.rb.esig.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private boolean ativo;
    @Column(nullable = false)
    private String nome;
    private String cidade;
    private String cep;
    private String pais;
    private String endereco;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String usuario;
    private String telefone;

    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;


    public Pessoa() {
    }

    public Pessoa(String nome, String cidade, String email, String cep, String endereco,
                  String usuario, String telefone, LocalDate dataNascimento, Cargo cargo, String pais) {
        this.nome = nome;
        this.cidade = cidade;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
        this.usuario = usuario;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.pais = pais;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getPais() {
        return pais;
    }


    public void setPais(String pais) {
        this.pais = pais;
    }
}
