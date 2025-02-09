package com.rb.esig.services;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.domain.Usuario;
import com.rb.esig.infra.utils.passwordEncoder;
import com.rb.esig.repositories.PessoaRepository;
import com.rb.esig.shared.exceptions.BadRequestException;
import com.rb.esig.shared.exceptions.NotFoundException;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Dependent
public class AuthService implements Serializable {
    private static final long serialVersionUID = 1L;


    private PessoaRepository pessoaRepository;

    public AuthService() {
        this.pessoaRepository = new PessoaRepository();
    }

    public Usuario autenticar(String usuario, String senha) {
        Optional<Usuario>  usuarioEncontrado = pessoaRepository.findByUsuario(usuario);

        if (usuarioEncontrado.isEmpty()) throw new NotFoundException("Usuário não encontrado.");

        System.out.println(usuarioEncontrado.get().getPessoa().getNome());
        boolean senhaCorreta = passwordEncoder.verify(senha, usuarioEncontrado.get().getSenha());

        if (!senhaCorreta) throw new BadRequestException("Usuário ou senha incorretos.");

        return usuarioEncontrado.get();
    }

    public Usuario cadastrar(String usuario, String senha){
        Optional<Usuario> usuarioEncontrado = this.pessoaRepository.findUsuario(usuario);

        if (usuarioEncontrado.isPresent()) throw new BadRequestException("Já há uma conta associado à este usuário.");


        Optional<Pessoa> pessoa = this.pessoaRepository.findPessoaByUsuario(usuario);

        if (pessoa.isEmpty()) throw new NotFoundException("Usuário não existe no banco de dados: " + usuario);

        String senhaCriptograda = passwordEncoder.encoder(senha);

       Usuario novoUsuario = new Usuario();
       novoUsuario.setAtivo(true);
       novoUsuario.setPessoa(pessoa.get());
       novoUsuario.setSenha(senhaCriptograda);

       return this.pessoaRepository.saveUsuario(novoUsuario);
    }



}
