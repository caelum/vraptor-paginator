package br.com.caelum.vraptor.paginator.view;

public class CurrentPage implements PageDefinition {

	private final String content;

	public CurrentPage(Integer next) {
		this.content = "" + next;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public String getClasses() {
		return "pagination-page pagination-current";
	}

	@Override
	public boolean hasLink() {
		return false;
	}

}
