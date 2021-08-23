package br.com.dataagil.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.dataagil.entity.Estado;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * 
 * @author Michel
 *
 */
@Repository("estadoDAOJPA")
@Transactional
public class EstadoDAOJPA implements EstadoDAO {

	@PersistenceContext
	private EntityManager entityManager;


	public void salvar(Estado estado) throws DAOExpcetion {
		// sql
		// Lancar cedo e tratar tarde
		try {
			entityManager.merge(estado);
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível salvar.", causa);
		}

	}


	public void excluir(Estado estado) throws DAOExpcetion {

		try {
			Estado estadoEx = buscarPorId(estado.getCodEstado());

			entityManager.remove(estadoEx);
		} catch (Exception causa) {
			causa.printStackTrace();
			throw new DAOExpcetion("Não foi possível Excluir.", causa);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estado> buscarTodos()  {
		Query consulta = entityManager
				.createQuery("select p from Estado p");
		return consulta.getResultList();
	}

	@Override
	public Estado buscarPorId(Integer codestado) throws DAOExpcetion {
		try {
			return entityManager.find(Estado.class, codestado);
	 }  catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível buscar por Código", causa);
		
		}	
	}
		
    @Override
	public Estado buscarPorDescricao(String nome) throws DAOExpcetion {
	    try {
		     return entityManager.find(Estado.class, nome);
	 }     catch (Exception causa) {
		     throw new DAOExpcetion("Não foi possível buscar por Descrição", causa);
	   }
   }
    
	@SuppressWarnings("unchecked")
	@Override    
	public List<Estado> buscarPorDescricao()  {
			Query consulta = entityManager
					.createQuery("select p from Estado p where p like %?%");
			return consulta.getResultList();
		}
    
    }


