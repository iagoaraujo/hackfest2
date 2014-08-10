package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="Local")
public class Local {

	@Id
	@SequenceGenerator(name = "LOCAL_SEQUENCE", sequenceName = "LOCAL_SEQUENCE", allocationSize = 1, initialValue = 0)
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column
	private String nomeLocal;
	
	@Column
	private String explicacao;
	
	@Column
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
	
}
