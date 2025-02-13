package com.rb.esig.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PessoaBeanDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String cidade;
    private String email;
    private String cep;
    private String endereco;
    private String usuario;
    private String telefone;
    private LocalDate dataNascimento;
    private String cargo;
    private String pais;


    public PessoaBeanDTO() {
    }

    public PessoaBeanDTO(String nome, String cidade, String email, String cep, String endereco, String usuario, String telefone, LocalDate dataNascimento, String cargo, String pais) {
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaBeanDTO that = (PessoaBeanDTO) o;
        return Objects.equals(getNome(), that.getNome()) && Objects.equals(getCargo(), that.getCargo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getCargo());
    }
}
