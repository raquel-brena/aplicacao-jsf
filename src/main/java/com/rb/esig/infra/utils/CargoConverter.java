package com.rb.esig.infra.utils;

import com.rb.esig.domain.Cargo;
import com.rb.esig.services.CargoService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "cargoConverter", managed = true)
public class CargoConverter implements Converter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        System.out.println(value.toString());
        if (value instanceof Cargo) {
            Cargo cargo = (Cargo) value;
            return String.valueOf(cargo.getId());
        } else {
            throw new ConverterException(new FacesMessage("Valor inválido: " + value));
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            CargoService cargoService = context.getApplication()
                    .evaluateExpressionGet(context, "#{cargoService}", CargoService.class);
            return cargoService.findById(id);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage("ID do cargo inválido: " + value), e);
        }
    }
}
