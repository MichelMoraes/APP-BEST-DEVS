package br.com.dataagil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name ="Perfil")
@Entity
public class Perfil {
	

	
	@Id
	@SequenceGenerator(name = "seq_perfil", sequenceName = "seq_perfil", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_perfil")
	private Integer codperfil;
	
	@Column
	private String descricao;
	

    @Column
	private String descricao_final;

    
    
	public String getDescricao_final() {
		return descricao_final;
	}
	public void setDescricao_final(String descricao_final) {
		this.descricao_final = descricao_final;
	}
	public Integer getCodperfil() {
		return codperfil;
	}
	public void setCodperfil(Integer codperfil) {
		this.codperfil = codperfil;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codperfil == null) ? 0 : codperfil.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		Perfil other = (Perfil) obj;
		if (codperfil == null) {
			if (other.codperfil != null)
				return false;
		} else if (!codperfil.equals(other.codperfil))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Perfil [codperfil=" + codperfil + ", descricao=" + descricao
				+ "]";
	}
	
}
