package Models;

import java.util.Set;

public class User {
	
	private long id;
	
	private String name;
	
	private String username;
	
	private int age;
	
	private String password;
	
	private String phone;
	
	private String address;
	
	private double soldetelephonique;
	
	private Set<String> role;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String username, int age, String password, String phone, String address,
			double soldetelephonique) {
		super();
		this.name = name;
		this.username = username;
		this.age = age;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.soldetelephonique = soldetelephonique;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSoldetelephonique() {
		return soldetelephonique;
	}

	public void setSoldetelephonique(double soldetelephonique) {
		this.soldetelephonique = soldetelephonique;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
	
	
	
}
