package br.com.caelum.vraptor.paginator.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import br.com.caelum.vraptor.paginator.Pager;
import br.com.caelum.vraptor.paginator.PagerDAOTest;
import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.Pair;
import br.com.caelum.vraptor.paginator.Querier;
import br.com.caelum.vraptor.paginator.User;
import br.com.caelum.vraptor.paginator.view.Page;

@SuppressWarnings("unchecked")
public class JPAPagerTest extends PagerDAOTest {
	public Pager<Querier<Pair<Query>>> getPager() {
		JPAPager dao = new JPAPager(manager);
		return dao;
	}
	
	@Override
	protected Querier<Pair<Query>> query(String alias,final String query) {
		Querier<Pair<Query>> querier = new Querier<Pair<Query>>(alias) {

			@Override
			public Pair<Query> statementWith(String select) {				
				return new Pair<Query>(manager.createQuery(query),query);
			}
		};
		return querier;
	}

	@Override
	protected List<User> get(Integer... ids) {
		return manager.createQuery("from User where id in :ids order by id").setParameter("ids", Arrays.asList(ids)).getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void should_pick_last_page_as_query_with_conditions() {
		Pager dao = getPager();
		Querier querier = new Querier<Pair<Query>>("u") {

			@Override
			public Pair<Query> statementWith(String select) {
				return new Pair<Query>(manager.createQuery("select u from User u where id > :id").setParameter("id",0), "select u from User u where id > :id");
			}
		};
		Paginator<User> results = dao.paginate(querier, new Page(4, 30));
		assertEquals("1,2,3,4," , results.getPages().toContent());
		assertEquals(4, results.getCurrentPage().getNumber());
		assertEquals(get(91,92,93,94,95,96,97,98,99,100), results.getVisibleItems());
	}	
}
