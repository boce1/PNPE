import java.io.*;
import java.util.*;

public class Question implements Serializable{
	public static enum Answer {A, B, C, D, EMPTY}
	
	private int id;
	private String questionText;
	private Answer answer;

	public Quistion(int id, String questionText, Answer answer) {
		setId(id);
		setQuestionText(questionText);
		setAnswer(answer);
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionText() {
		return this.questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Answer getAnswer() {
		return this.answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || o.getClass() != this.getClass()) {
			return false;
		}
		if(this.getId() == o.getId() && this.getQuestionText().equals(o.getQuestionText()) 
			&& o.getAnswer().equals(this.getAnswer())) {
			return true;
		}
	}
}