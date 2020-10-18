package pe.edu.upc.entity;

import java.util.Date;

public class Cliente {
	private int idCustomer;
	private int idUser;
	private String name;
	private String lastname;
	private int phone;
	private String email;
	private Date Birthdate;
	
	public Cliente() {
		super();
	}
	
	public Cliente(int idCustomer, int idUser, String name, String lastname, int phone, String email, Date Birthdate) {
		super();
		this.idCustomer = idCustomer;
		this.idUser = idUser;
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.Birthdate = Birthdate;
	}
	
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return Birthdate;
	}
	public void setBirthdate(Date birthdate) {
		Birthdate = birthdate;
	}
	
}
