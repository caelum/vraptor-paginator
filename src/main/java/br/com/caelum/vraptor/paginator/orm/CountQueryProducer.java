package br.com.caelum.vraptor.paginator.orm;

public class CountQueryProducer {

	public static String of(String original){
		String countString = "select count(*) " + original;
		if (original.toLowerCase().startsWith("select ")) {
			int fromPosition = original.toLowerCase().indexOf("from");
			countString = "select count(*) " + original.substring(fromPosition);
		}
		return countString;
	}
}
