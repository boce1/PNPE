package izpit1;

import java.util.*;
import java.util.stream.Collectors;
import java.net.*;
import java.io.*;

public class Server {
	private Object userLock;

	public User login(String username, String password) {
		synchronized(userLock) {
			List<User> userList = UserControl.readUsers();
			for(User user: userList) {
				if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
					return user;
				}
			}
		}
		return null;
	}

	public void register(User user) {
		synchronized(userLock) {
			List<User> users = UserControl.readUsers();
			boolean flag = true;
			for(User u: users) {
				if(u.equals(user)) {
					flag = false;
					break;
				}
			}
			if(flag) {
				users.add(user);
				UserControl.writeUsers(users);
			}

		}
	}

	public void userMenu(Socket connection) {
		try {
			Scanner keyboardIn = new Scanner(System.in);
			Scanner in = new Scanner(connection.getInputStream());
			PrintStream out = new PrintStream(connection.getOutputStream());

			while(true) {
				String username = keyboardIn.nextLine();
				String password = keyboardIn.nextLine();

				User user = login(username, password);
				switch(user.getUserType()) {
				case ADMIN:
					String name = keyboardIn.nextLine();
					String pass = keyboardIn.nextLine();
					String type = keyboardIn.nextLine().toLowerCase();
					
					User userToAdd = null;
					switch(type) {
					case "admin":
						userToAdd = UserFactory.createUser(name, pass, UserType.ADMIN);
						break;
					case "teacher":
						userToAdd = UserFactory.createUser(name, pass, UserType.TEACHER);
						break;
					case "student":
						userToAdd = UserFactory.createUser(name, pass, UserType.STUDENT);
						break;
					}
					
					List<User> users = UserControl.readUsers();
					if(userToAdd != null) {
						users.add(userToAdd);
					}
					
					UserControl.writeUsers(users);
				case TEACHER:
					String facultyNumber = keyboardIn.nextLine();
					String subject = keyboardIn.nextLine();
					int semester = keyboardIn.nextInt();
					int grade = keyboardIn.nextInt();

					Grade g = new Grade(subject, semester, grade);
					List<User> usersList = UserControl.readUsers();
					usersList = usersList.stream().filter((u) -> u.getUserType().equals(UserType.STUDENT))
															.collect(Collectors.toList());
					UserControl.writeUsers(usersList);
					/* 
					for(User u: usersList) {
						if(u instanceof Student && u.getUsername().equals(facultyNumber)) {
							List<Grade> studentsGrades = ((Student) u).getGrades();
							studentsGrades.add(g);
							((Student) u).setGrades(studentsGrades);
							UserControl.writeUsers(usersList);
						}
					}
					*/
				case STUDENT:
					List<Grade> grades = ((Student) user).getGrades();
					grades = grades.stream().sorted(Comparator.comparingInt(Grade::getSemester).thenComparing(Grade::getSubject)).collect(Collectors.toList());
					out.println(grades);
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		ServerSocket server;
		try {
			server = new ServerSocket(8080);
			while(true) {
				Socket connection = server.accept();

				Thread clientThread = new Thread(() -> {
					userMenu(connection);
				});

				clientThread.start();
			} 
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
}