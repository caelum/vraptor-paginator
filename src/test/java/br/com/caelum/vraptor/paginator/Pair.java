package br.com.caelum.vraptor.paginator;


public class Pair<T> {

	private T query;
	private String rawQuery;

	public Pair(T queryObject, String rawQuery) {
		this.query = queryObject;
		this.rawQuery = rawQuery;
	}
	
	public T getQuery() {
		return query;
	}
	
	public String getRawQuery() {
		return rawQuery;
	}

	
}
