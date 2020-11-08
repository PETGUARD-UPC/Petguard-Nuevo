package pe.edu.upc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	@NotEmpty(message = "Debe ingresar un nombre de usuario")
	@Column(name="username")
	private String username;
	 
	@Size(min=1, message = "Debe ingresar una contraseña")
	@Column(name="password")
	private String password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int idUser, String username, String password) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	


}
