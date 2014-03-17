package br.com.caelum.vraptor.paginator.orm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountQueryProducer {

	private static Pattern aliasPattern = Pattern.compile("select \\S+\\s+from\\s+\\S+\\s+(as\\s+)?(\\S+).*?");

	public static String of(String original) {
		String countString = "select count(*) " + original;
		if (original.toLowerCase().startsWith("select ")) {
			Matcher matcher = aliasPattern.matcher(original);
			matcher.matches();
			String selectClause = matcher.group(2);
			System.out.println(selectClause);
			int fromPosition = original.toLowerCase().indexOf("from");
			countString = "select count(" + selectClause + ") " + original.substring(fromPosition);
		}
		return countString;
	}
}
