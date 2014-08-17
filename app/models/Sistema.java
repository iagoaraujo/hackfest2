package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sistema {
	
	private List<Evento> eventos = new ArrayList<Evento>();
	private List<Local> locais = new ArrayList<Local>();
	
	private Pessoa usuarioLogado;
	
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
	
	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public Evento criaEventoComNovoLocal(Evento evento, Local local) {
		atualizaAdministradorDoEvento(evento);
		evento.setLocal(local);
		addEvento(evento);
		addLocal(local);
		return evento;
	}
	
	public Evento criaEventoLocalCadastrado(Evento evento, long localId) {
		for (Local local: locais) {
			if (local.getId()==localId) {
				evento.setLocal(local);
				break;
			}
		}
		atualizaAdministradorDoEvento(evento);
		addEvento(evento);
		return evento;
	}
	
	private void atualizaAdministradorDoEvento(Evento evento) {
		evento.setAdministrador(getUsuarioLogado());
		getUsuarioLogado().setNumEventosCriados(getUsuarioLogado()
				.getNumEventosCriados()+1);
	}
	
	public void addLocal(Local local) {
		if (!locais.contains(local)) {
			locais.add(local);
		}
	}
}