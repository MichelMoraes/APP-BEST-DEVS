package br.com.dataagil.repository;

import java.util.List;

import br.com.dataagil.entity.Perfil;
import br.com.dataagil.entity.Usuario;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * Padrao do Objeto de acesso a dados Usuario
 * 
 * @author Michel Moraes 
 * 
 */
public interface UsuarioDAO {
	void salvar(Usuario usuario) throws DAOExpcetion;

	void excluir(Usuario usuario) throws DAOExpcetion;

	List<Usuario> buscarTodos() ;

	Usuario buscarPorId(Integer codperfil) throws DAOExpcetion;



	Usuario buscarLogin(String login, String senha, Boolean ativo, Perfil perfil)	throws DAOExpcetion;
    Usuario buscarPorLoginOperadorAtual (String login) throws DAOExpcetion; 
	
}
