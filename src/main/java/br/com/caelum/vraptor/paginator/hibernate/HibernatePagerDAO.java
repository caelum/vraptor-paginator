package br.com.caelum.vraptor.paginator.hibernate;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import br.com.caelum.vraptor.paginator.PagerDAO;
import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.view.Page;

@RequestScoped
@SuppressWarnings("unchecked")
public class HibernatePagerDAO implements PagerDAO{
	
	
	private final Session session;

	@Inject
	public HibernatePagerDAO(Session session) {
		this.session = session;
	}
	
	/**
	 * @deprecated do not use, CDI only
	 */
	HibernatePagerDAO() {
		this(null);
	}

	@Override
	public <T> Paginator<T> paginate(Class<T> type, Page currentPage) {
		List<T> partialList = session.createCriteria(type).setFirstResult(currentPage.getStartingElement()).setMaxResults(currentPage.getElements()).list();
		Number count = (Number) session.createCriteria(type).setProjection(Projections.rowCount()).uniqueResult();
		int pageCount = countToPageCount(count, currentPage);
		return new Paginator<T>(partialList, currentPage, pageCount);
	}

	@Override
	public <T> Paginator<T> paginate(Query query,
			Page currentPage) {
		String queryString = query.getQueryString();
		String countString;
		if(queryString.startsWith("select ")) {
			int fromPosition = queryString.indexOf("from");
			countString = "select count(*) " + queryString.substring(fromPosition);
		} else {
			countString = "select count(*) " + queryString;
		}
		
		List<T> partialList = query
					.setMaxResults(currentPage.getElements())
					.setFirstResult(currentPage.getStartingElement())
					.list();
		
		Number count = (Number) session.createQuery(countString).uniqueResult();

		int pageCount = countToPageCount(count, currentPage);
		return new Paginator<T>(partialList, currentPage, pageCount);
	}
	
	private int countToPageCount(Number elementsCount, Page currentPage) {
		int pageCount = (int) Math.ceil((elementsCount.doubleValue()) / currentPage.getElements());
		return pageCount;
	}

}