package br.com.dataagil.web.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.Locale;
import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import br.com.dataagil.entity.Usuario;
import br.com.dataagil.service.UsuarioService;

public class Funcoes implements Serializable {

	private static final long serialVersionUID = 7572922211480576091L;

	public static void registrarLogErro(Exception e, Object classe) {

		Logger logger = LogManager.getLogger(classe);
		logger.error( e.getMessage() + " Causado por: " +e.getCause() );		

	}

	public static void registrarLogErro(Exception e, Object classe, String mensagemAdicional) {

		Logger logger = LogManager.getLogger(classe);
		logger.error( e.getMessage() + " Causado por: " +e.getCause() + " Mensagem: "+mensagemAdicional );		

	}

	


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filtrarPorValor(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if(filterText == null||filterText.equals("")) {
			return true;
		}

		if(value == null) {
			return false;
		}

		Double db = Double.valueOf(filterText);
		return ((Comparable) value).compareTo(BigDecimal.valueOf(db)) == 0;
	}

	/**
	 * Esse m√©todo usa generics e reflection para clonar um objeto
	 * 
	 * @param objeto
	 * @return clone do objeto
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T clone(T object) {
		T clone = null;

		try {
			/*
			 * instanciando o objeto clone de  acordo com o objeto passado por
			 * par√¢metro
			 */
			clone = (T) object.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// Obter o tipo de classe atual, quando acabar, passar para a super
		// classe, at√© chegar em Object.
		for (Class obj = object.getClass(); !obj.equals(Object.class); obj = obj
				.getSuperclass()) {
			// Percorrer campo por campo da classe...
			for (Field field : obj.getDeclaredFields()) {
				field.setAccessible(true);
				try {
					// Copiar campo atual
					field.set(clone, field.get(object));
				} catch (Throwable t) {
				}
			}

		}
		return clone;
	}

	
	
	public static String removeAcentos(String acentuada) {  
	    CharSequence cs = new StringBuilder(acentuada);  
	    return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");  
	}  
	

	
	public static Usuario getUsuarioAtual() throws Exception {
		Usuario usuario = new Usuario();
		String login = getLoginOperadorAtual();				
		if (login != null) {
			UsuarioService usuarioService = new UsuarioService();
			usuario = usuarioService.buscarOperadorAtual(login);
		}		
		return usuario;
	}

	public static String getLoginOperadorAtual() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();				
		return login;
	}
	
	

	public static byte[] redimensionarImagem(InputStream imagem, Integer largura, Integer altura) throws Exception {
		 
        byte[] bytesImagem = null;
        Graphics2D graphisImagem = null;
        ByteArrayOutputStream baos = null;
        
        try {
            //transforma InputStream em uma BufferedImage
            BufferedImage bufImagemLida =  ImageIO.read(imagem);            
            //Calculando a proporÁ„o para o redimensionamento.
            if (bufImagemLida.getHeight()>=bufImagemLida.getWidth()) {
	            if (bufImagemLida.getWidth()>largura) {
		            Double prop = (double) bufImagemLida.getHeight() / (double) bufImagemLida.getWidth();                        
		            Double prop2 = (double) altura / (double) largura;            
		            if (prop!=prop2) {
		            	altura = (int) (prop * largura);
		            }
	            }
	            else {
	            	largura = bufImagemLida.getWidth();
	            	altura = bufImagemLida.getHeight();
	            }
            }
            else {
            	if (bufImagemLida.getHeight()>altura) {
		            Double prop = (double) bufImagemLida.getWidth() / (double) bufImagemLida.getHeight();                        
		            Double prop2 = (double) largura / (double) altura;            
		            if (prop!=prop2) {
		            	largura = (int) (prop * altura);
		            }
	            }
	            else {
	            	largura = bufImagemLida.getWidth();
	            	altura = bufImagemLida.getHeight();
	            }
            }
            
	        //cria imagem com fundo transparente se a imagem conter Alpha.
            BufferedImage imagemRedimensionada = null;            
            if (bufImagemLida.getColorModel().hasAlpha()) {
            	imagemRedimensionada = new BufferedImage(largura, altura, BufferedImage.BITMASK);
            }
            else {
            	imagemRedimensionada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
            }
            
		    //realiza o redimensionamento da imagem
	        graphisImagem = imagemRedimensionada.createGraphics();
	        graphisImagem.drawImage(bufImagemLida,0,0, largura, altura, null);
	        baos = new ByteArrayOutputStream();	        
             
            //escreve a imagem no OutputStream
	        if (bufImagemLida.getColorModel().hasAlpha()) {
	        	ImageIO.write(imagemRedimensionada, "png", baos);
	        }
	        else {
	        	ImageIO.write(imagemRedimensionada, "jpg", baos);
	        }
	        
            //transforma o OutputStream em array de bytes e retorna
            bytesImagem = baos.toByteArray();            
        } catch (Exception e) {            
            Funcoes.registrarLogErro(e, Funcoes.class,"Erro no Redimensionamento da Imagem");
			throw new Exception(e);
        }finally{
            //libera recursos
            if(graphisImagem != null){
                graphisImagem.dispose();
            }
 
            if(baos != null){
                try {
                    baos.close();
                } catch (Exception e) {
                	Funcoes.registrarLogErro(e, Funcoes.class,"Erro no Redimensionamento da Imagem");
        			throw new Exception(e);
                }
            }
        }
 
        return bytesImagem;
    }


}
