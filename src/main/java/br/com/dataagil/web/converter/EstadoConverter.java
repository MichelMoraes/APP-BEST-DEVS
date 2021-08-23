package br.com.dataagil.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dataagil.entity.Estado;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.EstadoService;

@Component("estadoConverter")
public class EstadoConverter implements Converter {
	@Autowired
	private EstadoService estadoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {


		
		try {
			return estadoService.buscarPorId(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			return null;
		} catch (ServiceException e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Estado) {
			Estado estado = (Estado) value;
			return estado.getCodEstado().toString();
		}
		return "";

	}

}
