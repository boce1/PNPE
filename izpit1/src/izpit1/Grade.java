package izpit1;

public class Grade {
	private String subject;
	private int semester;
	private int grade;

	public Grade(String subject, int semester, int grade) {
		this.setSubject(subject);
		this.setSemester(semester);
		this.setGrade(grade);
	}

	public String getSubject() {
		return this.subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getSemester() {
		return this.semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getGrade() {
		return this.grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}