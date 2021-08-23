package br.com.dataagil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.dataagil.entity.Cidade;
import br.com.dataagil.exception.DAOExpcetion;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.repository.CidadeDAO;


/**
 * 
 * 
 * @author Michel Moraes
 *
 */
@Service
public class CidadeService {
	//InjeÃ§Ã£o de Dependencia
	@Autowired @Qualifier("cidadeDAOJPA") private CidadeDAO cidadeDAO;
	
	
	public void salvar(Cidade cidade) throws ServiceException{
		
		//Verificacoes
	
		try {
			Cidade cidadeExistente =cidadeDAO.buscarPorDescricao(cidade.getNome());
			//Regra de Negócio
		if(cidadeExistente!=null){  
				throw new ServiceException("Cidade já existe!");
			
		
		}
		cidadeDAO.salvar(cidade);
		} catch (DAOExpcetion e) {
			//Gerar uma nova exception ServiceExcetion
			throw new ServiceException(e);
		}
	}

	public List<Cidade> buscarTodos() {
		
		return cidadeDAO.buscarTodos();
		
	}

	public void excluir(Cidade cidade) throws ServiceException {
		try {
			cidadeDAO.excluir(cidade);
		} catch (DAOExpcetion e) {
		//	e.printStackTrace();
			throw  new ServiceException(e.getMessage(), e);
			
		}
	}
		public Cidade buscarPorId(Integer codcidade) throws ServiceException{
			try {
				return cidadeDAO.buscarPorId(codcidade);
			} catch (DAOExpcetion e) {
				throw  new ServiceException(e.getMessage(), e);
			}
		
	}
}
