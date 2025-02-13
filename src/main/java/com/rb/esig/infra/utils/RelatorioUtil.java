package com.rb.esig.infra.utils;

import net.sf.jasperreports.engine.*;

import java.io.InputStream;
import java.util.Map;

public class RelatorioUtil {
    public static JasperPrint gerarRelatorio(String caminhoJRXML, Map<String, Object> parametros) {
        try {
            InputStream reportStream = RelatorioUtil.class.getResourceAsStream(caminhoJRXML);

            if (reportStream == null) {
                throw new RuntimeException("Arquivo .jrxml não encontrado: " + caminhoJRXML);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            return jasperPrint;
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar o relatório: " + e.getMessage(), e);
        }
    }
}

