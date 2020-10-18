package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
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