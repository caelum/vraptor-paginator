package br.com.caelum.vraptor.paginator.hibernate;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import br.com.caelum.vraptor.paginator.Pager;
import br.com.caelum.vraptor.paginator.PagerDAOTest;
import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.User;
import br.com.caelum.vraptor.paginator.view.Page;

@SuppressWarnings("unchecked")
public class HibernatePagerTest extends PagerDAOTest {
	public Pager<Query> getPager() {
		HibernatePager dao = new HibernatePager(manager.unwrap(Session.class));
		return dao;
	}

	@Override
	protected Query query(String alias,String query) {
		return  manager.unwrap(Session.class).createQuery(query);
	}

	@Override
	protected List<User> get(Integer... ids) {
		return manager.unwrap(Session.class).createQuery("from User where id in :ids order by id").setParameterList("ids", Arrays.asList(ids)).list();
	}

	protected <QueryType> QueryType query(String alias, QueryType query) {		
		return query;
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void should_pick_last_page_as_query_with_conditions() {
		Pager dao = getPager();
		Paginator<User> results = dao.paginate(query("user","from User user where id > :id").setParameter("id",75), new Page(3, 10));
		assertEquals("1,2,3," , results.getPages().toContent());
		assertEquals(3, results.getCurrentPage().getNumber());
		assertEquals(get(96,97,98,99,100), results.getVisibleItems());
	}
}
