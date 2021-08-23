package br.com.dataagil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.dataagil.entity.Developer;
import br.com.dataagil.exception.DAOExpcetion;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.repository.DeveloperDAO;

@Service
public class DeveloperService {

	@Autowired @Qualifier("developerDAOJPA") 
	private DeveloperDAO developerDao;

	public void salvar(Developer developer) throws ServiceException{
		try {
			developerDao.salvar(developer);
		} catch (DAOExpcetion e) {
			e.printStackTrace();
		}

	}
	

	public List<Developer> buscarTodos() {
		return developerDao.buscarTodos();
		
	}

}
