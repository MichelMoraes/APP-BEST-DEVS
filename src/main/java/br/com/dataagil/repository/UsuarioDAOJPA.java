package br.com.dataagil.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.dataagil.entity.Perfil;
import br.com.dataagil.entity.Usuario;
import br.com.dataagil.exception.DAOExpcetion;
import br.com.dataagil.exception.ServiceException;

/**
 * 
 * 
 * @author Michel
 *
 */
@Repository("usuarioDAOJPA")
@Transactional
public class UsuarioDAOJPA implements UsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;


	public void salvar(Usuario usuario) throws DAOExpcetion {
		// sql
		// Lancar cedo e tratar tarde
		try {
			entityManager.merge(usuario);
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível salvar.", causa);
		}

	}

	
	public void excluir(Usuario usuario) throws DAOExpcetion {

		try {
			Usuario usuExc = buscarPorId(usuario.getCodusuario());
			if (usuario.getAdminsistema().equals("S")) {  
		    	throw new ServiceException("Exclusão não permitida pelo Administrador!");	
	}
			entityManager.remove(usuExc);
		} catch (Exception causa) {
			//causa.printStackTrace();
			throw new DAOExpcetion("Exclusão não permitida pelo Administrador!");
		}
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> buscarTodos()  {
			Query consulta = entityManager
					.createQuery("select u from Usuario u where u.adminsistema <> 'S'  ");
			return consulta.getResultList();
	}

	@Override
	public Usuario buscarPorId(Integer codusuario) throws DAOExpcetion {
		try {
			return entityManager.find(Usuario.class, codusuario);
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível buscar por Codigo.", causa);
		}
	}

	@Override
	public Usuario buscarLogin(String login, String senha, Boolean ativo, Perfil perfil) throws DAOExpcetion {
		try {
			String jpql = "Select u from Usuario u where u.login=:loginParam and u.senha=:senhaParam and u.ativo=:ativoParam and u.perfil=:perfilParam";

			Query consulta = entityManager.createQuery(jpql);

			consulta.setParameter("loginParam", login);
			consulta.setParameter("senhaParam", senha);
			consulta.setParameter("ativoParam", ativo);
			consulta.setParameter("perfilParam", perfil);
			
			consulta.setMaxResults(1);
			return (Usuario) consulta.getSingleResult();
		} catch (NoResultException e) {
			// engolir a exception
			return null;
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível buscar por login.", causa);
		}

	}


	@Override
	public Usuario buscarPorLoginOperadorAtual(String login) throws DAOExpcetion {
		try {	
			String jpql = "select u.nome from Usuario u where u.login=:loginParam";

			Query consulta = entityManager.createQuery(jpql);

			consulta.setParameter("loginParam", login);

			consulta.setMaxResults(1);
			return (Usuario) consulta.getSingleResult();
		} catch (NoResultException e) {
			// engolir a exception
			return null;
		} catch (Exception causa) {
			throw new DAOExpcetion("Não foi possível buscar por login.", causa);

		}


	}


}
