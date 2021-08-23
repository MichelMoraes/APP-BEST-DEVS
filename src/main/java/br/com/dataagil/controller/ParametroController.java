package br.com.dataagil.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="parametroController")
@ViewScoped
public class ParametroController implements Serializable {
	
	private static final long serialVersionUID = 6922882619491983662L;
	private Integer modulo;
	
	public ParametroController() {
		
	}

	public Integer getModulo() {
		return modulo;
	}

	public void setModulo(Integer modulo) {
		if (modulo!=null) {
			this.modulo = modulo;
		}
	}
		
	public String descricaoModulo() {
		if (modulo==1) {
			return "Medicina";
		}
		else if (modulo==2) {
			return "Odontologia";
		} 
		else if (modulo==3) {
			return "Estetica";
		}
		else if (modulo==4) {
			return "Fisioterapia";
		}
		else if (modulo==5) {
			return "Nutricao";
		}
		else if (modulo==99) {
			return "Configuracao";
		}
		else {
			return null;
		}
	}
	
	
	
}
