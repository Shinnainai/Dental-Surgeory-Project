
public class Dentist extends Person {

	private String password;
	
	public Dentist(String n, String a, String p) {
		super(n, a);
		password = p;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
