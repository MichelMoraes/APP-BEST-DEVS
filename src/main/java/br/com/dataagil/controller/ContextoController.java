package br.com.dataagil.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.sql.rowset.serial.SerialException;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dataagil.entity.Configuracao;
import br.com.dataagil.entity.Usuario;
import br.com.dataagil.service.ConfiguracaoService;
import br.com.dataagil.web.util.Funcoes;


@Scope(value="session")
@Controller
public class ContextoController implements Serializable {
		
	private static final long serialVersionUID = -1503034895420861688L;
	
	private String modulo = "";
	private Usuario usuarioLogado = null;
	private String caminhoAplicacao = null;
	
	private Configuracao configuracao = null;
	private StreamedContent imagem = null;	
				
	public ContextoController() {
				
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public Usuario getusuarioLogado() {
		try {
			
			if (this.usuarioLogado==null) {
				this.usuarioLogado =  Funcoes.getUsuarioAtual();
			}			
			return this.usuarioLogado;
			
		}catch(SerialException e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(),""));
			return new Usuario();
		}catch(Exception e){
			Funcoes.registrarLogErro(e, this);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro Interno! Não foi possível exibir o Operador Logado!",""));			
			return new Usuario();
		}
	}

	public void setusuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String getCaminhoAplicacao() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		this.caminhoAplicacao = external.getApplicationContextPath();
		return caminhoAplicacao;
	}

	public void setCaminhoAplicacao(String caminhoAplicacao) {
		this.caminhoAplicacao = caminhoAplicacao;
	}
	
	
	
	public Configuracao getFi_Configuracao() {
		if (this.configuracao==null) {
			try {
				ConfiguracaoService configuracaoService = new ConfiguracaoService();
				this.configuracao = (Configuracao) configuracaoService.buscarTodos();
			//	this.imagem = new DefaultStreamedContent(new ByteArrayInputStream(this.configuracao.getImagemLogotipo()));
				// TODO ver depois
			}catch(Exception e){
				Funcoes.registrarLogErro(e, this);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro Interno! "	+ "Não foi possível executar a Operação!",""));						
			}
		}
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
	
	public StreamedContent getImagem() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}
	
	public void atualizaPaginaAtual() { 
		FacesContext context = FacesContext.getCurrentInstance();  
        Application application = context.getApplication();  
        ViewHandler viewHandler = application.getViewHandler();  
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());  
        context.setViewRoot(viewRoot);  
        context.renderResponse();
    }

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Configuracao getConfiguracao() {
		return configuracao;
	}
		
	
	
}
