package br.com.caelum.vraptor.paginator.view;

import java.util.Iterator;
import java.util.List;

public class Pages implements Iterator<PageDefinition> {

	private final Page currentShownPage;
	private final Iterator<Integer> iterator;

	public Pages(Page currentPage, int totalPages) {
		this(currentPage, totalPages, WINDOW_SIZE);
	}

	public Pages(Page currentPage, int totalPages, int windowSize) {
		this.currentShownPage = currentPage;
		List<Integer> pages = PageCalculator.getPages(currentPage, totalPages, windowSize);
		this.iterator = pages.iterator();
	}

	/**
	 * Number of elements that appear before and after the current page.
	 */
	private static final int WINDOW_SIZE = 2;
	private static final PageDefinition THREE_DOTS = new ThreeDotsDefinition();

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@Override
	public PageDefinition next() {
		Integer next = iterator.next();
		if (next == PageCalculator.THREE_DOTS)
			return THREE_DOTS;
		if(next == currentShownPage.getNumber())
			return new CurrentPage(next);
		return new LinkedPageDefinition(next);
	}

	@Override
	public void remove() {
		throw new IllegalStateException(
				"Can not remove a page from the roller iterator");
	}

	public String toContent() {
		StringBuilder builder = new StringBuilder();
		while (hasNext()) {
			builder.append(next().getContent() + ",");
		}
		return builder.toString();
	}

}
