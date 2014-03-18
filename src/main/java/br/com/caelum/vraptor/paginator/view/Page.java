package br.com.caelum.vraptor.paginator.view;

public class Page {

	public static final int DEFAULT_NUMBER_OF_ELEMENTS = 10;

	private int number;
	private int numberOfElements;

	public Page() {
		this(1, DEFAULT_NUMBER_OF_ELEMENTS);
	}
	
	public Page(Integer number) {
		this(number, DEFAULT_NUMBER_OF_ELEMENTS);
	}

	public Page(Integer number, Integer numberOfElements) {
		if(number==null || number<1) number= 1;
		this.number = number;
		if(numberOfElements==null || numberOfElements < 1) numberOfElements = DEFAULT_NUMBER_OF_ELEMENTS;
		this.numberOfElements = numberOfElements;
	}

	public Page setNumber(int pageNumber) {
		this.number = pageNumber;
		return this;
	}

	public Page setElements(int elements) {
		this.numberOfElements = elements;
		return this;
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
