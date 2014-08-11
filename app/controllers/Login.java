package controllers;

import java.util.List;

import models.Pessoa;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

public class Login extends Controller {

	private static Form<Pessoa> loginForm = Form.form(Pessoa.class);
	private static GenericDAO dao = new GenericDAOImpl();
	
	@Transactional
	public static Result logout() {
		session().clear();
		return redirect(routes.Login.show());
	}
	
	@Transactional
    public static Result show() {
		if (session().get("user") != null) {
			return redirect(routes.Application.logar());
		}
        return ok(views.html.login.render(getLoginForm()));
    }
	
	@Transactional
	public static Result authenticate() {
		Pessoa pessoa = getLoginForm().bindFromRequest().get();
		Pessoa user = userRegistered(pessoa);
		if (user==null) {
			flash("fail", "Email ou Senha Inválidos");
			return badRequest(login.render(getLoginForm()));
		}
		session().clear();
		session("user", user.getId().toString());
		return redirect(routes.Application.logar());
	}
	
	@Transactional
	private static Pessoa userRegistered(Pessoa pessoa) {
		List<Pessoa> pessoas = getDao().findAllByClassName("Pessoa");
		for (Pessoa usuario: pessoas) {
			if (usuario.equals(pessoa)) {
				return usuario;
			}
		}
		return null;
	}
	
	private static GenericDAO getDao() {
		return dao;
	}
	
	private static Form<Pessoa> getLoginForm() {
		return loginForm;
	}
}
