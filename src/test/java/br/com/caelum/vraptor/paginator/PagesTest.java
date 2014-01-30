package br.com.caelum.vraptor.paginator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.vraptor.paginator.view.Page;
import br.com.caelum.vraptor.paginator.view.Pages;

public class PagesTest {

	@Test
	public void should_work_with_2_pages() {
		Pages roller = new Pages(new Page().number(1), 2);
		assertEquals("1,2,", roller.toContent());

		roller = new Pages(new Page().number(2), 2);
		assertEquals("1,2,", roller.toContent());
	}
	
	@Test
	public void should_work_with_1_page() {
		Pages roller = new Pages(new Page().number(1), 1);
		assertEquals("1,", roller.toContent());
	}
	
	@Test
	public void should_show_first_three_if_at_first_page() {
		for (int startingPage = 1; startingPage < 3; startingPage++) {
			Pages roller = new Pages(new Page().number(startingPage), 3);
			assertEquals("1,2,3,", roller.toContent());
		}
	}

	@Test
	public void should_show_three_dots_if_does_not_get_to_the_end() {
		Pages roller = new Pages(new Page().number(1), 6);
		assertEquals("1,2,3,...,6,", roller.toContent());
		
		roller = new Pages(new Page().number(2), 6);
		assertEquals("1,2,3,4,...,6,", roller.toContent());
		
		roller = new Pages(new Page().number(3), 6);
		assertEquals("1,2,3,4,5,6,", roller.toContent());

		roller = new Pages(new Page().number(4), 6);
		assertEquals("1,2,3,4,5,6,", roller.toContent());

		roller = new Pages(new Page().number(5), 6);
		assertEquals("1,...,3,4,5,6,", roller.toContent());

		roller = new Pages(new Page().number(6), 6);
		assertEquals("1,...,4,5,6,", roller.toContent());
	}

	@Test
	public void should_show_everything() {
		Pages roller = new Pages(new Page().number(1), 10);
		assertEquals("1,2,3,...,10,", roller.toContent());

		roller = new Pages(new Page().number(2), 10);
		assertEquals("1,2,3,4,...,10,", roller.toContent());

		roller = new Pages(new Page().number(3), 10);
		assertEquals("1,2,3,4,5,...,10,", roller.toContent());

		roller = new Pages(new Page().number(4), 10);
		assertEquals("1,2,3,4,5,6,...,10,", roller.toContent());

		roller = new Pages(new Page().number(5), 10);
		assertEquals("1,...,3,4,5,6,7,...,10,", roller.toContent());

		roller = new Pages(new Page().number(6), 10);
		assertEquals("1,...,4,5,6,7,8,...,10,", roller.toContent());

		roller = new Pages(new Page().number(7), 10);
		assertEquals("1,...,5,6,7,8,9,10,", roller.toContent());

		roller = new Pages(new Page().number(8), 10);
		assertEquals("1,...,6,7,8,9,10,", roller.toContent());

		roller = new Pages(new Page().number(9), 10);
		assertEquals("1,...,7,8,9,10,", roller.toContent());

		roller = new Pages(new Page().number(10), 10);
		assertEquals("1,...,8,9,10,", roller.toContent());


	}
	
	@Test
	public void should_handle_variable_window_sizes(){

		Pages roller = new Pages(new Page().number(5), 10, 3);
		assertEquals("1,2,3,4,5,6,7,8,...,10,", roller.toContent());

		roller = new Pages(new Page().number(5), 10, 4);
		assertEquals("1,2,3,4,5,6,7,8,9,10,", roller.toContent());

		roller = new Pages(new Page().number(1), 10, 9);
		assertEquals("1,2,3,4,5,6,7,8,9,10,", roller.toContent());

		roller = new Pages(new Page().number(1), 10, 999999);
		assertEquals("1,2,3,4,5,6,7,8,9,10,", roller.toContent());
}

}
