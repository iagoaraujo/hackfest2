package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity(name="Local")
public class Local {

	@Id
	@SequenceGenerator(name = "LOCAL_SEQUENCE", sequenceName = "LOCAL_SEQUENCE", allocationSize = 1, initialValue = 0)
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column
	@NotNull
	private String nomeLocal;
	
	@Column
	@NotNull
	private String explicacao;
	
	@Column
	@NotNull
	private int capacidade;

	public Local() {
	}
	
	public Local(String nome, String explicacao, int capacidade) {
		this.nomeLocal = nome;
		this.explicacao = explicacao;
		this.capacidade = capacidade;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nome) {
		this.nomeLocal = nome;
	}

	public String getExplicacao() {
		return explicacao;
	}

	public void setExplicacao(String explicacao) {
		this.explicacao = explicacao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomeLocal == null) ? 0 : nomeLocal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Local)) {
			return false;
		}
		Local other = (Local) obj;
		if (nomeLocal == null) {
			if (other.nomeLocal != null) {
				return false;
			}
		} else if (!nomeLocal.equals(other.nomeLocal)) {
			return false;
		}
		return true;
	}
	
}
