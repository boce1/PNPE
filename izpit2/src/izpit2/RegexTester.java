package izpit2;

import java.util.*;

public class RegexTester {
	public static List<Boolean> test(Regex regex, String[] strings) {
		List<Boolean> output = new ArrayList<Boolean>();

		for(String s: strings) {
			output.add(regex.getPattern().matcher(s).matches());
		}

		return output;
	}
}