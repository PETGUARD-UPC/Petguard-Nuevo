package pe.edu.upc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customers")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCustomer;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false)
	private int idUser;
	
	@Column(name = "name", length = 45, nullable = false)
	private String name;
	
	@Column(name = "lastname", length = 45, nullable = false)
	private String lastname;
	
	@Column(name = "phone", length = 9, nullable = false)
	private int phone;
	
	@Column(name = "email", length = 45, nullable = false)
	private String email;
	
	@DateTimeFormat
	@Column(name = "birhtdate")
	@Temporal(TemporalType.TIMESTAMP)
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