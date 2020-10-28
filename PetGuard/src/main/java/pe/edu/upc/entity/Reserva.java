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
@Table(name="reservations")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserva;
	
	@ManyToOne
	@JoinColumn(name = "idCustomer", nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "idCuidador", nullable = false)
	private Cuidador cuidador;
	
	@Column(name = "direction", length = 45, nullable = false)
	private String direction;
	
	@Column(name = "price", length = 9, nullable = false)
	private int price;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "starDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date starDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "finishDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;

	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserva(int idReserva, Cliente cliente, Cuidador cuidador, String direction, int price, Date starDate,
			Date finishDate) {
		super();
		this.idReserva = idReserva;
		this.cliente = cliente;
		this.cuidador = cuidador;
		this.direction = direction;
		this.price = price;
		this.starDate = starDate;
		this.finishDate = finishDate;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cuidador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getStarDate() {
		return starDate;
	}

	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	
}
