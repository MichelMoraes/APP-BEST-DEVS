package br.com.dataagil.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dataagil.entity.Configuracao;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.ConfiguracaoService;
import br.com.dataagil.web.util.Funcoes;
import br.com.dataagil.web.util.MensagemUtil;

@Scope(value="request")
@Controller(value="configuracaoController")
public class ConfiguracaoController implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1960216272166786242L;

	//Entrada dos campos
	private Configuracao configuracao;
	private List<Configuracao> listaConfig;
	private Integer exibirBuscaProntuarioInicial = 0;
	private String operadorLogado = Funcoes.getLoginOperadorAtual();

	//Lista contendo objetos configuracoes do banco
	private List<Configuracao> configuracoes;

	@Autowired
	private ConfiguracaoService configuracaoService;


	@PostConstruct
	public void init() {
		configuracao = new Configuracao();
		
		listaConfig = configuracaoService.buscarTodos();
		
		if (listaConfig != null && ! listaConfig.isEmpty() && listaConfig.size()>0) {
			setConfiguracao(listaConfig.get(0));

		}


	}
	

/**
	 * Método utilizado nos dataTables para filtrar campos com acento
	 * @param value Representa o Valor da Coluna
	 * @param filter Representa o Valor do Filtro 
	 * @param locale Uso opcional. Define parâmetros para ToLowerCase 
	 * @return Boolean
	 */
	public boolean filtrarDataTableComAcentos(Object value, Object filter, Locale locale) {
		if ((value!=null) && (filter!=null)) {
			return Funcoes.removeAcentos(value.toString().toLowerCase()).contains(Funcoes.removeAcentos(filter.toString().toLowerCase()));
		} else {
			return true;
		}
	}


	public void iniciar() {
		
			/*listaConfig = configuracaoService.verificarBuscaProntuarioInicial();

			configuracoes = null;

			if (configuracoes == null) {
				configuracoes =  configuracaoService.buscarTodos();
			}

			configuracao = new Configuracao();

			if (configuracoes != null && ! configuracoes.isEmpty() && configuracoes.size()>0) {
				setConfiguracao(configuracoes.get(0));

			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
*/
		}
	


	public void salvar(){
		try {
			
			
			configuracaoService.salvar(configuracao);
			init();
			MensagemUtil.mensagemInfo("Salvo com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro( e.getMessage());
		}
	}

	public void cancelar(){
		setConfiguracao(new Configuracao());
	}

	public void excluir(Configuracao configuracao){
		try {
			configuracaoService.excluir(configuracao);
			init();
			MensagemUtil.mensagemInfo("Excluído com Sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro(e.getMessage());
		}
	}

	public void editar(){

	}



	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
	public List<Configuracao> getConfiguracaos() {
		return configuracoes;
	}
	public void setConfiguracaos(List<Configuracao> configuracoes) {
		this.configuracoes = configuracoes;
	}
	public Integer getExibirBuscaProntuarioInicial() {
		return exibirBuscaProntuarioInicial;
	}
	public void setExibirBuscaProntuarioInicial(Integer exibirBuscaProntuarioInicial) {
		this.exibirBuscaProntuarioInicial = exibirBuscaProntuarioInicial;
	}


	public List<Configuracao> getListaConfig() {
		return listaConfig;
	}


	public void setListaConfig(List<Configuracao> listaConfig) {
		this.listaConfig = listaConfig;
	}

	public String getOperadorLogado() {
		return operadorLogado;
	}

	public void setOperadorLogado(String operadorLogado) {
		this.operadorLogado = operadorLogado;
	}

	public List<Configuracao> getConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(List<Configuracao> configuracoes) {
		this.configuracoes = configuracoes;
	}



}
