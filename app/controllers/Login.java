package controllers;

import models.Pessoa;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

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
			return redirect(routes.Application.index());
		}
        return redirect(routes.Application.index());
    }
	
}
