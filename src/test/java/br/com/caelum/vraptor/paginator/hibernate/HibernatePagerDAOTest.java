package br.com.caelum.vraptor.paginator.hibernate;

import org.hibernate.Session;

import br.com.caelum.vraptor.paginator.PagerDAO;
import br.com.caelum.vraptor.paginator.PagerDAOTest;

public class HibernatePagerDAOTest extends PagerDAOTest {
	public PagerDAO getPager() {
		HibernatePagerDAO dao = new HibernatePagerDAO(manager.unwrap(Session.class));
		return dao;
	}
}
