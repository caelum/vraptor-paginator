package br.com.caelum.vraptor.paginator;

public abstract class Querier<QueryType> {

	private final String prefix;

	public Querier(String prefix) {
		this.prefix = prefix;
		
	}

	public abstract QueryType statementWith(String select);

	public QueryType execute() {
		return statementWith(prefix);
	}
	
	public String getPrefix() {
		return prefix;
	}

}
