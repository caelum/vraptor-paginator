package br.com.caelum.vraptor.paginator.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import br.com.caelum.vraptor.paginator.Pager;
import br.com.caelum.vraptor.paginator.PagerDAOTest;
import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.User;
import br.com.caelum.vraptor.paginator.orm.jpa.JPAPager;
import br.com.caelum.vraptor.paginator.orm.jpa.PaginatedManagerProducer;
import br.com.caelum.vraptor.paginator.orm.jpa.PaginatedQuery;
import br.com.caelum.vraptor.paginator.view.Page;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class JPAPagerTest extends PagerDAOTest {
	public Pager<Query> getPager() {
		JPAPager dao = new JPAPager(manager);
		return dao;
	}
	
	@Override
	protected PaginatedQuery query(String alias,final String query) {
		return new PaginatedManagerProducer(manager).get(query);
	}

	@Override
	protected List<User> get(Integer... ids) {
		return manager.createQuery("from User where id in :ids order by id").setParameter("ids", Arrays.asList(ids)).getResultList();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldAcceptOnlyPaginatedQuery(){
		getPager().paginate(manager.createQuery("select u from User u"), null);		
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void should_pick_last_page_as_query_with_conditions() {
		Pager dao = getPager();
		Query query = new PaginatedManagerProducer(manager).get(
				"select u from User u where id > :id").setParameter("id", 75);
		Paginator<User> results = dao.paginate(query, new Page(3, 10));
		assertEquals("1,2,3," , results.getPages().toContent());
		assertEquals(3, results.getCurrentPage().getNumber());
		assertEquals(get(96,97,98,99,100), results.getVisibleItems());
	}	
}
