package izpit1;

import java.util.regex.Pattern;

public class UserFactory {
	public static Pattern facultyNumberChecker = Pattern.compile("\\d{9}");
	public static Pattern EGNChecker = Pattern.compile("\\d{10}");
	public static Pattern emailChecker = Pattern.compile("[a-z]+@tu-sofia.bg");
	public static Pattern passwordChecker = Pattern.compile("[\\w\\d\\W\\D]{5,}");

	public static User createUser(String username, String password, UserType type) {
		switch(type) {
		case ADMIN:
			return new Admin(username, password);
		case TEACHER:
			if(emailChecker.matcher(username).matches() && passwordChecker.matcher(password).matches()) {
				return new Teacher(username, password);
			}
			return null;
		case STUDENT:
			if(facultyNumberChecker.matcher(username).matches() && EGNChecker.matcher(password).matches()) {
				return new Student(username, password);
			}
			return null;
		default:
			return null;
		}
	}
}