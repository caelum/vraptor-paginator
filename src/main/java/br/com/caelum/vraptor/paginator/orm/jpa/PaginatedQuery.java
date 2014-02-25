package br.com.caelum.vraptor.paginator.orm.jpa;

import javax.persistence.Query;

public interface PaginatedQuery extends Query {

	/**
	 * 
	 * @return original jpql
	 */
	String raw();

}
