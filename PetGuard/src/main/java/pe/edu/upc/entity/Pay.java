package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pays")
public class Pay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPay;
	
	@NotEmpty(message = "Elegir tipo de tarjeta")
	@Column(name = "name")
	private String name;

	//@NotEmpty(message = "Elegir entidad financiera")
	@ManyToOne
	@JoinColumn(name = "idBanking")
	private Banking banking;

	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pay(int idPay, String name, Banking banking) {
		super();
		this.idPay = idPay;
		this.name = name;
		this.banking = banking;
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

	public Banking getBanking() {
		return banking;
	}

	public void setBanking(Banking banking) {
		this.banking = banking;
	}

	
	
	
}
