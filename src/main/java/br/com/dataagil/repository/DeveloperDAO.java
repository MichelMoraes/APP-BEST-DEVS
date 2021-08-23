package br.com.dataagil.repository;

import java.util.List;

import br.com.dataagil.entity.Developer;
import br.com.dataagil.exception.DAOExpcetion;

/**
 *  
 * @author  Michel Moraes
 */

public interface DeveloperDAO {
	void salvar(Developer developer) throws DAOExpcetion;
	List<Developer> buscarTodos() ;

}
