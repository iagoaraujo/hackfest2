import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.session;
import static play.test.Helpers.start;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Evento;
import models.Pessoa;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.specs2.mock.Mockito;

import play.data.Form;
import play.db.jpa.JPA;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.Helpers;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

private Pessoa usuario;
private Result result;
private GenericDAO dao;
	
	@Before
	public void setUp() throws Exception{
		start(fakeApplication(inMemoryDatabase()));
		usuario = new Pessoa("Arthur", "arthur@gmail.com", 
    			"password");
		dao = new GenericDAOImpl();
	}

    @Test
    public void testCadastroDeUsuario() {
    	Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
    	    public void run() {
    	        JPA.withTransaction(new play.libs.F.Callback0() {
    	            @Override
    	            public void invoke() throws Throwable {
    	                result = criaUsuario(usuario);
    	                
    	                List<Pessoa> usuarios = getDAO().findAllByClassName("Pessoa");
    	                assertThat(usuarios.size()).isEqualTo(1);
    	                Assert.assertTrue(usuarios.contains(usuario));
    	            }
    	        });
    	    }
    	});
    }
    
    @Test
    public void testLogin() {
    	Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
    	    public void run() {
    	        JPA.withTransaction(new play.libs.F.Callback0() {
    	            @Override
    	            public void invoke() throws Throwable {
    	            	criaUsuario(usuario);
    	            	Map<String, String> formLogin = new HashMap<String, String>();
    	            	formLogin.put("nome", usuario.getNome());
    	            	formLogin.put("email", usuario.getEmail());
    	            	formLogin.put("senha", usuario.getSenha());
    	            	
    	                result = callAction(controllers.routes.ref.Login.authenticate(), 
    	                		fakeRequest().withFormUrlEncodedBody(formLogin));
    	                assertThat(!session(result).isEmpty());
    	                
    	                List<Pessoa> usuarios = getDAO().findAllByClassName("Pessoa");
    	                assertThat(session(result).get("user"))
    	                	.isEqualTo(usuarios.get(0).getId().toString());
    	            }
    	        });
    	    }
    	});
    }
    
    
    
    private Result criaUsuario(Pessoa usuario) {
    	Map<String, String> formData = new HashMap<String, String>();
    	formData.put("nome", usuario.getNome());
    	formData.put("email", usuario.getEmail());
    	formData.put("senha", usuario.getSenha());
    	
        return callAction(controllers.routes.ref.Application.cadastrarUsuario(), 
        		fakeRequest().withFormUrlEncodedBody(formData));
    }
    
    private GenericDAO getDAO() {
    	return dao;
    }
}
