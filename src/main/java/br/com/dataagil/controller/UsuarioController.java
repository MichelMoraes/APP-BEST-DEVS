package br.com.dataagil.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dataagil.entity.Usuario;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.UsuarioService;
import br.com.dataagil.web.util.MensagemUtil;

@Scope(value="request")
@Controller("usuarioController")
public class UsuarioController {
	
	//Entrada dos campos
	private Usuario usuario;
	
	//Lista contendo objetos usuarios do banco
	private List<Usuario> usuarios=null;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	@PostConstruct
	public void init(){
		usuario =  new Usuario();
		usuarios =  usuarioService.buscarTodos();
		
	}
	public void salvar(){
		try {
			usuario.setAdminsistema("N");
			usuarioService.salvar(usuario);
			init();
			MensagemUtil.mensagemInfo("Salvo com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro( e.getMessage());
		}
	}
	
	public void cancelar(){
		setUsuario(new Usuario());
	}
	
	public void voltarindex(){
	
	}
	
	public void excluir(Usuario usuario){
		try {
			usuarioService.excluir(usuario);
			init();
			MensagemUtil.mensagemInfo("Excluído com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro(e.getMessage());
		}
	}
	
	public void editar(Usuario usuario){
		//pode fazer algo antes
		setUsuario(usuario);
	}
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
