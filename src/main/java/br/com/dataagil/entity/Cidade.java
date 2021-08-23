package br.com.dataagil.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Table(name = "cidade")
@Entity
public class Cidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7656707863885507109L;

	@Id
	@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_cidade")
	private Integer codcidade;
	
	@Column(nullable=false)
	private String nome;
	
	
    @Column
    private String estado;
    
    @Column
    private Integer codmunicipio;
  
    
	
	
	
	
	public Integer getCodcidade() {
		return codcidade;
	}
	public void setcodcidade(Integer codcidade) {
		this.codcidade = codcidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codcidade == null) ? 0 : codcidade.hashCode());
		result = prime * result + ((codmunicipio == null) ? 0 : codmunicipio.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (codcidade == null) {
			if (other.codcidade != null)
				return false;
		} else if (!codcidade.equals(other.codcidade))
			return false;
		if (codmunicipio == null) {
			if (other.codmunicipio != null)
				return false;
		} else if (!codmunicipio.equals(other.codmunicipio))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getCodmunicipio() {
		return codmunicipio;
	}
	public void setCodmunicipio(Integer codmunicipio) {
		this.codmunicipio = codmunicipio;
	}
	public void setCodcidade(Integer codcidade) {
		this.codcidade = codcidade;

	}
	@Override
	public String toString() {
		return "Cidade [codcidade=" + codcidade + ", nome=" + nome + ", estado=" + estado + ", codmunicipio="
				+ codmunicipio + "]";
	}


}