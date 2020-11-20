package pe.edu.upc.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reserves")
public class Reserve {
	
//	@NotNull(message = "Se debe elegir un cliente!")
	@ManyToOne
	@JoinColumn(name = "idCustomer", nullable = false)
	private Customer customer;
	
	/*
	 * //@NotNull(message = "Se debe elegir un cuidador!")
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "idKeeper") private Keeper keeper;
	 * 
	 * //@NotNull(message = "Se debe elegir un método de pago!")
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "idPay") private Pay pay;
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserve;
	
	/*
	 * @Size(min = 13, max = 18, message = "Ingresar numero de tarjeta válido")
	 * 
	 * @Column(name = "card") private String card;
	 * 
	 * @Size(min = 10, max = 50, message = "Ingresar dirección válido")
	 * 
	 * @Column(name = "site") private String site;
	 */

	@Column(name = "requestDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate;// cuando se pidio
	
	
	//@NotNull(message = "Ingresar fecha para la reserva!")
	@Future(message = "La fecha debe ser posterior al día actual")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	/*
	 * @Min(value = 1, message = "El minimo de horas de reserva es 1")
	 * 
	 * @Column(name = "hour") private int hour;
	 */

	@Column(name = "price")
	private Integer price;

	@PrePersist
	public void prePersist() {
		this.requestDate = new Date();
	}
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idReserve", nullable = true)
	private List<ReserveDetail> reserveDetails;
	
	
	public Reserve() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Reserve(@NotNull(message = "Se debe elegir un cliente!") Customer customer,
			@NotNull(message = "Se debe elegir un cuidador!") Keeper keeper,
			@NotNull(message = "Se debe elegir un método de pago!") Pay pay, int idReserve,
			@Size(min = 13, max = 18, message = "Ingresar numero de tarjeta válido") String card,
			@Size(min = 10, max = 50, message = "Ingresar dirección válido") String site, Date requestDate,
			@NotNull(message = "Ingresar fecha para la reserva!") @Future(message = "La fecha debe ser posterior al día actual") Date date,
			@Min(value = 1, message = "El minimo de horas de reserva es 1") int hour, Integer price,
			List<ReserveDetail> reserveDetails) {
		super();
		this.customer = customer;
//		this.keeper = keeper;
	//	this.pay = pay;
		this.idReserve = idReserve;
		//this.card = card;
		//this.site = site;
		this.requestDate = requestDate;
		this.date = date;
//		this.hour = hour;
		this.price = price;
		this.reserveDetails = reserveDetails;
	}



	public Double getTotal() {
		return reserveDetails.stream().collect(Collectors.summingDouble(ReserveDetail::calcularSubTotal));
	}
	
	public void addDetailReserve(ReserveDetail item) {
		this.reserveDetails.add(item);
	}

////////////////////

	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	/*
	 * public Keeper getKeeper() { return keeper; }
	 * 
	 * 
	 * 
	 * public void setKeeper(Keeper keeper) { this.keeper = keeper; }
	 * 
	 * 
	 * 
	 * public Pay getPay() { return pay; }
	 * 
	 * 
	 * 
	 * public void setPay(Pay pay) { this.pay = pay; }
	 */


	public int getIdReserve() {
		return idReserve;
	}



	public void setIdReserve(int idReserve) {
		this.idReserve = idReserve;
	}



	/*
	 * public String getCard() { return card; }
	 * 
	 * 
	 * 
	 * public void setCard(String card) { this.card = card; }
	 * 
	 * 
	 * 
	 * public String getSite() { return site; }
	 * 
	 * 
	 * 
	 * public void setSite(String site) { this.site = site; }
	 */



	public Date getRequestDate() {
		return requestDate;
	}



	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}


	/*
	 * public int getHour() { return hour; }
	 * 
	 * 
	 * 
	 * public void setHour(int hour) { this.hour = hour; }
	 */



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public List<ReserveDetail> getReserveDetails() {
		return reserveDetails;
	}



	public void setReserveDetails(List<ReserveDetail> reserveDetails) {
		this.reserveDetails = reserveDetails;
	}
	
	///////////////////////////
	

	
	
}
