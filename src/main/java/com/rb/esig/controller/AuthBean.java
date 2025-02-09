package com.rb.esig.controller;

import com.rb.esig.domain.Pessoa;
import com.rb.esig.domain.Usuario;
import com.rb.esig.services.AuthService;
import com.rb.esig.services.PessoaService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeUsuario;
    private String senha;
    private Usuario usuarioLogado;

    @Inject
    private AuthService authService;

    public String login() {
        Usuario usuario = authService.autenticar(nomeUsuario, senha);
            this.usuarioLogado = usuario;
            HttpSession session = getSession();
            session.setAttribute("usuarioLogado", usuario);
            return "index.xhtml?faces-redirect=true";

    }

    public String cadastrar() {
        Usuario usuario = authService.cadastrar(nomeUsuario, senha);

        System.out.println(usuario.getPessoa().getNome());
            this.usuarioLogado = usuario;
            return "home.xhtml?faces-redirect=true";

    }

    public String logout() {
        HttpSession session = getSession();
        session.invalidate();
        return "login.xhtml?faces-redirect=true";
    }

    private HttpSession getSession() {
        HttpServletRequest request = (HttpServletRequest) javax.faces.context.FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
        return request.getSession();
    }


    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Usuario getUsuarioLogado() { return usuarioLogado; }
}
