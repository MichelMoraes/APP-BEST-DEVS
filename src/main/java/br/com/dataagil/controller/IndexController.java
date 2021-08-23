package br.com.dataagil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dataagil.entity.Configuracao;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.ConfiguracaoService;
import br.com.dataagil.web.util.Funcoes;
import br.com.dataagil.web.util.MensagemUtil;

@Controller("indexController")

@Scope(value="request")

public class IndexController implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -856489394109593114L;


	private List<Configuracao> listaConfig = new ArrayList<Configuracao>();
	private String mensagemInicial = null;
	
	//private Configuracao ConfiguracaoDAO = new Configuracao();

	@Autowired
	ConfiguracaoService configuracaoService;

	

	@PostConstruct
	public void init(){
		//update panel grid
		PrimeFaces.current().ajax().update("frmIndex:pg");
		//context.update("frmIndex:pg");

	}





	public ConfiguracaoService getConfiguracaoService() {
		return configuracaoService;
	}


	public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
		this.configuracaoService = configuracaoService;
	}

	public List<Configuracao> getListaConfig() {
		return listaConfig;
	}

	public void setListaConfig(List<Configuracao> listaConfig) {
		this.listaConfig = listaConfig;
	}


	public String getMensagemInicial() {
		return mensagemInicial;
	}

	public void setMensagemInicial(String mensagemInicial) {
		this.mensagemInicial = mensagemInicial;
	}






}