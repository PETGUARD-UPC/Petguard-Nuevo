package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "bankingentities")
public class Banking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBanking;
	
	@NotEmpty(message = "Ingresa el nombre de la entidad bancaria!")
	@Column(name = "name")
	private String name;

	
	public Banking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Banking(int idBanking, String name) {
		super();
		this.idBanking = idBanking;
		this.name = name;
	}

	public int getIdBanking() {
		return idBanking;
	}

	public void setIdBanking(int idBanking) {
		this.idBanking = idBanking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
