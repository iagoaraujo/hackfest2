package controllers;

import java.util.List;

import models.Evento;
import models.Local;
import models.Pessoa;
import models.Sistema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	private static Form<Evento> eventoForm = Form.form(Evento.class);
	private static Form<Pessoa> pessoaForm = Form.form(Pessoa.class);
	private static Form<Local> localForm = Form.form(Local.class);
	private static GenericDAO dao = new GenericDAOImpl();

    public static Result index() {
        return ok(views.html.login.render(pessoaForm));
    }
    
    @Transactional
    public static Result logar() {
    	if (session().get("user") == null) {
    		return index();
    	}
    	if (getDao().findAllByClassName("Evento").isEmpty()) {
    		GeradorExemplos.gera();
    	}
    	
    	Sistema sistema = new Sistema();
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
    	Pessoa usuarioLogado = getDao().findByEntityId(Pessoa.class, 
				Long.valueOf(session("user")));
    	sistema.setUsuarioLogado(usuarioLogado);
    	return ok(views.html.sistema.render(sistema));
    }
    
    @Transactional
    public static Result cadastro() {
    	Sistema sistema = new Sistema();
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
    	List<Local> locais = getDao().findAllByClassName("Local");
    	return ok(views.html.cadastro.render(sistema, locais, eventoForm, localForm));
    }
    
    @Transactional
    public static Result newEvento() {
    	// Todos o eventos do Banco de Dados
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	// O formulario de evento
		Form<Evento> filledForm = eventoForm.bindFromRequest("nome","descricao",
				"data","tema1","tema2","tema3","tema4","tema5","localId", 
				"hasNewLocal", "prioritario");
		List<Local> locais = getDao().findAllByClassName("Local");
		
		Evento evento = filledForm.get();
		
		Pessoa usuarioLogado = getDao().findByEntityId(Pessoa.class, 
				Long.valueOf(session("user")));
		usuarioLogado.setNumEventosCriados(usuarioLogado.getNumEventosCriados()+1);
		getDao().merge(usuarioLogado);
		getDao().flush();
		evento.setAdministrador(usuarioLogado);
		if(!evento.isHasNewLocal()) {
			evento.setLocal(getDao().findByEntityId(Local.class, 
				evento.getLocalId()));
		}
		else {
			Form<Local> localFormFilled = localForm.bindFromRequest("nomeLocal",
					"explicacao", "capacidade");
			Local local = localFormFilled.get();
			getDao().persist(local);
			getDao().flush();
			evento.setLocal(local);
		}
		
		Sistema sistema = new Sistema();
    	sistema.setEventos(result);
    	if (filledForm.hasErrors()) {
			return badRequest(views.html.cadastro.render(sistema, locais, 
					eventoForm, localForm));
		} else {
			getDao().persist(evento);
			// Espelha no Banco de Dados
			getDao().flush();
			return redirect(routes.Application.logar());
		}
    	
    	
    }
    
    @Transactional
    public static Result participar(Long id) {
    	Evento evento = getDao().findByEntityId(Evento.class, id);
    	Sistema sistema = new Sistema();
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
    	return ok(views.html.evento.render(sistema, evento));
    }
    
    @Transactional
    public static Result addParticipante(Long id) {
    	// Todos o eventos do Banco de Dados
    	List<Evento> result;
		Evento evento = getDao().findByEntityId(Evento.class, id);
		Pessoa pessoa = getDao().findByEntityId(Pessoa.class, 
				Long.valueOf(session("user")));
		pessoa.setNumEventosInscritos(pessoa.getNumEventosInscritos()+1);
		evento.addParticipanteNoEvento(pessoa);
		evento.sortListaInscritos();
		getDao().merge(pessoa);
		getDao().merge(evento);
		getDao().flush();
		Sistema sistema = new Sistema();
    	result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
		return ok(views.html.inscricaoFeitaSucesso.render());
	}
    
    public static Result cadastroUsuario() {
    	return ok(views.html.cadastroUsuario.render(pessoaForm));
    }
    	
    @Transactional
    public static Result cadastrarUsuario() {
    	Form<Pessoa> filledForm = pessoaForm.bindFromRequest();
    	getDao().merge(filledForm.get());
    	getDao().flush();
    	return ok(views.html.cadastroUsuarioSucesso.render());
    }

	public static GenericDAO getDao() {
		return dao;
	}

	public static void setDao(GenericDAO dao) {
		Application.dao = dao;
	}

}
