package izpit1;

import java.util.*;

public class Student extends User {
	private List<Grade> grades;

	public Student(String username, String password) {
		super(username, password);
	}

	public UserType getUserType() {
		return UserType.STUDENT;
	}

	public List<Grade> getGrades() {
		return this.grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

}