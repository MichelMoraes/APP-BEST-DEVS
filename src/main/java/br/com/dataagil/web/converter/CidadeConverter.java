package br.com.dataagil.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dataagil.entity.Cidade;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.CidadeService;

@Component("cidadeConverter")
public class CidadeConverter implements Converter {
	@Autowired
	private CidadeService cidadeService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {


		
		try {
			return cidadeService.buscarPorId(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			return null;
		} catch (ServiceException e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Cidade) {
			Cidade cidade = (Cidade) value;
			return cidade.getCodcidade().toString();
		}
		return "";

	}

}
