package com.rb.esig.services;

import com.rb.esig.domain.Cargo;
import com.rb.esig.domain.Pessoa;
import com.rb.esig.domain.dtos.PessoaBeanDTO;
import com.rb.esig.repositories.PessoaRepository;
import com.rb.esig.shared.exceptions.BadRequestException;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;

@Dependent
public class PessoaService implements Serializable {
    private static final long serialVersionUID = 1L;

    private PessoaRepository repository;
    private CargoService cargoService;

    public PessoaService() {
        this.cargoService = new CargoService();
        this.repository = new PessoaRepository();
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }


    public boolean save(PessoaBeanDTO dto) {


        Cargo cargoEncontrado = this.cargoService.findByName(dto.getCargo());

        this.verificarSePessoaECadastrada(dto.getEmail(), dto.getUsuario());

        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(dto.getNome());
        novaPessoa.setCargo(cargoEncontrado);
        novaPessoa.setCep(dto.getCep());
        novaPessoa.setCidade(dto.getCidade());
        novaPessoa.setEmail(dto.getEmail());
        novaPessoa.setPais(dto.getPais());
        novaPessoa.setTelefone(dto.getTelefone());
        novaPessoa.setDataNascimento(dto.getDataNascimento());
        novaPessoa.setEndereco(dto.getEndereco());
        novaPessoa.setUsuario(dto.getUsuario());

        return true;
    }

    public void verificarSePessoaECadastrada(String email, String usuario) {
        if (this.repository.existePessoaPorEmailOuUsuario(email, usuario)) {
            throw new BadRequestException("Já existe uma pessoa cadastrada com esse e-mail ou usuário!");
        }
    }
}
