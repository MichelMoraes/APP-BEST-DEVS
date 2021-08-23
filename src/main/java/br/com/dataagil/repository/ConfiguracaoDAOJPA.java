package br.com.dataagil.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.dataagil.entity.Configuracao;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * 
 * @author Michel
 *
 */
@Repository("configuracaoDAOJPA")
@Transactional
public class ConfiguracaoDAOJPA implements ConfiguracaoDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4545838795224466710L;
	@PersistenceContext
	private EntityManager entityManager;


	public void salvar(Configuracao configuracao) throws DAOExpcetion {
		// sql
		// Lancar cedo e tratar tarde
		try {
			entityManager.merge(configuracao);
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível salvar.", causa);
		}

	}


	public void excluir(Configuracao configuracao) throws DAOExpcetion {

		try {
			Configuracao configuracaoEx = buscarPorId(configuracao.getCodconfiguracao());

			entityManager.remove(configuracaoEx);
		} catch (Exception causa) {
			//causa.printStackTrace();
			throw new DAOExpcetion("Não foi possível Excluir.", causa);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Configuracao> buscarTodos()  {
		Query consulta = entityManager
				.createQuery("select p from Configuracao p");
		return consulta.getResultList();
	}



	@Override
	public Configuracao buscarPorId(Integer codconfiguracao) throws DAOExpcetion {
		try {
			return entityManager.find(Configuracao.class, codconfiguracao);
		}  catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível buscar por Código", causa);

		}	
	}

	@Override
	public Configuracao buscarPorDescricao(String descricao) throws DAOExpcetion {
		try {
			return entityManager.find(Configuracao.class, descricao);
		}     catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível buscar por Descrição", causa);
		}
	}

	@SuppressWarnings("unchecked")
	@Override    
	public List<Configuracao> buscarPorDescricao()  {
		Query consulta = entityManager
				.createQuery("select p from Empresa p where p like %?%");
		return consulta.getResultList();
	}




}