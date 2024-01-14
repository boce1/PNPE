import java.io.*;
import java.util.*;
import java.net.*;

public class Server {
	private static Object quizLock;

	public Server() {}

	public void start() {
		try {
			ServerSocket server = new ServerSocket(8080);

			while(true) {
				Thread clientThread = new Thread(() -> {
					try {
						Socket connection = server.accept();
						userMenu(connection);
					}catch(IOException e) {
						e.printStackTrace();
					}
				});
				clientThread.start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void userMenu(Socket connection) {
		try {
			Scanner in = new Scanner(connection.getInputStream());
			PrintStream out = new PrintStream(connection.getOutputStream());

			while(true) {
				String egn = in.nextLine();
				String facultyNumber = in.nextLine();

				Student student = new Student(egn, facultyNumber);
				Quiz quiz = new Quiz(student, 0);

				while(quiz.hasNext()) {
					String studentAnswer = in.nextLine();
					switch(studentAnswer.toUpperCase()) {
					case "A":
						quiz.setAnswer(Question.Answer.A);
						break;
					case "B":
						quiz.setAnswer(Question.Answer.B);
						break;
					case "C":
						quiz.setAnswer(Question.Answer.C);
						break;
					case "D":
						quiz.setAnswer(Question.Answer.D);
						break;
					default:
						quiz.setAnswer(Question.Answer.EMPTY);
					}
				}
				Map<Integer, Question> map = new Map<Integer, Question>();
				for(Question q: quiz.getQuestions()) {
					map.put(q.getId(), q.getAnswer());
				}

				out.println(quiz.isPassed(map));

				synhronized(quizLock) {
					ObjectOutputStream outResults = new ObjectOutputSrteam(new FileOutputStream("results.bin"));
					outResults.writeObject(quiz);
					outResults.close();
				}

			}
			in.close();
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}