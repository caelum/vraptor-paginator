package br.com.caelum.vraptor.paginator.hibernate;

import java.util.Iterator;
import java.util.List;

import br.com.caelum.vraptor.paginator.view.Page;
import br.com.caelum.vraptor.paginator.view.Pages;

public class NewPaginatedResult<T> implements Iterable<T> {

	private final Page currentPage;
	private final int totalPages;
	private final List<T> results;

	public NewPaginatedResult(List<T> results, int totalPages, Page currentPage) {
		this.results = results;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}

	@Override
	public Iterator<T> iterator() {
		return results.iterator();
	}
	
	public List<T> getResults() {
		return results;
	}

	public Page getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}
	
	public boolean hasPages() {
		return totalPages >=1;
	}
	
	public Pages getRoller() {
		return new Pages(currentPage, totalPages);
	}

	public Pages getRoller(int window) {
		return new Pages(currentPage, totalPages, window);
	}

}
