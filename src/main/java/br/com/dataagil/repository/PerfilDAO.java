package br.com.dataagil.repository;

import java.util.List;

import br.com.dataagil.entity.Perfil;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * Padrao do Objeto de acesso a dados Perfil
 * 
 * @author  Michel Moraes
 */

public interface PerfilDAO {
	void salvar(Perfil perfil) throws DAOExpcetion;
	void excluir(Perfil perfil) throws DAOExpcetion;
	List<Perfil> buscarTodos() ;
	Perfil buscarPorId(Integer codperfil) throws DAOExpcetion;
	Perfil buscarPorDescricao(String descricao) throws DAOExpcetion;
	List<Perfil> buscarPorDescricao() ;
}
