package izpit2;

import java.util.*;
import java.io.*;

public class FileUtilities {
	public static String filePath = "regex.bin";

	public static List<Regex> readFile() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
			List<Regex> output = (List<Regex>) in.readObject();
			return output;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeFile(List<Regex> listRegex) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
			out.writeObject(listRegex);
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}