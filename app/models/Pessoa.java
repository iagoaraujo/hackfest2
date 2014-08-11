package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import play.data.validation.Constraints.Required;

@Entity(name = "Pessoa")
public class Pessoa implements Comparable<Pessoa>{
	// Gerador de Sequencia para o Id
	// Todo Id tem que ter o GeneratedValue a não ser que ele seja setado
	@Id
	@SequenceGenerator(name = "PESSOA_SEQUENCE", sequenceName = "PESSOA_SEQUENCE", allocationSize = 1, initialValue = 0)
	@GeneratedValue(strategy = GenerationType.TABLE)
	// Usar Id sempre Long
	private Long id;
	
	@Column
	@Required
	private String nome;
	
	@Column (unique=true)
	@Required
	private String email;
	
	@Column
	@Required
	private String senha;
	
	@Column
	private int numEventosInscritos;
	
	@Column
	private int numEventosCriados;
	
	// Construtor vazio para o Hibernate criar os objetos
	public Pessoa (){
		
	}
	
	public Pessoa (String nome, String email){
		this.email = email;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		if (!(obj instanceof Pessoa)) {
			return false;
		}
		Pessoa other = (Pessoa) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (senha == null) {
			if (other.senha != null) {
				return false;
			}
		} else if (!senha.equals(other.senha)) {
			return false;
		}
		return true;
	}

	public int getNumEventosInscritos() {
		return numEventosInscritos;
	}

	public void setNumEventosInscritos(int numEventosInscritos) {
		this.numEventosInscritos = numEventosInscritos;
	}

	public int getNumEventosCriados() {
		return numEventosCriados;
	}

	public void setNumEventosCriados(int numEventosCriados) {
		this.numEventosCriados = numEventosCriados;
	}

	@Override
	public int compareTo(Pessoa pessoa) {
		if(numEventosCriados>pessoa.getNumEventosCriados()) {
			return -1;
		}
		if(numEventosCriados<pessoa.getNumEventosCriados()) {
			return 1;
		}
		if(numEventosInscritos>pessoa.getNumEventosInscritos()) {
			return -1;
		}
		if(numEventosInscritos<pessoa.getNumEventosInscritos()) {
			return 1;
		}
		return 0;
	}

}
