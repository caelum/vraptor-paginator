package br.com.caelum.vraptor.paginator.jpa;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.caelum.vraptor.paginator.Pager;
import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.view.Page;

@RequestScoped
@SuppressWarnings("unchecked")
public class JPAPager implements Pager<Query>{

	private final EntityManager manager;

	public JPAPager(EntityManager session) {
		this.manager = session;
	}

	/**
	 * @deprecated do not use, CDI only
	 */
	JPAPager() {
		this(null);
	}

	@Override
	public <T> Paginator<T> paginate(Class<T> type, Page currentPage) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(type);
		criteria.from(type);
		
		List<T> partialList = manager.createQuery(criteria).setFirstResult(currentPage.getStartingElement())
				.setMaxResults(currentPage.getElements()).getResultList();
		
		CriteriaQuery<Number> countCriteria = criteriaBuilder.createQuery(Number.class);
		Root<T> countRoot = countCriteria.from(type);
		countCriteria.select(criteriaBuilder.count(countRoot));
		TypedQuery<Number> countQuery = manager.createQuery(countCriteria);
		int pageCount = countToPageCount(countQuery.getSingleResult(), currentPage);
		
		return new Paginator<T>(partialList, currentPage, pageCount);
	}

	@Override
	public <T> Paginator<T> paginate(Query query, Page currentPage) {
		if(!(query instanceof PaginatedQuery)){
			throw new IllegalArgumentException("Query parameter must implement PaginatedQuery. Use PaginatedManagerProducer to produces this object");
		}
		PaginatedQuery paginatedQuery = (PaginatedQuery) query;
		String queryString = paginatedQuery.raw();
		String countString = "select count(*) " + queryString;
		if (queryString.toLowerCase().startsWith("select ")) {
			int fromPosition = queryString.toLowerCase().indexOf("from");
			countString = "select count(*) " + queryString.substring(fromPosition);
		} 
					
		List<T> partialList = query.setMaxResults(currentPage.getElements())
				.setFirstResult(currentPage.getStartingElement()).getResultList();

		Query countQuery = manager.createQuery(countString);
		for(Parameter<?> p : query.getParameters()){
			countQuery.setParameter(p.getName(),query.getParameterValue(p.getName()));
		}
		
		
		Number count = (Number) countQuery.getSingleResult();
		int pageCount = countToPageCount(count, currentPage);
		return new Paginator<T>(partialList, currentPage, pageCount);
	}

	private int countToPageCount(Number elementsCount, Page currentPage) {
		int pageCount = (int) Math.ceil((elementsCount.doubleValue()) / currentPage.getElements());
		return pageCount;
	}

}