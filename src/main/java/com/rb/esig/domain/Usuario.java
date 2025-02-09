package com.rb.esig.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false )
    private boolean ativo;

    @OneToOne
    @JoinColumn(name = "pessoa_id", unique = true, nullable = false)
    private Pessoa pessoa;

    @Column(nullable = false)
    private String senha;

    @Column(name = "criado_em")
    private LocalDate criadoEm;
    @Column(name = "atualizado_em")
    private LocalDate atualizadoEm;

    public Usuario(Pessoa pessoa, String senha) {
        this.pessoa = pessoa;
        this.senha = senha;
    }

    public Usuario() {}

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDate getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDate atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
