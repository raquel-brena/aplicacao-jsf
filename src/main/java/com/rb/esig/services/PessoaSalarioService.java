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
            double salarioTotal = 0;
            Cargo cargo = p.getCargo();
            List<CargoVencimento> cargoVencimentos = cargo.getCargoVencimentos();

            for (CargoVencimento cv : cargoVencimentos) {
                Vencimento vencimento = cv.getVencimento();

                if (vencimento.getTipoVencimento() == TipoVencimento.CREDITO) {
                    salarioTotal = salarioTotal + vencimento.getValor();
                } else {
                    salarioTotal = salarioTotal - vencimento.getValor();
                }
            }
            PessoaSalarioConsolidado salarioConsolidado = new PessoaSalarioConsolidado();
            salarioConsolidado.setNomePessoa(p.getNome());
            salarioConsolidado.setSalario(salarioTotal);
            salarioConsolidado.setNomeCargo(cargo.getNome());

            this.save(salarioConsolidado);
        }

    }

    public void recalcularSalarios(List<Pessoa> pessoas) {
        this.repository.deleteAllAsync().thenRun(
                () -> this.calcularTodosSalarios(pessoas));
    }


    public void save(PessoaSalarioConsolidado pessoaSalario) {
        repository.save(pessoaSalario);
    }
}
