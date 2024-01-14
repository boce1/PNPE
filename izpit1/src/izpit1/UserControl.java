package izpit1;

import java.io.*;
import java.util.*;

public class UserControl {
	private static String filePath = "user.bin";

	public static void writeUsers(List<User> list) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
			out.writeObject(list);
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static List<User> readUsers() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
			List<User> users = (List<User>) in.readObject();
			in.close();
			return users;
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace(); 
		}
		return null;
	}

}