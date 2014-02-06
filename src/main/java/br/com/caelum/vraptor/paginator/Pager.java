package br.com.caelum.vraptor.paginator;

import br.com.caelum.vraptor.paginator.view.Page;

public interface Pager<QueryType> {

	<T> Paginator<T> paginate(Class<T> type, Page currentPage);

	<T> Paginator<T> paginate(QueryType query, Page currentPage);

}