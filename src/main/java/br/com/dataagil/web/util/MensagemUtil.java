package br.com.dataagil.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtil {


	public static void mensagemInfo(String msg) {
		
		//Acessando o contexto
		FacesContext contextoJSF = FacesContext.getCurrentInstance();
		//Criando um objeto 
		FacesMessage mensagemInfo = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null );
		//Adicioando objeto mensagem no contexto
		contextoJSF.addMessage(null, mensagemInfo);
		

	}
	
	public static void mensagemErro(String msg ) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg , "Ocorreu um erro Interno!"));
	}
	
	public static void mensagemAviso(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msg , null));
	}
}
