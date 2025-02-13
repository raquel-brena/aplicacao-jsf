package com.rb.esig.services;

import com.rb.esig.domain.Cargo;
import com.rb.esig.domain.Pessoa;
import com.rb.esig.domain.dtos.PessoaBeanDTO;
import com.rb.esig.repositories.PessoaRepository;
import com.rb.esig.shared.exceptions.BadRequestException;
import com.rb.esig.shared.exceptions.NotFoundException;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Dependent
public class PessoaService implements Serializable {
    private static final long serialVersionUID = 1L;

    private PessoaRepository repository;
    private UsuarioService usuarioService;
    private CargoService cargoService;

    public PessoaService() {
        this.repository = new PessoaRepository();
        this.usuarioService = new UsuarioService();
        this.cargoService = new CargoService();
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }


    public void save(PessoaBeanDTO dto) {
        verificarSePessoaECadastrada(dto.getEmail(), dto.getUsuario());
        Cargo cargoEncontrado = cargoService.findByName(dto.getCargo());

        Pessoa novaPessoa = convertToEntity(dto, new Pessoa());
        novaPessoa.setCargo(cargoEncontrado);

        repository.save(novaPessoa);
    }

    public void update(Pessoa dto) {
        Optional<Pessoa> pessoaExistente = findById(dto.getId());

        if (pessoaExistente.isPresent()) {
            dto.setUsuario(pessoaExistente.get().getUsuario());
            cargoService.findByName(dto.getCargo().getNome());
            repository.save(pessoaExistente.get());
        }
    }

    public Optional<Pessoa> findById(Long id) {
        Optional<Pessoa> pessoa = this.repository.findById(id);
        if (pessoa.isEmpty()) throw new NotFoundException("Pessoa com esse ID não existe");

        return pessoa;
    }

    public boolean delete(Long id, Long usuarioLogadoId) {
        Pessoa pessoa = this.repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Não existe pessoa cadastrada no banco de dados."));

        //Optional<Usuario> usuarioPessoa =  usuarioService.findByUsuario(pessoa.getUsuario());
        // if (usuarioPessoa.isPresent()) {
        //    usuarioService.delete(usuarioPessoa.get().getId(), usuarioLogadoId);
        //  }

        this.repository.delete(id);

        return pessoa.getId() == usuarioLogadoId;
    }

    public void verificarSePessoaECadastrada(String email, String usuario) {
        if (this.repository.existePessoaPorEmailOuUsuario(email, usuario)) {
            throw new BadRequestException("Já existe uma pessoa cadastrada com esse e-mail ou usuário!");
        }
    }

    private Pessoa convertToEntity(PessoaBeanDTO dto, Pessoa pessoaExistente) {
        pessoaExistente.setNome(dto.getNome());
        pessoaExistente.setCargo(this.cargoService.findByName(dto.getCargo()));
        pessoaExistente.setCep(dto.getCep());
        pessoaExistente.setCidade(dto.getCidade());
        pessoaExistente.setEmail(dto.getEmail());
        pessoaExistente.setPais(dto.getPais());
        pessoaExistente.setTelefone(dto.getTelefone());
        pessoaExistente.setDataNascimento(dto.getDataNascimento());
        pessoaExistente.setEndereco(dto.getEndereco());

        return pessoaExistente;
    }

}
