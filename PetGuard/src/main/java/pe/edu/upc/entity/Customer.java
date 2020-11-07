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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customers")
public class Customer {
	
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCustomer;
	 
	@NotEmpty(message = "Ingresa el nombre!")
	@Column(name = "name", length = 45)
	private String name;
	
	//@NotEmpty(message = "Ingresa el apellido!")
	@Size(max=40, min=3, message = "Ingresar apellido")
	@Column(name = "lastname", length = 45)
	private String lastname;
	
	//@NotEmpty(message = "Ingresa tu número de teléfono!")
	@Size(max = 9, min = 9, message = "Número de teléfono debe tener 9 dígitos")
	@Column(name = "phone")
	private String phone;
	
	//@NotEmpty(message = "Ingresa tu email!")
	@Email(message="Ingresar email válido")
	@Pattern(regexp=".+@.+\\..+", message="Ingresar email válido")
	@Column(name = "email", length = 20, nullable = false)
	private String email;
	
	@Past(message = "La fecha debe ser pasada!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birhtdate")
	@Temporal(TemporalType.DATE)
	private Date birthdate;


	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Customer(User user, int idCustomer, String name,
			 String lastname,
			String phone,
			String email,
			Date birthdate) {
		super();
		this.user = user;
		this.idCustomer = idCustomer;
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.birthdate = birthdate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getIdCustomer() {
		return idCustomer;
	}


	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	

	
	
}