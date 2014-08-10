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
	static Form<Evento> eventoForm = Form.form(Evento.class);
	static Form<Pessoa> pessoaForm = Form.form(Pessoa.class);
	static Form<Local> localForm = Form.form(Local.class);
	private static GenericDAO dao = new GenericDAOImpl();

    public static Result index() {
        return ok(views.html.login.render(pessoaForm));
    }
    
    @Transactional
    public static Result logar() {
    	if (session().get("user") == null) {
    		return index();
    	}
    	if (getDao().findAllByClassName("Evento").isEmpty()){
    		System.out.println("CRIA EXEMPLOS");
    		Local local1;
    		Local local2;
    		Local local3;
    		
    		Evento evento1;
    		Evento evento2;
    		Evento evento3;
    		Evento evento4;
    		Evento evento5;
    		Evento evento6;
    		Evento evento7;
    		Evento evento8;
    		Evento evento9;
    		Evento evento10;
    		
    		String tema1;
    		String tema2;
    		String tema3;
    		String tema4;
    		String tema5;
    		
    		local1 = new Local("Centro de Convencões", "Rua Brasília, 48", 50);
    		local2 = new Local("Escola Hackfest", "Rua São Paulo, 99", 40);
    		local3 = new Local("UFCG", "Bodocongó", 50);
    		
    		Pessoa administrador = new Pessoa("Jose", "jose@gmail.com");
    		administrador.setSenha("password");
    		
    		evento1 = new Evento("Dados abertos", "Esse evento tem o objetivo de realizar atividades com dados abertos", "11/09/2014", administrador, local1);
    		evento2 = new Evento("HTML", "Esse evento tem o objetivo de realizar atividades com HTML", "09/09/2014", administrador, local2);
    		evento3 = new Evento("Computacao Desplugada", "Esse evento tem o objetivo de realizar atividades com computacao desplugada", "11/06/2014", administrador, local3);
    		evento4 = new Evento("Metodos Formais", "Esse evento tem o objetivo de realizar atividades com metodos formais", "18/06/2014", administrador, local1);
    		evento5 = new Evento("Banco de Dados Gerenciais", "Esse evento tem o objetivo de realizar atividades com banco de dados gerenciais", "11/09/2014", administrador, local2);
    		evento6 = new Evento("Banco de Dados Sequenciais", "Esse evento tem o objetivo de realizar atividades com banco de dados sequenciais", "14/09/2014", administrador, local3);
    		evento7 = new Evento("Sites Dinamicos", "Esse evento tem o objetivo de realizar atividades com sites dinamicos", "17/08/2014", administrador, local1);
    		evento8 = new Evento("Aplicações Java", "Esse evento tem o objetivo de realizar atividades com aplicacoes java", "01/09/2014", administrador, local2);
    		evento9 = new Evento("Estruturas de dados", "Esse evento tem o objetivo de realizar atividades com estruturas de dados", "12/07/2014", administrador, local3);
    		evento10 = new Evento("Binarios", "Esse evento tem o objetivo de realizar atividades com binarios sem a utilizacao de computador", "11/09/2014", administrador, local1);
    		
    		tema1 = "Engenharia de Software";
    		tema2 = "Sistemas da Informacao";
    		tema3 = "Banco de Dados";
    		tema4 = "Computacao Desplugada";
    		tema5 = "Desenvolvimento para Web";
    		
    		// 5 eventos no tema 1
    		evento1.addTema(tema1);
    		evento4.addTema(tema1);
    		evento8.addTema(tema1);
    		evento9.addTema(tema1);
    		evento10.addTema(tema1);
    		
    		// 2 eventos no tema 2
    		evento4.addTema(tema2);
    		evento10.addTema(tema2);
    		
    		// 4 eventos no tema 3
    		evento2.addTema(tema3);		
    		evento4.addTema(tema3);
    		evento5.addTema(tema3);
    		evento6.addTema(tema3);
    		
    		// 4 eventos no tema 4
    		evento1.addTema(tema4);
    		evento3.addTema(tema4);
    		evento4.addTema(tema4);
    		evento10.addTema(tema4);
    		
    		// 3 eventos no tema 5
    		evento1.addTema(tema5);
    		evento2.addTema(tema5);
    		evento7.addTema(tema5);	
    		
    		getDao().persist(administrador);
    		
    		getDao().persist(local1);
    		getDao().persist(local2);
    		getDao().persist(local3);
    		
    		getDao().persist(evento1);
    		getDao().persist(evento2);
    		getDao().persist(evento3);
    		getDao().persist(evento4);
    		getDao().persist(evento5);
    		getDao().persist(evento6);
    		getDao().persist(evento7);
    		getDao().persist(evento8);
    		getDao().persist(evento9);
    		getDao().persist(evento10);
    		getDao().flush();
    		
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
				"hasNewLocal");
		List<Local> locais = getDao().findAllByClassName("Local");
		
		Evento evento = filledForm.get();
		
		evento.setAdministrador(getDao().findByEntityId(Pessoa.class, 
				Long.valueOf(session("user"))));
		if(!evento.isHasNewLocal()) {
			evento.setLocal(getDao().findByEntityId(Local.class, 
				evento.getLocalId()));
		}
		else {
			Form<Local> localFormFilled = localForm.bindFromRequest("nomeLocal",
					"explicacao", "capacidade");
			Local local = localFormFilled.get();
			getDao().persist(local);
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
		evento.addParticipanteNoEvento(pessoa);
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
