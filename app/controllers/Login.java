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

	static Form<Pessoa> loginForm = Form.form(Pessoa.class);
	static GenericDAO dao = new GenericDAOImpl();
	
	@Transactional
	public static Result logout() {
		session().clear();
		return show();
	}
	
	@Transactional
    public static Result show() {
		if (session().get("user") != null) {
			return redirect(routes.Application.logar());
		}
        return ok(views.html.login.render(loginForm));
    }
	
	@Transactional
	public static Result authenticate() {
		Pessoa pessoa = loginForm.bindFromRequest().get();
		Pessoa user = userRegistered(pessoa);
		if (user==null) {
			flash("fail", "Email ou Senha Inválidos");
			return badRequest(login.render(loginForm));
		}
		session().clear();
		System.out.println("NOME : " + user.getNome());

		System.out.println("ID : " + user.getId());
		session("user", user.getId().toString());
		return redirect(routes.Application.logar());
	}
	
	@Transactional
	private static Pessoa userRegistered(Pessoa pessoa) {
		GenericDAO dao = new GenericDAOImpl();
		List<Pessoa> pessoas = dao.findAllByClassName("Pessoa");
		for (Pessoa usuario: pessoas) {
			if (usuario.equals(pessoa)) {
				return usuario;
			}
		}
		return null;
	}
}
