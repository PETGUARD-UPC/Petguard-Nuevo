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
@Table(name = "keepers")
public class Cuidador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCuidador;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false)
	private Usuario usuario;
	
	@Column(name = "nameCuidador", length = 40, nullable = false)
	private String nameCuidador;
	
	@Column(name = "surnameCuidador", length = 40, nullable = false)
	private String surnameCuidador;
	
	@Column(name = "phoneCuidador", length = 10, nullable = false)
	private int phoneCuidador;
	
	@Column(name = "mailCuidador", length = 20, nullable = false)
	private String mailCuidador;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birhtCuidador")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthCuidador;
	
	@Column(name = "scoreCuidador")
	private int scoreCuidador;
	
	@Column(name = "salaryCuidador", nullable = false)
	private int salaryCuidador;
	
	@Column(name = "experienceCuidador", nullable = false)
	private int experienceCuidador;

	public Cuidador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cuidador(int idCuidador, Usuario usuario, String nameCuidador, String surnameCuidador, int phoneCuidador,
			String mailCuidador, Date birthCuidador, int scoreCuidador, int salaryCuidador, int experienceCuidador) {
		super();
		this.idCuidador = idCuidador;
		this.usuario = usuario;
		this.nameCuidador = nameCuidador;
		this.surnameCuidador = surnameCuidador;
		this.phoneCuidador = phoneCuidador;
		this.mailCuidador = mailCuidador;
		this.birthCuidador = birthCuidador;
		this.scoreCuidador = scoreCuidador;
		this.salaryCuidador = salaryCuidador;
		this.experienceCuidador = experienceCuidador;
	}

	public int getIdCuidador() {
		return idCuidador;
	}

	public void setIdCuidador(int idCuidador) {
		this.idCuidador = idCuidador;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNameCuidador() {
		return nameCuidador;
	}

	public void setNameCuidador(String nameCuidador) {
		this.nameCuidador = nameCuidador;
	}

	public String getSurnameCuidador() {
		return surnameCuidador;
	}

	public void setSurnameCuidador(String surnameCuidador) {
		this.surnameCuidador = surnameCuidador;
	}

	public int getPhoneCuidador() {
		return phoneCuidador;
	}

	public void setPhoneCuidador(int phoneCuidador) {
		this.phoneCuidador = phoneCuidador;
	}

	public String getMailCuidador() {
		return mailCuidador;
	}

	public void setMailCuidador(String mailCuidador) {
		this.mailCuidador = mailCuidador;
	}

	public Date getBirthCuidador() {
		return birthCuidador;
	}

	public void setBirthCuidador(Date birthCuidador) {
		this.birthCuidador = birthCuidador;
	}

	public int getScoreCuidador() {
		return scoreCuidador;
	}

	public void setScoreCuidador(int scoreCuidador) {
		this.scoreCuidador = scoreCuidador;
	}

	public int getSalaryCuidador() {
		return salaryCuidador;
	}

	public void setSalaryCuidador(int salaryCuidador) {
		this.salaryCuidador = salaryCuidador;
	}

	public int getExperienceCuidador() {
		return experienceCuidador;
	}

	public void setExperienceCuidador(int experienceCuidador) {
		this.experienceCuidador = experienceCuidador;
	}
	


}
