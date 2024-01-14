package izpit2;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.io.*;
import java.net.*;

public class Server {
	private static Object regexLock;
	
	public Server() {}

	public void addRegex(Regex regex) {
		synchronized(regexLock) {
			List<Regex> listRegex = (List<Regex>) FileUtilities.readFile();

			boolean isAlreadyThere = false;
			for(Regex r: listRegex) {
				if(r.equals(regex)) {
					isAlreadyThere = true;
					break;
				}
			}

			if(!isAlreadyThere) {
				listRegex.add(regex);
				FileUtilities.writeFile(listRegex);
			}
		}
	}

	public List<Regex> filterRegex(String description) {
		List<Regex> output = new ArrayList<Regex>();
		synchronized(regexLock) {
			List<Regex> listRegex = (List<Regex>) FileUtilities.readFile();

			output = listRegex.stream()
						.filter((regex) -> regex.getDescription().contains(description))
						.sorted(Comparator.comparingInt(Regex::getRating))
						.collect(Collectors.toList());
			Collections.reverse(output);

		}
		return output;
	}

	public void userMenu(Socket connection) {
		try {
			Scanner in = new Scanner(connection.getInputStream());
			PrintWriter out = new PrintWriter(connection.getOutputStream());

			while(true) {
				String choice = in.nextLine().toLowerCase(); // create or search
				switch(choice) {
				case "create":
					List<String> userList = new ArrayList<String>();

					String pattern = in.nextLine();
					String description = in.nextLine();

					Regex userRegex = new Regex(Pattern.compile(pattern), description);

					String userStr = "";
					while(userStr.equals("stop")) {
						userStr = in.nextLine();
						userList.add(userStr);
					}

					List<Boolean> testList = RegexTester.test(userRegex, userList.toArray());
					out.println(testList);

					String choiceSave = in.nextLine();
					if(choiceSave.equalsIgnoreCase("yes")) {
						addRegex(userRegex);
					}
				case "search":
					String descriptionFilter = in.nextLine();
					List<Regex> filteredRegex = filterRegex(descriptionFilter);

					int indexRegex = in.nextInt();
					Regex ChosenRegex = filteredRegex.get(indexRegex);

					List<String> userFilteredStrings = new ArrayList<String>();
					userStr = "";
					while(!userStr.equals("stop")) {
						userStr = in.nextLine();
						userFilteredStrings.add(userStr);
					}
					
					List<Boolean> testListFiltered = RegexTester.test(ChosenRegex, userFilteredStrings.toArray());
					out.println(testListFiltered);

					String ratingChoice = in.nextLine();
					if(ratingChoice.equalsIgnoreCase("yes")) {
						int ratingChange = 0;
						while(ratingChange != 1 || ratingChange != -1) {
							ratingChange = in.nextInt();
							synchronized(regexLock) {
								List<Regex> allRegex = FileUtilities.readFile();
								for(Regex r: allRegex) {
									if(r.equals(ChosenRegex)) {
										r.setRating(r.getRating() + ratingChange);
										break;
									}
								}
								FileUtilities.writeFile(allRegex);
							}
						}

					}
					
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	public void start() {
		try {
			ServerSocket server = new ServerSocket(8080);

			Thread clientThread = new Thread(() -> {
				try {
					Socket connection = server.accept();
					userMenu(connection);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
			
			clientThread.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
