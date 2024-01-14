package izpit1;

import java.io.*;

public abstract class User implements Serializable {
	private String username;
	private String password;

	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public abstract UserType getUserType();
}