package br.com.dataagil.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dataagil.entity.Cidade;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.CidadeService;
import br.com.dataagil.web.util.MensagemUtil;

@Scope(value="request")
@Controller
public class CidadeController {
	
	//Entrada dos campos
	private Cidade cidade;
	
	//Lista contendo objetos cidades do banco
	private List<Cidade> cidades;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	
	@PostConstruct
	public void init(){
		cidade =  new Cidade();
		cidades =  cidadeService.buscarTodos();
	}
	public void salvar(){
		try {
			cidadeService.salvar(cidade);
			init();
			MensagemUtil.mensagemInfo("Salvo com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro( e.getMessage());
		}
	}
	
	public void cancelar(){
		setCidade(new Cidade());
	}
	
	public void excluir(Cidade cidade){
		try {
			cidadeService.excluir(cidade);
			init();
			MensagemUtil.mensagemInfo("Excluído com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro(e.getMessage());
		}
	}
	
	public void editar(Cidade cidade){
		//pode fazer algo antes
		setCidade(cidade);
	}
	
	

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
}
