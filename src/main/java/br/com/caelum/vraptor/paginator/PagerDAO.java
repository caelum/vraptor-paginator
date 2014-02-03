package br.com.caelum.vraptor.paginator;

import org.hibernate.Query;

import br.com.caelum.vraptor.paginator.view.Page;

public interface PagerDAO {
	
	<T> Paginator<T> paginate(Class<T> type, Page currentPage);
	<T> Paginator<T> paginate(Query query, Page currentPage);

}
