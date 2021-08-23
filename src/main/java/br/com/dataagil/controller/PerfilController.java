package br.com.dataagil.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dataagil.entity.Perfil;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.PerfilService;
import br.com.dataagil.web.util.MensagemUtil;

@Scope(value="request")
@Controller
public class PerfilController {
	
	//Entrada dos campos
	private Perfil perfil;
	
	//Lista contendo objetos perfils do banco
	private List<Perfil> perfils;
	
	@Autowired
	private PerfilService perfilService;
	
	
	
	@PostConstruct
	public void init(){
		perfil =  new Perfil();
		perfils =  perfilService.buscarTodos();
	}
	public void salvar(){
		try {
			perfilService.salvar(perfil);
			init();
			MensagemUtil.mensagemInfo("Salvo com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro( e.getMessage());
		}
	}
	
	public void cancelar(){
		setPerfil(new Perfil());
	}
	
	public void excluir(Perfil perfil){
		try {
			perfilService.excluir(perfil);
			init();
			MensagemUtil.mensagemInfo("Excluído com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro(e.getMessage());
		}
	}
	
	public void editar(Perfil perfil){
		//pode fazer algo antes
		setPerfil(perfil);
	}
	
	

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public List<Perfil> getPerfils() {
		return perfils;
	}
	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}
	
}
