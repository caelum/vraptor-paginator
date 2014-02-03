package br.com.caelum.vraptor.paginator;

import java.util.Collection;

import br.com.caelum.vraptor.paginator.view.Page;
import br.com.caelum.vraptor.paginator.view.Pages;

/**
 * Represents both the items and the paginator element.
 * 
 * @author alberto souza
 * @author guilherme silveira
 */
public class Paginator<T> {
	
	private final Collection<T> visibleItems;
	private final Page currentPage;
	private final int totalNumberOfPages;

	public Paginator(Collection<T> visibleItems, Page currentPage, int totalNumberOfPages) {
		this.visibleItems = visibleItems;
		this.currentPage = currentPage;
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public Collection<T> getVisibleItems() {
		return visibleItems;
	}
	
	public Pages getPages() {
		return new Pages(currentPage, totalNumberOfPages);
	}
	
	public Page getCurrentPage() {
		return currentPage;
	}

}
