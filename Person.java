import java.io.Serializable;

public class Person implements Serializable {

	private String name;
	private String address;
	
	public Person(String n, String a) {
		name = n;
		address = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
