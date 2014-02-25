package br.com.caelum.vraptor.paginator.hibernate;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.TypedValue;
import org.hibernate.internal.PagerQueryImpl;
import org.hibernate.internal.QueryImpl;

import br.com.caelum.vraptor.paginator.Pager;
import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.view.Page;

@RequestScoped
@SuppressWarnings("unchecked")
public class HibernatePager implements Pager<Query> {

	private final Session session;

	@Inject
	public HibernatePager(Session session) {
		this.session = session;
	}

	/**
	 * @deprecated do not use, CDI only
	 */
	HibernatePager() {
		this(null);
	}

	@Override
	public <T> Paginator<T> paginate(Class<T> type, Page currentPage) {
		List<T> partialList = session.createCriteria(type).setFirstResult(currentPage.getStartingElement())
				.setMaxResults(currentPage.getElements()).list();
		Number count = (Number) session.createCriteria(type).setProjection(Projections.rowCount()).uniqueResult();
		int pageCount = countToPageCount(count, currentPage);
		return new Paginator<T>(partialList, currentPage, pageCount);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T> Paginator<T> paginate(Query query, Page currentPage) {		
		String queryString = query.getQueryString();
		String countString = "select count(*) " + queryString;
		if (queryString.toLowerCase().startsWith("select ")) {
			int fromPosition = queryString.toLowerCase().indexOf("from");
			countString = "select count(*) " + queryString.substring(fromPosition);
		}
		
		List<T> partialList = query.setMaxResults(currentPage.getElements())
				.setFirstResult(currentPage.getStartingElement()).list();

		Query countQuery = session.createQuery(countString);
		PagerQueryImpl queryImpl = new PagerQueryImpl((SessionImplementor) session,(QueryImpl)query);
		Map<String, TypedValue> namedParams = queryImpl.getNamedParams();
		
		Set<Entry<String, TypedValue>> entrySet = namedParams.entrySet();
		for (Entry<String, TypedValue> entry : entrySet) {
			countQuery.setParameter(entry.getKey(),entry.getValue().getValue());
		}
		

		Number count = (Number) countQuery.uniqueResult();

		int pageCount = countToPageCount(count, currentPage);
		return new Paginator<T>(partialList, currentPage, pageCount);
	}

	private int countToPageCount(Number elementsCount, Page currentPage) {
		int pageCount = (int) Math.ceil((elementsCount.doubleValue()) / currentPage.getElements());
		return pageCount;
	}
	

}