package br.com.dataagil.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dataagil.entity.Perfil;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.PerfilService;

@Component("perfilConverter")
public class PerfilConverter implements Converter {
	@Autowired
	private PerfilService perfilService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {


		
		try {
			return perfilService.buscarPorId(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			return null;
		} catch (ServiceException e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Perfil) {
			Perfil perfil = (Perfil) value;
			return perfil.getCodperfil().toString();
		}
		return "";

	}

}
