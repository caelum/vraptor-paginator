package br.com.caelum.vraptor.paginator;

import java.util.Collection;

public class Paginator<T> {

	private Collection<T> items;

	public Paginator(Collection<T> items,Page currentPage, int totalPages) {
		this.items = items;
	}	
	
	public Collection<T> getVisibleItems() {
		return items;
	}
	
}
