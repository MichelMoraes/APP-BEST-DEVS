package br.com.dataagil.repository;

import java.util.List;

import br.com.dataagil.entity.Cidade;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * Padrao do Objeto de acesso a dados Cidade
 * 
 * @author  Michel Moraes
 */

public interface CidadeDAO {
	void salvar(Cidade cidade) throws DAOExpcetion;
	void excluir(Cidade cidade) throws DAOExpcetion;
	List<Cidade> buscarTodos() ;
	Cidade buscarPorId(Integer codCidade) throws DAOExpcetion;
	Cidade buscarPorDescricao(String nome) throws DAOExpcetion;
	List<Cidade> buscarPorDescricao() ;
}
