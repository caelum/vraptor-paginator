package br.com.caelum.vraptor.paginator.jpa;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.vraptor.paginator.Pager;
import br.com.caelum.vraptor.paginator.PagerDAOTest;
import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.User;
import br.com.caelum.vraptor.paginator.orm.jpa.JPAPager;
import br.com.caelum.vraptor.paginator.orm.jpa.JPAPaginatedQuery;
import br.com.caelum.vraptor.paginator.view.Page;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class JPAPagerTest extends PagerDAOTest {
	public Pager<JPAPaginatedQuery> getPager() {
		JPAPager dao = new JPAPager(manager);
		return dao;
	}

	@Override
	protected JPAPaginatedQuery query(String alias, final String query) {
		return new JPAPaginatedQuery(manager, (JPAPager) getPager()).jpql(query);
	}

	@Override
	protected List<User> get(Integer... ids) {
		return manager.createQuery("from User where id in :ids order by id").setParameter("ids", Arrays.asList(ids))
				.getResultList();
	}

	@Test
	public void should_pick_last_page_as_query_with_conditions() {
		Paginator<Object> results = query(null, "select u from User u where id > :id").setParameter("id", 75).paginate(
				new Page(3, 10));
		assertEquals("1,2,3,", results.getPages().toContent());
		assertEquals(3, results.getCurrentPage().getNumber());
		assertEquals(get(96, 97, 98, 99, 100), results.getVisibleItems());
	}
}
