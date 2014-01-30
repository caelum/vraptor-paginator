package br.com.caelum.vraptor.paginator.view;

public class LinkedPageDefinition implements PageDefinition {

	private final String content;

	public LinkedPageDefinition(int page) {
		this.content = "" + page;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public String getClasses() {
		return "pagination-page pagination-linked";
	}
	
	@Override
	public boolean hasLink() {
		return true;
	}
}
