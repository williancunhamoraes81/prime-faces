package com.teste.pratico.modelo.converter;

import com.teste.pratico.modelo.Solicitante;
import com.teste.pratico.repositorio.SolicitanteRepositorio;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import java.io.Serializable;

@FacesConverter(value = "solicitanteConverter", forClass = Solicitante.class)
public class SolicitanteConverter implements Converter, Serializable {

    @Inject
    SolicitanteRepositorio repositorio;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        Long id = Long.valueOf(value);
        Solicitante solicitante = new Solicitante();
        solicitante.setId(id);
        return solicitante;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Solicitante solicitante = (Solicitante) value;
        if (solicitante.getId() == null) {
            return null;
        }

        return solicitante.getId().toString();
    }

}
