package dk.stoberiet.Models;

/**
 * Created by Olivi on 18-11-2016.
 */
public class CredentialModel {
	private String username;
	private String password;
	private Integer role;

	public CredentialModel() {
	}

	public CredentialModel(String username, String password, Integer role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "CredModel{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
