package br.com.caelum.vraptor.paginator.jpa;

import org.junit.Test;

import br.com.caelum.vraptor.paginator.JPADatabaseTest;

import static org.junit.Assert.assertEquals;

public class PaginatedManagerProducerTest extends JPADatabaseTest{

	@Test
	public void shouldGetRawQuery() throws Exception {
		PaginatedQuery paginatedQuery = (PaginatedQuery) new PaginatedManagerProducer(manager).get(
				"select u from User u where id > :id").setParameter("id", 75);
		assertEquals("select u from User u where id > :id",paginatedQuery.raw());
	}
}
