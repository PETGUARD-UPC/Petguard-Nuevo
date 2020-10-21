package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "pets")
public class Mascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMascota;
	
	@Column(name = "namePet", length=40, nullable = false)
	private String nombreMascota;
	
	@Column(name = "breedPet", length = 30, nullable = false)
	private String razaMascota;
	
	@Column(name = "agePet", length = 2,nullable = false)
	private String edadMascota;
	
	@Min(value = 1, message = "no se permite ingresar valores menores a 1 kg")
	private Double pesoMascota;
	
	@Column(name = "genderPet", length = 10, nullable = false)
	private String sexoMascota;
	
	public Mascota() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mascota(int idMascota, String nombreMascota, String razaMascota, String edadMascota, Double pesoMascota,
			String sexoMascota) {
		super();
		this.idMascota = idMascota;
		this.nombreMascota = nombreMascota;
		this.razaMascota = razaMascota;
		this.edadMascota = edadMascota;
		this.pesoMascota = pesoMascota;
		this.sexoMascota = sexoMascota;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombreMascota() {
		return nombreMascota;
	}

	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}

	public String getRazaMascota() {
		return razaMascota;
	}

	public void setRazaMascota(String razaMascota) {
		this.razaMascota = razaMascota;
	}

	public String getEdadMascota() {
		return edadMascota;
	}

	public void setEdadMascota(String edadMascota) {
		this.edadMascota = edadMascota;
	}

	public Double getPesoMascota() {
		return pesoMascota;
	}

	public void setPesoMascota(Double pesoMascota) {
		this.pesoMascota = pesoMascota;
	}

	public String getSexoMascota() {
		return sexoMascota;
	}

	public void setSexoMascota(String sexoMascota) {
		this.sexoMascota = sexoMascota;
	}
	
	
}
