package br.com.dataagil.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.dataagil.entity.Developer;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * 
 * @author Michel
 *
 */
@Repository("developerDAOJPA")
@Transactional
public class DeveloperDAOJPA implements DeveloperDAO {

	@PersistenceContext
	private EntityManager entityManager;


	public void salvar(Developer developer) throws DAOExpcetion {
		try {
			entityManager.merge(developer);
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível salvar o Developer.", causa);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Developer> buscarTodos()  {
		Query consulta = entityManager
				.createQuery("select d from Developer d");
		return consulta.getResultList();
	}

    }


