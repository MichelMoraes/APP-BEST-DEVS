package br.com.dataagil.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.dataagil.entity.Configuracao;
import br.com.dataagil.exception.DAOExpcetion;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.repository.ConfiguracaoDAO;


/**
 * 
 * 
 * @author Michel Moraes
 *
 */
@Service
public class ConfiguracaoService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4131754353599895651L;
	
	
	//InjeÃ§Ã£o de Dependencia
	@Autowired @Qualifier("configuracaoDAOJPA") private ConfiguracaoDAO configuracaoDAO;
	
	
	public void salvar(Configuracao configuracao) throws ServiceException{
		
		//Verificacoes
	
		try {
		/*	Empresa configuracaoExistente =configuracaoDAO.buscarPorDescricao(configuracao.getNome());
			//Regra de Negócio
		if(configuracaoExistente!=null){  
				throw new ServiceException("Esta configuracao já existe!");
		
		}*/
		configuracaoDAO.salvar(configuracao);
		} catch (DAOExpcetion e) {
			//Gerar uma nova exception ServiceExcetion
			throw new ServiceException(e);
		}
	}
	
	public List<Configuracao> buscarTodos() {
		
		return configuracaoDAO.buscarTodos();
		
	}

	public void excluir(Configuracao configuracao) throws ServiceException {
		try {
			configuracaoDAO.excluir(configuracao);
		} catch (DAOExpcetion e) {
		//	e.printStackTrace();
			throw  new ServiceException(e.getMessage(), e);
			
		}
	}
		public Configuracao buscarPorId(Integer codconfiguracao) throws ServiceException{
			try {
				return configuracaoDAO.buscarPorId(codconfiguracao);
			} catch (DAOExpcetion e) {
				throw  new ServiceException(e.getMessage(), e);
			}
		
	}
		
	
}
