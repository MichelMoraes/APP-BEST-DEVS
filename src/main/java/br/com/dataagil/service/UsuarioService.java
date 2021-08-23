package br.com.dataagil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.dataagil.entity.Usuario;
import br.com.dataagil.exception.DAOExpcetion;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.repository.UsuarioDAO;


/**
 * 
 * 
 * @author Michel Moraes
 *
 */
@Service
public class UsuarioService {
	//InjeÃ§Ã£o de Dependencia
	@Autowired @Qualifier("usuarioDAOJPA") private UsuarioDAO usuarioDAO;
	
	
	public void salvar(Usuario usuario) throws ServiceException{
		
		//Verificacoes
	
		try {
			Usuario usuarioExistente =usuarioDAO.buscarLogin(usuario.getLogin(), usuario.getSenha(), usuario.getAtivo(), usuario.getPerfil());
			//Regra de Negócio
		if(usuarioExistente!=null){  
				throw new ServiceException("Usuário já existe!");
			}
		if (usuario.getSenha() == null || usuario.getSenha() == ""    ) {
			throw new ServiceException("Insira uma Senha!");	
		}
	    if (usuario.getAdminsistema().equals("S")) {  
	    	throw new ServiceException("Alteração não permitida pelo Administrador!");
			
		}
		usuarioDAO.salvar(usuario);
		} catch (DAOExpcetion e) {
			//Gerar uma nova exception ServiceExcetion
			throw new ServiceException(e);
		}
	}
	
	public List<Usuario> buscarTodos() {
		
		return usuarioDAO.buscarTodos();
		
	}

	public void excluir(Usuario usuario) throws ServiceException {
		try {
			 
			usuarioDAO.excluir(usuario);
			 
			 
		} catch (DAOExpcetion e) {
		//	e.printStackTrace();
			throw  new ServiceException(e);
			
		}
	}
	
	public Usuario buscarOperadorAtual(String login) throws ServiceException{
		try {
			return usuarioDAO.buscarPorLoginOperadorAtual(login);
		} catch (DAOExpcetion e) {
			throw  new ServiceException(e.getMessage(), e);
		}
	
}
		public Usuario buscarPorId(Integer codusuario) throws ServiceException{
			try {
				return usuarioDAO.buscarPorId(codusuario);
			} catch (DAOExpcetion e) {
				throw  new ServiceException(e.getMessage(), e);
			}
		
	}
}
