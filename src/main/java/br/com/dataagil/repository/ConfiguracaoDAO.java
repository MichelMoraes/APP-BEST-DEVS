package br.com.dataagil.repository;

import java.util.List;

import br.com.dataagil.entity.Configuracao;
import br.com.dataagil.exception.DAOExpcetion;

/**
 * Padrao do Objeto de acesso a dados Empresa
 * 
 * @author  Michel Moraes
 */

public interface ConfiguracaoDAO {
	void salvar(Configuracao configuracao) throws DAOExpcetion;
	void excluir(Configuracao configuracao) throws DAOExpcetion;
	List<Configuracao> buscarTodos() ;
	Configuracao buscarPorId(Integer codconfiguracao) throws DAOExpcetion;
	Configuracao buscarPorDescricao(String descricao) throws DAOExpcetion;
	List<Configuracao> buscarPorDescricao() ;
	
}