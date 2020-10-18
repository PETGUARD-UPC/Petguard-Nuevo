package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Rol;

public interface IRolService {

	public void insertar(Rol rol);
	
	List<Rol>list();
	
	public void delete(int idRol);
}
