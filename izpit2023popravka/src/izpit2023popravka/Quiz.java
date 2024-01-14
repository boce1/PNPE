import java.util.*;
import java.io.*;

public class Quiz {
	private Student student;
	private List<Quistion> quistions;
	private int currentQuestionIndex;

	public Quiz(Student student, int currentQuestionIndex) {
		setStudent(student);
		setQuestions(loadQuestions());
		setCurrentQuestionIndex(currentQuestionIndex);
	}

	public List<Question> loadQuestions() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("quistions.bin"));
			List<Question> output = (List<Question>) in.readObject();
			Collections.shuffle(output);
			in.close();
			return output;
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public bollean hasNext() {
		return getCurrentQuestionIndex() < getQuestions.size();
	}
	public String getCurrentQuestion() {
		return getQuestions().get(getCurrentQuestionIndex());
	}
	public void setAnswer(Questions.Answer answer) {
		getQuestions().get(getCurrentQuestionIndex()).setAnswer(answer);
		setCurrentQuestionIndex(getCurrentQuestionIndex() + 1);
	}

	public boolean isPassed(Map<Integer, Question.Answer>) {
		Map<Integer, Question.Answer> answers = null;
		try {
			ObjectInputStream in = new ObjectINputStream(new FileInputStream("answers.bin"));
			Map<Integer, Question.Answer> answers = (Map<Integer, Question.Answer>) in.readObject();
			in.close()
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		int count = 0;
		for(Question q: getQuestions()) {
			if(q.getAnswer().equals(answers.get(q.getId()))) {
				count++;
			}
		}

		return count >= answers.size() / 2 + 1;
	}

}