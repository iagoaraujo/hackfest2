package controllers;

import java.util.ArrayList;
import java.util.List;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.F;
import models.Evento;
import models.Local;
import models.Pessoa;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import models.strategy.ETipoEvento;

public class GeradorExemplos {

	private static GenericDAO dao = new GenericDAOImpl(); 
	private static final int CAPACIDADE_LOCAL1 = 14;
	private static final int CAPACIDADE_LOCAL2 = 40;
	private static final int CAPACIDADE_LOCAL3 = 50;
	
	private static final int QUANTIDADE_INSCRICOES_MAIS_EXPERIENTES = 7;
	private static final int QUANTIDADE_INSCRICOES_INTERMEDIARIO = 6;
	private static final int QUANTIDADE_INSCRICOES_MENOS_EXPERIENTES = 3;

	private static Pessoa usuario1;
	private static Pessoa usuario2;
	private static Pessoa usuario3;
	private static Pessoa usuario4;
	private static Pessoa usuario5;
	private static Pessoa usuario6;
	private static Pessoa usuario7;
	private static Pessoa usuario8;
	private static Pessoa usuario9;
	private static Pessoa usuario10;
	private static Pessoa usuario11;
	private static Pessoa usuario12;
	private static Pessoa usuario13;
	private static Pessoa usuario14;
	private static Pessoa usuario15;
	private static Pessoa administrador;

	private static Local local1;
	private static Local local2;
	private static Local local3;

	private static Evento evento1;
	private static Evento evento2;
	private static Evento evento3;
	private static Evento evento4;
	private static Evento evento5;
	private static Evento evento6;
	private static Evento evento7;

	private static String tema1;
	private static String tema2;
	private static String tema3;
	private static String tema4;
	private static String tema5;
	
	@Transactional
	public static void gera() {
		JPA.withTransaction(new F.Callback0() {
			@Override
			public void invoke() throws Throwable {
					usuario1 = new Pessoa("jose", "jose@jose", "password");
					usuario2 = new Pessoa("maria", "maria@maria", "password");
					usuario3 = new Pessoa("joao", "joao@joao", "password");
					usuario4 = new Pessoa("lucas", "lucas@lucas", "password");
					usuario5 = new Pessoa("pedro", "pedro@pedro", "password");
					usuario6 = new Pessoa("carlos", "carlos@carlos", "password");
					usuario7 = new Pessoa("nazareno", "nazareno@nazareno", "password");
					usuario8 = new Pessoa("arthur", "arthur@arthur", "password");
					usuario9 = new Pessoa("iago", "iago@iago", "password");
					usuario10 = new Pessoa("marcos", "marcos@marcos", "password");
					usuario11 = new Pessoa("jessica", "jessica", "password");
					usuario12 = new Pessoa("bruno", "bruno@bruno", "password");
					usuario13 = new Pessoa("victor", "victor@victor", "password");
					usuario14 = new Pessoa("igor", "igor@igor", "password");
					usuario15 = new Pessoa("nara", "nara@nara", "password");

					usuario1.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MAIS_EXPERIENTES);
					usuario2.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MAIS_EXPERIENTES);
					usuario3.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MAIS_EXPERIENTES);
					usuario4.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MAIS_EXPERIENTES);
					usuario5.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MAIS_EXPERIENTES);
					usuario6.setNumEventosInscritos(QUANTIDADE_INSCRICOES_INTERMEDIARIO);
					usuario7.setNumEventosInscritos(QUANTIDADE_INSCRICOES_INTERMEDIARIO);
					usuario8.setNumEventosInscritos(QUANTIDADE_INSCRICOES_INTERMEDIARIO);
					usuario9.setNumEventosInscritos(QUANTIDADE_INSCRICOES_INTERMEDIARIO);
					usuario10.setNumEventosInscritos(QUANTIDADE_INSCRICOES_INTERMEDIARIO);
					usuario11.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MENOS_EXPERIENTES);
					usuario12.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MENOS_EXPERIENTES);
					usuario13.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MENOS_EXPERIENTES);
					usuario14.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MENOS_EXPERIENTES);
					usuario15.setNumEventosInscritos(QUANTIDADE_INSCRICOES_MENOS_EXPERIENTES);

					List<Pessoa> inscritosEvento1 = new ArrayList<Pessoa>();
					inscritosEvento1.add(usuario1);
					inscritosEvento1.add(usuario2);
					inscritosEvento1.add(usuario3);
					inscritosEvento1.add(usuario4);
					inscritosEvento1.add(usuario5);
					inscritosEvento1.add(usuario6);
					inscritosEvento1.add(usuario7);
					inscritosEvento1.add(usuario8);
					inscritosEvento1.add(usuario9);
					inscritosEvento1.add(usuario10);
					inscritosEvento1.add(usuario11);
					inscritosEvento1.add(usuario12);
					inscritosEvento1.add(usuario13);
					inscritosEvento1.add(usuario14);
					inscritosEvento1.add(usuario15);

					List<Pessoa> inscritosEvento2 = new ArrayList<Pessoa>();
					inscritosEvento2.add(usuario1);
					inscritosEvento2.add(usuario2);
					inscritosEvento2.add(usuario3);
					inscritosEvento2.add(usuario4);
					inscritosEvento2.add(usuario5);
					inscritosEvento2.add(usuario6);
					inscritosEvento2.add(usuario7);
					inscritosEvento2.add(usuario8);
					inscritosEvento2.add(usuario9);
					inscritosEvento2.add(usuario10);
					inscritosEvento2.add(usuario11);
					inscritosEvento2.add(usuario12);
					inscritosEvento2.add(usuario13);
					inscritosEvento2.add(usuario14);
					inscritosEvento2.add(usuario15);

					List<Pessoa> inscritosEvento3 = new ArrayList<Pessoa>();
					inscritosEvento3.add(usuario1);
					inscritosEvento3.add(usuario2);
					inscritosEvento3.add(usuario3);
					inscritosEvento3.add(usuario4);
					inscritosEvento3.add(usuario5);
					inscritosEvento3.add(usuario6);
					inscritosEvento3.add(usuario7);
					inscritosEvento3.add(usuario8);
					inscritosEvento3.add(usuario9);
					inscritosEvento3.add(usuario10);
					inscritosEvento3.add(usuario11);
					inscritosEvento3.add(usuario12);
					inscritosEvento3.add(usuario13);
					inscritosEvento3.add(usuario14);
					inscritosEvento3.add(usuario15);

					List<Pessoa> inscritosEvento4 = new ArrayList<Pessoa>();
					inscritosEvento4.add(usuario1);
					inscritosEvento4.add(usuario2);
					inscritosEvento4.add(usuario3);
					inscritosEvento4.add(usuario4);
					inscritosEvento4.add(usuario5);
					inscritosEvento4.add(usuario6);
					inscritosEvento4.add(usuario7);
					inscritosEvento4.add(usuario8);
					inscritosEvento4.add(usuario9);
					inscritosEvento4.add(usuario10);

					List<Pessoa> inscritosEvento5 = new ArrayList<Pessoa>();
					inscritosEvento5.add(usuario1);
					inscritosEvento5.add(usuario2);
					inscritosEvento5.add(usuario3);
					inscritosEvento5.add(usuario4);
					inscritosEvento5.add(usuario5);
					inscritosEvento5.add(usuario6);
					inscritosEvento5.add(usuario7);
					inscritosEvento5.add(usuario8);
					inscritosEvento5.add(usuario9);
					inscritosEvento5.add(usuario10);

					List<Pessoa> inscritosEvento6 = new ArrayList<Pessoa>();
					inscritosEvento6.add(usuario1);
					inscritosEvento6.add(usuario2);
					inscritosEvento6.add(usuario3);
					inscritosEvento6.add(usuario4);
					inscritosEvento6.add(usuario5);
					inscritosEvento6.add(usuario6);
					inscritosEvento6.add(usuario7);
					inscritosEvento6.add(usuario8);
					inscritosEvento6.add(usuario9);
					inscritosEvento6.add(usuario10);

					List<Pessoa> inscritosEvento7 = new ArrayList<Pessoa>();
					inscritosEvento7.add(usuario1);
					inscritosEvento7.add(usuario2);
					inscritosEvento7.add(usuario3);
					inscritosEvento7.add(usuario4);
					inscritosEvento7.add(usuario5);

					local1 = new Local("Escola Hackfest", "Rua Brasília, 48", 
							CAPACIDADE_LOCAL1);
					local2 = new Local("Centro de Convencões", "Rua São Paulo, 99", 
							CAPACIDADE_LOCAL2);
					local3 = new Local("UFCG", "Bodocongó", 
							CAPACIDADE_LOCAL3);

					administrador = new Pessoa("Jose", "jose@gmail.com", "password");

					evento1 = new Evento("Dados abertos", "Esse evento tem o objetivo de realizar atividades com dados abertos", "11/09/2014", administrador, local1);
					evento1.setTipoDeEvento(ETipoEvento.PRIORIDADE_EXPERIENTES.getNome());;
					evento1.setPessoasQueConfirmaram(inscritosEvento1);
					evento2 = new Evento("HTML", "Esse evento tem o objetivo de realizar atividades com HTML", "09/09/2014", administrador, local2);
					evento1.setTipoDeEvento(ETipoEvento.PRIORIDADE_EXPERIENTES.getNome());;
					evento2.setPessoasQueConfirmaram(inscritosEvento2);
					evento3 = new Evento("Computacao Desplugada", "Esse evento tem o objetivo de realizar atividades com computacao desplugada", "11/06/2014", administrador, local3);
					evento1.setTipoDeEvento(ETipoEvento.PRIORIDADE_EXPERIENTES.getNome());;
					evento3.setPessoasQueConfirmaram(inscritosEvento3);
					evento4 = new Evento("Metodos Formais", "Esse evento tem o objetivo de realizar atividades com metodos formais", "18/06/2014", administrador, local1);
					evento4.setPessoasQueConfirmaram(inscritosEvento4);
					evento5 = new Evento("Banco de Dados Gerenciais", "Esse evento tem o objetivo de realizar atividades com banco de dados gerenciais", "11/09/2014", administrador, local2);
					evento5.setPessoasQueConfirmaram(inscritosEvento5);
					evento6 = new Evento("Banco de Dados Sequenciais", "Esse evento tem o objetivo de realizar atividades com banco de dados sequenciais", "14/09/2014", administrador, local3);
					evento6.setPessoasQueConfirmaram(inscritosEvento6);
					evento7 = new Evento("Sites Dinamicos", "Esse evento tem o objetivo de realizar atividades com sites dinamicos", "17/08/2014", administrador, local1);
					evento7.setPessoasQueConfirmaram(inscritosEvento7);

					tema1 = "Engenharia de Software";
					tema2 = "Sistemas da Informacao";
					tema3 = "Banco de Dados";
					tema4 = "Computacao Desplugada";
					tema5 = "Desenvolvimento para Web";

					// 5 eventos no tema 1
					evento1.addTema(tema1);
					evento4.addTema(tema1);

					// 2 eventos no tema 2
					evento4.addTema(tema2);

					// 4 eventos no tema 3
					evento2.addTema(tema3);		
					evento4.addTema(tema3);
					evento5.addTema(tema3);
					evento6.addTema(tema3);

					// 4 eventos no tema 4
					evento1.addTema(tema4);
					evento3.addTema(tema4);
					evento4.addTema(tema4);

					// 3 eventos no tema 5
					evento1.addTema(tema5);
					evento2.addTema(tema5);
					evento7.addTema(tema5);	

					getDao().persist(administrador);
					getDao().persist(usuario1);
					getDao().persist(usuario2);
					getDao().persist(usuario3);
					getDao().persist(usuario4);
					getDao().persist(usuario5);
					getDao().persist(usuario6);
					getDao().persist(usuario7);
					getDao().persist(usuario8);
					getDao().persist(usuario9);
					getDao().persist(usuario10);
					getDao().persist(usuario11);
					getDao().persist(usuario12);
					getDao().persist(usuario13);
					getDao().persist(usuario14);
					getDao().persist(usuario15);

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
					getDao().flush();
				}
		});
	}
	
	private static GenericDAO getDao() {
		return dao;
	}
}
