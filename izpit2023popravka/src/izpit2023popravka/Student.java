import java.util.regex.Pattern;

public class Student {
	private String EGN;
	private String facultyNumber;

	public static Pattern EGNChecker = Pattern.compile("\\d{10}");
	public static Pattern facultyNumberChecker = Pattern.compile("\\d{9}");

	public Student(String egn, String facultyNumber) throws StudentException{
		if(!EGNChecker.matcher(egn).matches()) {
			throw new StudentException("invalid faculty number");
		}
		setEGN(egn);
		if(!facultyNumberChecker.matcher(facultyNumber).matches()) {
			throw new StudentException("invalid faculty number");
		}
		setFacultyNumber(facultyNumber);
	}

	public String getEGN() {
		return this.EGN;
	}
	public void setEGN(String egn) {
		this.EGN = egn;
	}

	public String getFacultyNumber() {
		return this.facultyNumber;
	}
	public void setFacultyNumber(String facultyNumber) {
		this.facultyNumber = facultyNumber;
	}
}