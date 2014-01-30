package br.com.caelum.vraptor.paginator.hibernate;

import java.util.List;

import br.com.caelum.vraptor.paginator.view.Page;

public class Pager {

	@SuppressWarnings("unchecked")
	public <T> NewPaginatedResult<T> paginate(Querier<T> querier, String name, Page page) {
		Long count = (Long) querier.query("select count(" + name + ") ").uniqueResult();
		int startIndex = page.getStartingElement();
		List<T> partialList = querier.query("select " + name + " ")
					.setMaxResults(page.getElements())
					.setFirstResult(startIndex)
					.list();

		int pageCount = (int) Math.ceil((count+0.0) / page.getElements());
		return new NewPaginatedResult<T>(partialList, pageCount, page);
	}

}
