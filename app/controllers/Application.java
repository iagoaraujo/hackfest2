package controllers;

import java.util.List;

import models.Evento;
import models.Local;
import models.Pessoa;
import models.Sistema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	private static Form<Evento> eventoForm = Form.form(Evento.class);
	private static Form<Pessoa> pessoaForm = Form.form(Pessoa.class);
	private static Form<Local> localForm = Form.form(Local.class);
	private static GenericDAO dao = new GenericDAOImpl();
	private static Sistema sistema = new Sistema();
    
    @Transactional
    public static Result logar() {
    	if (session().get("user") == null) {
    		return redirect(routes.Login.show());
    	}
    	atualizaSistema();
    	return ok(views.html.sistema.render(sistema));
    }
    
    @Transactional
    public static Result cadastro() {
    	atualizaLocaisDoSistema();
    	return ok(views.html.cadastro.render(sistema, eventoForm, localForm));
    }
    
    @Transactional
    public static Result newEvento() {
		Form<Evento> filledForm = eventoForm.bindFromRequest("nome","descricao",
				"data","tema1","tema2","tema3","tema4","tema5", "tipoDeEvento");
		DynamicForm requestData = Form.form().bindFromRequest();
		boolean hasNewLocal = Boolean.valueOf(requestData.get("hasNewLocal"));
		Evento evento = filledForm.get();
		if(!hasNewLocal) {
			long localId = Long.valueOf(requestData.get("localId"));
			evento = sistema.criaEventoLocalCadastrado(evento, localId);
		}
		else {
			Local local = createNewLocal();
			evento = sistema.criaEventoComNovoLocal(evento, 
					local);
			atualizaLocaisDoSistema();
		}
		getDao().merge(sistema.getUsuarioLogado());
		getDao().merge(evento);
		getDao().flush();
		atualizaEventosDoSistema();
		return redirect(routes.Application.logar());
    }

    @Transactional
	private static Local createNewLocal() {
		Form<Local> localFormFilled = localForm.bindFromRequest("nomeLocal",
				"explicacao", "capacidade");
		Local local = localFormFilled.get();
		getDao().persist(local);
		getDao().flush();
		return local;
	}
    
    @Transactional
    public static Result participar(Long id) {
    	Evento evento = getDao().findByEntityId(Evento.class, id);
    	return ok(views.html.evento.render(sistema, evento));
    }
    
    @Transactional
    public static Result addParticipante(Long id) {
    	// Todos o eventos do Banco de Dados
		Evento evento = getDao().findByEntityId(Evento.class, id);
		Pessoa pessoa = getDao().findByEntityId(Pessoa.class, 
				Long.valueOf(session("user")));
		pessoa.setNumEventosInscritos(pessoa.getNumEventosInscritos()+1);
		evento.addParticipanteNoEvento(pessoa);
		getDao().merge(pessoa);
		getDao().merge(evento);
		getDao().flush();
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

	private static GenericDAO getDao() {
		return dao;
	}

	@Transactional
	private static void atualizaEventosDoSistema() {
		List<Evento> result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
	}
	
	@Transactional
	private static void atualizaLocaisDoSistema() {
		List<Local> result = getDao().findAllByClassName("Local");
		sistema.setLocais(result);
	}
	
	@Transactional
	private static void atualizaSistema() {
		atualizaEventosDoSistema();
    	atualizaLocaisDoSistema();
    	atualizaUsuarioLogado();
	}
	
	@Transactional
	private static void atualizaUsuarioLogado() {
		Pessoa usuarioLogado = getDao().findByEntityId(Pessoa.class, 
				Long.valueOf(session("user")));
    	sistema.setUsuarioLogado(usuarioLogado);
	}
}
