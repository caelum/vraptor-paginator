package br.com.caelum.vraptor.paginator.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates which numbers should be displayed, adding a -1 when we need THREE_DOTS
 */
public class PageCalculator {

	static final int THREE_DOTS = -1;

	static List<Integer> getPages(Page currentPage, int totalPages,
			int windowSize) {
		List<Integer> pages = new ArrayList<>();
		pages.add(1);
		boolean doesNotShowNumber2 = currentPage.getNumber() - windowSize > 2;
		if (doesNotShowNumber2) {
			pages.add(THREE_DOTS);
		}
		
		int last = Math.min(currentPage.getNumber() + windowSize,
				totalPages - 1);
		int first = Math.max(currentPage.getNumber() - windowSize, 2);
		for (int next = first; next <= last; next++) {
			pages.add(next);
		}
		
		boolean needsToAddLastThreePoints = last < totalPages - 1;
		if (needsToAddLastThreePoints) {
			pages.add(THREE_DOTS);
		}
		if (totalPages > 1) {
			pages.add(totalPages);
		}
		
		return pages;
	}
}
