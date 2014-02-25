package br.com.caelum.vraptor.paginator;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.vraptor.paginator.view.Page;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"unchecked","rawtypes"})
public abstract class PagerDAOTest extends JPADatabaseTest {

	static final Page FIRST = new Page();

	@Test
	public void should_pick_first_page() {
		Pager<?> dao = getPager();
		Paginator<User> results = dao.paginate(User.class, FIRST);
		assertEquals("1,2,3,...,10," , results.getPages().toContent());
		assertEquals(1, results.getCurrentPage().getNumber());
		assertEquals(get(1,2,3,4,5,6,7,8,9,10), results.getVisibleItems());
	}

	protected abstract Pager<?> getPager();
	
	protected abstract <QueryType> QueryType query(String alias,String query);
	
	@Test
	public void should_pick_fifth_page() {
		Pager<?> dao = getPager();
		Paginator<User> results = dao.paginate(User.class, new Page(5,10));
		assertEquals("1,...,3,4,5,6,7,...,10," , results.getPages().toContent());
		assertEquals(5, results.getCurrentPage().getNumber());
		assertEquals(get(41,42,43,44,45,46,47,48,49,50), results.getVisibleItems());
	}

	@Test
	public void should_pick_middle_page() {
		Pager<?> dao = getPager();
		Paginator<User> results = dao.paginate(User.class, new Page(3,20));
		assertEquals("1,2,3,4,5," , results.getPages().toContent());
		assertEquals(3, results.getCurrentPage().getNumber());
		assertEquals(get(41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60), results.getVisibleItems());
	}

	@Test
	public void should_pick_first_page_as_query() {
		Pager dao = getPager();
		Paginator<User> results = dao.paginate(query("user","from User user"), FIRST);
		assertEquals("1,2,3,...,10," , results.getPages().toContent());
		assertEquals(1, results.getCurrentPage().getNumber());
		assertEquals(get(1,2,3,4,5,6,7,8,9,10), results.getVisibleItems());
	}

	@Test
	public void should_pick_last_page_as_query() {
		Pager dao = getPager();
		Paginator<User> results = dao.paginate(query("user","from User user"), new Page(4, 30));
		assertEquals("1,2,3,4," , results.getPages().toContent());
		assertEquals(4, results.getCurrentPage().getNumber());
		assertEquals(get(91,92,93,94,95,96,97,98,99,100), results.getVisibleItems());
	}

	@Test
	public void should_pick_first_page_as_query_with_select() {
		Pager dao = getPager();
		Paginator<User> results = dao.paginate(query("u","select u from User as u"), FIRST);
		assertEquals("1,2,3,...,10," , results.getPages().toContent());
		assertEquals(1, results.getCurrentPage().getNumber());
		assertEquals(get(1,2,3,4,5,6,7,8,9,10), results.getVisibleItems());
	}

	@Test
	public void should_pick_first_page_as_query_with_select_id() {
		Pager dao = getPager();
		Paginator<Integer> results = dao.paginate(query("u","select u.id from User as u"), FIRST);
		assertEquals("1,2,3,...,10," , results.getPages().toContent());
		assertEquals(1, results.getCurrentPage().getNumber());
		assertEquals(Arrays.asList(1,2,3,4,5,6,7,8,9,10), results.getVisibleItems());
	}

	@Test
	public void should_pick_first_page_as_query_with_select_two_fields() {
		Pager dao = getPager();
		Paginator<Integer> results = dao.paginate(query("u","select u.id,u.name from User as u"), FIRST);
		assertEquals("1,2,3,...,10," , results.getPages().toContent());
		assertEquals(1, results.getCurrentPage().getNumber());
	}

	protected abstract List<User> get(Integer... ids);

}
