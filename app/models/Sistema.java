package models;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

@Entity(name = "Sistema")
public class Sistema {
	// Todo Id tem que ter o GeneratedValue a nÃ£o ser que ele seja setado
	@Id
	@SequenceGenerator(name = "SISTEMA_SEQUENCE", sequenceName = "SISTEMA_SEQUENCE", allocationSize = 1, initialValue = 0)
	@GeneratedValue(strategy = GenerationType.TABLE)
	// Usar Id sempre Long
	private Long id;	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn
	private List<Evento> eventos = new ArrayList<Evento>();
	
	@Transient
	private Pessoa usuarioLogado;
	
	// Construtor vazio para o Hibernate criar os objetos 
	public Sistema(){
	}

	public Pessoa getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Pessoa usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	public void addEvento(Evento evento){
		this.eventos.add(evento);
	}
	
	public boolean removeEvento(Evento evento){
		return this.eventos.remove(evento);
	}
	
	public List<Evento> eventosPorTema(String tema){
		
		List<Evento> retorno = new ArrayList<Evento>();
		for (Evento evento: this.eventos){
			if (evento.getTemas().contains(tema)){
				retorno.add(evento);
			}
		}
		return retorno;
	}
	
	public List<Evento> eventosOrdenadosPorQuantidadeDePessoas(){
		List<Evento> copiaDeEventos = this.eventos;
		Collections.sort(copiaDeEventos);
		return copiaDeEventos;
	}
	
	public int numDeEventos(){
		return this.eventos.size();
	}
	
	public boolean contemEvento(Evento evento){
		return this.eventos.contains(evento);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void addPessoaNoEvento(Evento evento, Pessoa pessoa){
		if (this.eventos.contains(evento)){
			for (Evento eventoaux : this.eventos){
				if (eventoaux.equals(evento)){
					Evento eventoFinal = eventoaux;
					eventoFinal.addParticipanteNoEvento(pessoa);
					this.eventos.remove(eventoaux);
					this.eventos.add(eventoFinal);
					return;
				}
			}
		}
	}
}
