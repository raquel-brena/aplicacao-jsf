package com.rb.esig.services;

import com.rb.esig.domain.*;
import com.rb.esig.infra.utils.JpaUtil;
import com.rb.esig.repositories.PessoaSalarioRepository;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Dependent
public class PessoaSalarioService implements Serializable {
    private static final long serialVersionUID = 1L;
    PessoaService pessoaService;
    private PessoaSalarioRepository repository;

    public PessoaSalarioService() {
        this.repository = new PessoaSalarioRepository();
        this.pessoaService = new PessoaService();
    }

    public List<PessoaSalarioConsolidado> findAll() {
        return repository.findAll();
    }

    public CompletableFuture<Void> asyncCalcularTodosSalarios() {
        return CompletableFuture.runAsync(() -> {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                List<Pessoa> pessoas = pessoaService.findAll();
                int count = 0;

                for (Pessoa p : pessoas) {
                    if (count % 100 == 0) {
                        if (em.getTransaction().isActive()) {
                            em.getTransaction().commit();
                        }
                        em.getTransaction().begin();
                    }

                    double salarioCalculado = calcularValorSalario(p.getCargo());
                    PessoaSalarioConsolidado salarioJaCalculado = repository.findSalarioByNomePessoa(p.getNome());

                    if (salarioJaCalculado == null) {
                        PessoaSalarioConsolidado novoSalario = new PessoaSalarioConsolidado();
                        novoSalario.setId(p.getId());
                        novoSalario.setNomePessoa(p.getNome());
                        novoSalario.setSalario(salarioCalculado);
                        novoSalario.setNomeCargo(p.getCargo().getNome());
                        this.save(novoSalario);
                    } else {
                        salarioJaCalculado.setSalario(salarioCalculado);
                        this.save(salarioJaCalculado);
                    }
                    count++;
                }

                if (em.getTransaction().isActive()) {
                    em.getTransaction().commit();
                }
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw e;
            } finally {
                em.close();
            }
        });
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


    public void save(PessoaSalarioConsolidado pessoaSalario) {
        repository.save(pessoaSalario);
    }
}
