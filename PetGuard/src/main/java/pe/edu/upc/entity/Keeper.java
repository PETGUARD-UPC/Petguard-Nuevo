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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
//import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "keepers")
public class Keeper {

	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idKeeper;

	// @NotEmpty(message = "Ingresa el nombre!")
	@Column(name = "name", nullable = false)
	private String name;

	@Size(max = 40, min = 3, message = "Ingresar apellido!")
	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Size(max = 9, min = 9, message = "Número de teléfono debe tener 9 dígitos")
	@Column(name = "phone")
	private String phone;

	@Size(max = 8, min = 8, message = "DNI debe tener 8 dígitos")
	@Column(name = "dni")
	private String dni;

	@Email(message = "Ingresar email válido")
	@Pattern(regexp = ".+@.+\\..+", message = "Ingresar email válido")
	@Column(name = "email", nullable = false)
	private String email;

	// @NotNull(message = "Ingresar la fecha de nacimiento!")
	@Past(message = "la fecha debe ser pasada")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthdate")
	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Column(name = "score")
	private int score;

	// @NotNull(message = "Ingresar salario")
	@DecimalMin(value = "50.00", message = "El salario mínimo es 50 nuevos soles")
	@Column(name = "salary")
	private Double salary;

	// @Min(value = 1, message = "La experiencia debe ser mayor a 1 mes")
	@Column(name = "experience")
	private int experience;

	public Keeper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Keeper(User user, int idKeeper, String name, String lastname, String phone, String dni, String email,
			Date birthdate, int score, Double salary, int experience) {
		super();
		this.user = user;
		this.idKeeper = idKeeper;
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.dni = dni;
		this.email = email;
		this.birthdate = birthdate;
		this.score = score;
		this.salary = salary;
		this.experience = experience;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIdKeeper() {
		return idKeeper;
	}

	public void setIdKeeper(int idKeeper) {
		this.idKeeper = idKeeper;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
