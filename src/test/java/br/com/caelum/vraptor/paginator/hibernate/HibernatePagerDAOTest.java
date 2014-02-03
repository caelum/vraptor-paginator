package br.com.caelum.vraptor.paginator.hibernate;

import br.com.caelum.vraptor.paginator.PagerDAO;
import br.com.caelum.vraptor.paginator.PagerDAOTest;

public class HibernatePagerDAOTest extends PagerDAOTest {
	public PagerDAO getPager() {
		HibernatePagerDAO dao = new HibernatePagerDAO(session);
		return dao;
	}
}
