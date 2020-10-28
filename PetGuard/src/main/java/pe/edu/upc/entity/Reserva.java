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
	
	@ManyToOne
	@JoinColumn(name = "idPay", nullable = false)
	private MedioPago typePay;
	
	@Column(name = "numTarjeta", length = 45, nullable = false)
	private String numTarjeta;
	
	@Column(name = "direction", length = 45, nullable = false)
	private String direction;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "starDate")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@DateTimeFormat
	@Column(name = "hora")
	@Temporal(TemporalType.TIME)
	private Date hour;
	
	@Column(name = "price", nullable = false)
	private Double price;

	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserva(int idReserva, Cliente cliente, Cuidador cuidador, MedioPago typePay, String numTarjeta,
			String direction, Date date, Date hour, Double price) {
		super();
		this.idReserva = idReserva;
		this.cliente = cliente;
		this.cuidador = cuidador;
		this.typePay = typePay;
		this.numTarjeta = numTarjeta;
		this.direction = direction;
		this.date = date;
		this.hour = hour;
		this.price = price;
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

	public MedioPago getTypePay() {
		return typePay;
	}

	public void setTypePay(MedioPago typePay) {
		this.typePay = typePay;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	
}
