package br.com.dataagil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.dataagil.entity.Estado;
import br.com.dataagil.exception.DAOExpcetion;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.repository.EstadoDAO;


/**
 * 
 * 
 * @author Michel Moraes
 *
 */
@Service
public class EstadoService {
	//InjeÃ§Ã£o de Dependencia
	@Autowired @Qualifier("estadoDAOJPA") private EstadoDAO estadoDAO;
	
	
	public void salvar(Estado estado) throws ServiceException{
		
		//Verificacoes
	
		try {
			Estado estadoExistente =estadoDAO.buscarPorDescricao(estado.getNome());
			//Regra de Negócio
		if(estadoExistente!=null){  
				throw new ServiceException("Este estado já existe!");
			
		
		}
		estadoDAO.salvar(estado);
		} catch (DAOExpcetion e) {
			//Gerar uma nova exception ServiceExcetion
			throw new ServiceException(e);
		}
	}
	
	public List<Estado> buscarTodos() {
		
		return estadoDAO.buscarTodos();
		
	}

	public void excluir(Estado estado) throws ServiceException {
		try {
			estadoDAO.excluir(estado);
		} catch (DAOExpcetion e) {
		//	e.printStackTrace();
			throw  new ServiceException(e.getMessage(), e);
			
		}
	}
		public Estado buscarPorId(Integer codestado) throws ServiceException{
			try {
				return estadoDAO.buscarPorId(codestado);
			} catch (DAOExpcetion e) {
				throw  new ServiceException(e.getMessage(), e);
			}
		
	}
}
