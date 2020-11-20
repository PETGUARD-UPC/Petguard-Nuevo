package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "reservedetails")
public class ReserveDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserveDetails;

	@ManyToOne
	@JoinColumn(name = "idKeeper", nullable = false)
	private Keeper keeper;
	
	@Positive(message = "El monto debe de ser positivo")
	@Min(value = 1, message = "El minimo de horas de reserva es 1")
	@Column(name = "hour")
	private int hour;
	
	public Double calcularSubTotal() {
		return hour * keeper.getSalary();
	}

	public int getIdReserveDetails() {
		return idReserveDetails;
	}

	public void setIdReserveDetails(int idReserveDetails) {
		this.idReserveDetails = idReserveDetails;
	}

	public Keeper getKeeper() {
		return keeper;
	}

	public void setKeeper(Keeper keeper) {
		this.keeper = keeper;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	
}
