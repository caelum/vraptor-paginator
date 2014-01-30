package br.com.caelum.vraptor.paginator.view;

public class Page {

	static final int DEFAULT_NUMBER_OF_ELEMENTS = 10;

	private final int number;
	private final int numberOfElements;

	public Page() {
		this(1, DEFAULT_NUMBER_OF_ELEMENTS);
	}
	
	public Page(Integer number) {
		this(number, DEFAULT_NUMBER_OF_ELEMENTS);
	}

	public Page(Integer number, Integer numberOfElements) {
		if(number==null) number= 1;
		this.number = number;
		if(numberOfElements==null) numberOfElements = DEFAULT_NUMBER_OF_ELEMENTS;
		this.numberOfElements = numberOfElements;
	}

	public Page number(int pageNumber) {
		return new Page(pageNumber, numberOfElements);
	}

	public Page with(int elements) {
		return new Page(number, elements);
	}

	public int getElements() {
		return numberOfElements;
	}

	public int getNumber() {
		return number;
	}

	public int getStartingElement() {
		return (number-1) * numberOfElements;
	}
	
	public int number() {
		return number;
	}
	
	public int elements() {
		return numberOfElements;
	}

}
