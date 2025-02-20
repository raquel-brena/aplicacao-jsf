package com.rb.esig.services;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.domain.Usuario;
import com.rb.esig.infra.utils.passwordEncoder;
import com.rb.esig.repositories.PessoaRepository;
import com.rb.esig.shared.exceptions.BadRequestException;
import com.rb.esig.shared.exceptions.NotFoundException;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.Optional;

@Dependent
public class AuthService implements Serializable {
    private static final long serialVersionUID = 1L;

    private PessoaRepository pessoaRepository;

    private UsuarioService usuarioService;

    public AuthService() {
        this.pessoaRepository = new PessoaRepository();
        this.usuarioService = new UsuarioService();
    }

    public Usuario autenticar(String usuario, String senha) {
        Optional<Usuario> usuarioEncontrado = usuarioService.findByUsuario(usuario);

        if (usuarioEncontrado.isEmpty()) throw new BadRequestException("Usuário não existe.");
        if (!usuarioEncontrado.get().getAtivo()) throw new BadRequestException("Usuário inativo.");

        boolean senhaCorreta = passwordEncoder.verify(senha, usuarioEncontrado.get().getSenha());

        if (!senhaCorreta) throw new BadRequestException("Usuário ou senha incorretos.");

        return usuarioEncontrado.get();
    }

    public Usuario cadastrar(String usuario, String senha) {

        Optional<Usuario> usuarioEncontrado = this.usuarioService.userExists(usuario);

        if (usuarioEncontrado.isPresent()) {
            String message = "Já há uma conta associada à este usuário";
            if (!usuarioEncontrado.get().getAtivo()) {
                message += " em estado inativo.";
            }
            throw new BadRequestException(message);
        }

        Optional<Pessoa> pessoa = this.pessoaRepository.findPessoaByUsuario(usuario);

        if (pessoa.isEmpty()) throw new NotFoundException("Usuário não existe no banco de dados: " + usuario);

        String senhaCriptograda = passwordEncoder.encoder(senha);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setAtivo(true);
        novoUsuario.setPessoa(pessoa.get());
        novoUsuario.setSenha(senhaCriptograda);

        return this.usuarioService.save(novoUsuario);
    }
}
