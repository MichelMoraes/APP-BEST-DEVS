package br.com.dataagil.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dataagil.entity.Estado;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.EstadoService;
import br.com.dataagil.web.util.MensagemUtil;

@Scope(value="request")
@Controller
public class EstadoController {
	
	//Entrada dos campos
	private Estado estado;
	
	//Lista contendo objetos estados do banco
	private List<Estado> estados;
	
	@Autowired
	private EstadoService estadoService;
	
	
	
	@PostConstruct
	public void init(){
		estado =  new Estado();
		estados =  estadoService.buscarTodos();
	}
	public void salvar(){
		try {
			estadoService.salvar(estado);
			init();
			MensagemUtil.mensagemInfo("Salvo com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro( e.getMessage());
		}
	}
	
	public void cancelar(){
		setEstado(new Estado());
	}
	
	public void excluir(Estado estado){
		try {
			estadoService.excluir(estado);
			init();
			MensagemUtil.mensagemInfo("Excluído com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro(e.getMessage());
		}
	}
	
	public void editar(Estado estado){
		//pode fazer algo antes
		setEstado(estado);
	}
	
	

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
}
