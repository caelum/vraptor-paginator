package br.com.caelum.vraptor.paginator.orm.jpa;

import javax.persistence.Query;

import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.view.Page;

public interface PaginatedQuery extends Query {

	/**
	 * 
	 * @return original jpql
	 */
	String raw();
	
	public <T> Paginator<T> paginate(Page page);

}
