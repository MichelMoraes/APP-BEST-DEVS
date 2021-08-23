package br.com.dataagil.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.dataagil.entity.Cidade;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * 
 * @author Michel
 *
 */
@Repository("cidadeDAOJPA")
@Transactional
public class CidadeDAOJPA implements CidadeDAO {

	@PersistenceContext
	private EntityManager entityManager;


	public void salvar(Cidade cidade) throws DAOExpcetion {
		// sql
		// Lancar cedo e tratar tarde
		try {
			entityManager.merge(cidade);
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível salvar.", causa);
		}

	}


	public void excluir(Cidade cidade) throws DAOExpcetion {

		try {
			Cidade cidadeEx = buscarPorId(cidade.getCodcidade());

			entityManager.remove(cidadeEx);
		} catch (Exception causa) {
			causa.printStackTrace();
			throw new DAOExpcetion("Não foi possível Excluir.", causa);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> buscarTodos()  {
		Query consulta = entityManager
				.createQuery("select p from Cidade p");
		return consulta.getResultList();
	}

	@Override
	public Cidade buscarPorId(Integer codcidade) throws DAOExpcetion {
		try {
			return entityManager.find(Cidade.class, codcidade);
	 }  catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível buscar por Código", causa);
		
		}	
	}
		
    @Override
	public Cidade buscarPorDescricao(String nome) throws DAOExpcetion {
	    try {
		     return entityManager.find(Cidade.class, nome);
	 }     catch (Exception causa) {
		     throw new DAOExpcetion("Não foi possível buscar por Descrição", causa);
	   }
   }
    
	@SuppressWarnings("unchecked")
	@Override    
	public List<Cidade> buscarPorDescricao()  {
			Query consulta = entityManager
					.createQuery("select p from Cidade p where p like %?%");
			return consulta.getResultList();
		}
    
    }


