package br.com.dataagil.web.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dataagil.controller.ConfiguracaoController;
import br.com.dataagil.controller.ContextoController;
import br.com.dataagil.controller.ParametroController;


public class ContextoUtil {
	
	public static ContextoController getContextoController() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		ContextoController contextoController = (ContextoController) session.getAttribute("contextoController");
		return contextoController;
	}
	
	public static ParametroController getParametroController() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		ParametroController parametroController = (ParametroController) session.getAttribute("parametroController");
		return parametroController;
	}
	
	public static ConfiguracaoController getConfiguracaoController() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		ConfiguracaoController configuracaoController = (ConfiguracaoController) session.getAttribute("configuracaoController");
		return configuracaoController;
	}
	
	// TODO resolver links nos menus conforme restrições de acesso
	
	
}
