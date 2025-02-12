package com.rb.esig.controller;

import com.rb.esig.domain.Usuario;
import com.rb.esig.services.UsuarioService;
import org.primefaces.PrimeFaces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class UsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    @Inject
    private AuthBean authBean;
    @Inject
    private UsuarioService service;

    public UsuarioBean() {
        this.usuario = new Usuario();
    }

    public void save() {
        this.service.save(this.usuario);
        PrimeFaces.current().executeScript("PF('dlgCadastro').hide()");
    }

    public List<Usuario> findAll() {
        return service.findAll();
    }

    public List<Usuario> findAllAtivos() {
        return service.findAllAtivos();
    }

    public Usuario findById(Long id) {
        return this.service.findById(id);
    }

    public boolean usuarioExiste(String usuario) {
        try {
            this.service.findByUsuario(usuario);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public void delete(Long id, Long usuarioLogadoId) {
        boolean deletadoEstaLogado = this.service.delete(id, usuarioLogadoId);

        if (deletadoEstaLogado) authBean.logout();
    }

    public Usuario getUsuario() {
        return usuario;

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioService getService() {
        return service;
    }

    public void setService(UsuarioService service) {
        this.service = service;
    }
}
