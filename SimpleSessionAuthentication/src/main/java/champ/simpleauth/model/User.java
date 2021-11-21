package champ.simpleauth.model;

public class User {
	private int id;
	private String email;
	private String fullName;
	
	
	public User(int id, String email, String fullName) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}

	
	
}
