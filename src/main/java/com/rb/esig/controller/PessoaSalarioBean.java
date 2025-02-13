package com.rb.esig.controller;

import com.rb.esig.domain.PessoaSalarioConsolidado;
import com.rb.esig.infra.utils.RelatorioUtil;
import com.rb.esig.services.PessoaSalarioService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.PrimeFaces;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Named
@ViewScoped
public class PessoaSalarioBean implements Serializable {
    private static final long serialVersionUID = 1L;
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    @Inject
    private PessoaSalarioService service;
    private String buttonLabel = "Recalcular salários";

    public PessoaSalarioBean() {
    }
    private List<PessoaSalarioConsolidado> filteredPessoas;
    public List<PessoaSalarioConsolidado> findAll() {
        return service.findAll();
    }

    public void verificarProcessamento() {
    }

    public void calcularSalarios() {
        this.buttonLabel = "Calculando...";
        PrimeFaces.current().ajax().update("btnCalcular");
        service.asyncCalcularTodosSalarios().thenRun(() -> {
            this.buttonLabel = "Recalcular salários";
        });
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public void gerarRelatorioPDF() {
        try {
            List<PessoaSalarioConsolidado> salarios = service.findAll();
            Map<String, Object> parametros = new HashMap<>();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(salarios);
            JasperPrint jasperPrint = RelatorioUtil.gerarRelatorio("/a4.jrxml", parametros);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            byte[] pdfBytes = baos.toByteArray();

            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"relatorio.pdf\"");
            response.setContentLength(pdfBytes.length);

            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();

            facesContext.responseComplete();

            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PessoaSalarioConsolidado> getFilteredPessoas() {
        return filteredPessoas;
    }

    public void setFilteredPessoas(List<PessoaSalarioConsolidado> filteredPessoas) {
        this.filteredPessoas = filteredPessoas;
    }
}
