package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pets")
public class Pet {

	@NotNull(message = "Se debe elegir dueño de la mascota")
	@ManyToOne
	@JoinColumn(name = "idCustomer", nullable = false)
	private Customer customer;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPet;
	
	@NotEmpty(message = "Ingresa el nombre!")
	@Column(name = "name", length=40, nullable = false)
	private String name;
	
	@NotNull(message = "Se debe elegir raza de mascota!")
	@Column(name = "breed", length = 30, nullable = false)
	private String breed;
	
	@NotNull(message = "Ingresar edad de mascota")
	@Max(value = 20, message = "Edad máxima es 20 años")
	@Min(value = 1, message = "Edad minima es 1 año")
	@Column(name = "years")
	private int years;
	
	@NotNull(message = "Ingresar peso de mascota")
	@DecimalMax(value = "20.00", message = "El peso máximo es 20 kg")
	@DecimalMin(value = "1.00", message = "El peso mínimo es 1 kg")
	@Column(name = "weight")
	private Double weight;
	
	@NotNull(message = "Se debe elegir género de mascota!")
	@Column(name = "gender")
	private String gender;

	public Pet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pet(Customer customer, int idPet, String name, String breed, int years,
			Double weight, String gender) {
		super();
		this.customer = customer;
		this.idPet = idPet;
		this.name = name;
		this.breed = breed;
		this.years = years;
		this.weight = weight;
		this.gender = gender;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getIdPet() {
		return idPet;
	}

	public void setIdPet(int idPet) {
		this.idPet = idPet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	
	
}
