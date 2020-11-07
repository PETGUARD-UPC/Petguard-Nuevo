package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "pays")
public class Pay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPay;
	
	@NotNull(message = "Elegir tipo de tarjeta")
	@Column(name = "name")
	private String name;

	@NotNull(message = "Elegir entidad financiera")
	@Column(name = "entity")
	private String entity;

	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pay(int idPay, String name, String entity) {
		super();
		this.idPay = idPay;
		this.name = name;
		this.entity = entity;
	}

	public int getIdPay() {
		return idPay;
	}

	public void setIdPay(int idPay) {
		this.idPay = idPay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	
	
}
