package br.com.dataagil.web.converte.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="localDateConverter", forClass=java.time.LocalDate.class)
public class LocalDateConverter implements Converter {
		
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDate data;
		try {
		    data = LocalDate.parse(value, formatter);
		} catch (Exception exception) {    
			data = null;
        }
	    return data;
	}
	 
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		LocalDate data = (LocalDate) object;		 
		String dt = String.valueOf(data);		
		String[] t = dt.split("-");
		String dataString = t[2]+"/"+t[1]+"/"+t[0];
		return dataString;
	}
		
	
	
}   	
