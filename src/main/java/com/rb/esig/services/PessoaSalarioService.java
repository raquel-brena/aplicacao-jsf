package com.rb.esig.services;

import com.rb.esig.domain.*;
import com.rb.esig.repositories.CargoVencimentoRepository;
import com.rb.esig.repositories.PessoaSalarioRepository;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;

@Dependent
public class PessoaSalarioService implements Serializable {
    private static final long serialVersionUID = 1L;

    private PessoaSalarioRepository repository;
    private CargoVencimentoRepository cargoVencimentoRepository;

    public PessoaSalarioService() {
        this.repository = new PessoaSalarioRepository();
    }

    public List<PessoaSalarioConsolidado> findAll() {
        return repository.findAll();
    }

    public void calcularTodosSalarios(List<Pessoa> pessoas) {
        for (Pessoa p : pessoas) {
            double salarioCalculado = calcularValorSalario(p.getCargo());

            PessoaSalarioConsolidado salarioJaCalculado =
                    this.repository.findSalarioByNomePessoa(p.getNome());

            if (salarioJaCalculado == null) {
                PessoaSalarioConsolidado novoSalario = new PessoaSalarioConsolidado();
                novoSalario.setNomePessoa(p.getNome());
                novoSalario.setSalario(salarioCalculado);
                novoSalario.setNomeCargo(p.getCargo().getNome());
                this.save(novoSalario);
            } else {
                salarioJaCalculado.setSalario(salarioCalculado);
                this.save(salarioJaCalculado);
            }
        }
    }


    private double calcularValorSalario(Cargo cargo) {
        double total = 0;
        List<CargoVencimento> cargoVencimentos = cargo.getCargoVencimentos();

        for (CargoVencimento cv : cargoVencimentos) {
            Vencimento vencimento = cv.getVencimento();
            if (vencimento.getTipoVencimento() == TipoVencimento.CREDITO) {
                total += vencimento.getValor();
            } else {
                total -= vencimento.getValor();
            }
        }
        return total;
    }

    public void deletarSalarios() {
        this.repository.deleteAllAsync().join();
    }


    public void save(PessoaSalarioConsolidado pessoaSalario) {
        repository.save(pessoaSalario);
    }
}
