package br.com.dataagil.repository;

import java.util.List;

import br.com.dataagil.entity.Estado;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * Padrao do Objeto de acesso a dados Estado
 * 
 * @author  Michel Moraes
 */

public interface EstadoDAO {
	void salvar(Estado estado) throws DAOExpcetion;
	void excluir(Estado estado) throws DAOExpcetion;
	List<Estado> buscarTodos() ;
	Estado buscarPorId(Integer codestado) throws DAOExpcetion;
	Estado buscarPorDescricao(String nome) throws DAOExpcetion;
	List<Estado> buscarPorDescricao() ;
}
