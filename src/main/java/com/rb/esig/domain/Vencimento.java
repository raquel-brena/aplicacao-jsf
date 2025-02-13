package com.rb.esig.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vencimento")
public class Vencimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private float valor;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoVencimento tipoVencimento;
    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CargoVencimento> cargoVencimentos = new ArrayList<>();

    public Vencimento() {
    }

    public Vencimento(String descricao, float valor, TipoVencimento tipoVencimento) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipoVencimento = tipoVencimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<CargoVencimento> getCargoVencimentos() {
        return cargoVencimentos;
    }

    public void setCargoVencimentos(List<CargoVencimento> cargoVencimentos) {
        this.cargoVencimentos = cargoVencimentos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public TipoVencimento getTipoVencimento() {
        return tipoVencimento;
    }

    public void setTipoVencimento(TipoVencimento tipoVencimento) {
        this.tipoVencimento = tipoVencimento;
    }
}
