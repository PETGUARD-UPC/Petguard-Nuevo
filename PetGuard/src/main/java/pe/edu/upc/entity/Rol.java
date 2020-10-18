package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name ="roles")
public class Rol implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    
    @NotEmpty(message = "Debe ingresar el tipo de rol")
    @Column(name = "tipoRol")
    private String tipoRol;
    public Rol() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Rol(int idRol, String tipoRol) {
        super();
        this.idRol = idRol;
        this.tipoRol = tipoRol;
    }
    public int getIdRol() {
        return idRol;
    }
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    public String getTipoRol() {
        return tipoRol;
    }
    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

}