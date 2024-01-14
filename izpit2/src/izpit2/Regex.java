package izpit2;

import java.util.*;
import java.util.regex.Pattern;

public class Regex {
	public static int nextId = 0;

	private int id;
	private String description;
	private Pattern pattern;
	private int rating;

	public Regex(Pattern pattern, String description) {
		setPattern(pattern);
		setDescription(description);
		setId(nextId++);
		setRating(0);
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}

	public Pattern getPattern() {
		return this.pattern;
	}
	public void setPattern(Pattern r) {
		this.pattern = r;
	}

	public int getRating() {
		return this.rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}