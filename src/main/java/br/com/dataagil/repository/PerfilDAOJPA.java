package br.com.dataagil.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.dataagil.entity.Perfil;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * 
 * @author Michel
 *
 */
@Repository("perfilDAOJPA")
@Transactional
public class PerfilDAOJPA implements PerfilDAO {

	@PersistenceContext
	private EntityManager entityManager;


	public void salvar(Perfil perfil) throws DAOExpcetion {
		// sql
		// Lancar cedo e tratar tarde
		try {
			entityManager.merge(perfil);
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível salvar.", causa);
		}

	}


	public void excluir(Perfil perfil) throws DAOExpcetion {

		try {
			Perfil perfilEx = buscarPorId(perfil.getCodperfil());

			entityManager.remove(perfilEx);
		} catch (Exception causa) {
			causa.printStackTrace();
			throw new DAOExpcetion("Não foi possível Excluir.", causa);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> buscarTodos()  {
		Query consulta = entityManager
				.createQuery("select p from Perfil p");
		return consulta.getResultList();
	}

	@Override
	public Perfil buscarPorId(Integer codperfil) throws DAOExpcetion {
		try {
			return entityManager.find(Perfil.class, codperfil);
	 }  catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível buscar por Código", causa);
		
		}	
	}
		
    @Override
	public Perfil buscarPorDescricao(String descricao_final) throws DAOExpcetion {
	    try {
		     return entityManager.find(Perfil.class, descricao_final);
	 }     catch (Exception causa) {
		     throw new DAOExpcetion("Não foi possível buscar por Descrição", causa);
	   }
   

    }
    
	@SuppressWarnings("unchecked")
	@Override    
	public List<Perfil> buscarPorDescricao()  {
			Query consulta = entityManager
					.createQuery("select p from Perfil p where p like %?%");
			return consulta.getResultList();
		}
    }

