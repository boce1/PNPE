package izpit1;

public class Teacher extends User {
	public Teacher(String username, String password) {
		super(username, password);
	}

	public UserType getUserType() {
		return UserType.TEACHER;
	}
}