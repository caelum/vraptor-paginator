package br.com.caelum.vraptor.paginator.view;

public class ThreeDotsDefinition implements PageDefinition {

	@Override
	public String getContent() {
		return "...";
	}

	@Override
	public String getClasses() {
		return "pagination-three-dots";
	}

	@Override
	public boolean hasLink() {
		return false;
	}

}
