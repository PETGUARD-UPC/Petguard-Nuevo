package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "typePay")
public class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPay;
	
	@NotEmpty(message = "Debe ingresar el nombre del tipo de pago")
	@Column(name = "typePay", nullable = false)
	private String typePay;

	public MedioPago() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedioPago(int idPay, String typePay) {
		super();
		this.idPay = idPay;
		this.typePay = typePay;
	}

	public int getIdPay() {
		return idPay;
	}

	public void setIdPay(int idPay) {
		this.idPay = idPay;
	}

	public String getTypePay() {
		return typePay;
	}

	public void setTypePay(String typePay) {
		this.typePay = typePay;
	}
	
	

}
