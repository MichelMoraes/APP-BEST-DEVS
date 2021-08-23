package br.com.dataagil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.dataagil.entity.Perfil;
import br.com.dataagil.exception.DAOExpcetion;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.repository.PerfilDAO;


/**
 * 
 * 
 * @author Michel Moraes
 */

@Service
public class PerfilService {
	//Injeção de Dependencia
	@Autowired @Qualifier("perfilDAOJPA") private PerfilDAO perfilDAO;
	
	
	public void salvar(Perfil perfil) throws ServiceException{
		
		//Verificacoes
	
		try {
			perfilDAO.salvar(perfil);
		} catch (DAOExpcetion e) {
			//Gerar uma nova exception ServiceExcetion
			throw new ServiceException(e);
		}
	}
	
	public List<Perfil> buscarTodos() {
		
		return perfilDAO.buscarTodos();
		
	}

	public void excluir(Perfil perfil) throws ServiceException {
		try {
			perfilDAO.excluir(perfil);
		} catch (DAOExpcetion e) {
		//	e.printStackTrace();
			throw  new ServiceException(e.getMessage(), e);
			
		}
		
	}
	
	public Perfil buscarPorId(Integer codperfil) throws ServiceException{
		try {
			return perfilDAO.buscarPorId(codperfil);
		} catch (DAOExpcetion e) {
			throw  new ServiceException(e.getMessage(), e);
		}
	}
}
