package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

@Entity(name = "Evento")
public class Evento implements Comparable<Evento> {
	
	// Gerador de Sequencia para o Id
	// Todo Id tem que ter o GeneratedValue a não ser que ele seja setado
	@Id
	@SequenceGenerator(name = "EVENTO_SEQUENCE", sequenceName = "EVENTO_SEQUENCE", allocationSize = 1, initialValue = 0)
	@GeneratedValue(strategy = GenerationType.TABLE)
	// Usar Id sempre Long
	private Long id;
	
	@Column	
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String data;
	
	@OneToOne
	private Pessoa administrador;
	
	@OneToOne
	private Local local;
	
	@Column
	private String tema1;
	
	@Column
	private String tema2;
	
	@Column
	private String tema3;
	
	@Column
	private String tema4;
	
	@Column
	private String tema5;
	
	//Usado pelo formulário de cadastro do evento
	@Transient
	private Long localId;
	@Transient
	private boolean hasNewLocal;
		
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Pessoa> PessoasQueConfirmaram =  new ArrayList<Pessoa>();
	
	// Construtor vazio para o Hibernate criar os objetos
	public Evento(){
	}
	public Evento(String nome, String descricao, String data, Pessoa administrador,
			Local local) {
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.administrador = administrador;
		this.local = local;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public Long getLocalId() {
		return localId;
	}
		
	public void setLocalId(Long id) {
		this.localId = id;
	}
	
	public void addParticipanteNoEvento(Pessoa pessoa) {
		System.out.println("METODO PARA ADICIONAR");
		if (!this.PessoasQueConfirmaram.contains(pessoa)){
			System.out.println("PESSOA ADICIONADA");
			this.PessoasQueConfirmaram.add(pessoa);
		}
	}
	
	public void removerParticipanteNoEvento(Pessoa pessoa) {
		if (this.PessoasQueConfirmaram.contains(pessoa)){
			this.PessoasQueConfirmaram.remove(pessoa);
		}
		
	}
	
	public int numDePessoasQueConfirmaram(){
		return this.PessoasQueConfirmaram.size();
	}
	
	@Override
	public int compareTo(Evento evento) {
		if (this.PessoasQueConfirmaram.size() > evento.numDePessoasQueConfirmaram()) {
		      return -1;
		    }
	    if (this.PessoasQueConfirmaram.size() < evento.numDePessoasQueConfirmaram()) {
		      return 1;
		    }
	    return 0;
		  
	}	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Pessoa> getPessoasQueConfirmaram() {
		return PessoasQueConfirmaram;
	}
	public void setPessoasQueConfirmaram(List<Pessoa> pessoasQueConfirmaram) {
		PessoasQueConfirmaram = pessoasQueConfirmaram;
	}
	public String getTema1() {
		return tema1;
	}
	public void setTema1(String tema1) {
		this.tema1 = tema1;
	}
	public String getTema2() {
		return tema2;
	}
	public void setTema2(String tema2) {
		this.tema2 = tema2;
	}
	public String getTema3() {
		return tema3;
	}
	public void setTema3(String tema3) {
		this.tema3 = tema3;
	}
	public String getTema4() {
		return tema4;
	}
	public void setTema4(String tema4) {
		this.tema4 = tema4;
	}
	public String getTema5() {
		return tema5;
	}
	public void setTema5(String tema5) {
		this.tema5 = tema5;
	}
	
	public Local getLocal() {
		return local;
	}
	
	public void setLocal(Local local) {
		this.local = local;
	}
	
	public Pessoa getAdministrador() {
		return administrador;
	}
	
	public void setAdministrador(Pessoa administrador) {
		this.administrador = administrador;
	}
	
	public List<String> getTemas(){
		List<String> retorno = new ArrayList<String>();
		retorno.add(tema1);
		retorno.add(tema2);
		retorno.add(tema3);
		retorno.add(tema4);
		retorno.add(tema5);
		return retorno;
	}
	
	public void addTema(String tema){
		String tema11 = "Engenharia de Software";
		String tema21 = "Sistemas da Informacao";
		String tema31 = "Banco de Dados";
		String tema41 = "Computacao Desplugada";
		String tema51 = "Desenvolvimento para Web";
		if (tema.equals(tema11)){
			this.tema1 = tema;
			return;
		}
		else if (tema.equals(tema21)){
			this.tema2 = tema;
		}
		else if (tema.equals(tema31)){
			this.tema3 = tema;
		}
		else if (tema.equals(tema41)){
			this.tema4 = tema;
		}
		else if (tema.equals(tema51)){
			this.tema5 = tema;
		}
		
	}
	public boolean isHasNewLocal() {
		return hasNewLocal;
	}
	public void setHasNewLocal(boolean hasNewLocal) {
		this.hasNewLocal = hasNewLocal;
	}
	
	public boolean isEventoClosed() {
		return this.getLocal().getCapacidade()<=numDePessoasQueConfirmaram();
	}
	
	public boolean isUsuarioInscrito(Pessoa pessoa) {
		return PessoasQueConfirmaram.contains(pessoa);
	}
}
